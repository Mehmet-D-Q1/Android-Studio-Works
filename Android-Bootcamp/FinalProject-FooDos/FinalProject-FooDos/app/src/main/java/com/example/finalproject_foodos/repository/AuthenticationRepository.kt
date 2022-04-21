package com.example.finalproject_foodos.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AuthenticationRepository {

    private lateinit var application : Application
    private  var firebaseAuth : FirebaseAuth
    private val firebaseUser : MutableLiveData<FirebaseUser>
    private var userStatus : MutableLiveData<Boolean>


    init {
        // Initialize:
        firebaseAuth = Firebase.auth
        firebaseUser = MutableLiveData()
        userStatus = MutableLiveData()


        currentChecker()
    }

    fun currentChecker(){
        if (firebaseAuth.currentUser != null){
            firebaseUser.value = firebaseAuth.currentUser
            userStatus.value = false
        }
    }


    fun getFirebaseUser() : MutableLiveData<FirebaseUser> {
        return firebaseUser
    }


    //Create an account
     fun firebaseRegister(email : String, password : String) {
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { it ->
                if (it.isSuccessful){
                    firebaseUser.value = firebaseAuth.currentUser

                }else {

                }
            }
    }

    fun firebaseLogin(email : String, password : String) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ it ->

                    if (it.isSuccessful){
                        firebaseUser.value = firebaseAuth.currentUser

                    }else {

                    }


        }
    }

    fun firebaseSignOut(){
        firebaseAuth.signOut()
        userStatus.value = true
    }

    fun userStatus () : MutableLiveData<Boolean> {
        return userStatus
    }


}