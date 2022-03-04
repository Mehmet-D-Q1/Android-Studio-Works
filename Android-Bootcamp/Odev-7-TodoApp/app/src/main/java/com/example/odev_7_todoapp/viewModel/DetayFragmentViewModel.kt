package com.example.odev_7_todoapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.odev_7_todoapp.repository.YapilacaklarRepository

class DetayFragmentViewModel(application: Application) : AndroidViewModel(application) {
    val grepo = YapilacaklarRepository(application)

    fun guncelle(yapilacak_id : Int, yapilacak_is : String){
        grepo.gorevGuncelleRepo(yapilacak_id, yapilacak_is)
    }
}