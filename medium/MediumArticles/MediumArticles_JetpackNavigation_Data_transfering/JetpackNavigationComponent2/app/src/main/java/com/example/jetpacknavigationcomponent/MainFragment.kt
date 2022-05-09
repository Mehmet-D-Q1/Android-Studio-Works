package com.example.jetpacknavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController

class MainFragment : Fragment() {
    private lateinit var loginButton: Button
    private lateinit var signupButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginButton = view.findViewById(R.id.loginButton)!!
        loginButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_firstFragment)
        }
        signupButton = view.findViewById(R.id.signupButton)!!
        signupButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_AFragment)


        }
    }
}