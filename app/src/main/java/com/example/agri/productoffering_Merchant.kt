package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class productoffering_Merchant : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productoffering_merchant)

        val blogin = findViewById<Button>(R.id.Blogin)
        val signup = findViewById<Button>(R.id.signup)

        blogin.setOnClickListener {
            val intent = Intent(this, Merchant_Login::class.java)
            startActivity(intent)
        }

        signup.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent1 = Intent(this, SignupMerchant::class.java)
            startActivity(intent1)
        }


    }
}