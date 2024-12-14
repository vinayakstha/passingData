package com.example.task

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task.databinding.ActivityDestinationBinding

class DestinationActivity : AppCompatActivity() {
    lateinit var binding: ActivityDestinationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDestinationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name :String = intent.getStringExtra("name").toString()
        val email :String = intent.getStringExtra("email").toString()
        val gender :String = intent.getStringExtra("gender").toString()
        val city :String = intent.getStringExtra("city").toString()
        val country :String = intent.getStringExtra("country").toString()
        val image1 = intent.getIntExtra("image1", 0)

        binding.viewName.text = "Welcome: $name"
        binding.viewEmail.text = "Email: $email"
        binding.viewGender.text = "Gender: $gender"
        binding.viewCity.text = "City: $city"
        binding.viewCountry.text = "Country: $country"
        binding.viewImage.setImageResource(image1)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}