package com.patika.homework5.ui.movielist.model

import com.patika.homework5.service.response.MovieListResponse


data class MovieListViewStateModel(val moviesResponse: MovieListResponse) {

    fun getPage():Int=moviesResponse.page
    fun getResults(): String = "total count ${moviesResponse.totalResults}"
    fun getTotalPage(): String = "total page ${moviesResponse.totalPage}"
    fun getList(): List<Movie> = moviesResponse.movies
}
