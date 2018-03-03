package api.openevent.annotations

/**
 * Annotation for fields which are only dumped from the API and cannot be set
 * Correct equivalent would be properties with only getters but that would require
 * the fields to be constructor arguments whereas we want the data objects to be
 * completely mutable
 */
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class ReadOnly