package com.example.finalproject_foodos.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodsInBasketResponse(
    @SerializedName("sepet_yemekler") @Expose val sepetYemekler : ArrayList<FoodsInBasket>,
    @SerializedName("success") @Expose val success : Int
)
