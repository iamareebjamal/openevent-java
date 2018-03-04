package api.openevent

import api.openevent.annotations.ReadOnly
import api.openevent.annotations.Schema
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type

@Schema
@Type("data")
data class DataSchema(
        @Id(IntegerIdHandler::class)
        var id: Int? = null,
        var name: String? = null,
        var age: Int? = null,
        var isPooped: Boolean? = false,
        @ReadOnly var isHuman: Boolean? = null,
        @ReadOnly var xCalUrl: Boolean? = null
)