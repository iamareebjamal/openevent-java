package api.openevent.event

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface EventClient {

    @GET("events")
    fun getAll(): Single<List<Event>>

    @GET("events/{id}")
    fun get(@Path("id") id: Int): Single<Event>

    @POST("events")
    fun post(@Body item: MutableEvent): Single<Event>

    @PATCH("events/{id}")
    fun patch(@Body item: MutableEvent): Single<Event>

    @DELETE("events/{id}")
    fun delete(@Path("id") id: Int): Completable

}