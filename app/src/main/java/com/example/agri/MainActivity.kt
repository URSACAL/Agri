package com.example.agri

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val merchant = findViewById<Button>(R.id.merchantbutton)

        merchant.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, Merchant::class.java)
            startActivity(intent)
        }

        val consumer = findViewById<Button>(R.id.consumerbutton)

        consumer.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent1 = Intent(this, ConsumerOrder::class.java)
            startActivity(intent1)
        }
    }
}