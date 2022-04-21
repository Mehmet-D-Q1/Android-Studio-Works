package com.example.finalproject_foodos.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalproject_foodos.entity.Favorites
import com.example.finalproject_foodos.room.FavoritesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoritesFoodsRepository(application: Application) {

    private var favFoodsList : MutableLiveData<List<Favorites>>



    private val favDb : FavoritesDatabase

    init {
        favFoodsList = MutableLiveData()


        favDb = FavoritesDatabase.veritabaniErisimNesnesi(application)
    }

    fun getFavFoods() : MutableLiveData<List<Favorites>> {
        return favFoodsList
    }

    fun addFavRepo(yemekId : Int, yemekAdi : String, yemekResimAdi: String, yemekFiyat : Int, isFav: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val addFav = Favorites(yemekId, yemekAdi, yemekResimAdi, yemekFiyat, isFav)
            favDb.favoritesDao().addFavorites(addFav)
        }
    }

    fun deleteFavRepo(yemekId : Int, yemekAdi : String, yemekResimAdi: String, yemekFiyat : Int, isFav: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val deleteFav = Favorites(yemekId, yemekAdi, yemekResimAdi, yemekFiyat, isFav)
            favDb.favoritesDao().deleteFavorites(deleteFav)
        }
    }


    fun getAllFavRepo(){
        CoroutineScope(Dispatchers.Main).launch {
            favFoodsList.value = favDb.favoritesDao().getAllFavorites()
        }
    }

    fun getCount(): LiveData<Int> {
        return favDb.favoritesDao().getCount()
    }



}