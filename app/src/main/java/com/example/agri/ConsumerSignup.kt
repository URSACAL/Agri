package com.example.agri

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

data class HelperClass(
    val email: String,
    val password: String,
    val confirmPassword: String
    // Add more fields as needed
)

class ConsumerSignup : AppCompatActivity() {

    private lateinit var firebaseAuth: FirebaseAuth

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_signup)

        val signup = findViewById<Button>(R.id.signup)
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val confirmPasswordEditText = findViewById<EditText>(R.id.editTextTextPassword2)

        // Initialize Firebase Authentication
        firebaseAuth = FirebaseAuth.getInstance()

        // ...

        signup.setOnClickListener {
            // Retrieve email, password, and confirm password from EditText fields
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val confirmPassword = confirmPasswordEditText.text.toString()

            // Validate email format
            if (!isValidEmail(email)) {
                Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if password and confirm password match
            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Create a new user in Firebase Realtime Database
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign up success, update UI with the signed-in user's information
                        val user: FirebaseUser? = firebaseAuth.currentUser
                        // Store user data into Firebase Realtime Database
                        val helperClass = HelperClass(email, password, confirmPassword)
                        storeUserData(helperClass)
                        // Proceed to the next activity
                        val intent = Intent(this, ConsumerProfile::class.java)
                        startActivity(intent)
                    } else {
                        // If sign up fails, display a more detailed message to the user.
                        val exception = task.exception
                        if (exception != null) {
                            Toast.makeText(
                                baseContext,
                                "Authentication failed: ${exception.message}",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(
                                baseContext,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
        }
    }
        // Function to validate email format
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Function to store user data into Firebase Realtime Database
    private fun storeUserData(helperClass: HelperClass) {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val reference: DatabaseReference = database.reference.child("Account").push()

        reference.setValue(helperClass)
    }
}
