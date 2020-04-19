package com.example.themovie.activity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.themovie.R

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {
    val fm: FragmentManager? = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_user)





    }

    override fun onBackPressed() {
        if (fm?.backStackEntryCount!! <= 1) {
            super.onBackPressed()
        } else
            fm.popBackStack()
    }

}
