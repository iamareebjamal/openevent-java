package api.openevent.user

import api.openevent.annotations.ReadOnly
import api.openevent.annotations.contraints.Url
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type
import javax.validation.constraints.Email

@Type("user")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
data class MutableUser(
        @Email
        @ReadOnly
        var email: String,
        var password: String,

        @Id(IntegerIdHandler::class)
        val id: Int? = null,

        var firstName: String? = null,
        var lastName: String? = null,
        var details: String? = null,
        var contact: String? = null,
        @Url var avatarUrl: String? = null,
        @Url var originalImageUrl: String? = null,
        @Url var facebookUrl: String? = null,
        @Url var twitterUrl: String? = null,
        @Url var instagramUrl: String? = null,
        @Url var googlePlusUrl: String? = null
)