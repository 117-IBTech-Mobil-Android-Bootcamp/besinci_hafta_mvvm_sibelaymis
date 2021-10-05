package com.patika.homework5.service

import com.patika.homework5.service.response.MovieDetailResponse
import com.patika.homework5.service.response.MovieListResponse
import retrofit2.Call
import retrofit2.http.*

interface MovieAPI {

    @GET("movie/popular")
    fun getPopulerMovies(@QueryMap params : MutableMap<String, Any>) : Call<MovieListResponse>

    @GET("movie/{movie_Id}")
    fun getDetails(@Path("movie_Id") movieId:Int, @QueryMap params : MutableMap<String, Any>) : Call<MovieDetailResponse>

}