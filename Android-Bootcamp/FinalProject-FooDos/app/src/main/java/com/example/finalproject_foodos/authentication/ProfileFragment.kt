package com.example.finalproject_foodos.authentication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.viewmodel.ProfileFragmentViewModel


class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileFragmentViewModel

    private lateinit var buttonlogoutInProfile : Button
    private lateinit var textViewMailInProfile : TextView
    private lateinit var textViewPasswordInProfile : TextView
//    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        textViewMailInProfile =  view.findViewById<TextView>(R.id.textViewMailInProfile)
        textViewPasswordInProfile =  view.findViewById<TextView>(R.id.textViewPasswordInProfile)

        viewModel.firebaseUser.observe(viewLifecycleOwner) {

            if (it != null){
//                Toast.makeText(requireContext(), "User Sign in", Toast.LENGTH_SHORT).show()

                textViewMailInProfile.text = it.email
                textViewPasswordInProfile.text = it.uid

            }
        }

        viewModel.userStatus().observe(viewLifecycleOwner) {
            if (it == true){//çıkış yapmışsa
                findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
            }
        }

        buttonlogoutInProfile = view.findViewById<Button>(R.id.buttonLogoutInProfile)
        buttonlogoutInProfile.setOnClickListener {
            viewModel.firebaseSignOut()
//            findNavController().navigate(R.id.action_profileFragment_to_loginFragment)
        }


        return view

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : ProfileFragmentViewModel by viewModels()
        this.viewModel = tempViewModel


    }




}