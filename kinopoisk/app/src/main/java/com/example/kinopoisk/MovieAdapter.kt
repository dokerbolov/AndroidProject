package com.example.kinopoisk

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_row.view.*

class MovieAdapter(
    var list: List<Movie>? = null,
    val itemClickListener : RecyclerViewItemClick? = null
) : RecyclerView.Adapter<MovieAdapter.PostViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PostViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.movie_row, p0, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = list?.size ?: 0

    override fun onBindViewHolder(p0: PostViewHolder, p1: Int) {
        p0.bind(list?.get(p1))
    }

    fun clearAll() {
        (list as? ArrayList<Movie>)?.clear()
        notifyDataSetChanged()
    }

    inner class PostViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(movie: Movie?) {
            val movieName = view.findViewById<TextView>(R.id.movieName)

            movieName.text = movie?.title

            view.setOnClickListener {
                itemClickListener?.itemClick(adapterPosition, movie!!)
            }
        }
    }

    interface RecyclerViewItemClick {
        fun itemClick(position: Int, item: Movie)
    }

}
