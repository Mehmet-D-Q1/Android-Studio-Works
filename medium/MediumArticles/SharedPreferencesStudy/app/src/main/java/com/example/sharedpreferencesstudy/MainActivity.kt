package com.example.sharedpreferencesstudy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreferencesstudy.databinding.ActivityMainBinding
import com.google.android.material.chip.Chip

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var chipText: String = ""
    private val DATA = "MYDATA"
    private val sharedPreferences : SharedPreferences by lazy {
        this.getSharedPreferences(DATA, Context.MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.buttonWrite.setOnClickListener {
        saveData()
        }

        binding.buttonClearInputs.setOnClickListener {
            clearAllInputs()
        }
        binding.buttonClearAll.setOnClickListener {
            clearAllData()
        }
        binding.buttonDelete.setOnClickListener {
            deleteData()
        }
        binding.buttonRead.setOnClickListener {
            getData()
        }

        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip: Chip? = group.findViewById(checkedId)
            if (chip != null) {
                Toast.makeText(this, chip.text, Toast.LENGTH_SHORT).show()
            }
            chipText = chip?.text.toString()
        }
    }

    fun saveData() {

        binding.apply {
            sharedPreferences.edit().apply {
                this.putString("name",editTextName.text.toString()).apply().toString()
                this.putInt("age",editTextAge.text.toString().toInt()).apply()
                this.putFloat("weight",editTextWeight.text.toString().toFloat()).apply()
                this.putString("status",chipText).apply()
            }
        }
    }

    fun clearAllInputs() {
        binding.apply {
            editTextName.setText("")
            editTextAge.setText("")
            editTextWeight.setText("")
            chipMale.isChecked = false
            chipFemale.isChecked = false
        }
    }

    fun clearAllData() {
        sharedPreferences.edit().clear().apply()
    }

    fun deleteData() {
        val UserInfo = binding.editTextUserInfo.text.toString()
        sharedPreferences.edit().remove(UserInfo).apply()
    }

    fun getData() {
        binding.apply {
            editTextName.setText(sharedPreferences.getString("name","").toString())
            editTextAge.setText(sharedPreferences.getInt("age",0).toString())
            editTextWeight.setText(sharedPreferences.getFloat("weight",0.0F).toString())

            if (sharedPreferences.getString("status","") == "Male"){
                chipMale.isChecked = true
            }else if (sharedPreferences.getString("status","") == "Female"){
                chipFemale.isChecked = true
            }else{
                binding.chipFemale.isChecked = false
                binding.chipMale.isChecked = false
            }
        }
    }
}