package com.example.kinopoisk

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Response


class MovieDetailFragment : Fragment() {

    private var rootView: View? = null
    private lateinit var movieNmae: TextView
    private lateinit var moviePic: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false) as ViewGroup
        bindViews()

/*        val movieId = intent.getIntExtra("movie_id", 1)
        getMovie(id = movieId)*/

        return rootView
    }

    private fun bindViews() {
        movieNmae = (rootView as ViewGroup).findViewById(R.id.movieName)
        moviePic = (rootView as ViewGroup).findViewById(R.id.moviePic)
    }

//    private fun getMovie(id: Int){
//        RetrofitService.getPostApi().getMovieById(id).enqueue(object : retrofit2.Callback<Movie>){
//            override fun onFailure(call: retrofit2.Call<Movie>, t: Throwable){
//            }
//            override fun onResponse(call: retrofit2.Call<Movie>, response: Response<Movie>){
//                val movie = response.body()
//                if( movie != null){
//                    movieNmae.text = movie.title
//                }
//            }
//        }
//    }

}
