package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage


class MerchantEditProducts : AppCompatActivity() {

    private lateinit var ProductSKU: TextView
    private lateinit var ProductName: TextView
    private lateinit var ProductDesc: TextView
    private lateinit var ProductType: TextView
    private lateinit var ProductQuantity: TextView
    private lateinit var ProductImage: ImageView

    private var key: String = ""
    private var imageURL: String = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_edit_products)
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
            imageURL = bundle.getString("imageURl").toString()
            Glide.with(this).load(bundle.getString("imageURl")).into(ProductImage)
        }

        update.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, MerchantUpdates::class.java)
            startActivity(intent)
        }
        delete.setOnClickListener {
            val reference = FirebaseDatabase.getInstance().getReference("Add Products")
            val storage = FirebaseStorage.getInstance()
            val storageReference = storage.getReferenceFromUrl(imageURL)

            storageReference.delete().addOnSuccessListener {
                reference.child(key).removeValue().addOnSuccessListener {
                    Toast.makeText(this@MerchantEditProducts, "Deleted", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, MerchantProductListingDirectory::class.java))
                    finish()
                }.addOnFailureListener { e ->
                    Log.e("MerchantEditProducts", "Error removing data from Firebase: ${e.message}", e)
                    // Handle failure
                }
            }.addOnFailureListener { e ->
                Log.e("MerchantEditProducts", "Error deleting image from storage: ${e.message}", e)
                // Handle failure
            }
        }

        update.setOnClickListener {
            val intent = Intent(this@MerchantEditProducts, MerchantUpdates::class.java)
                .putExtra("productSKU", ProductSKU.text.toString())
                .putExtra("productName", ProductName.text.toString())
                .putExtra("productDesc", ProductDesc.text.toString())
                .putExtra("productType", ProductType.text.toString())
                .putExtra("productQuantity", ProductQuantity.text.toString())
                .putExtra("imageURl", imageURL)
                .putExtra("Key", key)

            startActivity(intent)
        }


    }

    }



