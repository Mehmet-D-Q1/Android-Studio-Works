/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    /**
     * DO NOT ALTER ANY VARIABLE OR VALUE NAMES OR THEIR INITIAL VALUES.
     *
     * Anything labeled var instead of val is expected to be changed in the functions but DO NOT
     * alter their initial values declared here, this could cause the app to not function properly.
     */
    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"

    // SELECT represents the "pick lemon" state
    private val SELECT = "select"

    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"

    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"

    // RESTART represents the state where the lemonade has be drunk and the glass is empty
    private val RESTART = "restart"

    // Default the state to select
    private var lemonadeState = "select"

    // Default lemonSize to -1
    private var lemonSize = -1

    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree() //LemonTree() class'ından bir nesne oluşturduk.
    private var lemonImage: ImageView? = null //ImageView tipinde bir değişken oluşturduk. null gelmezse atama yapacak, null gelirse null dönecek.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // === DO NOT ALTER THE CODE IN THE FOLLOWING IF STATEMENT ===
        if (savedInstanceState != null) { //Activite restart olduğunda verileri almak için getString(key,value),getInt(key,value)...vb. metodu kullanılır.
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select") //key:LEMONADE_STATE karşılık değerini alır value yoksa/yanlışsa 2. parametredeki default value:"select" geçerli olur.
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }

        // === END IF STATEMENT ===
        lemonImage = findViewById(R.id.image_lemon_state) //id'si image_lemon_state olan view komponentini lemonImage değişkenine atar.
        setViewElements() // ilgili view elementlerini ekrana set etmek için metod çağırıldı.
        lemonImage!!.setOnClickListener { //lemonImage referansının tuttuğu view komponentine tıklandığında olacaklar:
            // TODO: call the method that handles the state when the image is clicked
            clickLemonImage() //metodu çağrılır.
        }
        lemonImage!!.setOnLongClickListener { //lemonImage referansının tuttuğu view komponentine uzn basılı tutulduğunda olacaklar:
            // TODO: replace 'false' with a call to the function that shows the squeeze count
            showSnackbar() //metodu çağırılır.
            //false
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * This method saves the state of the app if it is put in the background.
     */
    override fun onSaveInstanceState(outState: Bundle) {//key-value olarak tutulacak bilgilere, Bundle class tipindeki outState nesnesiyle ulaşılacaktır.
        //key:"LEMONADE_STATE", value: lemonadeState değerleri state Bundle'a gider.
        outState.putString(LEMONADE_STATE, lemonadeState) //buradaki key:"LEMONADE_STATE" anahtarı onCreate te kullanılanla aynı olmalıdır. Bu key ile tutulan bilgiler buradan oraya gidecek.
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }

    /**
     * Clicking will elicit a different response depending on the state.
     * This method determines the state and proceeds with the correct action.
     */
    private fun clickLemonImage() {
        // TODO: use a conditional statement like 'if' or 'when' to track the lemonadeState
        //  when the the image is clicked we may need to change state to the next step in the
        //  lemonade making progression (or at least make some changes to the current state in the
        //  case of squeezing the lemon). That should be done in this conditional statement


        // TODO: When the image is clicked in the SELECT state, the state should become SQUEEZE
        //  - The lemonSize variable needs to be set using the 'pick()' method in the LemonTree class
        //  - The squeezeCount should be 0 since we haven't squeezed any lemons just yet.

        if (lemonadeState == SELECT) { //lemonadeState == SELECT eşit ise,
            lemonadeState = SQUEEZE //lemonadeState = SQUEEZE olarak atar.
            lemonSize = lemonTree.pick() //pick() metodu, 2 ila 4 arasında random bir sayı alınması için çağrılır ve değer, lemonSize değişkenine atanır.
            squeezeCount = 0
        }

        // TODO: When the image is clicked in the SQUEEZE state the squeezeCount needs to be
        //  INCREASED by 1 and lemonSize needs to be DECREASED by 1.
        //  - If the lemonSize has reached 0, it has been juiced and the state should become DRINK
        //  - Additionally, lemonSize is no longer relevant and should be set to -1

        else if (lemonadeState == SQUEEZE) {
            squeezeCount++
            lemonSize--
            if (lemonSize == 0) {
                lemonadeState = DRINK
                lemonSize = -1
            }
        }

        // TODO: When the image is clicked in the DRINK state the state should become RESTART
        else if (lemonadeState == DRINK) {
            lemonadeState = RESTART
        }

        // TODO: When the image is clicked in the RESTART state the state should become SELECT
        else {
            lemonadeState = SELECT
        }

        // TODO: lastly, before the function terminates we need to set the view elements so that the
        //  UI can reflect the correct state

        setViewElements() // ilgili view elementlerini ekrana set etmek için metod çağırıldı.
    }

    /**
     * Set up the view elements according to the state.
     */
    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.text_action) //id'si text_action olan view komponentini textAction değişkenine atar.

        // TODO: set up a conditional that tracks the lemonadeState

        // TODO: for each state, the textAction TextView should be set to the corresponding string from
        //  the string resources file. The strings are named to match the state

        // TODO: Additionally, for each state, the lemonImage should be set to the corresponding
        //  drawable from the drawable resources. The drawables have the same names as the strings
        //  but remember that they are drawables, not strings.

        when (lemonadeState) {
            SELECT -> { //lemonadeState : SELECT ise,
                textAction.setText(R.string.lemon_select); //strings.xml dosyasında name: lemon_select olan veriyi alır, textAction değişkenine set eder.
                (lemonImage?.setImageResource(R.drawable.lemon_tree)) //drawable kalsöründeki lemon_tree.xml dosyasını bulur, lemonImage değişkenine set eder.
            }
            SQUEEZE -> { //lemonadeState : SQUEEZE olduğunda,
                textAction.setText(R.string.lemon_squeeze) //strings.xml dosyasında name: lemon_squeeze olan veriyi alır, textAction değişkenine set eder.
                (lemonImage?.setImageResource(R.drawable.lemon_squeeze)) //drawable kalsöründeki lemon_squeeze.xml dosyasını bulur, lemonImage değişkenine set eder.
            }
            DRINK -> {
                textAction.setText(R.string.lemon_drink)
                (lemonImage?.setImageResource(R.drawable.lemon_drink))
            }
            RESTART -> {
                textAction.setText(R.string.lemon_empty_glass)
                (lemonImage?.setImageResource(R.drawable.lemon_restart))
            }
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     *
     * Long clicking the lemon image will show how many times the lemon has been squeezed.
     */
    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) { //lemonadeState != SQUEEZE eşit değilse false dönderir.Çünkü sıkma işlemi için SQUEEZE olması gerekir.
            return false
        }

        /*Snackbar.make(
                    root_layout, // Parent view
                    "Hello Snackbar!", // Message to show
                    Snackbar.LENGTH_SHORT // How long to display the message.
            ).show() //Snackbar mesajını göstermek için çağırılır.
        */
        val squeezeText = getString(R.string.squeeze_count, squeezeCount) //strings.xml dosyasında name: squeeze_count olan veriyi alır ve squeezeCount değişkenini veride kullanarak squeezeText değişkenine atar.
        Snackbar.make(
            findViewById(R.id.constraint_Layout), //Snackbar'ın mesaj viewini tutacak parent view(burada constraint_Layout)
            squeezeText, //Snackbar da gösterilecek mesaj
            Snackbar.LENGTH_SHORT //Gösterilecek mesajın gözükme süresi
        ).show() //Snackbar messajının gösterilmesi için gerekli.
        return true
    }
}

/**
 * A Lemon tree class with a method to "pick" a lemon. The "size" of the lemon is randomized
 * and determines how many times a lemon needs to be squeezed before you get lemonade.
 */
class LemonTree {
    fun pick(): Int { //random bir sayı üretmek için çağırılır. bu sayı limonun kaç defa sıkılacağını temsil eder.
        return (2..4).random()
    }
}
