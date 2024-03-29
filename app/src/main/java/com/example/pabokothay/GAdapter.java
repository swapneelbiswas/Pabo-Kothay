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

public class GAdapter extends RecyclerView.Adapter<GViewHolder> {
    private Context mContext;
    private List<GadgetData> myGadgetList;
    public GAdapter(Context mContext, List<GadgetData> myGadgetList) {
        this.mContext = mContext;
        this.myGadgetList = myGadgetList;
    }
    @NonNull
    @Override
    public GViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_row_item,parent,false);
        return new GViewHolder(mView);
    }
    @Override
    public void onBindViewHolder(@NonNull GViewHolder holder, int position) {

        Glide.with(mContext)
                .load(myGadgetList.get(position).getImage())
                .into(holder.imageView);
        holder.mTitle.setText(myGadgetList.get(position).getShopName());
        holder.mDescribe.setText(myGadgetList.get(position).getShopdescribe());
        holder.mID.setText(myGadgetList.get(position).getShopkeeperId());
        holder.mRating.setRating(myGadgetList.get(position).getRating());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(mContext, area_details.class);
                intent.putExtra("Image",myGadgetList.get(holder.getAdapterPosition()).getImage());
                intent.putExtra("Name",myGadgetList.get(holder.getAdapterPosition()).getShopName());
                intent.putExtra("Description",myGadgetList.get(holder.getAdapterPosition()).getShopdescribe());
                intent.putExtra("ID",myGadgetList.get(holder.getAdapterPosition()).getShopkeeperId());
                intent.putExtra("Rating",myGadgetList.get(holder.getAdapterPosition()).getRating());
                mContext.startActivity(intent);
                Animatoo.animateSlideLeft(mContext);
            }
        });
    }

    @Override
    public int getItemCount() {
        return myGadgetList.size();
    }

    public void filteredList(List<GadgetData> bookShopDataList){
        myGadgetList = bookShopDataList;
        notifyDataSetChanged();
    }

}
class GViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView;
    TextView mTitle,mDescribe,mID;
    CardView cardView;
    RatingBar mRating;

    public GViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView= itemView.findViewById(R.id.ivImage);
        mTitle= itemView.findViewById(R.id.tvTitle);
        mDescribe= itemView.findViewById(R.id.tvDescribe);
        mID= itemView.findViewById(R.id.tvLink);
        mRating= itemView.findViewById(R.id.rRatingBar);
        cardView= itemView.findViewById(R.id.srt_card);
    }
}