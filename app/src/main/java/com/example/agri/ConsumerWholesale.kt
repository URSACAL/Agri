package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ConsumerWholesale : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_wholesale)

        val previous = findViewById<Button>(R.id.back)
        val next = findViewById<Button>(R.id.next)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, ConsumerOrder::class.java)
            startActivity(intent)
        }

        next.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent1 = Intent(this, ConsumerNohassle::class.java)
            startActivity(intent1)
        }
    }
}