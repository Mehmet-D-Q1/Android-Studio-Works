package com.example.finalproject_foodos.retrofit

import com.example.finalproject_foodos.entity.CRUDResponse
import com.example.finalproject_foodos.entity.FoodsInBasketResponse
import com.example.finalproject_foodos.entity.FoodsResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodsDaoInterface {
//http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    @GET("yemekler/tumYemekleriGetir.php")
    fun allFoods() : Call<FoodsResponse>

    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    fun addFoods(@Field("yemek_adi") yemekAdi : String,
                 @Field("yemek_resim_adi") yemekResimAdi : String,
                 @Field("yemek_fiyat") yemekFiyat : Int,
                 @Field("yemek_siparis_adet") yemekSiparisAdet : Int,
                 @Field("kullanici_adi") kullaniciAdi : String) : Call<CRUDResponse>

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    fun getFoodsInBasket(@Field("kullanici_adi") kullaniciAdi : String) : Call<FoodsInBasketResponse>

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    fun deleteFoodsInBasket(@Field("sepet_yemek_id") sepetYemekId: Int,
                @Field("kullanici_adi") kullaniciAdi : String) : Call<CRUDResponse>

}