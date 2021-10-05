package com.patika.homework5.ui.favoritemovie

import android.content.Context
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.patika.homework5.service.MovieRepository
import com.patika.homework5.ui.favoritemovie.model.FavoriteMovieStateModel

class FavoriteMovieViewModel(context:Context):ViewModel() {
    val moviesResponse = MediatorLiveData<FavoriteMovieStateModel>()
    val filmRepository = MovieRepository()
    var context : Context=context

    init {
        filmRepository.getFavoriteMovies(context)
        moviesResponse.addSource(filmRepository.onFavoriteMoviesFetched) {
            moviesResponse.value = FavoriteMovieStateModel(it)
        }
    }

    fun getFavoriteMovieList(){
        filmRepository.getFavoriteMovies(context)
    }

}