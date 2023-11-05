package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ConsumerOrder : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comsumer_order)

        val previous = findViewById<Button>(R.id.back)
        val next = findViewById<Button>(R.id.next_to_wholesale)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        next.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent1 = Intent(this, ConsumerWholesale::class.java)
            startActivity(intent1)
        }
    }
}
