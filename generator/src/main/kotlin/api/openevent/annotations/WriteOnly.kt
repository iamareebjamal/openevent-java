package api.openevent.annotations

/**
 * Annotation for fields which are only read by the API and won't be present
 * when the object is retrieved. E.g. Password field in the [api.openevent.user.User] object
 */
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class WriteOnly