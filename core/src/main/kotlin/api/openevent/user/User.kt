package api.openevent.user

import api.openevent.annotations.ReadOnly
import api.openevent.annotations.WriteOnly
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type

@Type("user")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
data class User(
        @ReadOnly
        @Id(IntegerIdHandler::class)
        var id: Int? = null,
        var email: String? = null,
        @WriteOnly var password: String? = null,
        var firstName: String? = null,
        var lastName: String? = null,
        var details: String? = null,
        var contact: String? = null,
        var avatarUrl: String? = null,
        var originalImageUrl: String? = null,
        @ReadOnly var thumbnailImageUrl: String? = null,
        @ReadOnly var smallImageUrl: String? = null,
        @ReadOnly var iconImageUrl: String? = null,
        var facebookUrl: String? = null,
        var twitterUrl: String? = null,
        var instagramUrl: String? = null,
        var googlePlusUrl: String? = null,

        @ReadOnly var isAdmin: Boolean? = null,
        @ReadOnly var isSuperAdmin: Boolean? = null,
        @ReadOnly var isVerified: Boolean? = null,
        @ReadOnly var createdAt: String? = null,
        @ReadOnly var deletedAt: String? = null,
        @ReadOnly var lastAccessedAt: String? = null
)