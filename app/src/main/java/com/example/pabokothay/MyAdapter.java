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

public class MyAdapter extends RecyclerView.Adapter<ShopViewHolder>{
    private Context mContext;
    private List<BookShopData> myShopList;


    public MyAdapter(Context mContext, List<BookShopData> myShopList) {
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

        Glide.with(mContext).load(myShopList.get(position).getImage()).into(holder.imageView);
        holder.mTitle.setText(myShopList.get(position).getShopName());
        holder.mDescribe.setText(myShopList.get(position).getShopdescribe());
        holder.mID.setText(myShopList.get(position).getShopkeeperId());
        holder.mRating.setRating(myShopList.get(position).getRating());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext,area_details.class);
                intent.putExtra("Image",myShopList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",myShopList.get(holder.getAdapterPosition()).getShopName());
                intent.putExtra("Description",myShopList.get(holder.getAdapterPosition()).getShopdescribe());
                intent.putExtra("ID",myShopList.get(holder.getAdapterPosition()).getShopkeeperId());
                intent.putExtra("Rating",myShopList.get(holder.getAdapterPosition()).getRating());
                mContext.startActivity(intent);
                Animatoo.animateSlideLeft(mContext);
            }
        });
    }
    @Override
    public int getItemCount() {
        return myShopList.size();
    }

    public void filteredList(List<BookShopData> bookShopDataList){
        myShopList = bookShopDataList;
        notifyDataSetChanged();
    }
}

class ShopViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescribe,mID;
    CardView cardView;
    RatingBar mRating;

    public ShopViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.ivImage);
        mTitle= itemView.findViewById(R.id.tvTitle);
        mDescribe= itemView.findViewById(R.id.tvDescribe);
        mID= itemView.findViewById(R.id.tvLink);
        mRating= itemView.findViewById(R.id.rRatingBar);
        cardView= itemView.findViewById(R.id.srt_card);
    }
}
