package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ConsumerTagline : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_tagline)


        val home = findViewById<Button>(R.id.home)
        val profile = findViewById<Button>(R.id.profile)
        val products = findViewById<Button>(R.id.products)
        val report = findViewById<Button>(R.id.report)

        home.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, ConsumerTagline::class.java)
            startActivity(intent)
        }

        profile.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent1 = Intent(this, ConsumerSignedContracts::class.java)
            startActivity(intent1)
        }

        products.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent2 = Intent(this, ConsumerProductListingDIrectory::class.java)
            startActivity(intent2)
        }

        report.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent3 = Intent(this, ConsumerReports::class.java)
            startActivity(intent3)
        }
    }
}