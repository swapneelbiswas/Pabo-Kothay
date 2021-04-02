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

public class DAdapter extends RecyclerView.Adapter<DViewHolder> {
    private Context mContext;
    private List<DressData> myDressList;


    public DAdapter(Context mContext, List<DressData> myDressList) {
        this.mContext = mContext;
        this.myDressList = myDressList;
    }

    @NonNull
    @Override
    public DViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_item,parent,false);

        return new DViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull DViewHolder holder, int position) {

        Glide.with(mContext).load(myDressList.get(position).getImage()).placeholder(R.drawable.no_image).into(holder.imageView);
        holder.mTitle.setText(myDressList.get(position).getShopName());
        holder.mDescribe.setText(myDressList.get(position).getShopdescribe());
        holder.mID.setText(myDressList.get(position).getShopkeeperId());
        holder.mRating.setRating(myDressList.get(position).getRating());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext, area_details.class);
                intent.putExtra("Image",myDressList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",myDressList.get(holder.getAdapterPosition()).getShopName());
                intent.putExtra("Description",myDressList.get(holder.getAdapterPosition()).getShopdescribe());
                intent.putExtra("ID",myDressList.get(holder.getAdapterPosition()).getShopkeeperId());
                intent.putExtra("Rating",myDressList.get(holder.getAdapterPosition()).getRating());
                mContext.startActivity(intent);
                Animatoo.animateSlideLeft(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myDressList.size();
    }

}


class DViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView mTitle,mDescribe,mID;
    CardView cardView;
    RatingBar mRating;

    public DViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.ivImage);
        mTitle= itemView.findViewById(R.id.tvTitle);
        mDescribe= itemView.findViewById(R.id.tvDescribe);
        mID= itemView.findViewById(R.id.tvPrice);
        mRating= itemView.findViewById(R.id.rRatingBar);
        cardView= itemView.findViewById(R.id.srt_card);
    }
}