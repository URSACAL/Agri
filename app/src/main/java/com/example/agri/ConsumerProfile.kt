package com.example.agri

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference

class ConsumerProfile : AppCompatActivity() {
    private val PICK_IMAGE_REQUEST = 1
    private lateinit var captchaImageView: ImageView
    private lateinit var imageView: ImageView
    private lateinit var captchaEditText: EditText
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri

    // Add these declarations
    private var isPhotoUploaded = false
    private lateinit var selectedImageUri: Uri
    private var captchaText = ""

    private lateinit var databaseReference: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_profile)

        val ownersName = findViewById<EditText>(R.id.MP_OwnerName)
        val address = findViewById<EditText>(R.id.MP_OwnerAddress)
        val contactNumber = findViewById<EditText>(R.id.MP_OwnerContact)
        imageView = findViewById(R.id.imageView7)

        captchaImageView = findViewById(R.id.imageView8)
        captchaEditText = findViewById(R.id.captchaEditText)
        generateCaptcha()

        val submit = findViewById<Button>(R.id.submit)

        // Firebase Database initialization
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = database.getReference("ConsumerProfile")

        imageView.setOnClickListener {
            // Open image picker when clicking the ImageView
            openImagePicker()
        }

        submit.setOnClickListener {
            val ownersNameText = ownersName.text.toString()
            val addressText = address.text.toString()
            val contactNumberText = contactNumber.text.toString()

            // Check if any of the fields is empty
            if (ownersNameText.isEmpty() || addressText.isEmpty() || contactNumberText.isEmpty()) {
                // Show a Toast message indicating that all fields must be filled
                Toast.makeText(this@ConsumerProfile, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!isPhotoUploaded) {
                Toast.makeText(this, "Please upload a photo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Save data to Firebase Realtime Database
            val consumer = ConsumerprofileClass(
                ownersNameText,
                addressText,
                contactNumberText
            )

            // Use push() to generate a unique key for each entry
            val newConsumerRef = databaseReference.push()
            newConsumerRef.setValue(consumer)

            // Check captcha
            checkCaptcha(ownersNameText, addressText, contactNumberText)
        }
    }

    // ... (remaining functions remain unchanged)



    // ... (remaining functions remain unchanged)
private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val selectedImageUri = data.data

            // Set the selected image to the ImageView
            imageView.setImageURI(selectedImageUri)

            // Update the state variables
            isPhotoUploaded = true
            imageUri = selectedImageUri!!
        }
    }

    private fun generateCaptcha() {
        captchaText = generateRandomString(6)
        val captchaBitmap = textToBitmap(captchaText)
        captchaImageView.setImageBitmap(captchaBitmap)
    }

    private fun generateRandomString(length: Int): String {
        val charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        return (1..length)
            .map { charset.random() }
            .joinToString("")
    }

    private fun textToBitmap(text: String): Bitmap {
        val width = 200
        val height = 80
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = android.graphics.Canvas(bitmap)
        canvas.drawColor(Color.WHITE)

        val paint = android.graphics.Paint().apply {
            color = Color.BLACK
            textSize = 40f
        }

        val xPos = (width - paint.measureText(text)) / 2
        val yPos = (height / 2 - (paint.descent() + paint.ascent()) / 2)

        canvas.drawText(text, xPos, yPos, paint)
        return bitmap
    }

    private fun checkCaptcha(ownerName: String, address: String, contactNumber: String) {
        val userInput = captchaEditText.text.toString()
        if (userInput == captchaText) {
            // Captcha is correct
            generateCaptcha()
            captchaEditText.setText("")
            Toast.makeText(this@ConsumerProfile, "You have submitted successfully!", Toast.LENGTH_SHORT).show()
            showAlert(ownerName, address, contactNumber)
        } else {
            // Captcha is incorrect
            generateCaptcha()
            captchaEditText.setHint("try again")
        }
    }
    private fun showAlert(ownerName: String, address: String, contactNumber: String) {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        alertDialogBuilder.setTitle("Registration Submitted")
        alertDialogBuilder.setMessage("You've successfully submitted your registration and pending for approval")

        // Set the positive button and its click listener
        alertDialogBuilder.setPositiveButton("Consumer Profile", DialogInterface.OnClickListener { _, _ ->
            // Navigate to another page (replace YourTargetActivity::class.java with the actual activity)
            val intent = Intent(this@ConsumerProfile, ConsumerSignedContracts::class.java)
            intent.putExtra("ownersNameText", ownerName)
            intent.putExtra("addressText", address)
            intent.putExtra("contactNumberText", contactNumber)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Create and show the alert dialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    // Handle the result of the image selection
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
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