package com.example.finalproject_foodos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.finalproject_foodos.repository.AuthenticationRepository
import com.google.firebase.auth.FirebaseUser

class ProfileFragmentViewModel : ViewModel() {

    val userRepo = AuthenticationRepository()
    var firebaseUser = MutableLiveData<FirebaseUser>()
    var userStatus : MutableLiveData<Boolean>

    init {

        firebaseUser = userRepo.getFirebaseUser()
        userStatus = userRepo.userStatus()

    }

    fun firebaseSignOut(){
        userRepo.firebaseSignOut()
    }

    fun userStatus() : MutableLiveData<Boolean> {
        return userStatus
    }
}