package com.example.affirmations.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.affirmations.R
import com.example.affirmations.model.Affirmation

/**
 * Adapter for the [RecyclerView] in [MainActivity]. Displays [Affirmation] data object.
 */
//RecyclerView kullanımı; görünümleri(view), kodları layout'a bağlamak ve ekranda gösterebilmek için RecyclerView.Adapter class'ını extend edecek bir Adapter oluşturmamız gerekiyor.
//ItemAdapter class'ını bunun için oluşturup RecyclerView.Adapter clasından extend ettik.

class ItemAdapter(private val context: Context, private val dataset: List<Affirmation>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    // Each data item is just an Affirmation object.
    //Listedeki her bir öğe, bir görünüm tutucu(view holder) nesne tarafından tanımlanır. Görünüm tutucu(view holder) oluşturulduktan sonra, RecyclerView onu verilerine bağlar.
    //Buradaki görünüm tutucu(view holder):ItemViewHolder olarak tanımladık ve bu RecyclerView.ViewHolder'u extends ederek tanımlanır.
    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) { // Görünümleri tutacak olan sınıf. Her bir satırımızın içinde bulunan bileşenleri tanımlama işleminin yapıldığı sınıftır.
                                                                                   // yani list_item.xml olarak oluşturduğumuz her bir satırı temsil eden layouttaki view ları burada tanımlıyoruz.
        val textView: TextView = view.findViewById(R.id.item_title) //id:item_title olan view componentini bulup textView değişkenine atar.
        val imageView: ImageView = view.findViewById(R.id.item_image) //id:item_image olan view componentini bulup imageView değişkenine atar.
    }

    /**
     * Create new views (invoked by the layout manager)
     */
    //Sınıfımızı RecyclerView.Adapter sınıfından türettiğimiz için bazı metotları implement etmemiz gerekiyor.
    //Bunlar: onCreateViewHolder, onBindViewHolder, getItemCount metodlarıdır.
    //onCreateViewHolder metoduyla, xml i bağlama işlemini burada yapacağız.
    //Bu method adaptör oluşturulduğunda oluşturduğumuz ViewHolder'ın başlatılması ve hangi layoutta oluşturulmasını belirlemek için çağrılır.
    //bu metod, ViewHolder'ı ve ilişkili Görünümü oluşturur ve başlatır, ancak görünümün içeriğini doldurmaz—ViewHolder henüz belirli verilere bağlanmamıştır.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder { //list_item.xml olarak oluşturduğumuz her bir satırı temsil eden layout'u viewHolder ile ilişkilendirecek.
        // create a new view
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false) // xml layout ile ViewHolder kodları birbirine bağlamaya yarar.
        return ItemViewHolder(adapterLayout)
    }

    /**
     * Replace the contents of a view (invoked by the layout manager)
     */
    //RecyclerView, bir ViewHolder'ı verilerle ilişkilendirmek için bu yöntemi çağırır. Uygun verileri getirir ve verileri, görünüm tutucunun layout'unu doldurmak için kullanır.
    //layout içerisinde hangi verileri göstereceğimizi sağlayacak. onCreateViewHolder’dan dönen verileri bağlama işlemini gerçekleştirildiği metotdur.
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.textView.text =  context.resources.getString(item.stringResourceId)
        holder.imageView.setImageResource(item.imageResourceId)
    }

    /**
     * Return the size of your dataset (invoked by the layout manager)
     */
    //RecyclerView, veri kümesinin boyutunu almak için bu yöntemi çağırır.
    override fun getItemCount(): Int {//xml in kaç kez oluşturulacağını verecek. ItemAdapter clasının constructrundan gelen datasetin eleman sayısını döndüren metottur.
        return dataset.size
    }

    override fun getItemViewType(position: Int): Int { //bu metod view tipini temsil eden bir integer dönderir.
        return R.layout.list_item
    }
}