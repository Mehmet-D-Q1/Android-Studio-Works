package com.example.finalproject_foodos.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CRUDResponse(
    @SerializedName("success") @Expose val success : Int,
    @SerializedName("message") @Expose val message : String
)
