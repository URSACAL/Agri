package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class ConsumerNohassle : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_nohassle)

        val previous = findViewById<Button>(R.id.back)
        val signup = findViewById<Button>(R.id.signup)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, ConsumerWholesale::class.java)
            startActivity(intent)
        }


        signup.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent1 = Intent(this, ConsumerSignup::class.java)
            startActivity(intent1)
            }
        }
    }
