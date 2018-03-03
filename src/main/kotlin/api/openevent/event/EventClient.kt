package api.openevent.event

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface EventClient {

    @GET("events")
    fun getEvents(): Single<List<Event>>

    @GET("events/{id}")
    fun getEvent(@Path("id") id: Int): Single<Event>

    @POST("events")
    fun postEvent(@Body event: Event): Single<Event>

    @PATCH("events/{id}")
    fun patchEvent(@Path("id") id: Int, @Body event: Event): Single<Event>

    @PATCH("events/{id}")
    fun deleteEvent(@Path("id") id: Int): Completable

}