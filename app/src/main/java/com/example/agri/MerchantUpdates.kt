package com.example.agri;

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class MerchantUpdates : AppCompatActivity() {


    private lateinit var ProductSKU: EditText
    private lateinit var ProductName: EditText
    private lateinit var ProductImage: ImageView
    private lateinit var ProductDesc: EditText
    private lateinit var ProductType: EditText
    private lateinit var ProductQuantity: EditText
    private lateinit var updateButton: Button


    private var uri: Uri? = null
    private var imageURl: String? = null
    private var key: String? = null
    private var oldImageURL: String? = null

    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference

    private val activityResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback { result: ActivityResult ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data = result.data
                    uri = data?.data
                    ProductImage.setImageURI(uri)
                } else {
                    Toast.makeText(this@MerchantUpdates, "No Image Selected", Toast.LENGTH_SHORT)
                        .show()
                }
            })

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_updates)

        ProductSKU = findViewById(R.id.Uproductsku)
        ProductName = findViewById(R.id.Uproductname)
        ProductImage = findViewById(R.id.Uproductimage)
        ProductDesc = findViewById(R.id.Uproductdesc)
        ProductType = findViewById(R.id.Uproducttype)
        ProductQuantity = findViewById(R.id.Uproductquan)
        updateButton = findViewById(R.id.Uproduct)

        val bundle = intent.extras
        if (bundle != null) {
            oldImageURL = bundle.getString("imageURl")
            ProductSKU.setText(bundle.getString("productSKU"))
            ProductName.setText(bundle.getString("productName"))
            ProductDesc.setText(bundle.getString("productDesc"))
            ProductType.setText(bundle.getString("productType"))
            ProductQuantity.setText(bundle.getString("productQuantity"))
            key = bundle.getString("Key")
            Glide.with(this@MerchantUpdates).load(bundle.getString("imageURL")).into(ProductImage)
        }



        databaseReference =
            FirebaseDatabase.getInstance().getReference("Add Products").child(key!!)

        ProductImage.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }

        updateButton.setOnClickListener {

            saveData()
            val intent = Intent(this@MerchantUpdates, MerchantProductListingDirectory::class.java)
            startActivity(intent)
        }
    }

    private fun saveData() {
        storageReference = FirebaseStorage.getInstance().reference.child("product_images")
            .child(uri?.lastPathSegment!!)


        storageReference.putFile(uri!!)
            .addOnSuccessListener { taskSnapshot: UploadTask.TaskSnapshot ->
                storageReference.downloadUrl
                    .addOnSuccessListener { uri ->
                        imageURl = uri.toString()
                        updateData()

                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this@MerchantUpdates, "Failed to get image URL: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
            }
            .addOnFailureListener { e: Exception ->
                Toast.makeText(this@MerchantUpdates, "Failed to upload image: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }


    private fun updateData() {
        val productSKU = ProductSKU.text.toString().trim()
        val productName = ProductName.text.toString().trim()
        val productDesc = ProductDesc.text.toString().trim()
        val productType = ProductType.text.toString().trim()
        val productQuantity = ProductQuantity.text.toString().trim()


        val productClass = ProductClass(
            productSKU,
            productName,
            productDesc,
            productType,
            productQuantity,

        )

        databaseReference.setValue(productClass)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val reference =
                        FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL!!)
                    reference.delete()
                    Toast.makeText(this@MerchantUpdates, "Updated", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
            .addOnFailureListener { e: Exception ->
                Toast.makeText(this@MerchantUpdates, e.message.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
    }
}
