package api.openevent.codegen.schema

import api.openevent.annotations.ReadOnly
import api.openevent.annotations.contraints.Required
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.VariableElement

internal class MutableClassGenerator(
        element: Element,
        processingEnv: ProcessingEnvironment) :
        DataClassGenerator(element, processingEnv) {

    override val fileName: String
        get() = "Mutable${name.removeSuffix("Schema")}"

    override fun filterProperties(properties: List<VariableElement>): List<VariableElement> {
        return properties.filter {
            it.getAnnotation(ReadOnly::class.java) == null
        }.sortedByDescending {
            it.getAnnotation(Required::class.java) != null
        }
    }

    override fun getPropertyGenerator(element: VariableElement): PropertyGenerator {
        return object : PropertyGenerator(element) {
            override val isMutable: Boolean
                get() = !idField
            override val isNullable: Boolean
                get() = !requiredField
            override val hasDefaultValue: Boolean
                get() = !requiredField
        }
    }

}