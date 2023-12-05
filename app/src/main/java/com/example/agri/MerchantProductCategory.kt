package com.example.agri

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
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
            showAlert()
        }

        fruits.setOnClickListener {
            showAlert1()
        }

        vegetables.setOnClickListener {
            showAlert2()
        }

        rootcrops.setOnClickListener {
            showAlert3()
        }
    }
    private fun showAlert() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        alertDialogBuilder.setTitle("Manage Product Listing")
        alertDialogBuilder.setMessage("Manage Inventory: Changes Reflect Instantly in Your Listings.")

        // Set the negative button and its click listener
        alertDialogBuilder.setNegativeButton("Edit", DialogInterface.OnClickListener { _, _ ->
            // Navigate to MerchantProducts activity (replace with the actual activity)
            val intent = Intent(this@MerchantProductCategory, MerchantProductListingDirectory::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })
        // Set the positive button and its click listener
        alertDialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener { _, _ ->
            // Navigate to MerchantSignedContracts activity
            val intent = Intent(this@MerchantProductCategory, Merchant_AddProductGrains::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Create and show the alert dialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    private fun showAlert1() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        alertDialogBuilder.setTitle("Manage Product Listing")
        alertDialogBuilder.setMessage("Manage Inventory: Changes Reflect Instantly in Your Listings.")

        // Set the negative button and its click listener
        alertDialogBuilder.setNegativeButton("Edit", DialogInterface.OnClickListener { _, _ ->
            // Navigate to MerchantProducts activity (replace with the actual activity)
            val intent = Intent(this@MerchantProductCategory, Merchant_ProductListingDirectory_Fruits::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Set the positive button and its click listener
        alertDialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener { _, _ ->
            // Navigate to MerchantSignedContracts activity
            val intent = Intent(this@MerchantProductCategory, Merchant_ProductListingDirectory_Fruits::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Create and show the alert dialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    private fun showAlert2() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        alertDialogBuilder.setTitle("Manage Product Listing")
        alertDialogBuilder.setMessage("Manage Inventory: Changes Reflect Instantly in Your Listings.")

        // Set the negative button and its click listener
        alertDialogBuilder.setNegativeButton("Edit", DialogInterface.OnClickListener { _, _ ->
            // Navigate to MerchantProducts activity (replace with the actual activity)
            val intent = Intent(this@MerchantProductCategory, MerchantProductListingDirectory::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Set the positive button and its click listener
        alertDialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener { _, _ ->
            // Navigate to MerchantSignedContracts activity
            val intent = Intent(this@MerchantProductCategory, Merchant_AddProductVegetables::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Create and show the alert dialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
    private fun showAlert3() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        alertDialogBuilder.setTitle("Manage Product Listing")
        alertDialogBuilder.setMessage("Manage Inventory: Changes Reflect Instantly in Your Listings.")


        // Set the negative button and its click listener
        alertDialogBuilder.setNegativeButton("Edit", DialogInterface.OnClickListener { _, _ ->
            // Navigate to MerchantProducts activity (replace with the actual activity)
            val intent = Intent(this@MerchantProductCategory, MerchantProductListingDirectory::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Set the positive button and its click listener
        alertDialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener { _, _ ->
            // Navigate to MerchantSignedContracts activity
            val intent = Intent(this@MerchantProductCategory, Merchant_AddProductSpices::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Create and show the alert dialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

}




