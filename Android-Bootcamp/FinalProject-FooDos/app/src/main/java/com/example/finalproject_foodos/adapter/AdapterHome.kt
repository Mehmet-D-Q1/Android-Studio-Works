package com.example.finalproject_foodos.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject_foodos.R.drawable
import com.example.finalproject_foodos.databinding.ItemCardDesignHomeBinding
import com.example.finalproject_foodos.entity.Foods
import com.example.finalproject_foodos.fragments.FoodsHomeFragmentDirections
import com.example.finalproject_foodos.retrofit.ApiUtils
import com.example.finalproject_foodos.viewmodel.FoodsHomeFragmentViewModel
import com.squareup.picasso.Picasso


class AdapterHome(var mContext: Context, var foodsList: ArrayList<Foods>, var viewModel: FoodsHomeFragmentViewModel) :
    RecyclerView.Adapter<AdapterHome.DataHomeViewHolder>() {

    inner class DataHomeViewHolder(itemCardDesignHomeBinding: ItemCardDesignHomeBinding) :
        RecyclerView.ViewHolder(itemCardDesignHomeBinding.root) {
        var itemCardDesignHomeBinding: ItemCardDesignHomeBinding

        init {
            this.itemCardDesignHomeBinding = itemCardDesignHomeBinding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHomeViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = ItemCardDesignHomeBinding.inflate(layoutInflater, parent, false)
        return DataHomeViewHolder(design)
    }

    @SuppressLint("ResourceAsColor", "ResourceType", "UseCompatLoadingForDrawables",
        "NotifyDataSetChanged"
    )
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: DataHomeViewHolder, position: Int) {

        //databinding-layout bağlantısı
        val foodItem = foodsList.get(position)
        holder.itemCardDesignHomeBinding.foodObject = foodItem

        //Picasso
        Picasso.get()
            .load(ApiUtils.BASE_URL + "yemekler/resimler/" + foodsList.get(position).yemekResimAdi) // internet path
            .placeholder(drawable.loading) // preload
            .error(drawable.server_error) // load error
            .into(holder.itemCardDesignHomeBinding.imageViewFoodImageInHome); // View

        //FoodDetailFragment'e geçiş ve data transferi:
        holder.itemCardDesignHomeBinding.itemCardHome.setOnClickListener {
            val action =
                FoodsHomeFragmentDirections.actionFoodsHomeFragmentToFoodsDetailFragment(foodItem)
            it.findNavController().navigate(action)
        }

        //favorite item click
        holder.itemCardDesignHomeBinding.buttonFavoriteInHome.setOnClickListener {


        }
    }
    override fun getItemCount(): Int {
        return foodsList.size
    }







}


