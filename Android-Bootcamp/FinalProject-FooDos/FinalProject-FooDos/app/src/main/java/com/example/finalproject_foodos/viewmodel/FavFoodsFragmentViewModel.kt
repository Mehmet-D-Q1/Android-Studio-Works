package com.example.kisileruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalproject_foodos.entity.Favorites
import com.example.finalproject_foodos.repository.FavoritesFoodsRepository


class FavFoodsFragmentViewModel(application: Application) : AndroidViewModel(application){
    var favFoodsList = MutableLiveData<List<Favorites>>()


    val favRepo = FavoritesFoodsRepository(application)

    init {
        getAllFavorites()
        favFoodsList = favRepo.getFavFoods()

    }


    fun addFavFoods(yemekId : Int, yemekAdi : String, yemekResimAdi: String, yemekFiyat : Int, isFav: Int){
        favRepo.addFavRepo(yemekId, yemekAdi, yemekResimAdi, yemekFiyat, isFav)
    }

    fun deleteFavFoods(yemekId : Int, yemekAdi : String, yemekResimAdi: String, yemekFiyat : Int, isFav: Int){
        favRepo.deleteFavRepo(yemekId, yemekAdi, yemekResimAdi, yemekFiyat, isFav)
    }

    fun getAllFavorites(){
        favRepo.getAllFavRepo()
    }

    fun getCount(): LiveData<Int> {
        return favRepo.getCount()
    }



}