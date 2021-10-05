package com.patika.homework5.ui.moviedetail.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.patika.homework5.service.response.MovieDetailResponse
import com.patika.homework5.ui.movielist.model.Movie
import java.text.DateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MovieDetailStateModel(private val movieDetail: MovieDetailResponse) {
    fun getAge() = if(movieDetail.adult) "PG-13" else "PG-18"
    fun getImageUrl() = movieDetail.backdrop_path
    fun getTitle() = movieDetail.title
    fun getId() = movieDetail.id
    fun getAverageVoteAsString() = movieDetail.vote_average.toString()+"/10"
    fun getAverageVote() = movieDetail.vote_average
    fun getVoteCount() = movieDetail.vote_count.toString()
    fun getOverview() = movieDetail.overview
    @RequiresApi(Build.VERSION_CODES.O)
    fun getReleaseDate() = LocalDate.parse(movieDetail.release_date,DateTimeFormatter.ofPattern("yyyy-MM-dd")).year.toString()
    fun getRunTime() = movieDetail.runtime.toString() + "min"
    fun getGenres() = movieDetail.genres
    var isFavorite=false
}