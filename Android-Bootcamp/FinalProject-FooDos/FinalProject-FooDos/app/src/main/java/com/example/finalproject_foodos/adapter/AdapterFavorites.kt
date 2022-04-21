package com.example.finalproject_foodos.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject_foodos.R.drawable
import com.example.finalproject_foodos.databinding.ItemCardDesignFavoritesBinding
import com.example.finalproject_foodos.entity.Favorites
import com.example.finalproject_foodos.retrofit.ApiUtils
import com.example.kisileruygulamasi.viewmodel.FavFoodsFragmentViewModel
import com.google.android.material.chip.Chip
import com.squareup.picasso.Picasso


class AdapterFavorites(var mContext: Context, var favoritesList: List<Favorites>, var viewModel: FavFoodsFragmentViewModel) :
    RecyclerView.Adapter<AdapterFavorites.DataFavoritesViewHolder>() {

    var favoritedItemList : ArrayList<Favorites> = ArrayList()

    inner class DataFavoritesViewHolder(itemCardDesignFavoritesBinding: ItemCardDesignFavoritesBinding) :
        RecyclerView.ViewHolder(itemCardDesignFavoritesBinding.root) {
        var itemCardDesignFavoritesBinding: ItemCardDesignFavoritesBinding
        var chipIcon : Chip = itemCardDesignFavoritesBinding.buttonFavoriteInFavorite


        init {
           this.itemCardDesignFavoritesBinding = itemCardDesignFavoritesBinding
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataFavoritesViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val design = ItemCardDesignFavoritesBinding.inflate(layoutInflater, parent, false)

        return DataFavoritesViewHolder(design)
    }

    @SuppressLint("ResourceAsColor", "ResourceType", "UseCompatLoadingForDrawables",
        "NotifyDataSetChanged"
    )
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: DataFavoritesViewHolder, position: Int) {

        val holderItemPosition = favoritesList.get(holder.adapterPosition)

        //databinding-layout bağlantısı
        val favoritesItem = favoritesList.get(position)
        holder.itemCardDesignFavoritesBinding.foodObjectInFavorite = favoritesItem

        //Picasso
        Picasso.get()
            .load(ApiUtils.BASE_URL + "yemekler/resimler/" + favoritesList.get(position).yemekResimAdi) // internet path
//            .placeholder(drawable.loading) // preload
            .error(drawable.server_error) // load error
            .into(holder.itemCardDesignFavoritesBinding.imageViewFoodImageInFavorite) // View

        if (holderItemPosition.isFav == 1){
            holder.chipIcon.setChipIconResource(drawable.favorite_icon_fill)
        }

        holder.chipIcon.setOnClickListener {

            if (holderItemPosition.isFav == 1){
                favoritedItemList.add(holderItemPosition)

                viewModel.deleteFavFoods(holderItemPosition.yemekId,holderItemPosition.yemekAdi,holderItemPosition.yemekResimAdi,holderItemPosition.yemekFiyat,holderItemPosition.isFav)
                notifyItemChanged(position)
                notifyDataSetChanged()
                viewModel.getAllFavorites()
                viewModel.favRepo.getFavFoods()

            }

        }


    }
    override fun getItemCount(): Int {
        return favoritesList.size
    }



}


