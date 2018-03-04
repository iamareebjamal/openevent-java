import api.openevent.auth.AuthClient
import api.openevent.auth.Login
import api.openevent.config.RetrofitConfig
import api.openevent.event.EventClient
import api.openevent.user.MutableUser
import api.openevent.user.User
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import retrofit2.Retrofit

fun getEvents(retrofit: Retrofit) {
    val eventClient: EventClient = retrofit.create(EventClient::class.java)

    val events = eventClient.getAll().blockingGet()
    println(events)
}

fun authenticate(authClient: AuthClient) {
    val authResponse = authClient.login(Login("test@test.in", "tester")).blockingGet()

    println(authResponse)
}

fun signup(authClient: AuthClient) {
    val user = MutableUser("test@test.io", "pass")
    user.email = "test@test.io"
    user.password = "poo"

    val createdUser = authClient.signUp(user).blockingGet()
    println(createdUser)
}

fun main(args: Array<String>) {
    /*val time = System.currentTimeMillis()
    val retrofit: Retrofit = RetrofitConfig(true).createRetrofit()

    getEvents(retrofit)
    val authClient: AuthClient = retrofit.create(AuthClient::class.java)
    //signup(authClient)
    authenticate(authClient)

    println("Ended ${System.currentTimeMillis() - time}")*/
}
