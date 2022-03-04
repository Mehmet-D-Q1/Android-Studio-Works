package com.example.odev_7_todoapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.odev_7_todoapp.databinding.RowItemBinding
import com.example.odev_7_todoapp.entity.Yapilacaklar
import com.example.odev_7_todoapp.view.AnaSayfaFragmentDirections
import com.example.odev_7_todoapp.viewModel.AnaSayfaFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class YapilacaklarAdapter(var mContext: Context, var yapilacaklarListesi: List<Yapilacaklar>, var viewModel: AnaSayfaFragmentViewModel) : RecyclerView.Adapter<YapilacaklarAdapter.CardTasarimTutucu>() {

    inner class CardTasarimTutucu(tasarim : RowItemBinding) : RecyclerView.ViewHolder(tasarim.root){
        var tasarim : RowItemBinding
        init {
            this.tasarim = tasarim
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CardTasarimTutucu {
        val layoutInflater = LayoutInflater.from(mContext)
        val tasarim = RowItemBinding.inflate(layoutInflater, parent, false)
        return CardTasarimTutucu(tasarim)
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        val gorev = yapilacaklarListesi.get(position)
        val t = holder.tasarim

        t.gorevNesnesi = gorev

        t.satirCard.setOnClickListener {
            val gecis = AnaSayfaFragmentDirections.detayGecis(gorev = gorev)
            Navigation.findNavController(it).navigate(gecis)
        }
        t.imageViewSilResim.setOnClickListener {
            Snackbar.make(it, "${gorev.yapilacak_id} silinsin mi?", Snackbar.LENGTH_LONG)
                .setAction("Evet"){ viewModel.sil(gorev.yapilacak_id)}
                .show()
        }
    }

    override fun getItemCount(): Int {
        return yapilacaklarListesi.size
    }

}