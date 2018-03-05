package api.openevent.codegen

import api.openevent.annotations.Schema
import api.openevent.annotations.contraints.Required
import com.github.jasminb.jsonapi.annotations.Id
import com.squareup.kotlinpoet.*
import org.jetbrains.annotations.Nullable
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.AnnotationMirror
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind

internal abstract class DataClassGenerator(private val element: Element, private val processingEnv: ProcessingEnvironment, private val mutable: Boolean = false) {

    val name: String by lazy {
        element.simpleName.toString()
    }

    abstract val fileName: String

    private val classAnnotations: List<AnnotationMirror> by lazy {
        element.annotationMirrors.filter {
            it.annotationType.toString() != "kotlin.Metadata"
        }.filter {
            it.annotationType.toString() != Schema::class.java.name
        }
    }

    private val properties: List<Element> by lazy {
        element.enclosedElements.filter {
            it.kind == ElementKind.FIELD
        }
    }

    val generatedProperties: List<Element> by lazy {
        filterProperties(properties)
    }

    abstract fun filterProperties(properties: List<Element>): List<Element>

    fun generateClass(): String {
        val typeSpec = TypeSpec.classBuilder(fileName)

        typeSpec.addAnnotations(classAnnotations.map {
            AnnotationSpec.Companion.get(it)
        })

        val constructor = FunSpec.constructorBuilder()

        generatedProperties.map {
            createParamAndProperty(it)
        }.forEach {
            val (parameterSpec, propertySpec) = it
            constructor.addParameter(parameterSpec.build())
            typeSpec.addProperty(propertySpec.build())
        }

        return FileSpec.builder(processingEnv.elementUtils.getPackageOf(element).toString(), fileName)
                .addType(typeSpec
                        .addModifiers(KModifier.DATA)
                        .primaryConstructor(constructor.build())
                        .build())
                .build()
                .toString()
    }

    abstract inner class PropertyGenerator(private val element: Element) {

        private val elementName = element.simpleName.toString()

        val idField = element.getAnnotation(Id::class.java) != null
        val requiredField = element.getAnnotation(Required::class.java) != null

        abstract val isMutable: Boolean
        abstract val isNullable: Boolean
        abstract val hasDefaultValue: Boolean

        fun generate(): Pair<ParameterSpec.Builder, PropertySpec.Builder> {
            val type = element.asType()
                    .asTypeName()
                    .run { if (isNullable) this.asNullable() else this }

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

    abstract fun getPropertyGenerator(element: Element): PropertyGenerator

    private fun createParamAndProperty(element: Element): Pair<ParameterSpec.Builder, PropertySpec.Builder> {
        /*val elementName = element.simpleName.toString()

        val idField = element.getAnnotation(Id::class.java) != null
        val requiredField = element.getAnnotation(Required::class.java) != null

        val type = element.asType()
                .asTypeName()
                .run { if (requiredField || (!mutable && idField)) this else this.asNullable() }

        val annotations = element.annotationMirrors.filter {
            it.annotationType.toString() != Nullable::class.java.name
        }.filter {
            it.annotationType.toString() != Required::class.java.name
        }.map {
            AnnotationSpec.Companion.get(it)
        }

        val parameterSpec = ParameterSpec.builder(elementName, type)
                .run { if (requiredField || !mutable) this else this.defaultValue("null") }

        val propertySpec = PropertySpec.builder(elementName, type)
                .initializer(elementName)
                .mutable(mutable && !idField)
                .addAnnotations(annotations)

        return Pair(parameterSpec, propertySpec)*/

        return getPropertyGenerator(element).generate()
    }
}