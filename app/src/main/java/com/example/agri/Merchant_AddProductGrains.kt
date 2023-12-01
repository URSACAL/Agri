package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class Merchant_AddProductGrains : AppCompatActivity() {

    data class ProductClass(
        val ProductSKU: String,
        val ProductName: String,
        val ProductDesc: String,
        val ProductType: String,
        val ProductQuantity: String,
        var imageURL: String? = null
    )

    var imageURL: String? = null
    var uri: Uri? = null

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_addproductgrains)

        FirebaseApp.initializeApp(this)
        val firebaseDatabase = FirebaseDatabase.getInstance()

        val spinner1: Spinner = findViewById(R.id.productspinner)
        val options: Array<String> = resources.getStringArray(R.array.grains_option)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter

        // Set a listener if needed
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View?, position: Int, id: Long) {
                // Do something with the selected item
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Handle the situation where nothing is selected
            }
        }

        val Productsku = findViewById<EditText>(R.id.productsku)
        val Productname = findViewById<EditText>(R.id.productname)
        val Productdesc = findViewById<EditText>(R.id.productdesc)
        val Productquantity = findViewById<EditText>(R.id.editTextNumber)

        val Addproduct = findViewById<Button>(R.id.addproduct)
        val Productimage = findViewById<ImageView>(R.id.productimage)

        val activityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                val resultCode = result.resultCode
                if (resultCode == RESULT_OK) {
                    val data: Intent? = result.data
                    val uri = data?.data
                    Productimage.setImageURI(uri)
                } else {
                    Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show()
                }
            }

        Productimage.setOnClickListener {
            val photoPicker = Intent(Intent.ACTION_PICK)
            photoPicker.type = "image/*"
            activityResultLauncher.launch(photoPicker)
        }

        Addproduct.setOnClickListener {
            val database: FirebaseDatabase = FirebaseDatabase.getInstance()
            val reference: DatabaseReference =
                database.reference.child("Add Products") // Change this to a meaningful node name

            val productSKU = Productsku.text.toString()
            val productName = Productname.text.toString()
            val productDesc = Productdesc.text.toString()
            val productType = spinner1.selectedItem.toString()
            val productQuantity = Productquantity.text.toString()

            val productClass = ProductClass(productSKU, productName, productDesc, productType, productQuantity)

            // Generate a unique key for each product
            val productKey = reference.push().key

            if (productKey != null) {
                // Upload image to Firebase Storage
                val storageReference =
                    FirebaseStorage.getInstance().reference.child("product_images")
                        .child("$productKey.jpg")

                // Get the image from ImageView (productimage)
                val productImageView = findViewById<ImageView>(R.id.productimage)
                productImageView.isDrawingCacheEnabled = true
                productImageView.buildDrawingCache()
                val bitmap = (productImageView.drawable).toBitmap()

                // Convert the image to bytes
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
                val data = baos.toByteArray()

                // Upload the image to Firebase Storage
                val uploadTask = storageReference.putBytes(data)
                uploadTask.addOnFailureListener {
                    // Handle unsuccessful uploads
                    Toast.makeText(this, "Failed to upload image", Toast.LENGTH_SHORT).show()
                }.addOnSuccessListener {
                    // If the upload is successful, get the download URL
                    storageReference.downloadUrl.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val downloadUrl = task.result.toString()
                            // Set the imageURL in your data class
                            productClass.imageURL = downloadUrl

                            // Set other data and push to the database
                            reference.child(productKey).setValue(productClass)

                            Toast.makeText(this, "Product added successfully", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(this, "Failed to get download URL", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Failed to add product", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

//    private fun writeData(user: User) {
//
////        val myRef = firebaseDatabase.getReference("Android Tutorials")
//
//        database = FirebaseDatabase.getInstance()
//        reference = database.getReference("Android Tutorials")
//
//        reference.push().setValue(user)
//            .addOnSuccessListener {
//                Log.d(TAG, "Successfully wrote value.")
//            }
//            .addOnFailureListener {
//                Log.w(TAG, "Failed to write value.", it)
//            }
//    }





    
//    fun saveData() {
//        var Productsku = findViewById<EditText>(R.id.productsku)
//        var Productname = findViewById<EditText>(R.id.productname)
//        var Productdesc = findViewById<EditText>(R.id.productdesc)
//        var Productimage = findViewById<ImageView>(R.id.productimage)
//        val storageReference = uri?.lastPathSegment?.let {
//            FirebaseStorage.getInstance().reference.child("Android Images")
//                .child(it)
//        }
//
//        val builder = AlertDialog.Builder(this)
//        builder.setCancelable(false)
//        val dialog = builder.create()
//        dialog.show()
//
//        if (storageReference != null) {
//            uri?.let {
//                storageReference.putFile(it).addOnSuccessListener { taskSnapshot ->
//                    val uriTask = taskSnapshot.storage.downloadUrl
//                    while (!uriTask.isComplete);
//                    val urlImage = uriTask.result
//                    imageURL = urlImage.toString()
//                    uploadData()
//                    dialog.dismiss()
//                }.addOnFailureListener { e ->
//                    dialog.dismiss()
//                }
//            }
//        }
//    }
//    fun uploadData() {
//        val Productsku = findViewById<EditText>(R.id.productsku)
//        val Productname = findViewById<EditText>(R.id.productname)
//        val Productdesc = findViewById<EditText>(R.id.productdesc)
//
//        val ProductSKU = Productsku.text.toString()
//        val ProductName = Productname.text.toString()
//        val ProductDesc = Productdesc.text.toString()
//        val dataClass = imageURL?.let { DataClass(ProductSKU, ProductName, ProductDesc, it) }
//
//        FirebaseDatabase.getInstance().reference.child("Android Tutorials")
//            .setValue(dataClass).addOnCompleteListener { task ->
//                if (task.isSuccessful) {
////                    Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show()
//                    finish()
//                }
//            }.addOnFailureListener { e ->
////                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
//            }
//    }
//    data class DataClass(val ProductSKU: String, val ProductName: String, val ProductDesc: String, val imageURL: String) {
//        fun toMap(): Map<String, Any?> {
//            return mapOf(
//                "SKU" to ProductSKU,
//                "Name" to ProductName,
//                "Desc" to ProductDesc,
//                "imageURL" to imageURL
//            )
//        }


//        spinner.onItemSelectedListener
//        run {
//            val list: MutableList<String> = ArrayList()
//            list.add("Grain")
//            list.add("Vegetable")
//            list.add("Crops")
//            list.add("Fruits")
//
//            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, list)
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.setAdapter(adapter)
//
//        }





