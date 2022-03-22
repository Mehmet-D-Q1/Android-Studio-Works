package com.example.finalproject_foodos.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FoodsResponse(
    @SerializedName("yemekler") @Expose val yemekler : ArrayList<Foods>,
    @SerializedName("success") @Expose val success : Int
)
