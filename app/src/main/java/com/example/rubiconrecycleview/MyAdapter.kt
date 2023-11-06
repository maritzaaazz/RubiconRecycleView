package com.example.rubiconrecycleview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

// Adapter ini digunakan untuk menampilkan daftar berita dalam RecyclerView
class MyAdapter(private val newList: ArrayList<News>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private lateinit var mListener : onItemClickListener

    // Interface untuk menangani klik item pada RecyclerView
    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    // Metode ini digunakan untuk mengatur listener yang akan menangani klik item
    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    // Metode ini digunakan untuk membuat ViewHolder yang akan menampilkan item pada RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflasi layout item dari XML (list_item.xml)
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,
            parent, false)
        return MyViewHolder(itemView, mListener)
    }

    // Metode ini mengembalikan jumlah item dalam daftar
    override fun getItemCount(): Int {
        return newList.size
    }

    // Metode ini digunakan untuk mengikat data ke ViewHolder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = newList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.heading
        holder.tvPrice.text = currentItem.price
    }

    // ViewHolder yang mewakili tampilan satu item dalam RecyclerView
    class MyViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){
        val titleImage: ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading: TextView = itemView.findViewById(R.id.tvHeading)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)

        init {
            // Menambahkan listener klik item pada ViewHolder
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}