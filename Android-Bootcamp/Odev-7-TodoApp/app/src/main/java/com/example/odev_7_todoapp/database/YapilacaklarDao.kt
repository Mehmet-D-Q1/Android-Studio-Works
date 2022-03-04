package com.example.odev_7_todoapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.odev_7_todoapp.entity.Yapilacaklar

@Dao
interface YapilacaklarDao {
    @Insert
    suspend fun gorevEkle(yapilacakGorev : Yapilacaklar)

    @Update
    suspend fun gorevGuncelle(yapilacakGorev : Yapilacaklar)

    @Delete
    suspend fun gorevSil(yapilacakGorev : Yapilacaklar)

    @Query("SELECT*FROM yapilacaklar_veri_tablosu WHERE yapilacak_is like '%'|| :aramaKelimesi ||'%'")
    suspend fun gorevArama(aramaKelimesi : String) : List<Yapilacaklar>

    @Query("SELECT*FROM yapilacaklar_veri_tablosu")
    suspend fun tumGorevlerAl() : List<Yapilacaklar>
}