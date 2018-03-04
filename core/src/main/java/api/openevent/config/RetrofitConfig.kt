package api.openevent.config

import api.openevent.event.Event
import api.openevent.user.MutableUser
import api.openevent.user.User
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import com.fasterxml.jackson.module.kotlin.*
import okhttp3.Interceptor

class RetrofitConfig(private val debug: Boolean = false) {

    companion object {
        private const val defaultBaseUrl = "http://localhost:5000/v1/"
    }

    private val objectMapper: ObjectMapper by lazy {
        jacksonObjectMapper()
    }

    private val loggingInterceptor: Interceptor by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        if (debug)
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

    fun createRetrofit(baseUrl: String = defaultBaseUrl): Retrofit {
        return Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(JSONAPIConverterFactory(objectMapper, Event::class.java, User::class.java, MutableUser::class.java))
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .baseUrl(baseUrl)
                .build()
    }

}
