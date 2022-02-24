package com.example.odev_6_recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.odev_6_recyclerview.databinding.MainactivityRecyclerViewBinding

class UrunlerAdapter(var mContext : Context, var urunlerListesi : List<Urunler>) : RecyclerView.Adapter<UrunlerAdapter.CardTasarimTutucu>() {
    inner class CardTasarimTutucu(tasarim : MainactivityRecyclerViewBinding) :
            RecyclerView.ViewHolder(tasarim.root){
                var tasarim : MainactivityRecyclerViewBinding
                init {
                    this.tasarim = tasarim
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = MainactivityRecyclerViewBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val urun = urunlerListesi.get(position)
        val tutucu = holder.tasarim

        tutucu.imageViewUrunResim.setImageResource(mContext.resources.getIdentifier("${urun.urunResimAdi}", "drawable", mContext.packageName))
        tutucu.textViewUrunAdi.text = urun.name

    }

    override fun getItemCount(): Int {
        return urunlerListesi.size
    }

}