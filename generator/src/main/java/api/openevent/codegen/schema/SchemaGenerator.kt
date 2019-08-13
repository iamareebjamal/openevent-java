package api.openevent.codegen.schema

import com.github.jasminb.jsonapi.annotations.Type
import jdk.nashorn.internal.objects.NativeArray.forEach
import java.io.File
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.ExecutableElement
import javax.lang.model.element.VariableElement
import javax.lang.model.util.ElementFilter
import javax.tools.Diagnostic

internal class SchemaGenerator(private val element: Element, private val processingEnv: ProcessingEnvironment) {

    private val validated: Boolean

    init {
        var validated = true

        printNote("Validating ${element.simpleName}")

        val constructor = ElementFilter.constructorsIn(element.enclosedElements).find {
            it.parameters.isEmpty()
        }

        if (constructor == null) {
            printWarning("Class $element should have a visible default constructor")
            validated = false
        }

        if (element.getAnnotation(Type::class.java) == null) {
            printWarning("Class $element should have @${Type::class.java.name} annotation")
            validated = false
        }

        ElementFilter.fieldsIn(element.enclosedElements).forEach {
            printNote("$it ${it.kind} ${it.constantValue}")
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

        val dir = processingEnv.options["kapt.kotlin.generated"]

        mutableClassGenerator.generateClass().writeTo(File(dir))

        val properties = mutableClassGenerator.generatedProperties

        printNote("$properties")

        val immutableClassGenerator = ImmutableClassGenerator(element, processingEnv, properties)
        printNote("Generating Immutable Class for ${immutableClassGenerator.name} : ${immutableClassGenerator.fileName}")

        immutableClassGenerator.generateClass().writeTo(File(dir))
    }

}
