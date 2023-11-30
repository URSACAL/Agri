package com.example.agri

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Consumer_ProductListing : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference
    private lateinit var productDataTextView: TextView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_product_listing)

        databaseReference = FirebaseDatabase.getInstance().reference.child("Add Products")
        productDataTextView = findViewById(R.id.productDataTextView)

        retrieveData()
    }

    private fun retrieveData() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                if (dataSnapshot.exists()) {
                    val productClass = dataSnapshot.getValue(ProductClass::class.java)
                    // Update your UI or perform actions with the retrieved data
                    productDataTextView.text = "Product SKU: ${productClass?.productSKU}\n" +
                            "Product Name: ${productClass?.productName}\n" +
                            "Product Description: ${productClass?.productDesc}"
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Handle error
                productDataTextView.text = "Failed to retrieve data: ${databaseError.message}"
            }
        })
    }
}