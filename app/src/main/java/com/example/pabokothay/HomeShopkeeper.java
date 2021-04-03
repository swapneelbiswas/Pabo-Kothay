package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class HomeShopkeeper extends AppCompatActivity {

    private static final int GALLERY_CODE = 1;
    private FirebaseUser fUser;
    private DatabaseReference databaseReference;
    private String userID;
    TextView vFullName,vMail,vName,vNumber,vDes,vlink,vBookShopDes,vBookPrice,vBookShopName,vHhShopDes,vHhPrice,vHhShopName,vSpShopDes,vSpPrice,vSpShopName;
    String fName,emailUser,shopName,username,num,imageUrl,des,link,bookDesc,bPrice,bookShopName,hhDesc,hhPrice,hhShopName,spDesc,spPrice,spShopName;
    TextView vMbShopDes,vMbPrice,vMbShopName,vClothShopDes,vClothPrice,vClothShopName,vFShopDes,vFPrice,vFShopName;
    String mbDesc,mbPrice,mbShopName,clothDesc,clothPrice,clothShopName,fDesc,fPrice,fShopName;

    float bookRating,furnitureRating,houseRating,sportsRating,clothsRating,moboRating;
    RatingBar bRating,fRating,hRating,sRating,cRating,mRating;
    private StorageReference storageReference;
    private ImageView profile_image;
    CardView book_card,household_card,sports_card,mb_card,cloth_card,f_card;

    private ImageView bookShop_image,furnitureShop_image,houseShop_image,sportsShop_image,clothsShop_image,gadgetShop_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_shopkeeper);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        userID = fUser.getUid();


        profile_image=findViewById(R.id.profile_image);
        bookShop_image=findViewById(R.id.bookShopImage);
        furnitureShop_image=findViewById(R.id.fShopImage);
        houseShop_image=findViewById(R.id.hhShopImage);
        sportsShop_image=findViewById(R.id.spShopImage);
        clothsShop_image=findViewById(R.id.clothShopImage);
        gadgetShop_image=findViewById(R.id.mbShopImage);


        bRating =findViewById(R.id.rRatingBarbook);
        fRating =findViewById(R.id.rRatingBarfur);
        hRating =findViewById(R.id.rRatingBarhousehold);
        sRating =findViewById(R.id.rRatingBarsports);
        cRating =findViewById(R.id.rRatingBarcloth);
        mRating =findViewById(R.id.rRatingBarmbl);


        vFullName = findViewById(R.id.skName);
        vMail = findViewById(R.id.sMail);
        vDes = findViewById(R.id.sDescription);
        vlink = findViewById(R.id.sLink);
        vName = findViewById(R.id.sName);
        vNumber = findViewById(R.id.sNumber);


        book_card = findViewById(R.id.book_card);
        household_card = findViewById(R.id.household_card);
        sports_card = findViewById(R.id.sports_card);
        mb_card = findViewById(R.id.mb_card);
        cloth_card = findViewById(R.id.cloth_card);
        f_card = findViewById(R.id.f_card);

        profile_image = findViewById(R.id.profile_image2);

        vBookShopDes = findViewById(R.id.bookDes);
        vBookPrice = findViewById(R.id.bookPrice);
        vBookShopName = findViewById(R.id.bookShopN);

        vHhShopDes = findViewById(R.id.hhDes);
        vHhPrice = findViewById(R.id.hhPrice);
        vHhShopName = findViewById(R.id.hhN);

        vSpShopDes = findViewById(R.id.spDes);
        vSpPrice = findViewById(R.id.spPrice);
        vSpShopName = findViewById(R.id.spN);

        vMbShopDes = findViewById(R.id.mbDes);
        vMbPrice = findViewById(R.id.mbPrice);
        vMbShopName = findViewById(R.id.mbShopN);

        vClothShopDes = findViewById(R.id.clothDes);
        vClothPrice = findViewById(R.id.clothPrice);
        vClothShopName = findViewById(R.id.clothShopN);

        vFShopDes = findViewById(R.id.fDes);
        vFPrice = findViewById(R.id.fPrice);
        vFShopName = findViewById(R.id.fShopN);



        Intent intent = getIntent();
        username = intent.getStringExtra("fullName");
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference.child("ShopKeeper").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Shopkeeper userProfile = snapshot.getValue(Shopkeeper.class);
                if (userProfile != null) {
                    fName = userProfile.fullName;
                    link = userProfile.gLink;
                    des = userProfile.description;
                    num = userProfile.number;
                    shopName=userProfile.shopName;
                    imageUrl = userProfile.imageUrl;

                    emailUser = userProfile.email;
                    vMail.setText(emailUser);
                    vFullName.setText(fName);
                    vDes.setText(des);
                    vlink.setText(link);
                    vName.setText(shopName);
                    vNumber.setText(num);

                    Picasso.get().load(imageUrl).placeholder(R.drawable.sideheader).into(profile_image);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("Books").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                BookShopData userProfilebook = snapshot.getValue(BookShopData.class);
                if(userProfilebook!=null){
                    bPrice=userProfilebook.getPrice();
                    vBookPrice.setText(bPrice);
                    bookDesc=userProfilebook.getShopdescribe();
                    vBookShopDes.setText(bookDesc);
                    bookShopName=userProfilebook.getShopName();
                    vBookShopName.setText(bookShopName);

                    bookRating=userProfilebook.getRating();
                    bRating.setRating(bookRating);

                    imageUrl=userProfilebook.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(bookShop_image);

                }else
                {
                    book_card.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("Household").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HouseholdsData userProfilehh = snapshot.getValue(HouseholdsData.class);
                if (userProfilehh != null) {
                    hhPrice = userProfilehh.price;
                    vHhPrice.setText(hhPrice);
                    hhDesc = userProfilehh.shopdescribe;
                    vHhShopDes.setText(hhDesc);
                    houseRating=userProfilehh.getRating();
                    hRating.setRating(houseRating);
                    hhShopName = userProfilehh.shopName;
                    vHhShopName.setText(hhShopName);
                    imageUrl=userProfilehh.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(houseShop_image);
                } else {
                    household_card.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("Sports").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SportsData userProfilesp = snapshot.getValue(SportsData.class);
                if (userProfilesp != null) {
                    spPrice = userProfilesp.price;
                    vSpPrice.setText(spPrice);
                    spDesc = userProfilesp.shopdescribe;
                    vSpShopDes.setText(spDesc);
                    sportsRating=userProfilesp.getRating();
                    sRating.setRating(sportsRating);
                    spShopName = userProfilesp.shopName;
                    vSpShopName.setText(spShopName);
                    imageUrl=userProfilesp.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(sportsShop_image);
                } else {
                    sports_card.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("Mobile-Gadget").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GadgetData userProfilemb = snapshot.getValue(GadgetData.class);
                if (userProfilemb != null) {
                    mbPrice = userProfilemb.price;
                    vMbPrice.setText(mbPrice);
                    mbDesc = userProfilemb.shopdescribe;
                    vMbShopDes.setText(mbDesc);
                    moboRating=userProfilemb.getRating();
                    mRating.setRating(moboRating);
                    mbShopName = userProfilemb.shopName;
                    vMbShopName.setText(mbShopName);
                    imageUrl=userProfilemb.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(gadgetShop_image);
                } else {
                    mb_card.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("Cloths").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DressData userProfileCloth = snapshot.getValue(DressData.class);
                if (userProfileCloth != null) {
                    clothPrice = userProfileCloth.price;
                    clothDesc = userProfileCloth.shopdescribe;
                    clothShopName = userProfileCloth.shopName;
                    vClothPrice.setText(clothPrice);
                    vClothShopDes.setText(clothDesc);
                    vClothShopName.setText(clothShopName);
                    clothsRating=userProfileCloth.getRating();
                    cRating.setRating(clothsRating);
                    imageUrl=userProfileCloth.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(clothsShop_image);
                } else {
                    cloth_card.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

        databaseReference.child("Furniture").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FurnitureData userProfilef = snapshot.getValue(FurnitureData.class);
                if (userProfilef != null) {
                    fPrice = userProfilef.price;
                    vFPrice.setText(fPrice);
                    fDesc = userProfilef.shopdescribe;
                    vFShopDes.setText(fDesc);
                    fShopName = userProfilef.shopName;
                    vFShopName.setText(fShopName);
                    furnitureRating=userProfilef.getRating();
                    fRating.setRating(furnitureRating);
                    imageUrl=userProfilef.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(furnitureShop_image);
                } else {
                    f_card.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void logoutAcc(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LogInPage.class));
        Animatoo.animateInAndOut(HomeShopkeeper.this);
        finish();
    }
    public void goProfile(View view){
        Intent intent= new Intent(this, ProfileShopkeeper.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(HomeShopkeeper.this);
        finish();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(HomeShopkeeper.this);
        finish();
    }
}