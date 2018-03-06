package api.openevent

import api.openevent.annotations.ReadOnly
import api.openevent.annotations.Schema
import api.openevent.annotations.WriteOnly
import api.openevent.annotations.contraints.Required
import api.openevent.annotations.contraints.Url
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type
import org.threeten.bp.LocalDateTime
import javax.validation.constraints.Email

@Schema
@Type("data")
data class DataSchema(
        @Id(IntegerIdHandler::class)
        var id: Int? = null,
        @Required var name: String? = null,
        var age: Int? = null,
        @Required @WriteOnly var password: String? = null,
        @field:Url var testUrl: String?=  "Poop",
        @field:Email var email: String? = null,
        var isPooped: Boolean? = false,
        var publishedDate: LocalDateTime? = null,
        @ReadOnly var isHuman: Boolean? = null,
        @ReadOnly var xCalUrl: Boolean? = null
)