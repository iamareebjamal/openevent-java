package api.openevent.event

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import com.github.jasminb.jsonapi.IntegerIdHandler
import com.github.jasminb.jsonapi.annotations.Id
import com.github.jasminb.jsonapi.annotations.Type

@Type("event")
@JsonIgnoreProperties
@JsonNaming(PropertyNamingStrategy.KebabCaseStrategy::class)
data class Event(
        @Id(IntegerIdHandler::class)
        val id: Int,
        val identifier: String,
        val name: String,
        val startsAt: String,
        val endsAt: String,
        val timezone: String,

        val createdAt: String?,
        val deletedAt: String?,
        val schedulePublishedOn: String?,

        val description: String?,
        val privacy: String = "public",
        val state: EventState? = EventState.draft,
        val externalEventUrl: String?,

        val logoUrl: String?,
        val originalImageUrl: String?,
        val thumbnailImageUrl: String?,
        val largeImageUrl: String?,
        val iconImageUrl: String?,

        // Location
        val latitude: Float?,
        val longitude: Float?,
        val locationName: String?,
        val searchableLocationName: String?,

        val hasOrganizerInfo: Boolean = false,
        val organizerName: String?,
        val organizerDescription: String?,

        val isMapShown: Boolean = false,
        val isSponsorsEnabled: Boolean = false,
        val isSessionsSpeakersEnabled: Boolean = false,
        val ticketUrl: String?,
        val codeOfConduct: String?,

        val isTicketingEnabled: Boolean = true,
        val isTaxEnabled: Boolean = false,
        val paymentCountry: String?,
        val paymentCurrency: String?,
        val paypalEmail: String?,
        val canPayByPaypal: Boolean = false,
        val canPayByStripe: Boolean = false,
        val canPayByCheque: Boolean = false,
        val canPayByBank: Boolean = false,
        val canPayOnsite: Boolean = false,
        val chequeDetails: String?,
        val bankDetails: String?,
        val onsiteDetails: String?,

        var pentabarfUrl: String?,
        val icalUrl: String?,
        val xcalUrl: String?
) {

    fun edit(): MutableEvent {
        return MutableEvent(
                name = name,
                startsAt = startsAt,
                endsAt = endsAt,
                timezone = timezone,

                id = id,

                description = description,
                privacy = privacy,
                state = state,
                schedulePublishedOn = schedulePublishedOn,

                logoUrl = logoUrl,
                originalImageUrl = originalImageUrl,
                externalEventUrl = externalEventUrl,

                latitude = latitude,
                longitude = longitude,
                locationName = locationName,
                searchableLocationName = searchableLocationName,

                hasOrganizerInfo = hasOrganizerInfo,
                organizerName = organizerName,
                organizerDescription = organizerDescription,

                isMapShown = isMapShown,
                isSponsorsEnabled = isSponsorsEnabled,
                isSessionsSpeakersEnabled = isSessionsSpeakersEnabled,
                ticketUrl = ticketUrl,
                codeOfConduct = codeOfConduct,

                isTicketingEnabled = isTicketingEnabled,
                isTaxEnabled = isTaxEnabled,
                paymentCountry = paymentCountry,
                paymentCurrency = paymentCurrency,
                paypalEmail = paypalEmail,
                canPayByPaypal = canPayByPaypal,
                canPayByStripe = canPayByStripe,
                canPayByCheque = canPayByCheque,
                canPayByBank = canPayByBank,
                canPayOnsite = canPayOnsite,
                chequeDetails = chequeDetails,
                bankDetails = bankDetails,
                onsiteDetails = onsiteDetails
        )
    }

}