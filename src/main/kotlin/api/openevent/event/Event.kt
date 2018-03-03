package api.openevent.event

import api.openevent.annotations.ReadOnly
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type
import javax.validation.constraints.Max
import javax.validation.constraints.Min

@Type("event")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
data class Event(
        @ReadOnly
        @Id(IntegerIdHandler::class)
        var id: Int? = null,
        @ReadOnly var identifier: String? = null,
        var name: String? = null,
        var externalEventUrl: String? = null,
        var logoUrl: String? = null,

        var startsAt: String? = null,
        var endsAt: String? = null,
        @ReadOnly var createdAt: String? = null,
        @ReadOnly var deletedAt: String? = null,
        var schedulePublishedOn: String? = null,

        // Location
        var timezone: String? = "UTC",
        @Min(-90) @Max(90)
        var latitude: Float? = null,
        @Min(-180) @Max(180)
        var longitude: Float? = null,
        var locationName: String? = null,
        var searchableLocationName: String? = null,

        var description: String? = null,

        var originalImageUrl: String? = null,
        @ReadOnly var thumbnailImageUrl: String? = null,
        @ReadOnly var largeImageUrl: String? = null,
        @ReadOnly var iconImageUrl: String? = null,

        var hasOrganizerInfo: Boolean = false,
        var organizerName: String? = null,
        var organizerDescription: String? = null,

        var isMapShown: Boolean = false,

        var isSponsorsEnabled: Boolean = false,
        var isSessionsSpeakersEnabled: Boolean = false,
        var privacy: String? = "public",
        var state: EventState? = EventState.draft,
        var ticketUrl: String? = null,
        var codeOfConduct: String? = null,

        var isTicketingEnabled: Boolean = true,
        var isTaxEnabled: Boolean = false,
        var paymentCountry: String? = null,
        var paymentCurrency: String? = null,
        var paypalEmail: String? = null,
        var canPayByPaypal: Boolean = false,
        var canPayByStripe: Boolean = false,
        var canPayByCheque: Boolean = false,
        var canPayByBank: Boolean = false,
        var canPayOnsite: Boolean = false,
        var chequeDetails: String? = null,
        var bankDetails: String? = null,
        var onsiteDetails: String? = null,

        @ReadOnly var pentabarfUrl: String? = null,
        @ReadOnly var icalUrl: String? = null,
        @ReadOnly var xcalUrl: String? = null
)