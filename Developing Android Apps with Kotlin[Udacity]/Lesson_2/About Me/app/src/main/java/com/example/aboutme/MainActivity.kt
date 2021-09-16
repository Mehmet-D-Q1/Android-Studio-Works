package com.example.aboutme

import android.annotation.SuppressLint
import android.content.Context
import android.hardware.input.InputManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
/*//----------DataBinding Kullanmadan:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.done_button).setOnClickListener { addNickname(it) } //XML'de tanımlı Id'si done_button olan Buton tipindeki komponenti bulup,
                                                                                      //bu komponent üzerinde setOnClickListener metoduna parametre olarak addNickname(it) metodu gönderirlir.
                                                                                      //addNickname(it) : it parametresi, bir View komponenti olan buttonun referansıdır.

    }
    private fun addNickname(view : View){ //addNickname adında bir metod tanımladık. parametre olarak View tipinde bir view referansı gönderdik. Burada it ile gelecek olan done_buttonun referansı oluyor.
        val editText = findViewById<EditText>(R.id.nickname_edit) //XML'de tanımlı Id'si nickname_edit olan EditText tipindeki componentini bulup, EditText tipindeki editText değişkenine atıyoruz.
        val nicknameTextView = findViewById<TextView>(R.id.nickname_text) //XML'de tanımlı Id'si nickname_text olan TextView tipindeki componentini bulup, TextView tipindeki nicknameTextView değişkenine atıyoruz.

        nicknameTextView.text = editText.text //editText değişkeninin tuttuğu text bilgisini, nicknameTextView değişkeninin tuttuğu componentin text özelliğine atadık.
        editText.visibility = View.GONE //editText değişkeninin tuttuğu komponentin görünürlüğünü GONE(hem içeriği hem de layout tasarımda görünmez, yer kaplamaz) yaptık.
        view.visibility = View.GONE // view referansıyla gelen komponentin görünürlüğünü GONE yaptık. view referansı, done_button ın referansı olan it'i temsil eder. yani butonun görünürlüğü GONE yapıldı.
        nicknameTextView.visibility = View.VISIBLE //nicknameTextView değişkeninin tuttuğu komponentin görünürlüğünü VISIBLE(görünür) yaptık.

        //Hide the keyboard:
        //Butona basıldığktan sonra giriş işlemlerinin kapatılması gerekiyor.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }
*/
    //-----------------------------------------------------------------------------------------------------------------------------------------------------------//

    //----------DataBinding Kullanımı:
    private lateinit var binding: ActivityMainBinding // binding objesi için onCreate() metodunun üzerinde ActivityMainBinding class tipinde binding adında bir değişken oluşturduk.
    //ActivityMainBinding class'ı compiler tarafından bu MainActivity'ye özel oluşturulur. İsmi, bu Activity'nin layout dosya isminden gelir.
    //Oluşturulma şekli; activity_main -> ActivityMainBinding "Pascal case" yazım şekline çevirir.
    //Bu obje sayesinde layout ve layout'un view elementlerini, kodlarımızın bulunduğu class lardaki datalara bağlarız.

    // Instance of MyName data class.
    private val myName: MyName = MyName("Aleks Scoty") //MyName adında oluşturduğumuz sınıf tipinde bir myName değişkeni oluşturup ismini set diyoruz.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Activity ile layout'un birbirine bağlanması genellikle onCreate() metodunda yapılır. binding nesnesini burada başlatıyoruz.
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main) //DataBindingUtil sınıfındaki setContentView(activity, layout) metoduyla bu activity'i ilgili layout'a bağlaması için binding nesnesine atıyorz.

        //binding nesnesiyle komponentlere ulaşabiliyoruz.
        //id si: done_button olarak tanımlanmış botonu doneButton olarak "camelCase" şeklinde tanımladı.
        binding.doneButton.setOnClickListener { addNickname(it) } //Bu buton üzerinden setOnClickListener metodunu çağırdık ve addNickname(it) metodunu parametre olarak gönderdik. addNickname(it) : it parametresi, bir View komponenti olan buttonun referansıdır.

        binding.myName = myName   //layout XML dosyasında bildirilen ve kullanılan myName değişkenine bu sınıfta tanımladığımız myName değişkenini atıyoruz.

    }
    private fun addNickname(view: View) { //addNickname adında bir metod tanımladık. parametre olarak View tipinde bir view referansı gönderdik. Burada it ile gelecek olan doneButton referansı oluyor.

        //binding.apply{}: .apply{} -> Bir değer döndürmez, sadece içerisindeki yapıyı nesneye uygulamayı sağlar. Nesneyi yapılandırmak için kullanılır.
        //kodun daha kolay okunmasını da sağlar. Her view elementi için binding yazılması da gerekmez.
        binding.apply {
            //nicknameText.text = nicknameEdit.text //nicknameEdit değişkeninin tuttuğu componentin text bilgisini, nicknameText değişkeninin tuttuğu componentin text özelliğine atadık.
            myName?.nickName = nicknameEdit.text.toString()//nicknameEdit değişkeninin tuttuğu componentin text bilgisini stringe çevirip, layout taki myName.nickName parametresine atanır.
            invalidateAll()
            nicknameEdit.visibility = View.GONE //nicknameEdit değişkeninin tuttuğu komponentin görünürlüğünü GONE(hem içeriği hem de layout tasarımda görünmez, yer kaplamaz) yaptık.
            doneButton.visibility = View.GONE // doneButton değişkeninin tuttuğu komponentin görünürlüğünü GONE yaptık. view referansı, yani butonun görünürlüğü GONE yapıldı.
            nicknameText.visibility = View.VISIBLE //nicknameText değişkeninin tuttuğu komponentin görünürlüğünü VISIBLE(görünür) yaptık.
        }

        //Hide the keyboard:
        //Butona basıldığktan sonra giriş işlemlerinin kapatılması gerekiyor.
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)

    }


}