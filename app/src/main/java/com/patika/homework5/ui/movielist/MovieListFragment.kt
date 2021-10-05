package com.patika.homework5.ui.movielist

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.patika.homework5.R
import com.patika.homework5.base.BaseFragment
import com.patika.homework5.databinding.FragmentMovieListBinding
import com.patika.homework5.ui.movielist.adapter.MovieRecyclerViewAdapter
import com.patika.homework5.ui.movielist.model.Movie
import com.patika.homework5.util.NOT_INTERNET_CONNECTION
import com.patika.homework5.util.navigateFragment
import com.patika.homework5.util.showToast
import kotlinx.android.synthetic.main.fragment_movie_list.*


class MovieListFragment() : BaseFragment<MovieListViewModel, FragmentMovieListBinding>() {
    private var movieRecyclerviewAdapter: MovieRecyclerViewAdapter? = null
    private lateinit var layoutManager: LinearLayoutManager

    private var movieList = mutableListOf<Movie>()
    private var page = 1
    private var isLoading: Boolean = false
    private var isLastPage: Boolean = false

    override var viewModel: MovieListViewModel? = null

    override fun getLayoutID(): Int = R.layout.fragment_movie_list

    override fun observeLiveData() {
        viewModel?.moviesResponse?.observe(this, {

            dataBinding.movieListViewStateModel = it
            dataBinding.executePendingBindings()
            movieList.addAll(it.getList())
            if (it.getList().isEmpty()) {
                isLastPage = true
            } else {
                isLoading = false
                this.movieRecyclerviewAdapter?.notifyDataSetChanged()
            }
        })

    }

    override fun prepareView() {
        backgroundColorChange(R.color.purple_200)

        if (shouldCheckInternetConnection() && !isNetworkConnected()) {
            showToast(NOT_INTERNET_CONNECTION)
        }
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        recyclerView.layoutManager = layoutManager

         movieRecyclerviewAdapter=   MovieRecyclerViewAdapter(movieList) { clickedObject ->
                navigateFragment(
                    R.id.action_homeFragment_to_movieDetailFragment,
                    Bundle().apply {
                        putInt("movieId", clickedObject.id)
                    })

            }
        dataBinding.recyclerView.adapter =movieRecyclerviewAdapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                if (!isLoading && !isLastPage) {
                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {//eğer yüklenme işlemi yoksa ve liste en aşağıdaysa
                        isLoading = true
                        page += 1
                        viewModel?.getPopularMovies(page)
                    }
                }
            }
        })
    }

    override fun prepareViewModel() {
        viewModel = ViewModelProvider(this).get(MovieListViewModel::class.java)
    }


}