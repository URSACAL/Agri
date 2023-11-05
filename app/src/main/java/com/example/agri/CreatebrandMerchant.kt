package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CreatebrandMerchant : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createbrand_merchant)

        val previous = findViewById<Button>(R.id.previous)
        val next = findViewById<Button>(R.id.next_to_product_offer)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, Merchant::class.java)
            startActivity(intent)
        }

            next.setOnClickListener {
                // Define the behavior here (e.g., navigate to a new activity)
                val intent1 = Intent(this, productoffering_Merchant::class.java)
                startActivity(intent1)
            }

    }
}