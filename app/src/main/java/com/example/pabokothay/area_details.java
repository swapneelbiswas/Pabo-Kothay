package com.example.pabokothay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;

public class area_details extends AppCompatActivity {

    TextView tex,shopName;
    ImageView shopImage;
    TextView describe;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
        //getSupportActionBar().hide();

        tex = findViewById(R.id.textView9);
        tex.setClickable(true);
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
            link=mbundle.getString("Price");
        }
    }
    String s = "https://goo.gl/maps/bCPFuNrYWy7H4r1D9";
    public void browser1(View view)
    {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
        startActivity(browserIntent);
        Animatoo.animateSlideLeft(area_details.this);
    }
    //    public void gotolink(View view){
//        gotoUrl("https://www.google.com/");
//
//    }
//    private void gotoUrl(String s) {
//        Uri uri= Uri.parse(s);
//        startActivity(new Intent(Intent.ACTION_VIEW,uri));
//    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(area_details.this);
    }
}