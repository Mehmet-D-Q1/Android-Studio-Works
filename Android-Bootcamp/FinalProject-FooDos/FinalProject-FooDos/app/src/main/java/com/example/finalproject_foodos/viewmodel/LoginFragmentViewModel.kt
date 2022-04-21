package com.example.finalproject_foodos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject_foodos.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

class LoginFragmentViewModel : ViewModel() {

    val userRepo = AuthenticationRepository()
    var firebaseUser = MutableLiveData<FirebaseUser>()

    init {

        firebaseUser = userRepo.getFirebaseUser()

    }

    fun firebaseLogin(email : String, password : String){
        userRepo.firebaseLogin(email, password)
    }
}