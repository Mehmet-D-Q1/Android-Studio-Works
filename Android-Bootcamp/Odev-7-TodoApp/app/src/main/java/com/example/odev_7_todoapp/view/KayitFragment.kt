package com.example.odev_7_todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.kisileruygulamasi.viewmodel.KayitFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.KayitVMF
import com.example.odev_7_todoapp.R
import com.example.odev_7_todoapp.databinding.FragmentKayitBinding


class KayitFragment : Fragment() {

    private lateinit var tasarim : FragmentKayitBinding
    private lateinit var viewModel : KayitFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater, R.layout.fragment_kayit, container, false)
        tasarim.kayitFragment = this
        tasarim.kayitToolbarBaslik = "Görev Kayıt"
        return tasarim.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : KayitFragmentViewModel by viewModels(){
            KayitVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    fun buttonKaydetTikla(yapilacak_is : String){
        viewModel.kayit(yapilacak_is)
    }

}