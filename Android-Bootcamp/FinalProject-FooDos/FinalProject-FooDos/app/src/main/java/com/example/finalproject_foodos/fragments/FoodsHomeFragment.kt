package com.example.finalproject_foodos.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.finalproject_foodos.MainActivity
import com.example.finalproject_foodos.R
import com.example.finalproject_foodos.adapter.AdapterHome
import com.example.finalproject_foodos.databinding.FragmentFoodsHomeBinding
import com.example.finalproject_foodos.entity.Foods
import com.example.finalproject_foodos.viewmodel.FoodsHomeFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.FavFoodsFragmentViewModel
import com.example.kisileruygulamasi.viewmodel.FavFoodsVMFactory
import com.example.kisileruygulamasi.viewmodel.HomeFoodsVMFactory
import com.google.android.material.badge.BadgeDrawable


class FoodsHomeFragment : Fragment(){

    private lateinit var dBinding : FragmentFoodsHomeBinding
    private lateinit var viewModel: FoodsHomeFragmentViewModel
    private lateinit var viewModel2: FavFoodsFragmentViewModel
    private lateinit var adapterHome: AdapterHome
    lateinit var badge : BadgeDrawable
    var filteredList : ArrayList<Foods> = ArrayList()

    @SuppressLint("ResourceType", "NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_foods_home, container, false)
        dBinding.foodsHomeFragment = this
        setHasOptionsMenu(true)

        dBinding.recyclerViewHome.layoutManager = GridLayoutManager(requireContext(),3)
        dBinding.recyclerViewHome.setHasFixedSize(true)

        viewModel.foodsList.observe(viewLifecycleOwner) { it ->
            adapterHome = AdapterHome(requireContext(), it, viewModel, viewModel2)
            adapterHome.notifyDataSetChanged()

            dBinding.homeAdapterNesnesi = adapterHome

            dBinding.chipGroup.setOnCheckedStateChangeListener { group, checkedIds ->
                when(true) {
                    dBinding.chip1.isChecked -> {

                        sortedAscending (it)
                    }
                    dBinding.chip2.isChecked -> {

                        sortedDescending(it)
                    }
                    dBinding.chip3.isChecked -> {

                        sortedPriceAscending(it)
                    }
                    dBinding.chip4.isChecked -> {

                        sortedPriceDescending(it)
                    }

                    else -> {}
                }
            }

        }

        viewModel2.favFoodsList.observe(viewLifecycleOwner){

        }

        (requireActivity() as MainActivity).setSupportActionBar(dBinding.toolbarView)

        return dBinding.root

    }

    @SuppressLint("NotifyDataSetChanged")
    fun sortedAscending(array : ArrayList<Foods>){
        array.sortBy { it.yemekAdi }
        adapterHome.notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun sortedDescending(array : ArrayList<Foods>){
        array.sortByDescending { it.yemekAdi }
        adapterHome.notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun sortedPriceAscending(array : ArrayList<Foods>){
        array.sortBy { it.yemekFiyat }
        adapterHome.notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun sortedPriceDescending(array : ArrayList<Foods>){
        array.sortByDescending { it.yemekFiyat }
        adapterHome.notifyDataSetChanged()
    }


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val tempViewModel : FoodsHomeFragmentViewModel by viewModels(){
            HomeFoodsVMFactory(requireActivity().application)
        }
        this.viewModel = tempViewModel

        val tempViewModel2 : FavFoodsFragmentViewModel by viewModels(){
            FavFoodsVMFactory(requireActivity().application)
        }
        this.viewModel2 = tempViewModel2


        badge = BadgeDrawable.create(requireContext())
        setHasOptionsMenu(true); // It's important here
    }


    override fun onResume() {
        super.onResume()
        viewModel.loadFoods()
        viewModel2.getAllFavorites()
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {

        inflater.inflate(R.menu.toolbar_menu_home, menu)

        val searchItem = menu.findItem(R.id.app_bar_search)
        val searchView = searchItem.actionView as SearchView
        dBinding.toolbarView.title = ""
        searchView.queryHint = "Search here..."

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            //yazılan metin enter ile tetiklenir
            override fun onQueryTextSubmit(query: String?): Boolean {

//                Toast.makeText(requireActivity(), query, Toast.LENGTH_SHORT).show()

                return false
            }

            //yazılan metin yazar yazmaz tetiklenir
            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {


//                Toast.makeText(requireActivity(), newText, Toast.LENGTH_SHORT).show()

                    filteredList.clear()
                    val search = newText?.lowercase()
                    viewModel.foodsList.value?.forEach {
                        if (it.yemekAdi.lowercase().contains(search.toString())){
                            filteredList.add(it)


                        }
                    }

                adapterHome = AdapterHome(requireContext(), filteredList, viewModel, viewModel2)
                dBinding.homeAdapterNesnesi = adapterHome
                adapterHome.notifyDataSetChanged()

                return false
            }
        })
    }


    //ilgili menude tıklanan elemanları tetikler
    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.app_bar_search) {
//            Toast.makeText(requireActivity(), "arama", Toast.LENGTH_SHORT).show()

        }
        else if (item.itemId == R.id.item_card_counter){
//            Toast.makeText(requireActivity(), "sepet", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_foodsHomeFragment_to_foodsCardFragment)
        }

        return false
    }


}