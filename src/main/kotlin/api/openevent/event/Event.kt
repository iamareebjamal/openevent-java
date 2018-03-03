package api.openevent.event

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type

@Type("event")
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
data class Event(
        @Id(IntegerIdHandler::class)
        var id: Int? = null,
        var name: String? = null,
        var identifier: String? = null,
        var externalEventUrl: String? = null,
        var logoUrl: String? = null,

        var startsAt: String? = null,
        var endsAt: String? = null,
        var createdAt: String? = null,
        var deletedAt: String? = null,
        var schedulePublishedOn: String? = null,

        // Location
        var timezone: String? = "UTC",
        var latitude: Float? = null,
        var longitude: Float? = null,
        var locationName: String? = null,
        var searchableLocationName: String? = null,

        var description: String? = null,

        var originalImageUrl: String? = null,
        var thumbnailImageUrl: String? = null,
        var largeImageUrl: String? = null,
        var iconImageUrl: String? = null,

        var organizerName: String? = null,
        var hasOrganizerInfo: Boolean? = null,
        var organizerDescription: String? = null,

        var isMapShown: String? = null,

        var isSponsorsEnabled: Boolean? = null,
        var isSessionsSpeakersEnabled: Boolean? = null,
        var privacy: String? = "Public",
        var state: String? = "Draft",
        var ticketUrl: String? = null,
        var codeOfConduct: String? = null,

        var isTicketingEnabled: Boolean? = null,
        var isTaxEnabled: Boolean? = null,
        var paymentCountry: String? = null,
        var paymentCurrency: String? = null,
        var paypalEmail: String? = null,
        var canPayByPaypal: String? = null,
        var canPayByStripe: String? = null,
        var canPayByCheque: String? = null,
        var canPayByBank: String? = null,
        var canPayOnsite: String? = null,
        var chequeDetails: String? = null,
        var bankDetails: String? = null,
        var onsiteDetails: String? = null,

        var pentabarfUrl: String? = null,
        var icalUrl: String? = null,
        var xcalUrl: String? = null
)