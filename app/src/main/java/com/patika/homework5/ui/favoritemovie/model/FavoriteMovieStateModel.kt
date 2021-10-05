package com.patika.homework5.ui.favoritemovie.model

import com.patika.homework5.ui.moviedetail.model.MovieDetailStateModel
import com.patika.homework5.ui.movielist.model.Movie
import com.patika.homework5.ui.movielist.model.MovieStateModel

class FavoriteMovieStateModel(private val movies: MutableList<Movie>) {
    fun getList(): MutableList<Movie> = movies
}