package com.patika.homework4.service

import com.patika.homework5.service.MovieAPI
import com.patika.homework5.util.BASE_URL_FOR_MOVIE_APP
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceConnector {

    companion object {
        private var retrofit: Retrofit? = null
        lateinit var restInterface: MovieAPI

        fun init() {

            //logging interceptor
            val logging = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }

            //adding logging interceptor to okhttp
            val httpClient: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                //.addInterceptor(AuthenticationInterceptor())
                .build()

            //creating retrofit client to correspond with BackEnd Service
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL_FOR_MOVIE_APP)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build()


            restInterface = retrofit?.create<MovieAPI>(MovieAPI::class.java)!!
        }
    }


   /* class AuthenticationInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
                .newBuilder()
                .addHeader("Authorization", User.getCurrentInstance().token ?: "")
                .build()

            return chain.proceed(request)
        }

    }*/

}