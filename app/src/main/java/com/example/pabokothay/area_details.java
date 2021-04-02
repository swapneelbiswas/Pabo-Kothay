package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
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
import com.squareup.picasso.Picasso;

public class area_details extends AppCompatActivity {
    TextView tex,shopName;
    ImageView shopImage;
    TextView describe,area_shopname;
    RatingBar ratingBar,ratingBar2;
    String shopID,shop;
    String Type1Tree="Users",Type2Tree="Shops",Link,userID, num,Sname;
    float rating;
    DatabaseReference databaseReference;
    private FirebaseUser fUser;
    private DatabaseReference ratingRef;
    float avgRating;
    Button btncall;
    CardView rateCardView;

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

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        ratingRef= FirebaseDatabase.getInstance().getReference(Type1Tree);
        userID=fUser.getUid();

        tex = findViewById(R.id.textView9);
        tex.setClickable(true);
        ratingBar = findViewById(R.id.ratingBar);
        tex.setMovementMethod(LinkMovementMethod.getInstance());
        area_shopname=findViewById(R.id.area_detail_shopname);
        shopImage =findViewById(R.id.shopPicture);
        describe =findViewById(R.id.description);
        btncall=findViewById(R.id.call);
        rateCardView=findViewById(R.id.rate_card);
        Bundle mBundle =getIntent().getExtras();
        if(mBundle!=null){
            describe.setText(mBundle.getString("Description"));
            if(mBundle.getString("Image")!=null) {
                Glide.with(this).load(mBundle.getString("Image")).placeholder(R.drawable.no_image).into(shopImage);
            }
            shopID=mBundle.getString("ID");
            rating=mBundle.getFloat("Rating");
            ratingBar.setRating(rating);
        }
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        databaseReference.child(shop).child(shopID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                area_details_data shopData =snapshot.getValue(area_details_data.class);
                if(shopData!=null) {
                    Link = shopData.getPrice();
                    Sname= shopData.shopName;
                    area_shopname.setText(Sname);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(area_details.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("ShopKeeper").child(shopID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Shopkeeper userProfile = snapshot.getValue(Shopkeeper.class);
                if(userProfile!=null){
                    num =userProfile.number;


                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(area_details.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Callintent = new Intent(Intent.ACTION_DIAL);
                Callintent.setData(Uri.parse("tel:"+num));
                startActivity(Callintent);
            }
        });
    }

    public void browser1(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(Link));
        startActivity(browserIntent);
        Animatoo.animateSlideLeft(area_details.this);
    }
    public void yes(View view)
    {
        rateCardView.setVisibility(View.VISIBLE);
    }
    public void no(View view)
    {
        rateCardView.setVisibility(View.GONE);
    }


    public void submitRating(View view) {
        String s= String.valueOf(ratingBar.getRating());
        float f=Float.parseFloat(s);
        ratingRef.child(shop).child(shopID).child("RatingCount").child(userID).setValue(f);
        ratingRef.child(shop).child(shopID).child("RatingCount").addListenerForSingleValueEvent(new ValueEventListener() {
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
                        ratingRef.child(shop).child(shopID).child("rating").setValue(avgRating);
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(area_details.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        Toast.makeText(area_details.this, "Done", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(area_details.this);
    }
}