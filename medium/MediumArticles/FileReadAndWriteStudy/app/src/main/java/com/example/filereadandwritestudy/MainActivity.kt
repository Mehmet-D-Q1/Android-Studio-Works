package com.example.filereadandwritestudy

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.filereadandwritestudy.databinding.ActivityMainBinding
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonWriteInternal.setOnClickListener {
            writeContentToInternalStorage()
        }
        binding.buttonReadInternal.setOnClickListener {
            readContentFromInternalStorage()
        }
        binding.buttonDeleteInternal.setOnClickListener {
            deleteContentFromInternalStorage()
        }

        binding.buttonWriteExternal.setOnClickListener {
            writeContentToExternalStorage()
        }
        binding.buttonReadExternal.setOnClickListener {
            readContentFromExternalStorage()
        }
        binding.buttonDeleteExternal.setOnClickListener {
            deleteContentFromExternalStorage()
        }
    }

    private fun writeContentToInternalStorage() {
        val fileName = "Data.txt"
        val fileContent =
            "${binding.editTextName.text}\n${binding.editTextAge.text}\n${binding.editTextMail.text}\n"

        //1.method:
        applicationContext.openFileOutput(fileName, Context.MODE_PRIVATE).use { //openFileOutput : context ile ilişkili private bir dosyaya yazmak için açar. eğer dosya ismi yoksa oluşturur.
            it.write(fileContent.toByteArray())
        }

//        //2.method:
//        val file = File(applicationContext.filesDir, fileName)
//        FileOutputStream(file).use {
//            it.write(fileContent.toByteArray())
//        }
    }
    private fun readContentFromInternalStorage() {
        val fileName = "Data.txt"
        //1.method:
        val file = File(applicationContext.filesDir, fileName) // dosya sisteminde verileri depolamak ve erişmek için kullanılır.
        //filesDir : sistem dosya yolunu temsil eder. File : ilgili dosya yoluna fileName dosyasını ekler ve bir nesne verir.
//        Log.d("aa", getFileStreamPath(fileName).toString())
        if (file.exists()) {
            applicationContext.openFileInput(fileName).use { //openFileInput : context ile ilişkili private bir dosyayı okumak için açar.
                val contentText = it.bufferedReader().use { it.readText() }
//                Log.d("dd", contentText)
                Log.d("dd1", file.exists().toString())
                binding.textViewInternal.text = contentText
            }
        }
        Log.d("dd2", file.exists().toString())

//        //2.method:
//        applicationContext.openFileInput(fileName).bufferedReader().useLines {
////            it.fold("") {some, text -> Log.d("aa", "$some\n$text").toString() }
//          val contentText =  it.fold("") { some, text -> "$some\n$text"}
//            binding.textViewInternal.text = contentText
//
//        }
    }
    private fun deleteContentFromInternalStorage() {
//        val filePath = "/data/data/com.example.filereadandwritestudy/files/Data.txt"
//        val file = File(filePath)
        val fileName = "Data.txt"
        val file = File(applicationContext.filesDir, fileName)
        if (file.exists()) {
            Log.d("", file.exists().toString())
            file.delete()
        }
    }

    private fun writeContentToExternalStorage() {
        val fileName = "Data.txt"
        val fileContent =
            "${binding.editTextName.text}\n${binding.editTextAge.text}\n${binding.editTextMail.text}\n"
        val file = File(applicationContext.getExternalFilesDir(null), fileName)
        //getExternalFilesDir() :  Uygulamaya özel dosyalara, harici depolamadan erişmeyi sağlayan yolu verir.
        FileOutputStream(file).use { //FileOutputStream : belirtilen dosya nesnesi tarafından temsil edilen dosyaya yazmak için bir akış oluşturur.
            it.write(fileContent.toByteArray())
        }
    }
    private fun readContentFromExternalStorage() {
        val fileName = "Data.txt"
        val file = File(applicationContext.getExternalFilesDir(null), fileName)
        if (file.exists()) {
            FileInputStream(file).use { //FileInputStream : belirtilen dosya nesnesi tarafından temsil edilen dosyayı okumak için bir akış oluşturur.
                val contentText = it.bufferedReader().use { it.readText() }
                Log.d("dd4", file.exists().toString())
                binding.textViewExternal.text = contentText
            }
        }
        Log.d("dd5", file.exists().toString())
    }
    private fun deleteContentFromExternalStorage() {
        val fileName = "Data.txt"
        val file = File(applicationContext.getExternalFilesDir(null), fileName)
        if (file.exists()) {
            Log.d("ee", file.exists().toString())
            file.delete()
        }
    }


}
