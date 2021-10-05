package com.patika.homework5.service

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.patika.homework4.service.BaseCallBack
import com.patika.homework4.service.ServiceConnector
import com.patika.homework5.service.response.MovieDetailResponse
import com.patika.homework5.service.response.MovieListResponse
import com.patika.homework5.ui.favoritemovie.model.FavoriteMovieStateModel
import com.patika.homework5.ui.moviedetail.model.MovieDetailStateModel
import com.patika.homework5.ui.movielist.model.Movie
import com.patika.homework5.util.API_KEY
import com.patika.homework5.util.DEFAULT_VALUE
import com.patika.homework5.util.SHARED_KEY
import java.lang.reflect.Type

class MovieRepository {

    val onMoviesFetched = MutableLiveData<MovieListResponse>()
    val onMoviesDetailFetched = MutableLiveData<MovieDetailResponse>()
    val onFavoriteMoviesFetched = MutableLiveData<MutableList<Movie>>()

    fun getPopularMovies(page:Int){

        val params = mutableMapOf<String, Any>().apply {
            put("api_key", API_KEY)
            put("language", "en-Us")
            put("page", page)
        }
        ServiceConnector.restInterface.getPopulerMovies(params).enqueue(object  : BaseCallBack<MovieListResponse>(){
            override fun onSuccess(data: MovieListResponse) {
                onMoviesFetched.value=data
            }
        })
    }

    fun getMoviesDetail(id:Int=0){

        val params = mutableMapOf<String, Any>().apply {
            put("api_key", API_KEY)
            put("language", "en-Us")
        }
        ServiceConnector.restInterface.getDetails(id,params).enqueue(object  : BaseCallBack<MovieDetailResponse>(){
            override fun onSuccess(data: MovieDetailResponse) {
                onMoviesDetailFetched.value=data
            }
        })
    }

    fun getFavoriteMovies(context: Context){
        val sharedPreferences: SharedPreferences = context.getSharedPreferences("FavoriteList",
            Context.MODE_PRIVATE
        )
        var arrayItems: MutableList<Movie> = mutableListOf()
        val serializedObject = sharedPreferences.getString(SHARED_KEY, null)
        if (serializedObject != null) {
            val gson = Gson()
            val type: Type = object : TypeToken<MutableList<Movie?>?>() {}.type
            arrayItems = gson.fromJson(serializedObject, type)
        }
        onFavoriteMoviesFetched.value=arrayItems

    }
}