package com.example.finalproject_foodos.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.databinding.FragmentFoodsDetailBinding
import com.example.finalproject_foodos.retrofit.ApiUtils
import com.example.finalproject_foodos.viewmodel.FoodsDetailFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.DetailFoodsVMFactory
import com.example.kisileruygulamasi.viewmodel.FavFoodsVMFactory
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.squareup.picasso.Picasso


class FoodsDetailFragment : Fragment() {

    lateinit var dBinding: FragmentFoodsDetailBinding
    lateinit var viewModel: FoodsDetailFragmentViewModel
    private lateinit var firebaseAuth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_foods_detail, container, false)
        dBinding.foodsDetailFragment = this

        navigateFromDetailToHomeFragment()
        addButtonInDetail ()
        substractButtonInDetail ()

        val safeArgsData: FoodsDetailFragmentArgs by navArgs()

        firebaseAuth = FirebaseAuth.getInstance()
        dBinding.userName = firebaseAuth.currentUser!!.email.toString()

        dBinding.foodDetailObject = safeArgsData.foodObjectFromHome

        //Picasso
        Picasso.get()
            .load(ApiUtils.BASE_URL + "yemekler/resimler/" + safeArgsData.foodObjectFromHome.yemekResimAdi) // internet path
            .placeholder(R.drawable.loading) // preload
            .error(R.drawable.server_error) // load error
            .into(dBinding.imageViewFoodImageInDetail); // View

        return dBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodsDetailFragmentViewModel by viewModels(){
            DetailFoodsVMFactory(requireActivity().application)
        }
        this.viewModel = tempViewModel
    }

    fun navigateFromDetailToHomeFragment() {
        dBinding.toolbarDetail.setOnMenuItemClickListener {
            if (it.itemId == R.id.upp_bar_back) {
                findNavController().navigate(R.id.action_foodsDetailFragment_to_foodsHomeFragment)
            }
            true
        }
    }

    fun addButtonInDetail () {
        dBinding.buttonAddInDetail.setOnClickListener {
            val valueQuantity = dBinding.textViewQuantityInDetail.text
            var valueIntQuantity = Integer.parseInt(valueQuantity.toString())

            valueIntQuantity++

            dBinding.textViewQuantityInDetail.setText(valueIntQuantity.toString())
        }
    }

    fun substractButtonInDetail () {
        dBinding.buttonSubtractInDetail.setOnClickListener {
            val valueQuantity = dBinding.textViewQuantityInDetail.text
            var valueIntQuantity = Integer.parseInt(valueQuantity.toString())
            if (valueIntQuantity >= 2) {

                valueIntQuantity--

                dBinding.textViewQuantityInDetail.setText(valueIntQuantity.toString())
            }
        }
    }


    fun addToCardWithSnackBar(yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String) {

            Snackbar.make(view!!, "Do you want to add the selected product to the cart?", Snackbar.LENGTH_SHORT)
                .setAction("Yes") {

                    addDataToCard (yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)

                    Snackbar.make(view!!, "The product has been added to the cart!", Snackbar.LENGTH_SHORT)
                        .setBackgroundTint(resources.getColor(R.color.backgroundTint))
                        .setActionTextColor(resources.getColor(R.color.actionTextColor))
                        .show()
                }
                .setBackgroundTint(resources.getColor(R.color.backgroundTint))
                .setActionTextColor(resources.getColor(R.color.actionTextColor))
                .show()
        }


    fun addDataToCard (yemekAdi : String, yemekResimAdi : String, yemekFiyat : Int, yemekSiparisAdet : Int, kullaniciAdi : String){


        findNavController().navigate(R.id.action_foodsDetailFragment_to_foodsCardFragment)


        viewModel.addFoodsRepo(yemekAdi, yemekResimAdi, yemekFiyat, yemekSiparisAdet, kullaniciAdi)

    }

}
