package com.example.finalproject_foodos.authentication

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.viewmodel.RegisterFragmentViewModel
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern


class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterFragmentViewModel

    private lateinit var registerButtonInRegister : Button
    private lateinit var loginButtonInRegister : Button
    private lateinit var textInputLayoutUserNameInRegister : TextInputLayout
    private lateinit var textInputLayoutMailInRegister : TextInputLayout
    private lateinit var textInputLayoutPasswordInRegister : TextInputLayout
    private lateinit var textInputLayoutConfPasswordInRegister : TextInputLayout

//    private lateinit var firebaseAuth: FirebaseAuth


    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_register, container, false)


        viewModel.firebaseUser.observe(viewLifecycleOwner) {
            if (it != null){
//                Toast.makeText(requireContext(), "User created", Toast.LENGTH_SHORT).show()
            }
        }

        textInputLayoutUserNameInRegister =  view.findViewById<TextInputLayout>(R.id.textInputUserNameInRegister)
        textInputLayoutMailInRegister =  view.findViewById<TextInputLayout>(R.id.textInputMailInRegister)
        textInputLayoutPasswordInRegister =  view.findViewById<TextInputLayout>(R.id.textInputPasswordInRegister)
        textInputLayoutConfPasswordInRegister =  view.findViewById<TextInputLayout>(R.id.textInputConfirmPasswordInRegister)

        registerButtonInRegister = view.findViewById<Button>(R.id.buttonRegisterInRegister)
        registerButtonInRegister.setOnClickListener {

            validateRegisterForm()

        }

        loginButtonInRegister = view.findViewById<Button>(R.id.buttonLoginInRegister)
        loginButtonInRegister.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        return view
    }

    private fun validateRegisterForm(){
        val userNameInRegister = textInputLayoutUserNameInRegister.editText?.text
        val mailInRegister = textInputLayoutMailInRegister.editText?.text
        val passwordInRegister = textInputLayoutPasswordInRegister.editText?.text
        val confPasswordInRegister = textInputLayoutConfPasswordInRegister.editText?.text

        when {
            userNameInRegister.toString().trim().isNullOrEmpty() -> {
                textInputLayoutUserNameInRegister.editText?.setError("Please enter a valid username",null)
            }
            mailInRegister.toString().trim().isNullOrEmpty() -> {
                textInputLayoutMailInRegister.editText?.setError("Please enter a valid mail",null)
            }
            passwordInRegister.toString().trim().isNullOrEmpty() -> {
                textInputLayoutPasswordInRegister.editText?.setError("Please enter a valid password",null)

            }
            confPasswordInRegister.toString().trim().isNullOrEmpty() -> {
                textInputLayoutConfPasswordInRegister.editText?.setError("Please enter a valid password",null)

            }

            userNameInRegister!!.isNotEmpty() &&
                    mailInRegister!!.isNotEmpty() &&
                    passwordInRegister!!.isNotEmpty() &&
                    confPasswordInRegister!!.isNotEmpty() -> {
                        if (emailValidateInRegister(mailInRegister.toString().trim())){
                            if (passwordInRegister.toString().length > 6){

                                if (passwordInRegister.toString() == confPasswordInRegister.toString()){

                                    viewModel.firebaseRegister(mailInRegister.toString(), passwordInRegister.toString())
//                                    Toast.makeText(context,"Register Successfully", Toast.LENGTH_SHORT).show()
                                }else {
                                    textInputLayoutConfPasswordInRegister.editText!!.setError("Password didn't match",null)

                                }
                            }else{
                                textInputLayoutPasswordInRegister.editText!!.setError("Please enter a password greater than 6 digits",null)
                                textInputLayoutPasswordInRegister.isEndIconVisible = true
                            }

                        }else {
                            textInputLayoutMailInRegister.editText!!.setError("Please enter a valid mail address",null)
                        }
                    }
        }
    }

    private fun emailValidateInRegister(email: CharSequence) : Boolean{
        var isValid = true
        val expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(email)
        if (!matcher.matches()) {
            isValid = false
        }
        return isValid
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : RegisterFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
    }



}