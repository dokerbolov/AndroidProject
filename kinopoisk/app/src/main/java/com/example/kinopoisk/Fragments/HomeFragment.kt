package com.example.kinopoisk.Fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.kinopoisk.*
import com.example.kinopoisk.Activity.MovieDetailActivity
import com.example.kinopoisk.Adapter.MovieAdapter
import com.example.kinopoisk.api.ApiService
import com.example.kinopoisk.api.RetrofitService
import com.example.kinopoisk.model.Movie
import com.example.kinopoisk.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(),
    MovieAdapter.RecyclerViewItemClick {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView

    private var movieAdapter: MovieAdapter? = null
    private var rootView: View? = null
    private var movies: ArrayList<Movie>? = null
    private lateinit var movie: Movie


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false) as ViewGroup
        bindViews()

        swipeRefreshLayout.setOnRefreshListener {
            movieAdapter?.clearAll()
            movieAdapter?.notifyDataSetChanged()
            getMovies()
        }

        getMovies()
        return rootView
    }

    private fun bindViews() {
        recyclerView = (rootView as ViewGroup).findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        movieAdapter = MovieAdapter(itemClickListener = this)
        recyclerView.adapter = movieAdapter
        movieAdapter?.notifyDataSetChanged()
        swipeRefreshLayout = (rootView as ViewGroup).findViewById(R.id.swipeRefreshLayout)
    }

    override fun itemClick(position: Int, item: Movie){
        val intent = Intent(context, MovieDetailActivity::class.java)
        startActivity(intent)
    }

    private fun getMovies(){
        swipeRefreshLayout.isRefreshing = true
        val api: ApiService? = RetrofitService.getPostApi()?.create(ApiService::class.java)
        api?.getPopularMoviesList(BuildConfig.THE_MOVIE_DB_API_TOKEN, 1)?.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful){
                    val list = response.body()?.results
                    val list2 = list!!.subList(1, list.lastIndex)
                    if( list != null){
                        movie = list.first()
                    }
                    movieAdapter?.list = list2
                    movieAdapter?.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(HomeFragment::class.java.simpleName, t.toString())
                Toast.makeText(context,"Ne gruzit",Toast.LENGTH_SHORT).show()
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}
