package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

// Create a singleton class to hold your data
object MerchantDataHolder {
    var ownerName: String? = null
    var address: String? = null
    var contactNumber: String? = null
    // Add other fields if needed
}

class MerchantSignedContracts : AppCompatActivity() {

    private lateinit var ownerNameEditText: TextView
    private lateinit var ownerAddressEditText: TextView
    private lateinit var ownerContactEditText: TextView
    private lateinit var imageView: ImageView

    private lateinit var databaseReference: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_signed_contracts)

        imageView = findViewById(R.id.imageView7)
        ownerNameEditText = findViewById(R.id.MP_OwnerName)
        ownerAddressEditText = findViewById(R.id.MP_OwnerAddress)
        ownerContactEditText = findViewById(R.id.MP_OwnerContact)

        val previous = findViewById<ImageButton>(R.id.back)

        // Firebase Database initialization
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("ConsumerProfile")

        // Retrieve values passed through Intent or from the singleton class
        val ownerName = intent.getStringExtra("ownersNameText") ?: MerchantDataHolder.ownerName
        val address = intent.getStringExtra("addressText") ?: MerchantDataHolder.address
        val contactNumber = intent.getStringExtra("contactNumberText") ?: MerchantDataHolder.contactNumber

        // Update TextViews with retrieved values
        ownerNameEditText.text = ownerName
        ownerAddressEditText.text = address
        ownerContactEditText.text = contactNumber

        // Update the singleton class with the retrieved values
        MerchantDataHolder.ownerName = ownerName
        MerchantDataHolder.address = address
        MerchantDataHolder.contactNumber = contactNumber

        // You may need to load the image using a library like Glide or Picasso
        // Example using Glide:
        // Glide.with(this@ConsumerSignedContracts).load(it.imageUrl).into(imageView)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, MerchantTagline::class.java)
            startActivity(intent)
        }
    }
}
