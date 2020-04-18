package com.example.kinopoisk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.LinearLayout
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var favoriteFragment: FavoriteFragment
    lateinit var userFragment: UserFragment

    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // bottomNavigation = findViewById(R.id.bottom_navigation)
      //  swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {

        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)
        api.fetchAllUsers().enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                d("incorrect","onFailure")
            }

        })

        homeFragment = HomeFragment()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homeFragment)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()

        bottomNavigation.setOnNavigationItemSelectedListener {
            item -> when(item.itemId){
                R.id.home -> {
                    homeFragment = HomeFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homeFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.user -> {
                    userFragment = UserFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, userFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                R.id.favorite -> {
                    favoriteFragment = FavoriteFragment()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, favoriteFragment)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
            }
        }
            true
        }

    }

    private fun showData(users: List<User>){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MovieAdapter(users)
        }
    }

}
