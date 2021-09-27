package com.app.zivameapplication.webservices

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  Retrofit build class
 */
object RetrofitBuilder {
    private const val TIME_OUT = 30
    /**
     * The build method
     *
     * @return The Retrofit build
     */

    fun build(timeOut: Int = 120): Retrofit {
        val httpClient = OkHttpClient.Builder()


        httpClient.addInterceptor { chain ->
            val original = chain.request()

            // Request customization: add request headers
            val requestBuilder = original.newBuilder()
                .header("Content-Type", "application/json")

            val request = requestBuilder.build()
            chain.proceed(request)
        }

        val client = httpClient
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .writeTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .readTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .build()

        val gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com")//Prefs.baseUrl
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()

    }
}
