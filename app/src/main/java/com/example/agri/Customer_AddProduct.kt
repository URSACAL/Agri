package com.example.agri

//noinspection SuspiciousImport


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.initialize


class Customer_AddProduct : AppCompatActivity(){


    data class User(val ProductSKU: String, val ProductName: String, val ProductDesc: String, val imageURL: String)
//
    var imageURL: String? = null
    var uri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_addproduct)



        Firebase.initialize(this)
        var firebaseDatabase = Firebase.database

        val Productsku = findViewById<EditText>(R.id.productsku)
        val Productname = findViewById<EditText>(R.id.productname)
        val Productdesc = findViewById<EditText>(R.id.productdesc)

        val spinner = findViewById<Spinner>(R.id.productspinner)
        val Addproduct = findViewById<Button>(R.id.addproduct)
        val Productimage = findViewById<ImageView>(R.id.productimage)

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


        val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val Productimage = findViewById<ImageView>(R.id.productimage)
            val resultCode = result.resultCode
            if (resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val uri = data?.data
                Productimage.setImageURI(uri)
            } else {
                Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show()
            }
        }


        Productimage.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View?) {
                val photoPicker = Intent(Intent.ACTION_PICK)
                photoPicker.type = "image/*"
                activityResultLauncher.launch(photoPicker)
            }
        })

        Addproduct.setOnClickListener(){
            var database: FirebaseDatabase = FirebaseDatabase.getInstance()
            var reference: DatabaseReference = database.reference

            val productSKU = Productsku.text.toString()
            val productName = Productname.text.toString()
            val productDesc = Productdesc.text.toString()

//            val user = imageURL?.let { it1 -> User(Productsku, Productname,Productdesc, it1) }
//            if (user != null) {
//                writeData(user)
//            }


            val ProductClass = ProductClass(productSKU, productName, productDesc)
            reference.child("Add Product").setValue(ProductClass)
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




}
