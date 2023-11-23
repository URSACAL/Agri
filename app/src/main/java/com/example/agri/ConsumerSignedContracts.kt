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

class ConsumerSignedContracts : AppCompatActivity() {
    private var isPhotoUploaded = false
    private lateinit var selectedImageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_signed_contracts)

        val next = findViewById<Button>(R.id.contracts)

        next.setOnClickListener {
            // Retrieve data from EditText fields
            val imageView = findViewById<ImageView>(R.id.imageView7)
            val name = findViewById<EditText>(R.id.MP_OwnerName).text.toString()
            val address = findViewById<EditText>(R.id.MP_OwnerAddress).text.toString()
            val contact = findViewById<EditText>(R.id.MP_OwnerContact).text.toString()

            // Check if an image has been uploaded
            if (!isPhotoUploaded) {
                Toast.makeText(this, "Please upload a photo", Toast.LENGTH_SHORT).show()
            } else if (name.isEmpty() || address.isEmpty() || contact.isEmpty()) {
                // Show a toast or an alert indicating that fields are required
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            } else {
                // All conditions met, proceed to the next activity
                val intent = Intent(this, ConsumerContractListing::class.java)
                // You can also pass the image URI to the next activity if needed
                intent.putExtra("IMAGE_URI", selectedImageUri.toString())
                startActivity(intent)
            }
        }

        // Add functionality to upload a photo to imageView7
        val uploadPhotoButton = findViewById<ImageView>(R.id.imageView7)
        uploadPhotoButton.setOnClickListener {
            // Use the gallery to select an image
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            resultLauncher.launch(intent)
        }

        val previous = findViewById<ImageButton>(R.id.back)

        previous.setOnClickListener {
            // Define the behavior here (e.g., navigate to a new activity)
            val intent = Intent(this, ConsumerTagline::class.java)
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
