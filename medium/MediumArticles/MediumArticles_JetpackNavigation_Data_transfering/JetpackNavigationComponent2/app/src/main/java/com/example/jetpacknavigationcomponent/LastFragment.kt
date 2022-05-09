package com.example.jetpacknavigationcomponent

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.navArgs


class LastFragment : Fragment() {

    private lateinit var nameTextResult : TextView
    private lateinit var passwordTextResult : TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_last, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val safeArgs : LastFragmentArgs by navArgs()

        nameTextResult = view.findViewById(R.id.textViewResultName)
        passwordTextResult = view.findViewById(R.id.textViewResultPassword)

        nameTextResult.text = safeArgs.nameFromFirst
        passwordTextResult.text = safeArgs.passwordFromSecond.toString()

    }
}