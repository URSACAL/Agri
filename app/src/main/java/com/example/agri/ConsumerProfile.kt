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


    private var captchaText = ""
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_profile)

        val OwnersName = findViewById<EditText>(R.id.MP_OwnerName)
        val Address = findViewById<EditText>(R.id.MP_OwnerAddress)
        val ContactNumber = findViewById<EditText>(R.id.MP_OwnerContact)

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
            reference = database.getReference("ConsumerProfile")

            val OwnersName = OwnersName.text.toString()
            val Address = Address.text.toString()
            val ContactNumber = ContactNumber.text.toString()



            val Merchant = ConsumerprofileClass(
                OwnersName,
                Address,
                ContactNumber

            )
            reference.child(OwnersName).setValue(Merchant)

            checkCaptcha()
            showAlert()
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
            Toast.makeText(this@ConsumerProfile, "You have submitted successfully!", Toast.LENGTH_SHORT).show()
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
        alertDialogBuilder.setPositiveButton("Consumer Profille", DialogInterface.OnClickListener { _, _ ->
            // Navigate to another page (replace YourTargetActivity::class.java with the actual activity)
            val intent = Intent(this@ConsumerProfile, ConsumerSignedContracts::class.java)
            startActivity(intent)
            finish() // Optional: Close the current activity if needed
        })

        // Create and show the alert dialog
        val alertDialog: AlertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}
