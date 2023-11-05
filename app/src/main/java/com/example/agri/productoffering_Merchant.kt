package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class productoffering_Merchant : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productoffering_merchant)

        val previous = findViewById<Button>(R.id.previous)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, CreatebrandMerchant::class.java)
            startActivity(intent)
        }

            val signup = findViewById<Button>(R.id.signup)

            signup.setOnClickListener {
                // Define the behavior here (e.g., navigate to a new activity)
                val intent1 = Intent(this, SignupMerchant::class.java)
                startActivity(intent1)
            }


    }
}