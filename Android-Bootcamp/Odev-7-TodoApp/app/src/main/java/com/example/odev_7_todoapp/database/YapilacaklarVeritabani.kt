package com.example.odev_7_todoapp.database

import android.content.Context
import android.provider.CalendarContract
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.odev_7_todoapp.entity.Yapilacaklar

@Database(entities = [Yapilacaklar::class], version = 1, exportSchema = false)
abstract class YapilacaklarVeritabani : RoomDatabase() {

    abstract fun yapilacaklarDao() : YapilacaklarDao

    companion object {
        @Volatile
        private var INSTANCE : YapilacaklarVeritabani? = null
        fun veritabaniErisimNesnesi(context: Context) : YapilacaklarVeritabani {
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        YapilacaklarVeritabani::class.java,
                        "yapilacaklar.sqlite"
                    ).createFromAsset("yapilacaklar.sqlite").build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}