package com.patika.homework5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.tabs.TabLayoutMediator
import com.patika.homework5.R
import com.patika.homework5.ui.adapter.MyViewPagerAdapter
import com.patika.homework5.ui.favoritemovie.FavoriteMoviesFragment
import com.patika.homework5.ui.movielist.MovieListFragment
import com.patika.homework5.util.FAVORITES
import com.patika.homework5.util.MOVIES
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    private val titleList= mutableListOf(MOVIES, FAVORITES)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = MyViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(MovieListFragment())
        adapter.addFragment(FavoriteMoviesFragment())
        viewPager.adapter = adapter
        //We switch between tabs, that is, between viewPagers.
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }
}