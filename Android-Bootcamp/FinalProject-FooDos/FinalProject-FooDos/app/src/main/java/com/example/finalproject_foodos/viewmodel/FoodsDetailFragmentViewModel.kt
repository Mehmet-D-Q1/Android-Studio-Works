package com.example.finalproject_foodos.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.finalproject_foodos.repository.FoodsRepository

class FoodsDetailFragmentViewModel(application: Application) : ViewModel() {
    val foodsRepo = FoodsRepository(application)

    fun addFoodsRepo(yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String){
        foodsRepo.addFoods(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
    }
}