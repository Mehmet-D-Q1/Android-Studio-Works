package com.example.odev_7_todoapp.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.odev_7_todoapp.R
import com.example.odev_7_todoapp.adapter.YapilacaklarAdapter
import com.example.odev_7_todoapp.databinding.FragmentAnaSayfaBinding
import com.example.odev_7_todoapp.viewModel.AnaSayfaFragmentViewModel
import com.example.odev_7_todoapp.viewModel.AnasayfaVMF

class AnaSayfaFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var tasarim : FragmentAnaSayfaBinding
    private lateinit var viewModel : AnaSayfaFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        tasarim = DataBindingUtil.inflate(inflater,R.layout.fragment_ana_sayfa,container,false)

        tasarim.anasayfaFragment = this

        //toolbar ve menu:
        tasarim.anasayfaToolbarBaslik = "Yapılacaklar"
        //tasarim.toolbarAnaSayfa.subtitle = "Yapılacaklar Alt Başlık"
        (activity as AppCompatActivity).setSupportActionBar(tasarim.toolbarAnaSayfa)


        viewModel.yapilacaklarListesi.observe(viewLifecycleOwner) {
            val adapter = YapilacaklarAdapter(requireContext(), it, viewModel)
            tasarim.gorevlerAdapterNesnesi = adapter
        }

        return tasarim.root
    }

    fun fabTikla(v : View){
        Navigation.findNavController(v).navigate(R.id.kayit_gecis)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val tempViewModel : AnaSayfaFragmentViewModel by viewModels(){
            AnasayfaVMF(requireActivity().application)
        }
        viewModel = tempViewModel
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        val item = menu.findItem(R.id.arama)
        val searchView = item.actionView as SearchView
        searchView.setOnQueryTextListener(this)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        viewModel.ara(query)
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        viewModel.ara(newText)
        return true
    }

    override fun onResume() {
        super.onResume()
        viewModel.gorevleriYukle()
    }



}