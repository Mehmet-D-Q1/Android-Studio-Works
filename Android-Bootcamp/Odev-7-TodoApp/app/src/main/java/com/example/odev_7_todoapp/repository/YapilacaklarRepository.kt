package com.example.odev_7_todoapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.odev_7_todoapp.database.YapilacaklarDao
import com.example.odev_7_todoapp.database.YapilacaklarVeritabani
import com.example.odev_7_todoapp.entity.Yapilacaklar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class YapilacaklarRepository(var application: Application) {

    var yapilacaklarListesi : MutableLiveData<List<Yapilacaklar>>
    var veriTabani : YapilacaklarVeritabani

    init {
        yapilacaklarListesi = MutableLiveData()
        veriTabani = YapilacaklarVeritabani.veritabaniErisimNesnesi(application)!!
    }

    fun tumGorevleriGetirRepo() : MutableLiveData<List<Yapilacaklar>>{
        return yapilacaklarListesi
    }

    fun gorevEkleRepo(yapilacak_is : String) {
        CoroutineScope(Dispatchers.Main).launch {
            val eklenecekGorev = Yapilacaklar(0, yapilacak_is)
            veriTabani.yapilacaklarDao().gorevEkle(eklenecekGorev)
        }
    }

    fun gorevGuncelleRepo(yapilacak_id : Int, yapilacak_is : String) {
        CoroutineScope(Dispatchers.Main).launch {
            val guncellenecekGorev = Yapilacaklar(yapilacak_id, yapilacak_is)
            veriTabani.yapilacaklarDao().gorevGuncelle(guncellenecekGorev)
        }
    }

    fun gorevSilRepo(yapilacak_id : Int) {
        CoroutineScope(Dispatchers.Main).launch {
            val silinecekGorev = Yapilacaklar(yapilacak_id,"")
            veriTabani.yapilacaklarDao().gorevSil(silinecekGorev)
            tumGorevlerAlRepo()
        }
    }

    fun gorevAramaRepo(aramaKelimesi: String) {
        CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = veriTabani.yapilacaklarDao().gorevArama(aramaKelimesi)
        }
    }

    fun tumGorevlerAlRepo() {
        CoroutineScope(Dispatchers.Main).launch {
            yapilacaklarListesi.value = veriTabani.yapilacaklarDao().tumGorevlerAl()
        }
    }
}