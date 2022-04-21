package com.example.finalproject_foodos.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.adapter.AdapterFavorites
import com.example.finalproject_foodos.databinding.FragmentFoodsFavoriteBinding
import com.example.kisileruygulamasi.viewmodel.FavFoodsFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.FavFoodsVMFactory


class FoodsFavoriteFragment : Fragment() {

    private lateinit var viewModel : FavFoodsFragmentViewModel
    private lateinit var dBinding : FragmentFoodsFavoriteBinding
    private lateinit var adapterFavorites: AdapterFavorites

    @SuppressLint("NotifyDataSetChanged", "RestrictedApi")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        dBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_foods_favorite, container, false)
        dBinding.foodsFavoritesFragment = this

        setHasOptionsMenu(true)

        dBinding.recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext())
        dBinding.recyclerViewFavorites.setHasFixedSize(true)

        viewModel.favFoodsList.observe(viewLifecycleOwner) { it ->
            adapterFavorites = AdapterFavorites(requireContext(), it, viewModel)
            adapterFavorites.notifyDataSetChanged()
        dBinding.favoritesAdapterNesnesi = adapterFavorites

        }

        return dBinding.root
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FavFoodsFragmentViewModel by viewModels {
            FavFoodsVMFactory(requireActivity().application)
        }
        this.viewModel = tempViewModel

    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllFavorites()
    }




}