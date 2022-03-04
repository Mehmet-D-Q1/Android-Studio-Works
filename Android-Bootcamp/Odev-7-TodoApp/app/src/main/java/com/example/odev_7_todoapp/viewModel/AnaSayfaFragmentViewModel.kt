package com.example.odev_7_todoapp.viewModel

import android.app.Application
import androidx.lifecycle.*
import com.example.odev_7_todoapp.entity.Yapilacaklar
import com.example.odev_7_todoapp.repository.YapilacaklarRepository


class AnaSayfaFragmentViewModel(application: Application) : AndroidViewModel(application) {
    var yapilacaklarListesi = MutableLiveData<List<Yapilacaklar>>()

    val grepo = YapilacaklarRepository(application)

    init {
        gorevleriYukle()
        yapilacaklarListesi = grepo.tumGorevleriGetirRepo()
    }

    fun ara(aramaKelimesi : String){
        grepo.gorevAramaRepo(aramaKelimesi)
    }

    fun sil(yapilacak_id: Int){
        grepo.gorevSilRepo(yapilacak_id)
    }

    fun gorevleriYukle(){
        grepo.tumGorevlerAlRepo()
    }





}