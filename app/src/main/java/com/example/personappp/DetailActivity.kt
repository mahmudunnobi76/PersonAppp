package com.example.personappp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.personappp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val name = intent.getStringExtra("name")
        val course = intent.getDoubleExtra("course",0.0)
        val center = intent.getIntExtra("center",0)
        val img = intent.getIntExtra("image",0)
        val desc = intent.getStringExtra("desc")

        binding.apply {
            personName.text = name
            personCourse.text = "Price: $"+course.toString()
            personCenter.text = "Quantity: "+center.toString()
            personImg.setImageResource(img)
            personDesc.text = desc
        }

    }
}