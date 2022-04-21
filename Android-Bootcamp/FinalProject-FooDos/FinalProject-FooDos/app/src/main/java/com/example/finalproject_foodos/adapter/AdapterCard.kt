package com.example.finalproject_foodos.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.databinding.ItemCardDesignCardBinding
import com.example.finalproject_foodos.entity.FoodsInBasket
import com.example.finalproject_foodos.retrofit.ApiUtils
import com.example.finalproject_foodos.viewmodel.FoodsCardFragmentViewModel
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class AdapterCard(
    var mContext: Context,
    var foodsListInBasket: ArrayList<FoodsInBasket>,
    var viewModel: FoodsCardFragmentViewModel
) :
    RecyclerView.Adapter<AdapterCard.DataCardViewHolder>() {

    inner class DataCardViewHolder(itemCardDesignCardBinding: ItemCardDesignCardBinding) :
        RecyclerView.ViewHolder(itemCardDesignCardBinding.root) {
        var itemCardDesignCardBinding: ItemCardDesignCardBinding


        init {
            this.itemCardDesignCardBinding = itemCardDesignCardBinding

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataCardViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = ItemCardDesignCardBinding.inflate(layoutInflater, parent, false)
        return DataCardViewHolder(design)
    }

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: DataCardViewHolder, position: Int) {

        //databinding-layout bağlantısı
        val foodItem = foodsListInBasket.get(position)
        holder.itemCardDesignCardBinding.foodObjectInCard = foodItem


        //Picasso
        Picasso.get()
            .load(ApiUtils.BASE_URL + "yemekler/resimler/" + foodsListInBasket.get(position).yemekResimAdi) // internet path
//            .placeholder(R.drawable.loading) // preload
            .error(R.drawable.server_error) // load error
            .into(holder.itemCardDesignCardBinding.imageViewFoodImageInCard); // View


        //Card food delete
        holder.itemCardDesignCardBinding.buttonDeleteInCard.setOnClickListener {
                Snackbar.make(it, "Do you want to delete the selected product?", Snackbar.LENGTH_SHORT)
                    .setAction("Yes") {
                        viewModel.deleteFoodsInBasketRepo(
                            foodItem.sepetYemekId,
                            foodItem.kullaniciAdi
                        )



                        Snackbar.make(it, "The product has been deleted from the cart!", Snackbar.LENGTH_SHORT)
                            .setBackgroundTint(mContext.getColor(R.color.backgroundTint))
                            .setActionTextColor(mContext.getColor(R.color.actionTextColor))
                            .show()
                    }
                    .setBackgroundTint(mContext.getColor(R.color.backgroundTint))
                    .setActionTextColor(mContext.getColor(R.color.actionTextColor))
                    .show()
        }

    }


    override fun getItemCount(): Int {
        return foodsListInBasket.size

    }


    //Calculate Total cards prices
    fun CalculateTotalCardPrice() : Int {
        var total = 0
        for(index in foodsListInBasket){
            total += index.yemekSiparisAdet*index.yemekFiyat
        }
        val cardItemTotalPrice: Int = total
        return cardItemTotalPrice
    }



}


