package com.example.finalproject_foodos.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.viewmodel.LoginFragmentViewModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    private lateinit var viewModel: LoginFragmentViewModel

    private lateinit var registerButtonInLogin : Button
    private lateinit var loginButtonInLogin : Button
    private lateinit var textInputLayoutUserNameInLogin : TextInputLayout
    private lateinit var textInputLayoutPasswordInLogin : TextInputLayout

    private lateinit var firebaseAuth : FirebaseAuth

//    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        // Initialize:
        firebaseAuth = Firebase.auth

        viewModel.firebaseUser.observe(viewLifecycleOwner) {
            if (it != null){
//                Toast.makeText(requireContext(), "User logged", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_loginFragment_to_profileFragment)
            }
        }

        textInputLayoutUserNameInLogin =  view.findViewById<TextInputLayout>(R.id.textInputMailInLogin)
        textInputLayoutPasswordInLogin =  view.findViewById<TextInputLayout>(R.id.textInputPasswordInLogin)



        registerButtonInLogin = view.findViewById<Button>(R.id.buttonRegisterInLogin)
        registerButtonInLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        loginButtonInLogin = view.findViewById<Button>(R.id.buttonLoginInLogin)
        loginButtonInLogin.setOnClickListener {

            val emailInput = textInputLayoutUserNameInLogin.editText?.text.toString().trim()
            val passwordInput = textInputLayoutPasswordInLogin.editText?.text.toString().trim()
            if (emailInput != "" && passwordInput != ""){
                viewModel.firebaseLogin(emailInput,passwordInput)

            }
        }


        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : LoginFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }


}