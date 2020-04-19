package com.example.kinopoisk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.manager.SupportRequestManagerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment(), MovieAdapter.RecyclerViewItemClick {

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var recyclerView: RecyclerView
    lateinit var movieDetailFragment: MovieDetailFragment

    private var movieAdapter: MovieAdapter? = null
    private var rootView: View? = null

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
        movieDetailFragment = MovieDetailFragment()
        val fragmentManager = activity!!.supportFragmentManager
        val fragmentTransaction = fragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, movieDetailFragment)
            .commit()
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
