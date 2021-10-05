package com.patika.homework5.ui.favoritemovie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.patika.homework5.R
import com.patika.homework5.databinding.RowMovieBinding
import com.patika.homework5.ui.movielist.model.Movie
import com.patika.homework5.ui.movielist.model.MovieStateModel

class FavoriteMovieAdapter(
    private val movieList: List<Movie>,
    private val block: (Movie) -> Unit
) : RecyclerView.Adapter<MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            return MovieViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.row_movie,parent,false))
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.populate(movie)
        holder.setOnItemClickListener(movie, this@FavoriteMovieAdapter.block)
        }

    override fun getItemCount(): Int = movieList.size


}

class MovieViewHolder(private val binding: RowMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun populate(movie: Movie) {
        binding.movieState = MovieStateModel(movie)
        binding.executePendingBindings()
    }
    fun setOnItemClickListener(
        movie: Movie,
        block: (Movie) -> Unit
    ) {
        binding.root.setOnClickListener {

            block.invoke(movie)
        }
    }
}

