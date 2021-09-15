package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class MainActivity : AppCompatActivity() {//AppCompatActivity(): Modern Android özelliklerine daha önceki uygulama sürümleriyle uyumluluk problemi yaşamadan erişmemize imkan sağlar.

    //lateinit keyword: kotlin derleyicisi, üzerinde herhangi bir operasyon çağırmadan önce değişkenin başlatılacağını garanti eder.
    //Bu sayede değişkenin null gelmeyeceğini de garanti ediyoruz.
    lateinit var diceImage: ImageView // ImageView tipinde değişkeni lateinit olarak tanımlıyoruz. Bunu yapmamızın sebebi, performans sorunu yaşamamak. Başlangıç değeri atamamızada gerek kalmıyor.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) //setContentView(): içinde tanımlanan activity layoutunu, inflate edecek ve ekranda gösterilmesini sağlayacak.

        val rollButton: Button = findViewById(R.id.roll_button) //findViewById(R.id.roll_button) XML dosyasında tanımladığımız id si roll_button isimli componenti bulup, Button tipinde oluşturduğumuz rollButton değişkenine atadık.

        //rollButton.text = "Let' Roll" //komponentin text özelliğini değiştirdik.
        /*
        //rollButton değişkeninin tuttupu buton componentine tıklandığında ne yapılacağını setOnClickListener metoduyla sağlayabiliriz.BU metodu bir lambda fonksiyonu formatında yazdık.
        //Burada butona tıklandığında bir Toast mesajı vermesini sağladık. Toast sınıfından makeText() metodunu çağırdık.
        //val toast: Toast = Toast.makeText(context, text, duration) toast.show() şeklindede yazılabilirdi.
        //Toast.makeText(context, text, duration).show()
        //context: bu toast mesajının gösteileceği Activity'i tanımlar.
        //text: gösterilecek metin mesajını belirtir.
        //duration: ekranda mesajın ne kadar süre gösterileceğidir. Hazır metodlar kullanılabilir. LENGTH_SHORT YADA LENGTH_LONG gibi.
        //show(): metodu ekranda mesajın gösterilmesini sağlar.

        //Bu şekildede yapılabilir:
        //val toast: Toast = Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT)
        //rollButton.setOnClickListener { toast.show() }

        //rollButton.setOnClickListener { Toast.makeText(this, "button clicked", Toast.LENGTH_SHORT).show() }
        */
        rollButton.setOnClickListener { rollDice() } //Butona tıklandığında rollDice() metodu çağrılacak.
        diceImage = findViewById(R.id.dice_image) //XML'de tanımlı Id'si dice_image olan ImageView tipindeki componenti, ImageView tipindeki diceImage nesnesine atıyoruz.
    }
    private fun rollDice() {//rollDice() metodu tanımladık.
        //val resultText: TextView = findViewById(R.id.result_text) //XML'de tanımlı Id'si result_text olan TextView tipindeki componenti, TextView tipindeki resultText nesnesine atıyoruz.
        val randomInt = (1..6).random()  //Random().nextInt(6) + 1  // 1 ila 6 arasında rastgele sayılar oluşturduk.

        //resultText.text = "Dice Rolled!" //resultText değişkeninin tuttuğu componentin text özelliğini değiştirdik.
        //resultText.text = randomInt.toString()// sayıları stringe çevirip, resultText değişkeninin tuttuğu componentin text özelliğini değiştirdik.

        val drawableResource = when(randomInt){ //random gelen sayılara karşılık when ile ilgili seçim yapılarak, seçim: drawableResource değişkenine atanır.
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val diceImage: ImageView = findViewById(R.id.dice_image)//XML'de tanımlı Id'si dice_image olan ImageView tipindeki componenti, ImageView tipindeki diceImage nesnesine atıyoruz.
        diceImage.setImageResource(drawableResource) //setImageResource() metodu; parametre olarak aldığı drawableResource un ImageView içeriğini set eder.

    }
}