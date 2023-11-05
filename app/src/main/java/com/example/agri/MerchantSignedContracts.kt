package com.example.agri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MerchantSignedContracts : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_signed_contracts)

        val next = findViewById<Button>(R.id.contracts)

        next.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, MerchantContractListing::class.java)
            startActivity(intent)
        }

        val previous= findViewById<ImageButton>(R.id.back)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, MerchantTagline::class.java)
            startActivity(intent)
        }
    }
}