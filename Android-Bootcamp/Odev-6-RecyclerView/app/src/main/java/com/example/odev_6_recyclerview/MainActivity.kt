package com.example.odev_6_recyclerview

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.e
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.children
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.odev_6_recyclerview.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.chip.Chip
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var tasarim : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tasarim = ActivityMainBinding.inflate(layoutInflater)
        setContentView(tasarim.root)

        //BottomNavigation iconlarının orjinal renklerini kullanmak için:
        tasarim.bottomNavView.itemIconTintList = null

        tasarim.rv.layoutManager = StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL)

        val urunlerListesi = ArrayList<Urunler>()
        var urun1 = Urunler("Et, Balık, Şarküteri","card_1")
        var urun2 = Urunler("Meyve ve Sebze","card_2")
        var urun3 = Urunler("Kahvaltılık ve Süt","card_3")
        var urun4 = Urunler("Temel Gıda","card_4")
        var urun5 = Urunler("Fırın","card_5")
        var urun6 = Urunler("Organik ve Diyet","card_6")
        var urun7 = Urunler("Pratik Yemekler","card_7")
        var urun8 = Urunler("Atıştırmalık","card_8")
        var urun9 = Urunler("Tatlı","card_9")
        var urun10 = Urunler("Dondurma","card_10")
        var urun11 = Urunler("Su","card_11")
        var urun12 = Urunler("İçecek","card_12")
        var urun13 = Urunler("Temizlik","card_13")
        var urun14 = Urunler("Kişisel Bakım","card_14")
        var urun15 = Urunler("Sağlık ve Makyaj ","card_15")
        var urun16 = Urunler("Bebek","card_16")
        var urun17 = Urunler("Ev, Yaşam","card_17")
        var urun18 = Urunler("Fırsat Ürünleri","card_18")


        urunlerListesi.add(urun1)
        urunlerListesi.add(urun2)
        urunlerListesi.add(urun3)
        urunlerListesi.add(urun4)
        urunlerListesi.add(urun5)
        urunlerListesi.add(urun6)
        urunlerListesi.add(urun7)
        urunlerListesi.add(urun8)
        urunlerListesi.add(urun9)
        urunlerListesi.add(urun10)
        urunlerListesi.add(urun11)
        urunlerListesi.add(urun12)
        urunlerListesi.add(urun13)
        urunlerListesi.add(urun14)
        urunlerListesi.add(urun15)
        urunlerListesi.add(urun16)
        urunlerListesi.add(urun17)
        urunlerListesi.add(urun18)

        val adapter = UrunlerAdapter(this, urunlerListesi)
        tasarim.rv.adapter = adapter


    }
}