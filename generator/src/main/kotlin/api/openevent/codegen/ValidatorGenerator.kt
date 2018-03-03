package api.openevent.codegen

import api.openevent.annotations.ReadOnly
import api.openevent.annotations.WriteOnly
import javax.annotation.processing.*
import javax.lang.model.SourceVersion
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic

@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes(value = [ReadOnly::class.java.name, WriteOnly::class.java.name])
class ValidatorGenerator : AbstractProcessor() {

    override fun process(annotations: MutableSet<out TypeElement>?, roundEnv: RoundEnvironment?): Boolean {
        processingEnv?.messager?.printMessage(Diagnostic.Kind.WARNING, "Processing $annotations $roundEnv")
        roundEnv?.getElementsAnnotatedWith(ReadOnly::class.java)?.forEach {
            processingEnv.messager.printMessage(Diagnostic.Kind.WARNING, "${it.simpleName} is interesting.")
        }

        return true
    }

}
