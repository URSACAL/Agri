package com.example.agri

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

class MerchantProfile : AppCompatActivity() {

    private val PICK_IMAGE_REQUEST = 1
    private lateinit var captchaImageView: ImageView
    private lateinit var imageView: ImageView
    private lateinit var captchaEditText: EditText
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri


    private var isPhotoUploaded = false
    private lateinit var selectedImageUri: Uri
    private var captchaText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mechant_profile)

        val OwnersName = findViewById<EditText>(R.id.Customer)
        val Address = findViewById<EditText>(R.id.address)
        val ContactNumber = findViewById<EditText>(R.id.number)
        val BusinessName = findViewById<EditText>(R.id.businessName)
        imageView = findViewById(R.id.imageView7)

        captchaImageView = findViewById(R.id.imageView8)
        captchaEditText = findViewById(R.id.captchaEditText)


        generateCaptcha()

        val submit = findViewById<Button>(R.id.submit)
        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference: DatabaseReference = database.reference

        imageView.setOnClickListener {
            // Open image picker when clicking the ImageView
            openImagePicker()
        }

        submit.setOnClickListener {
            database = FirebaseDatabase.getInstance()
            reference = database.getReference("MerchantProfile")

            val ownersName = OwnersName.text.toString()
            val address = Address.text.toString()
            val contactNumber = ContactNumber.text.toString()
            val businessName = BusinessName.text.toString()

            // Check if any of the fields is empty
            if (ownersName.isEmpty() || address.isEmpty() || contactNumber.isEmpty() || businessName.isEmpty()) {
                // Show a Toast message indicating that all fields must be filled
                Toast.makeText(
                    this@MerchantProfile,
                    "Please fill in all fields",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            if (!isPhotoUploaded) {
                Toast.makeText(this, "Please upload a photo", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val merchant = MerchantprofileClass(
                ownersName,
                address,
                contactNumber,
                businessName
            )
            reference.child(ownersName).setValue(merchant)

            checkCaptcha()

        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, PICK_IMAGE_REQUEST)
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

    private fun checkCaptcha() {
        val userInput = captchaEditText.text.toString()
        if (userInput == captchaText) {
            // Captcha is correct
            // Add your logic here
            generateCaptcha()
            captchaEditText.setText("")
            Toast.makeText(
                this@MerchantProfile,
                "You have submitted successfully!",
                Toast.LENGTH_SHORT
            ).show()
            showAlert()
        } else {
            // Captcha is incorrect
            // Add your logic here
            generateCaptcha()
            captchaEditText.setText("try again")
        }
    }

    private fun showAlert() {
        val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this)

        // Set the alert dialog title and message
        alertDialogBuilder.setTitle("Registration Submitted")
        alertDialogBuilder.setMessage("You've successfully submitted your registration and pending for approval")

        // Set the positive button and its click listener
        alertDialogBuilder.setPositiveButton(
            "Merchant Profile",
            DialogInterface.OnClickListener { _, _ ->
                // Navigate to another page (replace YourTargetActivity::class.java with the actual activity)
                val intent = Intent(this@MerchantProfile, MerchantSignedContracts::class.java)
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



