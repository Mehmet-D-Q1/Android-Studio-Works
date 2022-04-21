package com.example.finalproject_foodos.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject_foodos.entity.FoodsInBasket
import com.example.finalproject_foodos.repository.FoodsRepository
import com.google.firebase.auth.FirebaseAuth

class FoodsCardFragmentViewModel(application: Application) : ViewModel() {
    val foodsRepo = FoodsRepository(application)
    var foodsListInBasket = MutableLiveData<ArrayList<FoodsInBasket>>()
    private lateinit var firebaseAuth : FirebaseAuth

    init {

        firebaseAuth = FirebaseAuth.getInstance()
        getFoodsInBasketRepo(firebaseAuth.currentUser!!.email.toString())

        foodsListInBasket = foodsRepo.getBasketFoods()

    }

    fun getFoodsInBasketRepo(kullaniciAdi : String){
        foodsRepo.getFoodsInBasket(kullaniciAdi)
    }

    fun deleteFoodsInBasketRepo(sepetYemekId : Int, kullaniciAdi : String) {
        foodsRepo.deleteFoodsInBasket(sepetYemekId, kullaniciAdi)
    }
}