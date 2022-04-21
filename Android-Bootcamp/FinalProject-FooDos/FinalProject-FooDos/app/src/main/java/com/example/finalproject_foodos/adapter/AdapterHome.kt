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
import com.example.kisileruygulamasi.viewmodel.FavFoodsFragmentViewModel
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso


class AdapterHome(
    var mContext: Context,
    var foodsList: ArrayList<Foods>,
    var viewModel: FoodsHomeFragmentViewModel,
    var viewModel2: FavFoodsFragmentViewModel
) :
    RecyclerView.Adapter<AdapterHome.DataHomeViewHolder>() {


    inner class DataHomeViewHolder(itemCardDesignHomeBinding: ItemCardDesignHomeBinding) :
        RecyclerView.ViewHolder(itemCardDesignHomeBinding.root) {
        var itemCardDesignHomeBinding: ItemCardDesignHomeBinding
        var chipIcon: Chip = itemCardDesignHomeBinding.buttonFavoriteInHome

        init {
            this.itemCardDesignHomeBinding = itemCardDesignHomeBinding

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataHomeViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)

        val design = ItemCardDesignHomeBinding.inflate(layoutInflater, parent, false)
        return DataHomeViewHolder(design)
    }


    @SuppressLint("ResourceAsColor", "ResourceType", "NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: DataHomeViewHolder, position: Int) {

        val holderItemPosition = foodsList.get(holder.adapterPosition)

        //databinding-layout bağlantısı
        val foodItem = foodsList.get(position)
        holder.itemCardDesignHomeBinding.foodObject = foodItem

        //Picasso
        Picasso.get()
            .load(ApiUtils.BASE_URL + "yemekler/resimler/" + foodsList.get(position).yemekResimAdi) // internet path
//            .placeholder(drawable.loading) // preload
            .error(drawable.server_error) // load error
            .into(holder.itemCardDesignHomeBinding.imageViewFoodImageInHome) // View

        //FoodDetailFragment'e geçiş ve data transferi:
        holder.itemCardDesignHomeBinding.itemCardHome.setOnClickListener {
            val action =
                FoodsHomeFragmentDirections.actionFoodsHomeFragmentToFoodsDetailFragment(foodItem)
            it.findNavController().navigate(action)
        }


        if (!viewModel2.favFoodsList.value.isNullOrEmpty()) {
            for (item in viewModel2.favFoodsList.value!!) {
                if (item.yemekId == holderItemPosition.yemekId) {
                    holder.chipIcon.setChipIconResource(drawable.favorite_icon_fill)
                    break
                }
                else {
                    holder.chipIcon.setChipIconResource(drawable.favorite_icon_outline)
                }
            }
        }

        var aa = false
        holder.chipIcon.setOnClickListener {



            for (item in viewModel2.favFoodsList.value!!) {
                if (item.yemekId == holderItemPosition.yemekId) {
                    aa = true
                    break
                }
                aa = false

            }




            if (aa == true) {
                viewModel2.deleteFavFoods(
                    holderItemPosition.yemekId,
                    holderItemPosition.yemekAdi,
                    holderItemPosition.yemekResimAdi,
                    holderItemPosition.yemekFiyat,
                    1
                )
                holder.chipIcon.setChipIconResource(drawable.favorite_icon_outline)
                viewModel2.getAllFavorites()
            } else if(aa == false) {
                viewModel2.addFavFoods(
                    holderItemPosition.yemekId,
                    holderItemPosition.yemekAdi,
                    holderItemPosition.yemekResimAdi,
                    holderItemPosition.yemekFiyat,
                    1
                )
                holder.chipIcon.setChipIconResource(drawable.favorite_icon_fill)
                viewModel2.getAllFavorites()

            }

        }
    }

        override fun getItemCount(): Int {
            return foodsList.size
        }




    }




