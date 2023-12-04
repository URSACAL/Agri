package com.example.agri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date

class ConsumerAgreement : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_agreement)

        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val submitButton = findViewById<Button>(R.id.submit)
        val text = findViewById<TextView>(R.id.text)

        val currentDate = getCurrentDate()
        text.text ="This Supply Agreement is entered into this date: $currentDate, (Effective Date)"

        submitButton.setOnClickListener {
            if (checkBox.isChecked) {
                // Checkbox is checked, generate today's date and set it to the TextView
                val intent = Intent(this, ConsumerReports::class.java)
                startActivity(intent)
            } else {
                // Checkbox is not checked, show a message or take appropriate action
                // For example, display a Toast message
                showToast("Please agree to the contract before submitting.")
            }
        }
    }

    private fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return dateFormat.format(Date())
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
