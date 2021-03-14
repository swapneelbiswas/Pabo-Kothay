package com.example.pabokothay;

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

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<ShopViewHolder>{
    private Context mContext;
    private List<ShopData> myShopList;


    public MyAdapter(Context mContext, List<ShopData> myShopList) {
        this.mContext = mContext;
        this.myShopList = myShopList;
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_item,parent,false);

        return new ShopViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        holder.imageView.setImageResource(myShopList.get(position).getImage());
        holder.mTitle.setText(myShopList.get(position).getShopName());
        holder.mDescribe.setText(myShopList.get(position).getShopdescribe());
//        holder.mPrice.setText(myShopList.get(position).getPrice());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext,area_details.class);
                intent.putExtra("Image",myShopList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",myShopList.get(holder.getAdapterPosition()).getShopName());
                intent.putExtra("Description",myShopList.get(holder.getAdapterPosition()).getShopdescribe());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myShopList.size();
    }
}

class ShopViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescribe,mPrice;
    CardView cardView;


    public ShopViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.ivImage);
        mTitle= itemView.findViewById(R.id.tvTitle);
        mDescribe= itemView.findViewById(R.id.tvDescribe);
        //mPrice= itemView.findViewById(R.id.tvPrice);
        cardView= itemView.findViewById(R.id.srt_card);
    }
}
