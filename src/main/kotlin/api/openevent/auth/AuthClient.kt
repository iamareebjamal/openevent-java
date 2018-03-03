package api.openevent.auth

import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthClient {

    @POST("../auth/session")
    fun login(@Body login: Login): Single<AuthToken>

}