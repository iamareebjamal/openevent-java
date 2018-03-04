package api.openevent.auth

import api.openevent.user.MutableUser
import api.openevent.user.User
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthClient {

    @POST("../auth/session")
    fun login(@Body login: Login): Single<AuthToken>

    @POST("users")
    fun signUp(@Body user: MutableUser): Single<User>

}