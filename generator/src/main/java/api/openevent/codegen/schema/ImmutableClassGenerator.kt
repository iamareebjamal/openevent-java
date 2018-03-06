package api.openevent.codegen.schema

import api.openevent.annotations.WriteOnly
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.VariableElement

internal class ImmutableClassGenerator(
        element: Element,
        processingEnv: ProcessingEnvironment) :
        DataClassGenerator(element, processingEnv) {

    override val fileName: String
        get() = name.removeSuffix("Schema")

    override fun filterProperties(properties: List<VariableElement>): List<VariableElement> {
        return properties.filter {
            it.getAnnotation(WriteOnly::class.java) == null
        }
    }

    override fun getPropertyGenerator(element: VariableElement): PropertyGenerator {
        return object : PropertyGenerator(element) {
            override val isMutable: Boolean
                get() = false
            override val isNullable: Boolean
                get() = !(requiredField || idField)
            override val hasDefaultValue: Boolean
                get() = false
        }
    }


}