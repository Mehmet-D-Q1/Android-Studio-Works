package com.example.colormyviews

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }
    private fun setListeners(){
        //clickableViews adında View tipinde bir List oluşturduk. Kotlin Android Extensions plugin kütüphanesini import ederek ve gradle e eklenen plugin sayesinde
        //Layout XML dosyasında id'si tanımlanmış view komponentlerine direk ulaşabiliyoruz.List'in içerisine componentlerin id'lerini yazdık.
        val clickableViews: List<View> = listOf(box_one_text, box_two_text, box_three_text, box_four_text, box_five_text, constraint_layout, red_button, yellow_button, green_button)

        //for döngüsüyle clickableViews list inin içindeki her bir elemanı item geçici değişkeni ile geziyoruz.
        //bu item değişkeni üzerinde setOnClickListener metoduna parametre olarak makeColored(it) metodu gönderilir.
        //makeColored(it) : it parametresi, gönderilecek olan View tipindeki komponent referansıdır.
        for (item in clickableViews){ item.setOnClickListener { makeColored(it) } }
    }
    private fun makeColored(view: View){// View class tipinde view parametreli bir metod tanımladık.
        when (view.id) { //when-else seçim kontrolü ile seçimi yapılan ilgili view componentine karşılık atanacak arkaplan rengi seçilir. Seçim kriteri view referanslı parametre ile component id sidir.

            // Boxes using Color class colors for background(Color class)
            //id: box_one_text olan view komponentin arkaplan rengini, parametre olarak alınan view referansıyla setBackgroundColor() metodu çağrılır ve Color class referansıyla rengi belirtilerek atanır.
            R.id.box_one_text -> view.setBackgroundColor(Color.DKGRAY)
            R.id.box_two_text -> view.setBackgroundColor(Color.GRAY)

            // Boxes using Android color resources for background(R.class)
            //id: box_three_text olan view komponentin arkaplan rengini, parametre olarak alınan view referansıyla setBackgroundResource() metodu çağrılır ve android.R.color class referansıyla rengi belirtilerek atanır.
            R.id.box_three_text -> view.setBackgroundResource(android.R.color.holo_green_light)
            R.id.box_four_text -> view.setBackgroundResource(android.R.color.holo_green_dark)
            R.id.box_five_text -> view.setBackgroundResource(android.R.color.holo_green_light)

            // Boxes using custom colors for background(colors.xml)
            //id: red_button olan view komponenti seçildiğinde, id: box_three_text olan TextView componentin arkaplan rengi setBackgroundResource() metodu çağrılarak colors.xml dosyasında tanımlı rengi belirtilerek atanır.
            R.id.red_button -> box_three_text.setBackgroundResource(R.color.my_red)
            R.id.yellow_button -> box_four_text.setBackgroundResource(R.color.my_yellow)
            R.id.green_button -> box_five_text.setBackgroundResource(R.color.my_green)

            else -> view.setBackgroundColor(Color.LTGRAY)
        }
    }
}