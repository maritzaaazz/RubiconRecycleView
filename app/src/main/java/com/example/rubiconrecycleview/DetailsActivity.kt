package com.example.rubiconrecycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        // Mengambil referensi ke elemen-elemen tampilan pada layout activity_details.xml
        val headingNews : TextView = findViewById(R.id.heading)
        val mainNews : TextView = findViewById(R.id.details)
        val imageNews : ImageView = findViewById(R.id.image)

        // Mengambil data dari intent yang memulai aktivitas ini
        val bundle : Bundle?= intent.extras
        val heading = bundle!!.getString("heading")
        val imageId = bundle.getInt("imageId")
        val news = bundle.getString("news")

        // Menetapkan data ke elemen-elemen tampilan
        headingNews.text = heading // Menetapkan judul ke TextView "headingNews"
        mainNews.text = news // Menetapkan isi ke TextView "mainNews"
        imageNews.setImageResource(imageId)  // Menetapkan gambar ke ImageView "imageNews"

    }
}