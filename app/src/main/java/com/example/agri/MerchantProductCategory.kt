package com.example.agri

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.agri.databinding.ActivityMerchantProductCategoryBinding

class MerchantProductCategory : AppCompatActivity() {

    private lateinit var binding: ActivityMerchantProductCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMerchantProductCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cropName = arrayOf(
            "Grains", "Vegetables", "Fruits", "Root Crops"
        )

        val cropImage = intArrayOf(
            R.drawable.grains, R.drawable.vegetables, R.drawable.fruits, R.drawable.root
        )

        val gridAdapter = GridAdapter(this@MerchantProductCategory, cropName, cropImage)
        binding.gridView.adapter = gridAdapter

        binding.gridView.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(
                this@MerchantProductCategory,
                "You Clicked on " + cropName[position],
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
