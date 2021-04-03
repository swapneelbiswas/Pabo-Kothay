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

public class SportAdapter extends RecyclerView.Adapter<SportsViewHolder> {

    private Context mContext;
    private List<SportsData> mySportsGoodsList;

    public SportAdapter(Context mContext, List<SportsData> mySportsGoodsList) {
        this.mContext = mContext;
        this.mySportsGoodsList = mySportsGoodsList;
    }

    @NonNull
    @Override
    public SportsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_item,parent,false);
        return new SportsViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsViewHolder holder, int position) {

        Glide.with(mContext)
                .load(mySportsGoodsList.get(position).getImage())
                .into(holder.imageView);
        holder.mTitle.setText(mySportsGoodsList.get(position).getShopName());
        holder.mDescribe.setText(mySportsGoodsList.get(position).getShopdescribe());
        holder.mID.setText(mySportsGoodsList.get(position).getShopkeeperId());
        holder.mRating.setRating(mySportsGoodsList.get(position).getRating());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext, area_details.class);
                intent.putExtra("Image",mySportsGoodsList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",mySportsGoodsList.get(holder.getAdapterPosition()).getShopName());
                intent.putExtra("Description",mySportsGoodsList.get(holder.getAdapterPosition()).getShopdescribe());
                intent.putExtra("ID",mySportsGoodsList.get(holder.getAdapterPosition()).getShopkeeperId());
                intent.putExtra("Rating",mySportsGoodsList.get(holder.getAdapterPosition()).getRating());
                mContext.startActivity(intent);
                Animatoo.animateSlideLeft(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mySportsGoodsList.size();
    }
    public void filteredList(List<SportsData> sportsDataList){
        mySportsGoodsList = sportsDataList;
        notifyDataSetChanged();
    }
}

class SportsViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView;
    TextView mTitle,mDescribe,mID;
    CardView cardView;
    RatingBar mRating;
    public SportsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.ivImage);
        mTitle= itemView.findViewById(R.id.tvTitle);
        mDescribe= itemView.findViewById(R.id.tvDescribe);
        mID= itemView.findViewById(R.id.tvLink);
        mRating= itemView.findViewById(R.id.rRatingBar);
        cardView= itemView.findViewById(R.id.srt_card);
    }
}
