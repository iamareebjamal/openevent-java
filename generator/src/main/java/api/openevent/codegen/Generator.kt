package api.openevent.codegen

import api.openevent.annotations.Schema
import api.openevent.codegen.schema.SchemaGenerator
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = ["api.openevent.annotations.Schema"])
class Generator : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        var validated = true

        roundEnv.getElementsAnnotatedWith(Schema::class.java).forEach {
            val roundValidation = validateSchema(it)
            if (validated) validated = roundValidation
        }

        if (!validated) {
            processingEnv.messager.printMessage(Diagnostic.Kind.ERROR, "Schema Validation Failed")
        }

        return validated
    }

    private fun validateSchema(element: Element): Boolean {
        val schemaGenerator = SchemaGenerator(element, processingEnv)
        val validated = schemaGenerator.isValid()

        if (validated) {
            schemaGenerator.generate()
        }

        return validated
    }


}
