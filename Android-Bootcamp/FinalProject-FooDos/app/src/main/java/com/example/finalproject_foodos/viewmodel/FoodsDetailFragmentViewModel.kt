package com.example.finalproject_foodos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.finalproject_foodos.repository.FoodsRepository

class FoodsDetailFragmentViewModel : ViewModel() {
    val foodsRepo = FoodsRepository()

    fun addFoodsRepo(yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String){
        foodsRepo.addFoods(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)
    }
}