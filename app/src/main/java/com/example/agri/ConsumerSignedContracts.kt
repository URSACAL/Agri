package com.example.agri

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ConsumerSignedContracts : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_signed_contracts)


        val imageView = findViewById<ImageView>(R.id.imageView7)
        val ownerNameEditText = findViewById<EditText>(R.id.MP_OwnerName)
        val ownerAddressEditText = findViewById<EditText>(R.id.MP_OwnerAddress)
        val ownerContactEditText = findViewById<EditText>(R.id.MP_OwnerContact)
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference: DatabaseReference = database.reference
        val previous = findViewById<ImageButton>(R.id.back)

        // Retrieve data from Intent
        val dataOwnersName = intent.getStringExtra("dataOwnersName")
        val dataAddress = intent.getStringExtra("dataAddress")
        val dataContactNumber = intent.getStringExtra("dataContactNumber")
        val imageUri = intent.getStringExtra("IMAGE_URI")

        // Set values to EditText fields
        ownerNameEditText.setText(dataOwnersName)
        ownerAddressEditText.setText(dataAddress)
        ownerContactEditText.setText(dataContactNumber)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, ConsumerTagline::class.java)
            startActivity(intent)
        }
    }
}

