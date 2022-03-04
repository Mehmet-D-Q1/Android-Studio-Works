package com.example.odev_7_todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.odev_7_todoapp.R
import com.example.odev_7_todoapp.databinding.FragmentDetayBinding
import com.example.odev_7_todoapp.viewModel.DetayFragmentViewModel
import com.example.odev_7_todoapp.viewModel.DetayVMF


class DetayFragment : Fragment() {

    private lateinit var tasarim : FragmentDetayBinding
    private lateinit var viewModel : DetayFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_detay, container, false)
        tasarim.detayFragment = this
        tasarim.detayToolbarBaslik = "Gorev Detay"

        val bundle : DetayFragmentArgs by navArgs()
        val gelenGorev = bundle.gorev

        tasarim.gorevNesnesi = gelenGorev
        return tasarim.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetayFragmentViewModel by viewModels(){
            DetayVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun buttonGuncelleTikla(yapilacak_id : Int, yapilacak_is : String){
        viewModel.guncelle(yapilacak_id, yapilacak_is )
    }

}