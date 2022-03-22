package com.example.finalproject_foodos.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.adapter.AdapterHome
import com.example.finalproject_foodos.databinding.FragmentFoodsHomeBinding
import com.example.finalproject_foodos.viewmodel.FoodsHomeFragmentViewModel
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils


class FoodsHomeFragment : Fragment() {

    private lateinit var dBinding : FragmentFoodsHomeBinding
    private lateinit var viewModel: FoodsHomeFragmentViewModel
    private lateinit var adapterHome: AdapterHome
    lateinit var badge : BadgeDrawable


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_foods_home, container, false)
        dBinding.foodsHomeFragment = this

        dBinding.recyclerViewHome.layoutManager = GridLayoutManager(requireContext(),3)

        viewModel.foodsList.observe(viewLifecycleOwner) { it ->
            adapterHome = AdapterHome(requireContext(), it, viewModel)
            dBinding.homeAdapterNesnesi = adapterHome
        }


        //
        dBinding.toolbarView.setOnMenuItemClickListener { menuItem ->
            if (menuItem.itemId == R.id.item_card_counter){
                Log.e("menu","heyyyooo2")

                badge.number = 10
                badge.backgroundColor = ContextCompat.getColor(requireContext(), R.color.BottomNav_clickedcolor)
                //badge.badgeGravity = BadgeDrawable.TOP_START
                badge.horizontalOffset = (45)
                badge.verticalOffset = (-15)
                BadgeUtils.attachBadgeDrawable(badge, dBinding.toolbarView, R.id.item_card_counter)
                findNavController().navigate(R.id.action_foodsHomeFragment_to_foodsCardFragment)
            }
            true
        }


        return dBinding.root

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
        val tempViewModel : FoodsHomeFragmentViewModel by viewModels()
        this.viewModel = tempViewModel
        badge = BadgeDrawable.create(requireContext())

    }

    override fun onResume() {
        super.onResume()
        viewModel.loadFoods()
    }





}