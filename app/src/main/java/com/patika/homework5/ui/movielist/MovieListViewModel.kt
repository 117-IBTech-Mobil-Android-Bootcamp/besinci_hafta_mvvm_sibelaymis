package com.patika.homework5.ui.movielist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.patika.homework5.service.MovieRepository
import com.patika.homework5.ui.movielist.model.MovieListViewStateModel
import com.patika.homework5.util.API_KEY

class MovieListViewModel() : ViewModel() {
    val moviesResponse = MediatorLiveData<MovieListViewStateModel>()
    val filmRepository = MovieRepository()

    init {
        getPopularMovies()
        moviesResponse.addSource(filmRepository.onMoviesFetched) {
            moviesResponse.value = MovieListViewStateModel(it)
        }
    }

    fun getPopularMovies(page:Int=1){
        filmRepository.getPopularMovies(page)

    }
}