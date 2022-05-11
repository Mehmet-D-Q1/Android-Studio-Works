package com.example.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.viewpager2.screens.FirstFragment
import com.example.viewpager2.screens.FourthFragment
import com.example.viewpager2.screens.SecondFragment
import com.example.viewpager2.screens.ThirdFragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import me.relex.circleindicator.CircleIndicator3

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var tabLayout: TabLayout
//    private lateinit var indicator: CircleIndicator3
    private lateinit var wormsDotsIndicator: WormDotsIndicator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)

        val fragments : ArrayList<Fragment> = arrayListOf(FirstFragment(),SecondFragment(),ThirdFragment(),FourthFragment())
        viewPagerAdapter = ViewPagerAdapter(this, fragments)
        viewPager.adapter = viewPagerAdapter

        val tabHeaders : ArrayList<String> = arrayListOf("Ace","Jack","Queen","King")
        val tabIcons : ArrayList<Int> = arrayListOf(R.drawable.card_1,R.drawable.card_2,R.drawable.card_3,R.drawable.card_4)
        tabLayout = findViewById(R.id.tabLayout)
        TabLayoutMediator(tabLayout, viewPager){ tab, position ->
            tab.text = tabHeaders[position]
            tab.setIcon(tabIcons[position])
        }.attach()

//        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL

//        indicator = findViewById(R.id.circleIndicator)
//        indicator.setViewPager(viewPager)

        //3Dots:
        wormsDotsIndicator = findViewById(R.id.wormIndicator)
        wormsDotsIndicator.setViewPager2(viewPager)

//        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
//        viewPager.layoutDirection = ViewPager2.LAYOUT_DIRECTION_RTL
//        tabLayout.layoutDirection = View.LAYOUT_DIRECTION_LTR

    }

}