package com.patika.homework5.service.response

import com.google.gson.annotations.SerializedName
import com.patika.homework5.ui.movielist.model.Movie

data class MovieListResponse(
    val page: Int,
    @SerializedName("total_pages") val totalPage: Int,
    @SerializedName("total_results") val totalResults: Int,
    @SerializedName("results") val movies : List<Movie>
)
