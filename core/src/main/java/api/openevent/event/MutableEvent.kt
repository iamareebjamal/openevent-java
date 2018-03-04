package api.openevent.event

import api.openevent.annotations.contraints.Url
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Type("event")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
data class MutableEvent(
        var name: String,
        var startsAt: String,
        var endsAt: String,
        var timezone: String,

        @Id(IntegerIdHandler::class)
        val id: Int? = null,

        var description: String? = null,
        var privacy: String? = "public",
        var state: EventState? = EventState.draft,
        var schedulePublishedOn: String? = null,

        @Url var logoUrl: String? = null,
        @Url var originalImageUrl: String? = null,
        @Url var externalEventUrl: String? = null,

        @Min(-90) @Max(90)
        var latitude: Float? = null,
        @Min(-180) @Max(180)
        var longitude: Float? = null,
        var locationName: String? = null,
        var searchableLocationName: String? = null,

        var hasOrganizerInfo: Boolean = false,
        var organizerName: String? = null,
        var organizerDescription: String? = null,

        var isMapShown: Boolean = false,
        var isSponsorsEnabled: Boolean = false,
        var isSessionsSpeakersEnabled: Boolean = false,
        @Url var ticketUrl: String? = null,
        var codeOfConduct: String? = null,

        var isTicketingEnabled: Boolean = true,
        var isTaxEnabled: Boolean = false,
        var paymentCountry: String? = null,
        var paymentCurrency: String? = null,
        @Email var paypalEmail: String? = null,
        var canPayByPaypal: Boolean = false,
        var canPayByStripe: Boolean = false,
        var canPayByCheque: Boolean = false,
        var canPayByBank: Boolean = false,
        var canPayOnsite: Boolean = false,
        var chequeDetails: String? = null,
        var bankDetails: String? = null,
        var onsiteDetails: String? = null
)
