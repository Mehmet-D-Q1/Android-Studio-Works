package com.example.finalproject_foodos.entity


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Foods(
    @SerializedName("yemek_id")
    @Expose
    val yemekId: Int,
    @SerializedName("yemek_adi")
    @Expose
    val yemekAdi: String,
    @SerializedName("yemek_resim_adi")
    @Expose
    val yemekResimAdi: String,
    @SerializedName("yemek_fiyat")
    @Expose
    val yemekFiyat: Int
) : Serializable {

}