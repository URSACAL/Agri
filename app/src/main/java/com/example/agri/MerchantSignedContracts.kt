package com.example.agri

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MerchantSignedContracts : AppCompatActivity() {
    private var isPhotoUploaded = false
    private lateinit var selectedImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_signed_contracts)

        val next = findViewById<Button>(R.id.contracts)
        val imageView = findViewById<ImageView>(R.id.imageView7)
        val ownerNameEditText = findViewById<EditText>(R.id.MP_OwnerName)
        val ownerAddressEditText = findViewById<EditText>(R.id.MP_OwnerAddress)
        val ownerContactEditText = findViewById<EditText>(R.id.MP_OwnerContact)
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference: DatabaseReference = database.reference


        next.setOnClickListener {
            // Retrieve values from EditText fields
            database = FirebaseDatabase.getInstance()
            reference = database.getReference("MerchantSignedContracts")
            val ownerName = ownerNameEditText.text.toString()
            val ownerAddress = ownerAddressEditText.text.toString()
            val ownerContact = ownerContactEditText.text.toString()

            val contract = MerchantsignedontractsClass(
                ownerName,
                ownerAddress,
                ownerContact,
            )
            reference.child(ownerName).setValue(contract)

            // Check if any of the fields is empty
            if (ownerName.isEmpty() || ownerAddress.isEmpty() || ownerContact.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if an image is selected in the ImageView
            if (!isPhotoUploaded) {
                Toast.makeText(this, "Please upload a photo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // If all conditions are met, proceed to the next activity
            val intent = Intent(this, MerchantContractListing::class.java)
            // You can also pass the image URI to the next activity if needed
            intent.putExtra("IMAGE_URI", selectedImageUri.toString())
            startActivity(intent)
        }

        // Add functionality to upload a photo to imageView7
        imageView.setOnClickListener {
            // Use the gallery to select an image
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultLauncher.launch(intent)
        }

        val previous = findViewById<ImageButton>(R.id.back)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, MerchantTagline::class.java)
            startActivity(intent)
        }
    }

    // Handle the result of the image selection
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { uri ->
                // Set the selected image to imageView7
                val imageView = findViewById<ImageView>(R.id.imageView7)
                imageView.setImageURI(uri)
                isPhotoUploaded = true
                selectedImageUri = uri
            }
        }
    }
}
