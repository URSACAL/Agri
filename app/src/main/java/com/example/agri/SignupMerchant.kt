package com.example.agri

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignupMerchant : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup_merchant)

        val email = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val password = findViewById<EditText>(R.id.editTextTextPassword)
        val confirmPassword = findViewById<EditText>(R.id.editTextTextConfirmPassword)
        val signup = findViewById<Button>(R.id.signup)

        var database: FirebaseDatabase = FirebaseDatabase.getInstance()
        var reference: DatabaseReference = database.reference

        signup.setOnClickListener(View.OnClickListener {

            // Get input values
            val emailText = email.text.toString().trim()
            val passwordText = password.text.toString().trim()
            val confirmPasswordText = confirmPassword.text.toString().trim()

            // Validate email format
            if (!isValidEmail(emailText)) {
                Toast.makeText(this@SignupMerchant, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            // Validate password and confirm password match
            if (passwordText != confirmPasswordText) {
                Toast.makeText(this@SignupMerchant, "Passwords do not match", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }

            // Perform Firebase registration
        //    val auth = FirebaseAuth.getInstance()
          //  auth.createUserWithEmailAndPassword(emailText, passwordText)
            //    .addOnCompleteListener { task ->
              //      if (task.isSuccessful) {
                //        // Registration successful, update UI or navigate to the next screen
                  //      val intent = Intent(this, MerchantTagline::class.java)
              //          startActivity(intent)
                //    } else {
                //        // If sign in fails, display a message to the user.
                 //       Toast.makeText(baseContext, "Authentication failed.",
             //               Toast.LENGTH_SHORT).show()
               //     }
               // }
        })
    }

    // Validate email format
    private fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
