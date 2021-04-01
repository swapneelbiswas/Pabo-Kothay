package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class area_details extends AppCompatActivity {
    TextView tex,shopName;
    ImageView shopImage;
    TextView describe;
    RatingBar ratingBar,ratingBar2;
    String link,shop,searchType,searchType2,searchType3,searchType4;

    float rating;

    private FirebaseUser fUser;
    private DatabaseReference ratingRef;
    private String userID;
    float avgRating,getRatingValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
        ShopType shopType=new ShopType();
        if(shopType.getShopType().equals("Books")){
            shop = shopType.getShopType();
        }
        else if(shopType.getShopType().equals("Furniture")){
            shop = shopType.getShopType();
        }
        else if(shopType.getShopType().equals("Sports")){
            shop = shopType.getShopType();
        }
        else if(shopType.getShopType().equals("Household")){
            shop = shopType.getShopType();
        }
        else if(shopType.getShopType().equals("Mobile-Gadget")){
            shop = shopType.getShopType();
        }
        else if(shopType.getShopType().equals("Cloths")){
            shop = shopType.getShopType();
        }
        else{
            shop="Trash";
        }

        Toast.makeText(area_details.this, shop, Toast.LENGTH_LONG).show();


        fUser = FirebaseAuth.getInstance().getCurrentUser();
        ratingRef= FirebaseDatabase.getInstance().getReference("Users");
        userID=fUser.getUid();

        tex = findViewById(R.id.textView9);
        tex.setClickable(true);
        ratingBar = findViewById(R.id.ratingBar);
        tex.setMovementMethod(LinkMovementMethod.getInstance());

        shopImage =findViewById(R.id.shopPicture);
        shopName= findViewById(R.id.shop_name);
        describe =findViewById(R.id.description);
        Bundle mbundle =getIntent().getExtras();
        if(mbundle!=null){

            describe.setText(mbundle.getString("Description"));
            if(mbundle.getInt("Image")!=0) {
//                shopImage.setImageResource(mbundle.getInt("Image"));
                Glide.with(this)
                        .load(mbundle.getString("Image"))
                        .into(shopImage);
            }
            shopName.setText(mbundle.getString("Name"));
            link=mbundle.getString("Price");
            rating=mbundle.getFloat("Rating");
            ratingBar.setRating(rating);
        }
    }

//    String s = "https://goo.gl/maps/bCPFuNrYWy7H4r1D9";
    public void browser1(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
        startActivity(browserIntent);
        Animatoo.animateSlideLeft(area_details.this);
    }

    public void submitRating(View view) {
        Toast.makeText(area_details.this, shop, Toast.LENGTH_LONG).show();
        String s= String.valueOf(ratingBar.getRating());
        float f=Float.parseFloat(s);
        ratingRef.child(shop).child("RatingTest").child(userID).setValue(f);
        ratingRef.child(shop).child("RatingTest").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists() &&  snapshot.getChildrenCount()>0){
                    float RatingTotal=0;
                    float RatingCount=0;
                    for(DataSnapshot child : snapshot.getChildren()){
                        String valueInString= String.valueOf(child.getValue().toString());
                        float inFloat=Float.parseFloat(valueInString);
                        RatingTotal = RatingTotal+ inFloat ;
                        RatingCount++;
                    }
                    if(RatingCount!=0){
                        avgRating=RatingTotal/RatingCount;
                        ratingRef.child("RatingDriver").child(userID).setValue(avgRating);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(area_details.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(area_details.this);
    }
}