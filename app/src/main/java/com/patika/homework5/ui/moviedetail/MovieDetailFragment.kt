package com.patika.homework5.ui.moviedetail

import  android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.ViewModelProvider
import com.patika.homework5.R
import com.patika.homework5.base.BaseFragment
import com.patika.homework5.databinding.FragmentMovieDetailBinding
import com.patika.homework5.ui.moviedetail.model.MovieDetailStateModel
import com.patika.homework5.ui.movielist.model.Movie
import com.patika.homework5.util.*
import kotlinx.android.synthetic.main.fragment_movie_detail.*


class MovieDetailFragment : BaseFragment<MovieDetailViewModel?, FragmentMovieDetailBinding>() {
    override var viewModel: MovieDetailViewModel?=null
    private var list= mutableListOf<Movie>()
    private var isFavoriteFilm:Boolean=false

    override fun getLayoutID(): Int =R.layout.fragment_movie_detail
    override fun observeLiveData() {

        viewModel?.movieDetailResponse?.observe(this, {

            dataBinding.movieDetailState = it
            dataBinding.executePendingBindings()
            it.getGenres().forEach{ genresModel ->
                val linearLayoutView : View = LayoutInflater.from(requireContext()).inflate(R.layout.genre_view, dataBinding.container, false)
                val type = linearLayoutView.findViewById<TextView>(R.id.movie_type)

                type.text = genresModel.name

                dataBinding.container.addView(linearLayoutView)
            }
            checkMovieFavorite(it)
        })
    }

    private fun checkMovieFavorite(movieStateModel:MovieDetailStateModel){
        this.isFavoriteFilm = this.list.any { movie-> movie.id==movieStateModel.getId() }
        val favoriteBtn=view?.findViewById<AppCompatImageView>(R.id.favorite_btn)
        favoriteBtn?.setImageResource(if(this.isFavoriteFilm) R.drawable.ic_favorite_black_24dp else R.drawable.ic_favorite_border_black_24dp)
    }
    override fun prepareView() {
        backgroundColorChange(R.color.purple_200)

        if(shouldCheckInternetConnection() && !isNetworkConnected()){
            showToast(NOT_INTERNET_CONNECTION)
        }
        this.list=getData(SHARED_KEY)!!

        val movieId=arguments?.getInt("movieId")
        this.viewModel?.movieId=movieId!!
        this.viewModel?.getMovieDetail()

        favorite_btn.setOnClickListener {
            if(this.isFavoriteFilm){
                favorite_btn.setImageResource(R.drawable.ic_favorite_border_black_24dp)
                this.isFavoriteFilm=false
                this.viewModel?.movieDetailResponse?.value?.let { it1 -> removeFavorite(Movie(id =it1.getId(),poster_path = it1.getImageUrl(),title = it1.getTitle(),vote_average = it1.getAverageVote() )) }
            }
            else {
                favorite_btn.setImageResource(R.drawable.ic_favorite_black_24dp)
                this.isFavoriteFilm=true
                this.viewModel?.movieDetailResponse?.value?.let { it1 -> addFavorite(Movie(id =it1.getId(),poster_path = it1.getImageUrl(),title = it1.getTitle(),vote_average = it1.getAverageVote() )) }
            }
        }
    }

    override fun prepareViewModel() {
        this.viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
    }

    private fun addFavorite(item: Movie){
        val list=getData<Movie>(SHARED_KEY)
        list?.add(item)
        saveData(SHARED_KEY,list!!)
    }

    private fun removeFavorite(item:Movie){
        val list=getData<Movie>(SHARED_KEY)
        list?.remove(item)
        saveData(SHARED_KEY,list!!)
    }

}

