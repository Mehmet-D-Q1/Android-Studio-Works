package com.example.finalproject_foodos.retrofit

class ApiUtils {

    //http://kasimadalan.pe.hu/yemekler/tumYemekleriGetir.php

    companion object {
        val BASE_URL = "http://kasimadalan.pe.hu/"

        fun getFoodsDaoInterface(): FoodsDaoInterface {
            return RetrofitClient.getClient(BASE_URL).create(FoodsDaoInterface::class.java)
        }
    }
}