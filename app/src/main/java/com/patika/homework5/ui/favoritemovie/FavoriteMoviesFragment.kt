package com.patika.homework5.ui.favoritemovie

import android.os.Bundle
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.patika.homework5.R
import com.patika.homework5.base.BaseFragment
import com.patika.homework5.databinding.FragmentFavoriteMoviesBinding
import com.patika.homework5.ui.favoritemovie.adapter.FavoriteMovieAdapter
import com.patika.homework5.ui.movielist.model.Movie
import com.patika.homework5.util.*
import kotlinx.android.synthetic.main.fragment_movie_list.*

class FavoriteMoviesFragment : BaseFragment<FavoriteMovieViewModel,FragmentFavoriteMoviesBinding>() {
    override var viewModel: FavoriteMovieViewModel?=null
    private lateinit var layoutManager: LinearLayoutManager
    private var favoriteMovieAdapter: FavoriteMovieAdapter? = null
    private var movieList = mutableListOf<Movie>()

    override fun getLayoutID(): Int =R.layout.fragment_favorite_movies

    override fun observeLiveData() {
        viewModel?.moviesResponse?.observe(this, {

            dataBinding.favoriteMovieStateModel = it
            dataBinding.executePendingBindings()
            this.movieList.clear()
            this.movieList.addAll(it.getList())
            favoriteMovieAdapter?.notifyDataSetChanged()
            if(this.movieList.isEmpty()){
                val notAddMovieTV=view?.findViewById<AppCompatTextView>(R.id.not_add_movie)
                notAddMovieTV?.visible()
            }
            else{
                val notAddMovieTV=view?.findViewById<AppCompatTextView>(R.id.not_add_movie)
                notAddMovieTV?.gone()
            }
        })
    }

    override fun prepareView() {
        backgroundColorChange(R.color.purple_200)
        if (shouldCheckInternetConnection() && !isNetworkConnected()) {
            showToast(NOT_INTERNET_CONNECTION)
        }
        viewModel?.getFavoriteMovieList()
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

        favoriteMovieAdapter=   FavoriteMovieAdapter(movieList) { clickedObject ->
            navigateFragment(
                R.id.action_homeFragment_to_movieDetailFragment,
                Bundle().apply {
                    putInt("movieId", clickedObject.id)
                })

        }
        dataBinding.recyclerView.adapter =favoriteMovieAdapter
    }

    override fun prepareViewModel() {
//        viewModel?.context=requireContext()
        viewModel = ViewModelProvider(this,FavoriteMovieViewModelFactory(requireContext())).get(FavoriteMovieViewModel::class.java)
    }


}