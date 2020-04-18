package com.example.kinopoisk

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), MovieAdapter.RecyclerViewItemClick {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView

    private var movieAdapter: MovieAdapter? = null
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        bindViews()

        swipeRefreshLayout.setOnRefreshListener {
            movieAdapter?.clearAll()
            getMovies()
        }

        movieAdapter = MovieAdapter(itemClickListener = this)
        recyclerView.adapter = movieAdapter

        getMovies()
        return rootView
    }

    private fun bindViews() {
        recyclerView = (rootView as ViewGroup).findViewById(R.id.recyclerView)
        swipeRefreshLayout = (rootView as ViewGroup).findViewById(R.id.swipeRefreshLayout)
    }

    override fun itemClick(position: Int, item: Movie){
    }

    private fun getMovies(){
        swipeRefreshLayout.isRefreshing = true
        RetrofitService.getPostApi().getMovieList().enqueue(object : Callback<List<Movie>> {
            override fun onFailure(call: Call<List<Movie>>, t: Throwable){
                swipeRefreshLayout.isRefreshing = false
            }

            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                Log.d("My_movie_list", response.body().toString())
                if(response.isSuccessful){
                    val list = response.body()
                    movieAdapter?.list = list
                    movieAdapter?.notifyDataSetChanged()
                }
                swipeRefreshLayout.isRefreshing = false
            }
        })
    }
}
