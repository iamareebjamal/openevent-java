package api.openevent.codegen.schema

import api.openevent.annotations.Schema
import api.openevent.annotations.contraints.Required
import com.github.jasminb.jsonapi.annotations.Id
import com.squareup.kotlinpoet.*
import org.jetbrains.annotations.Nullable
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.AnnotationMirror
import javax.lang.model.element.Element
import javax.lang.model.element.VariableElement
import javax.lang.model.util.ElementFilter
import kotlin.reflect.jvm.internal.impl.builtins.jvm.JavaToKotlinClassMap
import kotlin.reflect.jvm.internal.impl.name.FqName
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy

internal abstract class DataClassGenerator(
        private val element: Element,
        private val processingEnv: ProcessingEnvironment
) {

    val name: String by lazy {
        element.simpleName.toString()
    }

    val packageName by lazy {
        processingEnv.elementUtils.getPackageOf(element).toString()
    }

    abstract val fileName: String

    private val classAnnotations: List<AnnotationMirror> by lazy {
        element.annotationMirrors.filter {
            it.annotationType.toString() != "kotlin.Metadata"
        }.filter {
            it.annotationType.toString() != Schema::class.java.name
        }
    }

    private val properties: List<VariableElement> by lazy {
        ElementFilter.fieldsIn(element.enclosedElements)
    }

    val generatedProperties: List<VariableElement> by lazy {
        filterProperties(properties)
    }

    abstract fun filterProperties(properties: List<VariableElement>): List<VariableElement>

    fun generateClass(): FileSpec {
        val typeSpec = TypeSpec.classBuilder(fileName)

        typeSpec.addAnnotations(classAnnotations.map {
            AnnotationSpec.get(it)
        })

        val constructor = FunSpec.constructorBuilder()

        generatedProperties.map {
            createParamAndProperty(it)
        }.forEach {
            val (parameterSpec, propertySpec) = it
            constructor.addParameter(parameterSpec.build())
            typeSpec.addProperty(propertySpec.build())
        }

        val typeSpecBuilder = typeSpec
                .addModifiers(KModifier.DATA)
                .primaryConstructor(constructor.build())
        addExtra(typeSpecBuilder)
        return FileSpec.builder(packageName, fileName)
                .addType(typeSpecBuilder.build())
                .build()
    }

    protected open fun addExtra(specBuilder: TypeSpec.Builder) {
        // Nothing
    }

    abstract inner class PropertyGenerator(private val element: VariableElement) {

        private val elementName = element.simpleName.toString()

        val idField: Boolean by lazy {
            element.getAnnotation(Id::class.java) != null
        }
        val requiredField: Boolean by lazy {
            element.getAnnotation(Required::class.java) != null
        }

        abstract val isMutable: Boolean
        abstract val isNullable: Boolean
        abstract val hasDefaultValue: Boolean

        fun Element.javaToKotlinType(): TypeName =
                asType().asTypeName().javaToKotlinType()

        private fun TypeName.javaToKotlinType(): TypeName {
            return if (this is ParameterizedTypeName) {
                (rawType.javaToKotlinType() as ClassName)
                        .parameterizedBy(*typeArguments.map { it.javaToKotlinType() }.toTypedArray())
            } else {
                val className = JavaToKotlinClassMap.INSTANCE.mapJavaToKotlin(FqName(toString()))
                                ?.asSingleFqName()?.asString()

                return if (className == null) {
                    this
                } else {
                    ClassName.bestGuess(className)
                }
            }
        }

        fun generate(): Pair<ParameterSpec.Builder, PropertySpec.Builder> {
            val type = element.asType()
                    .asTypeName()
                    .javaToKotlinType()
                    .run { if (this@PropertyGenerator.isNullable) this.copy(nullable = true) else this }

            val annotations = element.annotationMirrors.filter {
                it.annotationType.toString() != Nullable::class.java.name
            }.filter {
                it.annotationType.toString() != Required::class.java.name
            }.map {
                AnnotationSpec.Companion.get(it)
            }

            val parameterSpec = ParameterSpec.builder(elementName, type)
                    .run { if (hasDefaultValue) this.defaultValue("null") else this }

            val propertySpec = PropertySpec.builder(elementName, type)
                    .initializer(elementName)
                    .mutable(isMutable)
                    .addAnnotations(annotations)

            return Pair(parameterSpec, propertySpec)
        }

    }

    abstract fun getPropertyGenerator(element: VariableElement): PropertyGenerator

    private fun createParamAndProperty(element: VariableElement): Pair<ParameterSpec.Builder, PropertySpec.Builder> {
        return getPropertyGenerator(element).generate()
    }
}