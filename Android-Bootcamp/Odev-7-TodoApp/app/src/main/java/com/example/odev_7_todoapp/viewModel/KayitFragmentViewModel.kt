package com.example.kisileruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.odev_7_todoapp.repository.YapilacaklarRepository

class KayitFragmentViewModel(application: Application) : AndroidViewModel(application){
    val grepo = YapilacaklarRepository(application)
    fun kayit(yapilacak_is : String){
        grepo.gorevEkleRepo(yapilacak_is)
    }
}