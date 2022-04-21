package com.example.finalproject_foodos.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.finalproject_foodos.entity.Favorites

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorites(favoritesFoods : Favorites)

    @Delete
    suspend fun deleteFavorites(favoritesFoods : Favorites)

    @Query("SELECT * FROM favoritelist")
    suspend fun getAllFavorites() : List<Favorites>

//    @Query("SELECT EXISTS(SELECT * FROM favoritelist WHERE yemek_id = :yemekId)")
//    suspend fun isExistWithId(yemekId : Int) : Int

    @Query("SELECT COUNT(*) FROM favoritelist")
    fun getCount(): LiveData<Int>

}

// @Insert(onConflict = OnConflictStrategy.IGNORE)