package com.patika.homework5.ui.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.patika.homework5.R
import com.patika.homework5.databinding.RowMovieBinding
import com.patika.homework5.ui.movielist.model.Movie
import com.patika.homework5.ui.movielist.model.MovieStateModel

class MovieRecyclerViewAdapter(
    private val movieList: List<Movie>,
    private val block: (Movie) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val VIEW_TYPE_ITEM = 0 //Movie item
    private val VIEW_TYPE_LOADING = 1 //Loading

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(viewType==VIEW_TYPE_ITEM) {
            return MovieViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context),R.layout.row_movie,parent,false))
        }else{
            return LoadingHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_loading_view,parent,false))
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val movie = movieList[position]
        if(holder is MovieViewHolder) {
            var recyclerHolder:MovieViewHolder=holder
            recyclerHolder.populate(movie)
            recyclerHolder.setOnItemClickListener(movie, this@MovieRecyclerViewAdapter.block)
        }
    }

    override fun getItemCount(): Int = movieList.size

    override fun getItemViewType(position: Int): Int {
        if(position<movieList.size-1){
            return VIEW_TYPE_ITEM
        }
        else{
            return VIEW_TYPE_LOADING
        }
    }

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

class LoadingHolder(view: View): RecyclerView.ViewHolder(view)