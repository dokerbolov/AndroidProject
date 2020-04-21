package com.example.kinopoisk.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.kinopoisk.model.Movie
import com.example.kinopoisk.R
import com.example.kinopoisk.api.RetrofitService
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var detailTitle: TextView
    private lateinit var detailImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        detailTitle = findViewById(R.id.detailMovieName)
        detailImage = findViewById(R.id.detailMoviePic)

        val detailMovieId = intent.getIntExtra("movie_id", 1)
//        getMovie(id = detailMovieId)
    }

//    private fun getMovie(id: Int) {
//        RetrofitService.getPostApi()
//            .getMovieById(id).enqueue(object : Callback<Movie> {
//            override fun onFailure(call: Call<Movie>, t: Throwable) {
//
//            }

//            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
//                val movie = response.body()
//                if (movie != null) {
//                    detailTitle.text = movie.title
//                    Picasso.with(this@MovieDetailActivity)
//                        .load(movie?.url)
//                        .into(detailImage)
//                }
//            }
//        })
}


