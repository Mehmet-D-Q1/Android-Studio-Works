package com.example.dogglersapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.dogglersapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setup view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Launch the VerticalListActivity on verticalBtn click
        binding.verticalBtn.setOnClickListener { launchVertical() }

        // Launch the HorizontalListActivity on horizontalBtn click
        binding.horizontalBtn.setOnClickListener { launchHorizontal() }

        // Launch the GridListActivity on gridBtn click
        binding.gridBtn.setOnClickListener { launchGrid() }
    }

    private fun launchVertical() {
        //Bir Activity‘den başka bir Activity‘e geçişi sağlayan veya bilgi aktarımını yapan bu Intent nesnesidir.
        listIntent = Intent(this, VerticalListActivity::class.java) //Bu sınıftan, VerticalListActivity.kt sınıfına geçişi sağlar.
        //startActivity() metodu ile başlatılan Activity bileşeninden herhangi bir bilgi, veri alınmaz. Sadece bizi diğer Activity ekranına götürür.
        startActivity(listIntent) //Bu intent nesnesini startActivity() metodu ile başlatırız.

    }

    private fun launchHorizontal() {
        listIntent = Intent(this, HorizontalListActivity::class.java)
        startActivity(listIntent)
    }

    private fun launchGrid() {
        listIntent = Intent(this, GridListActivity::class.java)
        startActivity(listIntent)
    }
}