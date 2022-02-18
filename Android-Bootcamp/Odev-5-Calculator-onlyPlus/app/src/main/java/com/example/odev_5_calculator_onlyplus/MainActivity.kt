package com.example.odev_5_calculator_onlyplus

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.odev_5_calculator_onlyplus.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val operatorList = listOf("+", "=", ".")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonOne.setOnClickListener { addToExpressionScreen("1") }
        binding.buttonTwo.setOnClickListener { addToExpressionScreen("2") }
        binding.buttonThree.setOnClickListener { addToExpressionScreen("3") }
        binding.buttonFour.setOnClickListener { addToExpressionScreen("4") }
        binding.buttonFive.setOnClickListener { addToExpressionScreen("5") }
        binding.buttonSix.setOnClickListener { addToExpressionScreen("6") }
        binding.buttonSeven.setOnClickListener { addToExpressionScreen("7") }
        binding.buttonEight.setOnClickListener { addToExpressionScreen("8") }
        binding.buttonNine.setOnClickListener { addToExpressionScreen("9") }
        binding.buttonZero.setOnClickListener { addToExpressionScreen("0") }
        binding.buttonDot.setOnClickListener { addToExpressionScreen(".") }

        binding.buttonEqual.setOnClickListener { addToExpressionScreen("=") }
        binding.buttonPlus.setOnClickListener { addToExpressionScreen("+") }

        binding.buttonDelete.setOnClickListener { deleteAll() }
        binding.buttonBackSpace.setOnClickListener { backSpace() }


    }

    private fun deleteAll() {
        binding.expressionScreen.text = ""
        binding.resultScreen.text = ""
    }

    private fun backSpace() {
        if (binding.expressionScreen.text.isNotEmpty()) {
            binding.expressionScreen.text = binding.expressionScreen.text.dropLast(1)
        }
    }

    private fun addToExpressionScreen(digitValue: String) {
        if (operatorList.contains(digitValue) && binding.expressionScreen.text.isNotEmpty()) {
            lastDigitOperatorControl(digitValue)
        }
        else if ((digitValue == ".") && (binding.expressionScreen.text.isEmpty())){
            binding.expressionScreen.append("0${digitValue}").toString()
        }
        else if (!operatorList.contains(digitValue) && binding.expressionScreen.text.endsWith("=") && binding.expressionScreen.text.isNotEmpty()){
            binding.expressionScreen.text = ""
            binding.expressionScreen.append(digitValue)
        }
        else if (binding.expressionScreen.text.endsWith("=") && digitValue == "+" && binding.expressionScreen.text.isNotEmpty()){
            showInResultScreen()
        }
        else{
            binding.expressionScreen.append(digitValue)
        }
    }

    private fun lastDigitOperatorControl(lastDigit: String) {
        var existOfEnd : Boolean = false

        for (i in operatorList){
            //listenin içerisindeki elemanlardan herhangi birisi işlemin sonunda yoksa;
            if (!binding.expressionScreen.text.endsWith(i) ) {
                existOfEnd = true
            }else if(binding.expressionScreen.text.endsWith("=") && lastDigit == "+"){
                existOfEnd = false
                showInResultScreen()
            }else{
                existOfEnd = false
                break
            }
        }
        //yoksa, parametre olarak geleni ekle;
        if(existOfEnd){

            if (lastDigit == "+" && !binding.expressionScreen.text.contains("+")){
                binding.expressionScreen.append(lastDigit)
            }else if((lastDigit == "+") && (binding.expressionScreen.text.contains("+"))){
                addOperation()
                showInResultScreen()
            }
            else if(lastDigit == "="){
                binding.expressionScreen.append(lastDigit)
                addOperation()
            }
            else{
                binding.expressionScreen.append(lastDigit)
            }
        }
    }

    private fun addOperation() {

        var ss = binding.expressionScreen.text.split("+","=")
        println(ss)
        if (ss.size >= 2 && ss[0].isNotEmpty() && ss[1].isNotEmpty()){
            var toplam = ss[0].toDouble() + ss[1].toDouble()
            binding.resultScreen.text = toplam.toString()
        }
        else{
             binding.resultScreen.text = ss[0]
        }
    }

    private fun showInResultScreen() {
        binding.expressionScreen.text = binding.resultScreen.text
    }



}