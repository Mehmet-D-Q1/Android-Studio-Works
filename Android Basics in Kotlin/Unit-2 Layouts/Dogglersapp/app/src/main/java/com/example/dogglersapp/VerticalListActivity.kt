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
package com.example.dogglersapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dogglersapp.const.Layout
import com.example.dogglersapp.adapter.DogCardAdapter

import com.example.dogglersapp.databinding.ActivityVerticalListBinding

class VerticalListActivity : AppCompatActivity() {

    //Oluşturduğumuz binding nesnesiyle bu xml layout'u ile etkiLeŞen tüm view komponentlerine kolayca erişebileceğiz.
    //lateinit keyword: değişkeni kullanmadan önce yazılan kodun başlatacağına dair bir sözdür.
    private lateinit var binding: ActivityVerticalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //ActivityVerticalListBinding classı, activity_vertical_list.xml layout undaki viewlara erişmek için inflate metodunu çağırır ve binding nesnesini başlatır.
        binding = ActivityVerticalListBinding.inflate(layoutInflater) //XML layout'u okur. ve bir binding değişkenine atar. inflate: XML ile kodların bağlanmasını sağlar.
        setContentView(binding.root) //setContentView(): içinde bulnduğumuz bu Activity'nin hangi XML layout'u kullandığını ve iletişim kurulacağını belirtir.

        //id : vertical_recycler_view(viewBinding tarafından pascalCase'e çevirilir.) olan componentin adapterine DogCardAdapter clasından gelecek bilgiyi atıyoruz.
        binding.verticalRecyclerView.adapter = DogCardAdapter(
            applicationContext,
            Layout.VERTICAL
        )

        // Specify fixed size to improve performance
        binding.verticalRecyclerView.setHasFixedSize(true)

        // Enable up button for backward navigation
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
