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
    //private val userInfos : MutableLiveData<ArrayList<FirebaseUserInfos>>

    init {
        // Initialize:
        firebaseAuth = Firebase.auth
        firebaseUser = MutableLiveData()
        userStatus = MutableLiveData()
        //userInfos = MutableLiveData()

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
//                    Toast.makeText(application,"Successfully Register", Toast.LENGTH_SHORT).show()
                }else {
//                    Toast.makeText(application, "Registration Failed : ${it.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun firebaseLogin(email : String, password : String) {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{ it ->

                    if (it.isSuccessful){
                        firebaseUser.value = firebaseAuth.currentUser

//                    Toast.makeText(application, "Successfully Login", Toast.LENGTH_LONG).show()
                    }else {
//                    Toast.makeText(application, "Login Failed : ${it.exception?.message}", Toast.LENGTH_LONG).show()
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