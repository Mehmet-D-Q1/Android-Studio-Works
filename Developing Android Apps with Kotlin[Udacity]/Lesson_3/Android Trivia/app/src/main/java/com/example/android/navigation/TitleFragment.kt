package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding


/**
 * A simple [Fragment] subclass.
 * Use the [TitleFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class TitleFragment : Fragment() {
    //onCreateView() metodu, Fragment ile ilişkili layout xml dosyasını inflate etmesi ve görünümü(view componentleri) (return)döndürmek için kullanılır.
    //onCreateView() metodu, Fragment'ın, View nesne hiyerarşisini oluşturması gerektiğinde çağrılır. //Bunu ya dinamik olarak ya da XML layout düzeni şişirme yoluyla yapar.
    //onCreateView yöntemi, Fragment'in UI componentlerini başlatması için çağrılır. grafiksel elemanlar yoksa null dönderir.
    //inflater LayoutInflater: Fragment daki herhangi bir görünümü şişirmek(inflate) için kullanılabilen LayoutInflater nesnesi,
    //container ViewGroup: Null değilse, bu, Fragment kullanıcı arabiriminin eklenmesi gereken parent görünümdür.
    //saveInstanceState Bundle: Null değilse, Bu Fragment, bir önceki kaydedilmiş durumdan yeniden oluşturulur.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Bu Fragment'i derlemek için FragmentTitleBinding tipinde bir binding objesi oluşturmak ve Fragment'in view'ini inflate etmek için de
        //DataBindingUtil.inflate() metodunu, oluşturduğumuz binding objesi üzerinde çağırmamız gerekiyor.
        //inflate(LayoutInflater inflater, int layoutId, ViewGroup parent, boolean attachToParent)
        //inflater: LayoutInflater -> binding layout'u inflate edecek LayoutInflater referansı.
        //layoutId:	int: -> inflate edilecek XML layout'un kaynağı.
        //parent: ViewGroup -> inflate edilmiş olan layout'un parent'ı olacak ViewGroup.
        //attachToParent: boolean -> Inflate edilmiş layout'un inflate sırasında ViewGroup'a (kapsayıcı) eklenmesi gerekip gerekmediğini gösteren bir boolen değer.
        val binding: FragmentTitleBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_title, container, false
        )

        //Oluşturulan binding nesnesiyle id'si playButton olan component üzerinde bir setOnClickListener metodu çağırıyoruz.
        //Hedef fragmentler arasında gezinmek için Navigation classı, view parametresi alan findNavController(view) yardımcı metodu sağlar.
        //Navigation Host Fragment , current(mevcut) fragment in view hiyerarşisindeki (kapsayıcısı)parenti dır. (activity_main.xml de eklenmiş olan NavHostFragment view componenti)
        //Bu metod, (current)mevcut fragment daki herhangi bir view dan NavHostFragment 'ı bulması için  yukarıya doğru gezinebilmeyi sağlar.
        //Navigation.findNavController(view) bize bir navigation controller nesnesi verir. Bu nesne ile current(mevcut) fragmentten(host olarak tanımlı)  bir hedefe gezinmeyi sağlayan navigate() metodu çağrılır.
        //navigate(R.id.action_titleFragment_to_gameFragment) metodu, bir birine gezinecek olan fragmentlerin id'sini parametre olarak alır(navigation.xml de).
        binding.playButton.setOnClickListener { v: View -> v.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment()) }

        //Bu şekilde de yazılabilir.
        //binding.playButton.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment))

        Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)
        setHasOptionsMenu(true)
        return binding.root //inflate edilmiş layoutun kök görünümünü dönderir.
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.overflow_menu,menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }

}