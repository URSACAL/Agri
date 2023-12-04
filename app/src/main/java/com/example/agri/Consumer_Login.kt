package com.example.agri

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Consumer_Login : AppCompatActivity() {

    private lateinit var loginuser: EditText
    private lateinit var loginpass: EditText
    private lateinit var loginbutton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_login)

        // Initialize UI elements
        loginuser = findViewById(R.id.log_user)
        loginpass = findViewById(R.id.log_pass)
        loginbutton = findViewById(R.id.login)

        loginbutton.setOnClickListener(View.OnClickListener {
            if (!validateUsername() or !validatePassword()) {
                // Handle validation failure if needed
            } else {
                checkUser()
            }
        })
    }

    fun validateUsername(): Boolean {
        val value = loginuser.text.toString()
        return if (value.isEmpty()) {
            loginuser.error = "Username cannot be empty"
            false
        } else {
            loginuser.error = null
            true
        }
    }

    fun validatePassword(): Boolean {
        val value = loginpass.text.toString()
        return if (value.isEmpty()) {
            loginpass.error = "Password cannot be empty"
            false
        } else {
            loginpass.error = null
            true
        }
    }

    fun checkUser() {
        val userUsername = loginuser.text.toString().trim()
        val userPassword = loginpass.text.toString().trim()
        val reference = FirebaseDatabase.getInstance().getReference("Account")
        val checkUserDatabase = reference.orderByChild("email").equalTo(userUsername)
        checkUserDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    loginuser.error = null
                    val passwordFromDB = snapshot.child(userUsername).child("password").getValue(
                        String::class.java
                    )
                    if (passwordFromDB == userPassword) {
                        loginuser.error = null
                        val emailFromDB = snapshot.child(userUsername).child("email").getValue(
                            String::class.java
                        )
                        val intent = Intent(this@Consumer_Login, ConsumerTagline::class.java)
                        intent.putExtra("email", emailFromDB)
                        intent.putExtra("password", passwordFromDB)
                        startActivity(intent)
                    } else {
                        loginpass.error = "Invalid Credentials"
                        loginpass.requestFocus()
                    }
                } else {
                    loginuser.error = "User does not exist"
                    loginuser.requestFocus()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle onCancelled if needed
            }
        })
    }
}
