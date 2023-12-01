package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class MerchantEditProducts : AppCompatActivity() {

    private lateinit var ProductSKU: TextView
    private lateinit var ProductName: TextView
    private lateinit var ProductDesc: TextView
    private lateinit var ProductType: TextView
    private lateinit var ProductQuantity: TextView
    private lateinit var ProductImage: ImageView

    private var key: String = ""
    private var imageUrl: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_product_listing)
        val delete = findViewById<Button>(R.id.delete)
        val update = findViewById<Button>(R.id.update)

        ProductSKU = findViewById(R.id.PSKU)
        ProductName = findViewById(R.id.Pname)
        ProductDesc = findViewById(R.id.Pdesc)
        ProductType = findViewById(R.id.Ptype)
        ProductQuantity = findViewById(R.id.Pquantity)
        ProductImage = findViewById(R.id.Pimage)


        val bundle = intent.extras
        if (bundle != null) {
            ProductSKU.text = bundle.getString("productSKU")
            ProductName.text = bundle.getString("productName")
            ProductDesc.text = bundle.getString("productDesc")
            ProductType.text = bundle.getString("productType")
            ProductQuantity.text = bundle.getString("productQuantity")
            key = bundle.getString("Key").toString()
            imageUrl = bundle.getString("Image").toString()
            Glide.with(this).load(bundle.getString("imageURl")).into(ProductImage)
        }

        update.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, MerchantUpdate::class.java)
            startActivity(intent)
        }

    }



}
