package com.example.finalproject_foodos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject_foodos.entity.FoodsInBasket
import com.example.finalproject_foodos.repository.FoodsRepository

class FoodsCardFragmentViewModel : ViewModel() {
    val foodsRepo = FoodsRepository()
    var foodsListInBasket = MutableLiveData<ArrayList<FoodsInBasket>>()

    init {
        getFoodsInBasketRepo("mehmet_dogan")
        foodsListInBasket = foodsRepo.getBasketFoods()

    }

    fun getFoodsInBasketRepo(kullaniciAdi : String){
        foodsRepo.getFoodsInBasket(kullaniciAdi)
    }

    fun deleteFoodsInBasketRepo(sepetYemekId : Int, kullaniciAdi : String) {
        foodsRepo.deleteFoodsInBasket(sepetYemekId, kullaniciAdi)
    }
}