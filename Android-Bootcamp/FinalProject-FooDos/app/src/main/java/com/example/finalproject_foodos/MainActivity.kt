package com.example.finalproject_foodos

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navControllerFragment: NavController

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //bottomnav fragmentler arası geçiş:
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.hostFragmentContainerView) as NavHostFragment
        navControllerFragment = navHostFragment.navController
        val bottomNavView = findViewById<BottomNavigationView>(R.id.bottom_nav_view)
        NavigationUI.setupWithNavController(bottomNavView, navControllerFragment)


        //navigationbar menu kotrolü:
        navHostFragment.navController.addOnDestinationChangedListener{ _,destination,_ ->
            if (destination.id == R.id.foodsDetailFragment){
                bottomNavView.visibility = View.GONE
            }
            else if (destination.id == R.id.splashScreenIntro){
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
//                 actionBar?.hide()
                bottomNavView.visibility = View.GONE
            }
            else if (destination.id == R.id.loginFragment){
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                bottomNavView.visibility = View.GONE
            }
            else if (destination.id == R.id.registerFragment){
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
                bottomNavView.visibility = View.GONE
            }
            else if (destination.id == R.id.profileFragment){
                window.decorView.systemUiVisibility = View.VISIBLE
                bottomNavView.visibility = View.VISIBLE
            }
            else {
                window.decorView.systemUiVisibility = View.VISIBLE
                bottomNavView.visibility = View.VISIBLE
            }
        }

    }




}