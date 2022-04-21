package com.example.kisileruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject_foodos.viewmodel.FoodsHomeFragmentViewModel

class HomeFoodsVMFactory (var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodsHomeFragmentViewModel(application) as T

    }
}