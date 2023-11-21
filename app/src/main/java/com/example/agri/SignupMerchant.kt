package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest


class SignupMerchant : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_merchant)

        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val confirmpassword = findViewById<EditText>(R.id.editTextTextConfirmPassword)
        val signup = findViewById<Button>(R.id.signup)

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference: DatabaseReference = database.reference


        signup.setOnClickListener(View.OnClickListener {

            database = FirebaseDatabase.getInstance()
            reference = database.getReference("Account")

            val Email = email.text.toString()
            val Password =password.text.toString()
            val ConfirmPassword = confirmpassword.text.toString()
            val HelperClass = HelperClass(Email, Password, ConfirmPassword)
            reference.child(Email).setValue(HelperClass)
            Toast.makeText(this@SignupMerchant, "You have Signup successfully!", Toast.LENGTH_SHORT)
                .show()

            val intent = Intent(this, MerchantTagline::class.java)
            startActivity(intent)



//            val auth = FirebaseAuth.getInstance()

//


//            auth.createUserWithEmailAndPassword(Email, Password)
//                .addOnCompleteListener { task ->
//                    if (task.isSuccessful) {
//                        val user = auth.currentUser
//                    } else {
//                        // If sign in fails, display a message to the user.
//                        Toast.makeText(baseContext, "Authentication failed.",
//                            Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//            reference.child(Email).setValue(HelperClass)

        })

    }
}