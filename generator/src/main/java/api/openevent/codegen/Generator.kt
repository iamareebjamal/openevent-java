package api.openevent.codegen

import api.openevent.annotations.ReadOnly
import api.openevent.annotations.Schema
import com.squareup.kotlinpoet.*
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = ["api.openevent.annotations.Schema"])
class Generator : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "Processing $annotations $roundEnv")

        roundEnv.getElementsAnnotatedWith(Schema::class.java).forEach {
            processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, "${it.simpleName} is cool")
        }

        test()

        return true
    }

    private fun test() {
        processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, FileSpec.builder("", "Foo")
                .addType(TypeSpec.classBuilder("User")
                        .addModifiers(KModifier.DATA)
                        .primaryConstructor(FunSpec.constructorBuilder()
                                .addParameter("id", Int::class)
                                .addParameter("name", String::class.asTypeName().asNullable())
                                .build())
                        .addProperty(PropertySpec.builder("id", Int::class)
                                .initializer("id")
                                .build())
                        .addProperty(PropertySpec.builder("name", String::class.asTypeName().asNullable())
                                .initializer("name")
                                .mutable(true)
                                .build())
                        .addFunction(FunSpec.builder("edit")
                                .returns(Generator::class)
                                .addCode(CodeBlock.of("return poop"))
                                .build())
                        .build()
                )
                .build()
                .toString())
    }

}
