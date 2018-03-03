import api.openevent.auth.AuthClient
import api.openevent.auth.Login
import api.openevent.config.RetrofitConfig
import api.openevent.event.EventClient
import api.openevent.user.User
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
    val user = User()
    user.email = "test@test.io"
    user.password = "poo"

    val createdUser = authClient.signUp(user).blockingGet()
    println(createdUser)
}

fun main(args: Array<String>) {
    val time = System.currentTimeMillis()
    /*val retrofit: Retrofit = RetrofitConfig(true).createRetrofit()

    getEvents(retrofit)
    val authClient: AuthClient = retrofit.create(AuthClient::class.java)
    // signup(authClient)
    authenticate(authClient)*/

    println("Ended ${System.currentTimeMillis() - time}")
}
