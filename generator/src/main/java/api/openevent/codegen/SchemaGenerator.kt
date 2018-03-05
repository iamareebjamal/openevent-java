package api.openevent.codegen

import com.github.jasminb.jsonapi.annotations.Type
import com.squareup.kotlinpoet.*
import org.jetbrains.annotations.Nullable
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.AnnotationMirror
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.tools.Diagnostic

internal class SchemaGenerator(private val element: Element, private val processingEnv: ProcessingEnvironment) {

    private val validated: Boolean

    init {
        var validated = true

        printNote("Validating ${element.simpleName}")

        if (element.kind != ElementKind.CLASS) {
            printWarning("Element $element is not a class")
            validated = false
        }

        if (element.getAnnotation(Type::class.java) == null) {
            printWarning("Element $element should have @${Type::class.java.name} annotation")
            validated = false
        }

        this.validated = validated
    }

    fun isValid() = validated

    private fun printNote(string: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, string)
    }

    private fun printWarning(string: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, string)
    }

    private fun printError(string: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, string)
    }

    fun generate() {
        val mutableClassGenerator = MutableClassGenerator(element, processingEnv)
        printNote("Generating Immutable Class for ${mutableClassGenerator.name} : ${mutableClassGenerator.fileName}")
        printNote(mutableClassGenerator.generateClass())

        val properties = mutableClassGenerator.generatedProperties

        printNote("$properties")

        val immutableClassGenerator = ImmutableClassGenerator(element, processingEnv)
        printNote("Generating Immutable Class for ${immutableClassGenerator.name} : ${immutableClassGenerator.fileName}")
        printNote(immutableClassGenerator.generateClass())
    }

}