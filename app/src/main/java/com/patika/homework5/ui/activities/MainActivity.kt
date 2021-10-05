package com.patika.homework5.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.patika.homework5.ui.activities.adapter.MyViewPagerAdapter
import com.patika.homework5.R
import com.patika.homework5.ui.favoritemovie.FavoriteMoviesFragment
import com.patika.homework5.ui.movielist.MovieListFragment
import com.patika.homework5.util.FAVORITES
import com.patika.homework5.util.MOVIES
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }


}