package com.example.rubiconrecycleview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<News>
    lateinit var imageId : Array<Int>
    lateinit var price : Array<String>
    lateinit var heading : Array<String>
    lateinit var news : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inisialisasi data untuk ditampilkan di RecyclerView
        imageId = arrayOf(
            R.drawable.ar,
            R.drawable.br,
            R.drawable.cr,
            R.drawable.ds,
            R.drawable.es,
            R.drawable.fsp,
            R.drawable.gg
        )

        heading = arrayOf(
            "Jeep Wrangler Rubicon 2Dr",
            "Jeep Wrangler Rubicon 4Dr",
            "Jeep Wrangler Rubicon 4Dr SKT",
            "Jeep Wrangler Sahara 2Dr",
            "Jeep Wrangler Sahara 4Dr",
            "Jeep Wrangler Sport 4Dr",
            "Jeep Wrangler Gladiator"
        )

        news = arrayOf(
            getString(R.string.news_ar),
            getString(R.string.news_br),
            getString(R.string.news_cr),
            getString(R.string.news_ds),
            getString(R.string.news_es),
            getString(R.string.news_fsp),
            getString(R.string.news_gg),
        )

        price = arrayOf(
            "1.768.000.000",
            "1.973.000.000",
            "2.179.000.000",
            "1.821.000.000",
            "1.982.000.000",
            "1.993.000.000",
            "2.109.000.000"
        )

        newRecyclerView = findViewById(R.id.recyclerView) // Menemukan RecyclerView dalam layout
        newRecyclerView.layoutManager = LinearLayoutManager(this) // Mengatur LayoutManager RecyclerView sebagai LinearLayoutManager
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf() // Inisialisasi ArrayList untuk menyimpan data
        getUserdata() // Memanggil fungsi untuk mengisi data ke ArrayList dan menyiapkan adapter untuk RecyclerView
    }

    private fun getUserdata(){
        for (i in imageId.indices){
            val news = News(imageId[i],heading[i], price[i]) // Membuat objek News dengan data yang akan ditampilkan
            newArrayList.add(news) // Menambahkan objek News ke ArrayList
        }

        var adapter = MyAdapter(newArrayList) // Membuat adapter RecyclerView dengan data dari ArrayList
        newRecyclerView.adapter = adapter // Mengatur adapter untuk RecyclerView

        adapter.setOnItemClickListener(object : MyAdapter.onItemClickListener{
            // Mengatur tindakan yang akan dijalankan ketika item dalam daftar diklik
            override fun onItemClick(position: Int) {
                // Mengambil data yang terkait dengan item yang diklik
                val clickedHeading = heading[position]

                // Menampilkan pesan toast yang memberi tahu pengguna item apa yang telah diklik
                Toast.makeText(this@MainActivity, "You clicked on item: $clickedHeading",
                    Toast.LENGTH_SHORT).show()

                // Membuat Intent yang akan mengarahkan pengguna ke DetailsActivity
                val intent = Intent(this@MainActivity, DetailsActivity::class.java)

                // Menambahkan data tambahan ke Intent, seperti judul (heading), gambar (imageId),
                // berita (news), dan harga (price) yang berkaitan dengan item yang diklik
                intent.putExtra("heading", newArrayList[position].heading)
                intent.putExtra("imageId", newArrayList[position].titleImage)
                intent.putExtra("news", news[position])
                intent.putExtra("price", newArrayList[position].price)
                startActivity(intent)
            }

        })
    }
}