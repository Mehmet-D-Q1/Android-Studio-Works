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
package com.example.dogglersapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogglersapp.R
import com.example.dogglersapp.const.Layout
import com.example.dogglersapp.data.DataSource

/**
 * Adapter to inflate the appropriate list item layout and populate the view with information
 * from the appropriate data source
 */
class DogCardAdapter(
    private val context: Context?,
    private val layout: Int
): RecyclerView.Adapter<DogCardAdapter.DogCardViewHolder>() {

    // TODO: Initialize the data using the List found in data/DataSource

    val dataSet = DataSource.dogs
    /**
     * Initialize view elements
     */
    class DogCardViewHolder(view: View?): RecyclerView.ViewHolder(view!!) { // Görünümleri tutacak olan sınıf. Her bir satırımızın içinde bulunan bileşenleri tanımlama işleminin yapıldığı sınıftır.
        // TODO: Declare and initialize all of the list item UI components
        val dogImageView : ImageView = view!!.findViewById(R.id.imageView)
        val dogNameText : TextView = view!!.findViewById(R.id.textName)
        val dogAgeText : TextView = view!!.findViewById(R.id.textAge)
        val dogHobbyText : TextView = view!!.findViewById(R.id.textHobbies)
    }

    //Create new views (invoked by the layout manager)
    //RecyclerView, yeni bir ViewHolder oluşturması gerektiğinde bu yöntemi çağırır.
    //Bu metod, ViewHolder'ı ve ilişkili Görünümü oluşturur ve başlatır, ancak görünümün içeriğini doldurmaz. ViewHolder henüz belirli verilere bağlanmamıştır.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogCardViewHolder {
        // TODO: Use a conditional to determine the layout type and set it accordingly.
        //  if the layout variable is Layout.GRID the grid list item should be used. Otherwise the
        //  the vertical/horizontal list item should be used.

        // TODO Inflate the layout

        // TODO: Null should not be passed into the view holder. This should be updated to reflect
        //  the inflated layout.
        // create a new view
        val adapterLayout = when (layout) {
            Layout.GRID -> LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item, parent, false)
            else -> LayoutInflater.from(parent.context).inflate(R.layout.vertical_horizontal_list_item, parent, false)
        }
        return DogCardViewHolder(adapterLayout)
    }

    //Return the size of your dataset (invoked by the layout manager)
    //RecyclerView, veri kümesinin boyutunu almak için bu yöntemi çağırır.
    //xml in kaç kez oluşturulacağını verecek. Listemizin eleman sayısını döndüren metottur.
    override fun getItemCount(): Int { return dataSet.size } // TODO: return the size of the data set instead of 0

    //Replace the contents of a view (invoked by the layout manager)
    //RecyclerView, bir ViewHolder'ı verilerle ilişkilendirmek için bu yöntemi çağırır. Uygun verileri getirir ve verileri, görünüm tutucunun layout'unu doldurmak için kullanır.
    //layout içerisinde hangi verileri göstereceğimizi sağlayacak. onCreateViewHolder’dan dönen verileri bağlama işlemini gerçekleştirildiği metotdur.
    override fun onBindViewHolder(holder: DogCardViewHolder, position: Int) {
        // TODO: Get the data at the current position
        // TODO: Set the image resource for the current dog
        // TODO: Set the text for the current dog's name
        // TODO: Set the text for the current dog's age
        val dogData = dataSet[position]
        holder.dogImageView.setImageResource(dogData.imageResourceId) // dataset ten gelen verilerden resim bilgisini aktarıyoruz.
        holder.dogNameText.text = dogData.name // dataset ten gelen verilerden name bilgisini aktarıyoruz.
//        holder.dogAgeText.text = dogData.age
        val resources = context?.resources  // string.xml dosyasında kayıtlı verilere erişmek için değişken tanımlandı.
        // TODO: Set the text for the current dog's hobbies by passing the hobbies to the
        //  R.string.dog_hobbies string constant.
        //  Passing an argument to the string resource looks like:
        //  resources?.getString(R.string.dog_hobbies, dog.hobbies)
        holder.dogAgeText.text = resources?.getString(R.string.dog_age, dogData.age)  // dataset ten ve string.xml den gelen verilerden age bilgisini aktarıyoruz.
        holder.dogHobbyText.text = resources?.getString(R.string.dog_hobbies, dogData.hobbies) // dataset ten ve string.xml den gelen verilerden hobbies bilgisini aktarıyoruz.


    }
}
