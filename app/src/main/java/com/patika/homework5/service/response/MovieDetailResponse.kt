package com.patika.homework5.service.response

import com.patika.homework5.ui.moviedetail.model.GenresModel

data class MovieDetailResponse(
    val title: String,
    val adult: Boolean,
    val backdrop_path: String,
    val id: Int,
    val vote_average: Double,
    val vote_count: Int,
    val overview: String,
    val release_date: String,
    val runtime: Int,
    val genres: ArrayList<GenresModel>
)