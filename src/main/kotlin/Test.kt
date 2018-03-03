import api.openevent.auth.AuthClient
import api.openevent.auth.Login
import api.openevent.config.RetrofitConfig
import api.openevent.event.EventClient
import retrofit2.Retrofit

fun main(args: Array<String>) {
    val time = System.currentTimeMillis()
    val retrofit: Retrofit = RetrofitConfig(true).createRetrofit()
    val eventClient: EventClient = retrofit.create(EventClient::class.java)

    val events = eventClient.getEvents().blockingGet()
    println(events)

    val authClient: AuthClient = retrofit.create(AuthClient::class.java)

    val authResponse = authClient.login(Login("test@test.in", "tester")).blockingGet()

    println(authResponse)

    println("Ended ${System.currentTimeMillis() - time}")
}
