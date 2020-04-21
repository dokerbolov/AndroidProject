package com.example.kinopoisk.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk.model.Movie
import com.example.kinopoisk.R
import com.squareup.picasso.Picasso

class MovieAdapter(
    var list: List<Movie>? = null,
    val itemClickListener : RecyclerViewItemClick? = null
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MovieViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.movie_row, p0, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(p0: MovieViewHolder, p1: Int) {
        p0.bind(list?.get(p1))
    }

    fun clearAll() {
        (list as? ArrayList<Movie>)?.clear()
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie?) {
            val movieId = view.findViewById<TextView>(R.id.movieId)
            val movieName = view.findViewById<TextView>(R.id.movieName)
            val moviePic = view.findViewById<ImageView>(R.id.moviePic)

            movieId.text = movie?.id.toString()
            movieName.text = movie?.originalTitle
            Picasso.with(view.context)
                .load(movie?.poster_path)
                .into(moviePic)
            view.setOnClickListener {
                itemClickListener?.itemClick(adapterPosition, movie!!)
            }
        }
    }

    interface RecyclerViewItemClick {
        fun itemClick(position: Int, item: Movie)
    }

}
