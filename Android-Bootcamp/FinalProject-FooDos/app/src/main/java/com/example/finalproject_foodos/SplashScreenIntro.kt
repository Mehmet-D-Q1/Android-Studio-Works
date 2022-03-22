package com.example.finalproject_foodos

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


@SuppressLint("CustomSplashScreen")
class SplashScreenIntro : Fragment() {

    lateinit var rotateAnimation : Animation
    lateinit var translateAnimation : Animation
    lateinit var translateText1: TextView
    lateinit var translateText2: TextView
    lateinit var rotateImage : ImageView

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?) : View? {

        val view = inflater.inflate(R.layout.splash_screen_intro, container, false)

        // Initialize Firebase Auth
        firebaseAuth = Firebase.auth

        rotateImage = view.findViewById(R.id.imageViewSplash)
        translateText1= view.findViewById(R.id.textViewFoodos)
        translateText2= view.findViewById(R.id.textViewWelcome)
        rotateAnimation()

        Handler().postDelayed({

            // kullanıcı eğer loginse login ekranına geçmesine gerek yok direk profil ekranına geçecek
            val currentUser = firebaseAuth.currentUser
            if(currentUser != null){
                findNavController().navigate(R.id.action_splashScreenIntro_to_profileFragment)
            } else {
                findNavController().navigate(R.id.action_splashScreenIntro_to_loginFragment)
            }

//            findNavController().navigate(R.id.action_splashScreenIntro_to_loginFragment)
        },4000)

        return view

    }

   private fun rotateAnimation() {
       rotateAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.rotate)
       translateAnimation = AnimationUtils.loadAnimation(requireContext(), R.anim.texttranslate)
       rotateImage.startAnimation(rotateAnimation)
       translateText1.startAnimation(translateAnimation)
       translateText2.startAnimation(translateAnimation)

   }





}