package com.patika.homework5.ui.favoritemovie

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FavoriteMovieViewModelFactory(val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java)){
            return FavoriteMovieViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}