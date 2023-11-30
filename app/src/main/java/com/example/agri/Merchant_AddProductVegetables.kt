package com.example.agri

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class Merchant_AddProductVegetables : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_merchant_add_product_vegetables)

        val spinner: Spinner = findViewById(R.id.productspinner)
        val options: Array<String> = resources.getStringArray(R.array.vegetables_option)

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Set a listener if needed
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>, selectedItemView: View, position: Int, id: Long) {
                // Do something with the selected item
            }

            override fun onNothingSelected(parentView: AdapterView<*>) {
                // Handle the situation where nothing is selected
            }
        }
    }
}

