package api.openevent.codegen

import api.openevent.annotations.ReadOnly
import api.openevent.annotations.contraints.Required
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element

internal class MutableClassGenerator(
        element: Element,
        processingEnv: ProcessingEnvironment) :
        DataClassGenerator(element, processingEnv, true) {

    override val fileName: String
        get() = "Mutable${name.removeSuffix("Schema")}"

    override fun filterProperties(properties: List<Element>): List<Element> {
        return properties.filter {
            it.getAnnotation(ReadOnly::class.java) == null
        }.sortedByDescending {
            it.getAnnotation(Required::class.java) != null
        }
    }

    override fun getPropertyGenerator(element: Element): PropertyGenerator {
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