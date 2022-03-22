package com.example.finalproject_foodos.fragments

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.adapter.AdapterCard
import com.example.finalproject_foodos.databinding.FragmentFoodsCardBinding
import com.example.finalproject_foodos.entity.FoodsInBasket
import com.example.finalproject_foodos.viewmodel.FoodsCardFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class FoodsCardFragment : Fragment() {
    private lateinit var dBinding: FragmentFoodsCardBinding
    private lateinit var viewModel: FoodsCardFragmentViewModel
    private lateinit var adapterCard: AdapterCard
    private lateinit var adapterCard2: AdapterCard
    lateinit var foodsListInBasket2: ArrayList<FoodsInBasket>

    @SuppressLint("NotifyDataSetChanged")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_foods_card, container, false)
        dBinding.foodsCardFragment = this

        dBinding.recyclerViewCard.layoutManager = LinearLayoutManager(requireContext())

        viewModel.foodsListInBasket.observe(viewLifecycleOwner) { it ->
            adapterCard = AdapterCard(requireContext(), it, viewModel)
            dBinding.cardAdapterNesnesi = adapterCard

            //call total card prices from AdapterCard
            val totalPriceText = adapterCard.CalculateTotalCardPrice().toString()
            dBinding.textViewTotalPriceInCard.text = totalPriceText
        }


        dBinding.buttonConfirmInCard.setOnClickListener {
            if (dBinding.recyclerViewCard.adapter?.itemCount == 0){
                Log.e("ddd","There are no products in the cart")
                Snackbar.make(it, "There are no products in the cart!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(requireContext().getColor(R.color.backgroundTint))
                    .setActionTextColor(requireContext().getColor(R.color.actionTextColor))
                    .show()
            }else{
                Log.e("ee", "${dBinding.recyclerViewCard.adapter?.itemCount}")
                Snackbar.make(it, "Your orders have been processed!", Snackbar.LENGTH_SHORT)
                    .setBackgroundTint(requireContext().getColor(R.color.backgroundTint))
                    .setActionTextColor(requireContext().getColor(R.color.actionTextColor))
                    .show()
            }

        }

        return dBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel: FoodsCardFragmentViewModel by viewModels()
        this.viewModel = tempViewModel


    }


}
