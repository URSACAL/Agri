package com.example.agri;

import android.content.Context;
import android.content.Intent;
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

public class GridAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private List<ProductClass> dataList;

    public GridAdapter(Context context, List<ProductClass> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide.with(context).load(dataList.get(position).getImageURL()).into(holder.grid_image);
        holder.item_name.setText(dataList.get(position).getProductName());


        holder.gridView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Consumer_ProductListing.class);
                intent.putExtra("imageURl", dataList.get(holder.getAdapterPosition()).getImageURL());
                intent.putExtra("productSKU", dataList.get(holder.getAdapterPosition()).getProductSKU());
                intent.putExtra("productName", dataList.get(holder.getAdapterPosition()).getProductName());
                intent.putExtra("productDesc", dataList.get(holder.getAdapterPosition()).getProductDesc());
                intent.putExtra("productType", dataList.get(holder.getAdapterPosition()).getProductType());
                intent.putExtra("productQuantity", dataList.get(holder.getAdapterPosition()).getProductQuantity());


                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void searchDataList(ArrayList<ProductClass> searchList){
        dataList = searchList;
        notifyDataSetChanged();
    }
}

class MyViewHolder extends RecyclerView.ViewHolder{

    CardView gridView;
    TextView item_name ;
    ImageView grid_image;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        grid_image = itemView.findViewById(R.id.grid_image);
        item_name = itemView.findViewById(R.id.item_name);
        gridView = itemView.findViewById(R.id.gridView);



    }
}