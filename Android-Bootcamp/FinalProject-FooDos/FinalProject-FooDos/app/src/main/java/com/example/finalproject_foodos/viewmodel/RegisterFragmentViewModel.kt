package com.example.finalproject_foodos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject_foodos.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

class RegisterFragmentViewModel : ViewModel() {

    val userRepo = AuthenticationRepository()
    var firebaseUser = MutableLiveData<FirebaseUser>()

    init {

        firebaseUser = userRepo.getFirebaseUser()

    }

     fun firebaseRegister(email : String, password : String){
        userRepo.firebaseRegister(email, password)
    }

}