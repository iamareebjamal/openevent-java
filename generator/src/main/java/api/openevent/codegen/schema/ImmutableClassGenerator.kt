package api.openevent.codegen.schema

import api.openevent.annotations.WriteOnly
import com.squareup.kotlinpoet.*
import javax.annotation.processing.ProcessingEnvironment
import javax.lang.model.element.Element
import javax.lang.model.element.VariableElement
import javax.tools.Diagnostic

internal class ImmutableClassGenerator(
        element: Element,
        private val processingEnv: ProcessingEnvironment,
        private val mutableProperties: List<VariableElement>) :
        DataClassGenerator(element, processingEnv) {

    override val fileName: String
        get() = name.removeSuffix("Schema")

    override fun filterProperties(properties: List<VariableElement>): List<VariableElement> {
        return properties.filter {
            it.getAnnotation(WriteOnly::class.java) == null
        }
    }

    private val mutableClassName by lazy {
        "Mutable$fileName"
    }

    override fun addExtra(specBuilder: TypeSpec.Builder) {
        specBuilder.addFunction(FunSpec.builder("edit")
                .returns(ClassName(packageName, mutableClassName))
                .addCode(getFunctionBody())
                .build())
    }

    private fun getFunctionBody(): String {
        val propertiesBody = StringBuilder()

        for (property in mutableProperties) {
            val localProperty = if (property.getAnnotation(WriteOnly::class.java) != null)
                "null"
            else
                property.toString()
            propertiesBody.append("\t$property = $localProperty\n")
        }

        return "return $mutableClassName(\n" +
                propertiesBody +
                ")\n"
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