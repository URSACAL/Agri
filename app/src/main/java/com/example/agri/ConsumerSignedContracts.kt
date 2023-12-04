package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConsumerSignedContracts : AppCompatActivity() {


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_signed_contracts)

        val next = findViewById<Button>(R.id.contracts)
        val imageView = findViewById<ImageView>(R.id.imageView7)
        val ownerNameEditText = findViewById<EditText>(R.id.MP_OwnerName)
        val ownerAddressEditText = findViewById<EditText>(R.id.MP_OwnerAddress)
        val ownerContactEditText = findViewById<EditText>(R.id.MP_OwnerContact)
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference: DatabaseReference = database.reference
        val previous = findViewById<ImageButton>(R.id.back)


        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, ConsumerTagline::class.java)
            startActivity(intent)
        }
    }
}

