package com.example.finalproject_foodos.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finalproject_foodos.entity.Favorites

@Database(entities = [Favorites::class], version = 1, exportSchema = false)
abstract class FavoritesDatabase : RoomDatabase() {

    abstract fun favoritesDao() : FavoritesDao

    companion object {
        @Volatile
        private var INSTANCE : FavoritesDatabase? = null
        fun veritabaniErisimNesnesi(context: Context) : FavoritesDatabase {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoritesDatabase::class.java,
                        "favorites_database"
                    ).createFromAsset("favorites_database.sqlite").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}