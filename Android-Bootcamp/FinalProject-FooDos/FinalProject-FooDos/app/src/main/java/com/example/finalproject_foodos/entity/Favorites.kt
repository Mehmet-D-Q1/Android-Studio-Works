package com.example.finalproject_foodos.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "favoritelist")
data class Favorites(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "yemek_id") @NotNull val yemekId: Int,

    @ColumnInfo(name = "yemek_adi") @NotNull val yemekAdi: String,

    @ColumnInfo(name = "yemek_resim_adi") @NotNull val yemekResimAdi: String,

    @ColumnInfo(name = "yemek_fiyat") @NotNull val yemekFiyat: Int,

    @ColumnInfo(name = "is_favorite") @NotNull val isFav: Int ) : Serializable {
}