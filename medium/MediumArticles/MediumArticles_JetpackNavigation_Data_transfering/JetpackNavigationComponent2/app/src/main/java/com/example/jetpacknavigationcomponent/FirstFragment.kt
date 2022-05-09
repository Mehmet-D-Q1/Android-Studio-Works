package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs


class FirstFragment : Fragment() {

    private lateinit var buttonLogin: Button
    private lateinit var nameText : EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nameText = view.findViewById(R.id.editTextName)

        buttonLogin = view.findViewById(R.id.buttonLogin)!!
        buttonLogin.setOnClickListener {

            val amount = nameText.text.toString()
            val action = FirstFragmentDirections.actionFirstFragmentToSecondFragment(amount)
            findNavController().navigate(action)
        }

    }

}