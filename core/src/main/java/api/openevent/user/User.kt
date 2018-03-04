package api.openevent.user

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type
import javax.validation.constraints.Email

@Type("user")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
data class User(
        @Id(IntegerIdHandler::class)
        val id: Int,
        @Email
        val email: String,
        val firstName: String?,
        val lastName: String?,
        val details: String?,
        val contact: String?,
        val avatarUrl: String?,
        val originalImageUrl: String?,
        val thumbnailImageUrl: String?,
        val smallImageUrl: String?,
        val iconImageUrl: String?,
        val facebookUrl: String?,
        val twitterUrl: String?,
        val instagramUrl: String?,
        val googlePlusUrl: String?,

        val isAdmin: Boolean?,
        val isSuperAdmin: Boolean?,
        val isVerified: Boolean?,
        val createdAt: String?,
        val deletedAt: String?,
        val lastAccessedAt: String?
) {

    fun edit(): MutableUser {
        return MutableUser(
                id = id,
                email = email,
                password = "",
                firstName = firstName,
                lastName = lastName,
                details = details,
                contact = contact,
                avatarUrl = avatarUrl,
                originalImageUrl = originalImageUrl,
                facebookUrl = facebookUrl,
                twitterUrl = twitterUrl,
                instagramUrl = instagramUrl,
                googlePlusUrl = googlePlusUrl
        )
    }

}