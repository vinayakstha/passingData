package com.example.task

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.task.databinding.ActivityDataPassingBinding

class DataPassingActivity : AppCompatActivity(){
    lateinit var binding: ActivityDataPassingBinding
    val countries = arrayOf("Nepal","India","US","Spain","Australia","Canada","China","England","France")
    val cities = arrayOf("Kathmandu", "Lalitpur","Bhaktapur","Kanchanpur","Birgunj",
                        "Washington DC","Barcelona","Madrid","Perth","Sydney","Manchester")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding =ActivityDataPassingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerAdapter = ArrayAdapter(this@DataPassingActivity ,
            android.R.layout.simple_spinner_dropdown_item,
            cities)
        binding.spinnerCity.adapter = spinnerAdapter
//change1
        val autoCompleteAdapter = ArrayAdapter(this@DataPassingActivity,
            android.R.layout.simple_list_item_1,
            countries)
        binding.autoCompleteCountry.setAdapter(autoCompleteAdapter)
        binding.autoCompleteCountry.threshold =2

        binding.btnSubmit.setOnClickListener {
            if (binding.editName.text.isEmpty()){
                binding.editName.error = "Name can't be empty"
            }else if (binding.editPassword.text.isEmpty()||binding.editPassword.text.length<8){
                binding.editPassword.error = "Invalid Password"
            }else if (!binding.checkBoxAgree.isChecked){
                Toast.makeText(this@DataPassingActivity,
                    "Please click on i agree",
                    Toast.LENGTH_LONG).show()
            }
            else {
                val intent = Intent(this@DataPassingActivity,
                    DestinationActivity::class.java)
                val name: String = binding.editName.text.toString()
                val email: String = binding.editEmail.text.toString()
                val gender: String = when {
                    binding.radioMale.isChecked -> "Male"
                    binding.radioFemale.isChecked -> "Female"
                    else -> "Others"
                }
                val city: String = binding.spinnerCity.selectedItem.toString()
                val country: String = binding.autoCompleteCountry.text.toString()

                intent.putExtra("name", name)
                intent.putExtra("gender", gender)
                intent.putExtra("email", email)
                intent.putExtra("city", city)
                intent.putExtra("country", country)
                intent.putExtra("image1", R.drawable.darwizzy)
                intent.putExtra("countries", arrayOf(countries))

                startActivity(intent)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}