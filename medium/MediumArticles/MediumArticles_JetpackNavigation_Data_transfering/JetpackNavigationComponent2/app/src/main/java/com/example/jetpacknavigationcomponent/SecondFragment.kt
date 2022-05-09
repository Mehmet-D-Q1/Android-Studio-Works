package com.example.jetpacknavigationcomponent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class SecondFragment : Fragment() {

    private lateinit var buttonPasswordVerify: Button
    private lateinit var passwordText : EditText
    val safeArgs : SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataFromFirst = safeArgs.namefromFirst0

        passwordText = view.findViewById(R.id.editTextPassword)

        buttonPasswordVerify = view.findViewById(R.id.buttonPasswordVerify)!!
        buttonPasswordVerify.setOnClickListener {

            val password : Int = passwordText.text.toString().toInt()
            val action = SecondFragmentDirections.actionSecondFragmentToLastFragment(dataFromFirst,password)
            findNavController().navigate(action)
        }
    }
}