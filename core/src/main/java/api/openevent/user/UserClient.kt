package api.openevent.user

import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.*

interface UserClient {

    @GET("users")
    fun getAll(): Single<List<User>>

    @GET("users/{id}")
    fun get(@Path("id") id: Int): Single<User>

    @POST("users")
    fun post(@Body item: MutableUser): Single<User>

    @PATCH("users/{id}")
    fun patch(@Body item: MutableUser): Single<User>

    @DELETE("users/{id}")
    fun delete(@Path("id") id: Int): Completable

}