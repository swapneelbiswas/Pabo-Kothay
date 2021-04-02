package com.example.pabokothay;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;

import java.util.List;

public class FAdapter extends RecyclerView.Adapter<FViewHolder> {
    private Context mContext;
    private List<FurnitureData> myFurnitureList;


    public FAdapter(Context mContext, List<FurnitureData> myFurnitureList) {
        this.mContext = mContext;
        this.myFurnitureList = myFurnitureList;
    }

    @NonNull
    @Override
    public FViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_item,parent,false);

        return new FViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull FViewHolder holder, int position) {
        Glide.with(mContext).load(myFurnitureList.get(position).getImage()).placeholder(R.drawable.no_image).into(holder.imageView);
        holder.mTitle.setText(myFurnitureList.get(position).getShopName());
        holder.mDescribe.setText(myFurnitureList.get(position).getShopdescribe());
        holder.mID.setText(myFurnitureList.get(position).getShopkeeperId());
        holder.mRating.setRating(myFurnitureList.get(position).getRating());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext, area_details.class);
                intent.putExtra("Image",myFurnitureList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",myFurnitureList.get(holder.getAdapterPosition()).getShopName());
                intent.putExtra("Description",myFurnitureList.get(holder.getAdapterPosition()).getShopdescribe());
                intent.putExtra("ID",myFurnitureList.get(holder.getAdapterPosition()).getShopkeeperId());
                intent.putExtra("Rating",myFurnitureList.get(holder.getAdapterPosition()).getRating());
                mContext.startActivity(intent);
                Animatoo.animateSlideLeft(mContext);
            }
        });
    }
    @Override
    public int getItemCount() {
        return myFurnitureList.size();
    }

    public void filteredList(List<FurnitureData> furnitureDataList){
        myFurnitureList = furnitureDataList;
        notifyDataSetChanged();
    }

}


class FViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView mTitle,mDescribe,mID;
    CardView cardView;
    RatingBar mRating;

    public FViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.ivImage);
        mTitle= itemView.findViewById(R.id.tvTitle);
        mDescribe= itemView.findViewById(R.id.tvDescribe);
        mID= itemView.findViewById(R.id.tvLink);
        mRating= itemView.findViewById(R.id.rRatingBar);
        cardView= itemView.findViewById(R.id.srt_card);
    }
}