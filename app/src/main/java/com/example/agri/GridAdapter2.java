package com.example.agri;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

    public class GridAdapter2 extends RecyclerView.Adapter<MyViewHolder2> {

        private Context context;
        private List<ProductClass> dataList;

        public GridAdapter2(Context context, List<ProductClass> dataList) {
            this.context = context;
            this.dataList = dataList;
        }

        @NonNull
        @Override
        public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item2, parent, false);
            return new MyViewHolder2(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder2 holder, @SuppressLint("RecyclerView") int position) {
            Glide.with(context).load(dataList.get(position).getImageURL()).into(holder.grid_image2);
            holder.item_name2.setText(dataList.get(position).getProductName());

            holder.gridView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Log to check if onClick is triggered
                    Log.d("GridAdapter2", "onClick triggered for position: " + position);

                    Intent intent = new Intent(context, MerchantEditProducts.class);
                    intent.putExtra("imageURl", dataList.get(position).getImageURL());
                    intent.putExtra("productSKU", dataList.get(position).getProductSKU());
                    intent.putExtra("productName", dataList.get(position).getProductName());
                    intent.putExtra("productDesc", dataList.get(position).getProductDesc());
                    intent.putExtra("productType", dataList.get(position).getProductType());
                    intent.putExtra("productQuantity", dataList.get(position).getProductQuantity());
                    intent.putExtra("Key", dataList.get(position).getKey());
                    // Log to check values before starting the activity
                    Log.d("GridAdapter2", "Intent values: " + intent.getExtras());

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        public void searchDataList(ArrayList<ProductClass> searchList) {
            dataList = searchList;
            notifyDataSetChanged();
        }
    }

    class MyViewHolder2 extends RecyclerView.ViewHolder {

        CardView gridView2;
        TextView item_name2;
        ImageView grid_image2;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);

            grid_image2 = itemView.findViewById(R.id.grid_image2);
            item_name2 = itemView.findViewById(R.id.item_name2);
            gridView2 = itemView.findViewById(R.id.gridView2);
        }
    }

