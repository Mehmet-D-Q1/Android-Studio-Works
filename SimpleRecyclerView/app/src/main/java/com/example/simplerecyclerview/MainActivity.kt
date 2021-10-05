package com.example.simplerecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    private val exampleList = generateDummyList(500)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewAdapter = ExampleAdapter(exampleList,this)
        viewManager = LinearLayoutManager(this)

        recyclerView = findViewById<RecyclerView>(R.id.recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    fun insertItem(view: View){
        val index: Int = Random.nextInt(8)
        val newIem = ExampleItem(R.drawable.ic_android, "New item at position $index", "Line 2")
        exampleList.add(index, newIem)
        recyclerView.adapter?.notifyItemInserted(index)
    }

    override fun onItemClick(position: Int) {
        Toast.makeText(this, "Item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem:ExampleItem = exampleList[position]
        clickedItem.text1 = "clicked"
        recyclerView.adapter?.notifyItemChanged(position)
    }

    fun removeItem(view: View){
        val index: Int = Random.nextInt(8)
        exampleList.removeAt(index)
        recyclerView.adapter?.notifyItemRemoved(index)
    }

    private fun generateDummyList(size:Int): ArrayList<ExampleItem>{
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size){
            val drawable = when (i%3){
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sun
            }
            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }
        return list
    }
}