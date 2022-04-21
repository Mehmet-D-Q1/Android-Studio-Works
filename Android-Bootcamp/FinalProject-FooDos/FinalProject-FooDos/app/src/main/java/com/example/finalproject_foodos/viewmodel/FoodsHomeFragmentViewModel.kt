package com.example.finalproject_foodos.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject_foodos.entity.Foods
import com.example.finalproject_foodos.repository.FoodsRepository

class FoodsHomeFragmentViewModel(application: Application) : ViewModel() {

    var foodsList = MutableLiveData<ArrayList<Foods>>()


    val foodsRepo = FoodsRepository(application)

    init {

        loadFoods()
        foodsList = foodsRepo.getFoods()
    }

    fun loadFoods() {
        foodsRepo.getAllFoods()
    }






}