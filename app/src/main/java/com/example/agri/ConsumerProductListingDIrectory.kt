package com.example.agri

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ConsumerProductListingDIrectory : AppCompatActivity() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var eventListener: ValueEventListener
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: MutableList<ProductClass>
    private lateinit var adapter: GridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consumer_product_listing_directory)

        recyclerView = findViewById(R.id.recyclerView)
        val gridLayoutManager = GridLayoutManager(this, 2)
        recyclerView.layoutManager = gridLayoutManager

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.show()

        dataList = mutableListOf()

        adapter = GridAdapter(this, dataList)
        recyclerView.adapter = adapter

        databaseReference = FirebaseDatabase.getInstance().getReference("Add Products")
        dialog.show()
        eventListener = databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                dataList.clear()
                for (itemSnapshot in snapshot.children) {
                    val dataClass = itemSnapshot.getValue(ProductClass::class.java)

                    dataClass?.let {
                        it.key = itemSnapshot.key
                        dataList.add(it)
                    }
                }
                adapter.notifyDataSetChanged()
                dialog.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                dialog.dismiss()
            }
        })




    }


}