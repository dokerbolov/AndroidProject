package com.example.kinopoisk.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.kinopoisk.Fragments.FavoriteFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.kinopoisk.Fragments.HomeFragment
import com.example.kinopoisk.Fragments.UserFragment
import com.example.kinopoisk.R


class MainActivity : AppCompatActivity() {

    lateinit var homeFragment: HomeFragment
    lateinit var favoriteFragment: FavoriteFragment
    lateinit var userFragment: UserFragment

    lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigation = findViewById(R.id.bottom_navigation)

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
                    favoriteFragment =
                        FavoriteFragment()
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

}
