package com.example.kisileruygulamasi.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.finalproject_foodos.viewmodel.FoodsDetailFragmentViewModel

class DetailFoodsVMFactory (var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return FoodsDetailFragmentViewModel(application) as T

    }
}