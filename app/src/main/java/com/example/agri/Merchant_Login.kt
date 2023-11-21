package com.example.agri

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Merchant_Login : AppCompatActivity() {
//
//    val loginuser = findViewById<EditText>(R.id.log_user)
//    val loginpass = findViewById<EditText>(R.id.log_pass)
//    val loginbutton = findViewById<Button>(R.id.login)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_login)

//        loginbutton.setOnClickListener(View.OnClickListener {
//            if (!validateUsername() or !validatePassword()) {
//            } else {
//                checkUser()
//            }
//        })
    }

//    fun validateUsername(): Boolean {
//        val `val` = loginuser!!.text.toString()
//        return if (`val`.isEmpty()) {
//            loginuser.error = "Username cannot be empty"
//            false
//        } else {
//            loginuser.error = null
//            true
//        }
//    }
//
//    fun validatePassword(): Boolean {
//        val `val` = loginpass!!.text.toString()
//        return if (`val`.isEmpty()) {
//            loginpass.error = "Password cannot be empty"
//            false
//        } else {
//            loginpass.error = null
//            true
//        }
//    }
//
//    fun checkUser() {
//        val userUsername = loginuser!!.text.toString().trim { it <= ' ' }
//        val userPassword = loginpass!!.text.toString().trim { it <= ' ' }
//        val reference = FirebaseDatabase.getInstance().getReference("Account")
//        val checkUserDatabase = reference.orderByChild("email").equalTo(userUsername)
//        checkUserDatabase.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if (snapshot.exists()) {
//                    loginuser.error = null
//                    val passwordFromDB = snapshot.child(userUsername).child("password").getValue(
//                        String::class.java
//                    )
//                    if (passwordFromDB == userPassword) {
//                        loginuser.error = null
//                        val emailFromDB = snapshot.child(userUsername).child("email").getValue(
//                            String::class.java
//                        )
//                        val intent = Intent(this@Merchant_Login, MerchantTagline::class.java)
//                        intent.putExtra("email", emailFromDB)
//                        intent.putExtra("password", passwordFromDB)
//                        startActivity(intent)
//                    } else {
//                        loginpass.error = "Invalid Credentials"
//                        loginpass.requestFocus()
//                    }
//                } else {
//                    loginuser.error = "User does not exist"
//                    loginuser.requestFocus()
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {}
//        })
//    }
}