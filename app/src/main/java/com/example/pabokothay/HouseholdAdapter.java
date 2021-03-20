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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;

import java.util.List;

public class HouseholdAdapter extends RecyclerView.Adapter<HViewHolder>{
    private Context mContext;
    private List<HouseholdsData> myStuffList;


    public HouseholdAdapter(Context mContext, List<HouseholdsData> myStuffList) {
        this.mContext = mContext;
        this.myStuffList = myStuffList;
    }

    @NonNull
    @Override
    public HViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_item,parent,false);

        return new HViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull HViewHolder holder, int position) {

        Glide.with(mContext)
                .load(myStuffList.get(position).getImage())
                .into(holder.imageView);
        holder.imageView.setImageResource(myStuffList.get(position).getImage());
        holder.mTitle.setText(myStuffList.get(position).getShopName());
        holder.mDescribe.setText(myStuffList.get(position).getShopdescribe());
//        holder.mPrice.setText(myFurnitureList.get(position).getPrice());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext, area_details.class);
                intent.putExtra("Image",myStuffList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",myStuffList.get(holder.getAdapterPosition()).getShopName());
                intent.putExtra("Description",myStuffList.get(holder.getAdapterPosition()).getShopdescribe());
                mContext.startActivity(intent);
                Animatoo.animateSlideLeft(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myStuffList.size();
    }
}

class HViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescribe,mPrice;
    CardView cardView;


    public HViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.ivImage);
        mTitle= itemView.findViewById(R.id.tvTitle);
        mDescribe= itemView.findViewById(R.id.tvDescribe);
        //mPrice= itemView.findViewById(R.id.tvPrice);
        cardView= itemView.findViewById(R.id.srt_card);
    }
}