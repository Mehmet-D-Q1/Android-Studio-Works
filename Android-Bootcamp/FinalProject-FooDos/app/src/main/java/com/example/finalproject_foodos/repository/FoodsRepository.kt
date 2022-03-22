package com.example.finalproject_foodos.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.finalproject_foodos.entity.*
import com.example.finalproject_foodos.retrofit.ApiUtils
import com.example.finalproject_foodos.retrofit.FoodsDaoInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FoodsRepository {

    private val foodsList : MutableLiveData<ArrayList<Foods>>
    private val foodsListInBasket : MutableLiveData<ArrayList<FoodsInBasket>>
    private val foodsDaoI : FoodsDaoInterface

    init {
        foodsList = MutableLiveData()
        foodsListInBasket = MutableLiveData()
        foodsDaoI = ApiUtils.getFoodsDaoInterface()
    }

    fun getFoods() : MutableLiveData<ArrayList<Foods>> {
        return foodsList
    }
    fun getBasketFoods() : MutableLiveData<ArrayList<FoodsInBasket>> {
        return foodsListInBasket
    }

    fun getAllFoods(){
        foodsDaoI.allFoods().enqueue(object : Callback<FoodsResponse> {
            override fun onResponse(call: Call<FoodsResponse>?, response: Response<FoodsResponse>) {
                val list = response.body()!!.yemekler
                foodsList.value = list
                Log.e("Yemekler", "${response.body()!!.success} - ${response.body()!!.yemekler}")
            }
            override fun onFailure(call: Call<FoodsResponse>?, t: Throwable?) {

            }
        })
    }

    fun addFoods(yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String){
        foodsDaoI.addFoods(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                val basari = response.body()?.success
                val mesaj = response.body()?.message
                Log.e("Yemek ekle", "$basari - $mesaj")
            }
            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {

            }
        })
    }

    fun getFoodsInBasket(kullaniciAdi : String){
        foodsDaoI.getFoodsInBasket(kullaniciAdi).enqueue(object : Callback<FoodsInBasketResponse> {
            override fun onResponse(call: Call<FoodsInBasketResponse>, response: Response<FoodsInBasketResponse>) {
                val list = response.body()!!.sepetYemekler

                if (response.isSuccessful){
                    foodsListInBasket.value = list
                    Log.e("Sepettekiler2", "${response.isSuccessful}")
                }
                Log.e("Sepettekiler2", "${response.body()?.success} - ${response.body()?.sepetYemekler}")

            }

            override fun onFailure(call: Call<FoodsInBasketResponse>?, t: Throwable?) {
                val emptyFoodList = ArrayList<FoodsInBasket>()
                    Log.e("Sepettekiler fail", "${t?.message}")
                    foodsListInBasket.value = emptyFoodList

            }
        })
    }

    fun deleteFoodsInBasket(sepetYemekId : Int, kullaniciAdi : String){
        foodsDaoI.deleteFoodsInBasket(sepetYemekId, kullaniciAdi).enqueue(object : Callback<CRUDResponse> {
            override fun onResponse(call: Call<CRUDResponse>?, response: Response<CRUDResponse>) {
                getFoodsInBasket(kullaniciAdi)

                val basari = response.body()?.success
                val mesaj = response.body()?.message
                Log.e("Yemek sil", "$basari - $mesaj")
            }
            override fun onFailure(call: Call<CRUDResponse>?, t: Throwable?) {
                Log.e("Sepettekiler", "${t?.message}")
            }
        })
    }


}