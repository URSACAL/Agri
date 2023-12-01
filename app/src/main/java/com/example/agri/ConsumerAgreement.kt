package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ConsumerAgreement : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_agreement)

        val agreement = findViewById<Button>(R.id.submit)

        agreement.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, ConsumerContractListing::class.java)
            startActivity(intent)
        }

    }
}