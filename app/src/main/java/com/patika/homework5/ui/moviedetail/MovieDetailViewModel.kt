package com.patika.homework5.ui.moviedetail

import android.app.Application
import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.patika.homework5.service.MovieRepository
import com.patika.homework5.ui.favoritemovie.model.FavoriteMovieStateModel
import com.patika.homework5.ui.moviedetail.model.MovieDetailStateModel
import com.patika.homework5.util.API_KEY
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import com.google.gson.Gson
import com.patika.homework5.util.DEFAULT_VALUE


class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {

    val movieDetailResponse = MediatorLiveData<MovieDetailStateModel>()
    private val movieRepository = MovieRepository()
    var movieId: Int = 0

    fun getMovieDetail() {
        movieRepository.getMoviesDetail(movieId)
        movieDetailResponse.addSource(movieRepository.onMoviesDetailFetched) {
            movieDetailResponse.value = MovieDetailStateModel(it)
        }
    }


}