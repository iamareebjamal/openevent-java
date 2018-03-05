package api.openevent.codegen

import api.openevent.annotations.Schema
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = ["api.openevent.annotations.Schema"])
class Generator : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>, roundEnv: RoundEnvironment): Boolean {
        var validated = true

        roundEnv.getElementsAnnotatedWith(Schema::class.java).forEach {
            validated = validateSchema(it)
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
