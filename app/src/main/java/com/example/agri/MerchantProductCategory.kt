package com.example.agri

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MerchantProductCategory : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_product_category)

        val grains = findViewById<ImageView>(R.id.grains)
        val fruits = findViewById<ImageView>(R.id.fruit)
        val vegetables = findViewById<ImageView>(R.id.vegetables)
        val rootcrops = findViewById<ImageView>(R.id.rootcrop)

        grains.setOnClickListener {
            // Navigate to Merchant_AddProductGrains activity
            val intent = Intent(this, Merchant_AddProductGrains::class.java)
            startActivity(intent)
        }

        fruits.setOnClickListener {
            // Navigate to Merchant_AddProductFruits activity
            val intent = Intent(this, Merchant_AddProductFruits::class.java)
            startActivity(intent)
        }

        vegetables.setOnClickListener {
            // Navigate to Merchant_AddProductVegetables activity
            val intent = Intent(this, Merchant_AddProductVegetables::class.java)
            startActivity(intent)
        }

        rootcrops.setOnClickListener {
            // Navigate to Merchant_AddProductRootcrops activity
            val intent = Intent(this, Merchant_AddProductRootcrops::class.java)
            startActivity(intent)
        }
    }
}




