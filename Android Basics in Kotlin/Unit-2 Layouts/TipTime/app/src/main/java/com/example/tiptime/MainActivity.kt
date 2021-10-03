package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    //lateinit keyword: değişkeni kullanmadan önce yazılan kodun başlatacağına dair bir sözdür. Bunu yapmazsanız, uygulamanız çökecektir.
    //viewBinding, activity_main XML dosyasının ismini otomatik olarak CamelCase'e çevirir ve sonuna Binding ifadesini ekleyerek ActivityMainBinding olur.
    private lateinit var binding: ActivityMainBinding //XML dosyasındaki her bir componente tek bir binding nesnesiyle ulaşabiliyoruz.
                                              // Bunun için viewBinding'i gradle'a tanımladık ve burada nesnesini oluşturduk.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //Bu satır, activity_main.xml dosyasındaki görünümlere(views) erişmek için kullanacağınmz bağlama(binding) nesnesini başlatır.
        setContentView(binding.root) //binding.root:uygulamadaki view hiyerarşisinin(parent, child, views) kök dosyasını belirtir. setContentView() metodu bu view hiyerarşisini yükler.

        // Old way with findViewById()
        /*
        val myButton: Button = findViewById(R.id.calculate_button)
        myButton.text = "A button"
        */

        // Better way with view binding
        /*
        val myButton: Button = binding.calculateButton
        myButton.text = "A button"
        */

        // Best way with view binding and no extra variable
        /*
        binding.calculateButton.text = "A button"
        */

        //binding nesnesiyle calculateButton(viewBinding, view elemenetlerine XML'de verilen id leri otomatik olarak camelCase'e çevirir) view elementine tıklandığında ne yapılacağı belirtilecek.
        binding.calculateButton.setOnClickListener { calculateTip() } //calculateButton view componentine tıklandığında calculateTip() metodu çağırılacak.
    }

    fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString() //id:costOfService olan view componentindeki text özelliğini alır, sitringe çevirip stringInTextField değişkenine atar.
                                                                      //string'e çeviriyoruz çünkü .text olması ilgili view objesi, sonuç olarak text döndürecektir ve işlem yapılamayacaktır.
                                                                      //işlem yapmak için artık String'ten diğer tiplere dönüştürülebilir.
        val cost = stringInTextField.toDoubleOrNull() //string tipli değişkeni double yada null'a çevirdik ve cost değişkenine atadık.
        if (cost == null) { //cost: nnull gelirse;
            binding.tipResult.text = "" // id:tipResult olan view componentinin text alanına boş string gönderecek.
            return //metodtan aşağıdaki satırlara uğramadan direk çıkılacak.
        }

        val selectedId = binding.tipOptions.checkedRadioButtonId //id:tipOptions olan view componentindeki RadioButton'ı seçili olan id li componenti al ve selectedId değişkenine ata.(Hangi RadioButton componentinin seçili olduğunu bulur.)

        val tipPercentage = when (selectedId) { //radioButton componentlerinin seçim kotntrolü:
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        var tip = tipPercentage * cost
        val roundUp = binding.roundUpSwitch.isChecked //id:roundUpSwitch olan view componentinin seçili olup olamadığı bilgisini alır(true/false) ve roundUp değişkenine atar.
        if (roundUp) { //roundUp : true ise
            tip = kotlin.math.ceil(tip) //math.ceil() metodu sayıyı en yakın üst integer değere yuvarlar.
        }

        val nf = NumberFormat.getCurrencyInstance(Locale.UK)// tip değişkenini BELİRTİLEN para birimi tipine formatlayacak metodlar.
        val formattedTip = nf.format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip) //String.xml dosyasında tanımlı name: tip_amount olan veriyi alır ve formattedTip değişkenini kullanarak
                                                                              //id:tip_result olan view komponentin text özelliğine atar.

    }

}


