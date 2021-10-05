package com.patika.homework5.ui.movielist.model

import com.patika.homework5.service.response.MovieListResponse
import com.patika.homework5.util.BASE_URL_FOR_MOVIE_IMAGE

class MovieStateModel(private val movie: Movie) {
    fun getImageUrl() = movie.poster_path
    fun getTitle() = movie.title
    fun getId() = movie.id
    fun getAverageVote() = movie.vote_average.toString()
}