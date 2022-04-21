package com.example.finalproject_foodos

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.kisileruygulamasi.viewmodel.FavFoodsFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.FavFoodsVMFactory
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.google.android.material.bottomnavigation.BottomNavigationView

@ExperimentalBadgeUtils class MainActivity : AppCompatActivity() {
    private lateinit var navControllerFragment: NavController
    private lateinit var badge : BadgeDrawable
    private lateinit var viewModel : FavFoodsFragmentViewModel
    private lateinit var bottomNavView : BottomNavigationView

    @SuppressLint("WrongConstant")
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //badge
        badge = BadgeDrawable.create(this)

        //bottomnav fragmentler arası geçiş:
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.hostFragmentContainerView) as NavHostFragment
        navControllerFragment = navHostFragment.navController
        bottomNavView = findViewById(R.id.bottom_nav_view)
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


        val tempViewModel : FavFoodsFragmentViewModel by viewModels {
            FavFoodsVMFactory(this.application)
        }
        this.viewModel = tempViewModel
        viewModel.getAllFavorites()
        viewModel.getCount().observe(this) { it ->
            displayBadge(it)
            Log.e("badge1",it.toString())
        }


    }

    fun displayBadge(it : Int) {
        val badge = bottomNavView.getOrCreateBadge(R.id.foodsFavoriteFragment)
        badge.number = it
        Log.e("badge2",badge.number.toString())
        badge.backgroundColor = ContextCompat.getColor(applicationContext, R.color.BottomNav_clickedcolor)
        //badge.badgeGravity = BadgeDrawable.TOP_START
        badge.horizontalOffset = (10)
        badge.verticalOffset = (5)
//        BadgeUtils.attachBadgeDrawable(badge, bottomNavView, R.id.item_card_counter, R.layout.fragment_foods_favorite)
//        BadgeUtils.attachBadgeDrawable(badge, bottomNavView)
    }


}