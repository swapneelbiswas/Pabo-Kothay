package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ProfileShopkeeper extends AppCompatActivity {

    private static final int GALLERY_CODE = 1;
    private FirebaseUser fUser;
    private DatabaseReference databaseReference;
    private String userID;
    TextView vFullName,vBusinessName,vName,vNumber,vDes,vlink,vBookShopDes,vBookPrice,vBookShopName,vHhShopDes,vHhPrice,vHhShopName,vSpShopDes,vSpPrice,vSpShopName;
    String fName,emailUser,username,hhPrice,num,imageUrl,des,link,bookDesc,bPrice,bookShopName,hhDesc,hhShopName,spDesc,spPrice,spShopName;
    TextView vMbShopDes,vMbPrice,vMbShopName,vClothShopDes,vClothPrice,vClothShopName,vFShopDes,vFPrice,vFShopName;
    String mbDesc,mbPrice,mbShopName,clothDesc,clothPrice,clothShopName,fDesc,fPrice,fShopName,businessName;
    private Uri imageUri;
    int shopKeepC=0,bookC=0,sportsC=0,houseC=0,gadgetC=0,clothsC=0,furnitureC=0;
    private StorageReference storageReference;
    private ImageView profile_image,bookShop_image,furnitureShop_image,houseShop_image,sportsShop_image,clothsShop_image,gadgetShop_image;
    CardView book_card,household_card,sports_card,mb_card,cloth_card,f_card;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_shopkeeper);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        userID=fUser.getUid();

        vFullName=findViewById(R.id.fullNamep);
        vBusinessName=findViewById(R.id.BusinessNamep);
        vDes = findViewById(R.id.describe);
        vlink = findViewById(R.id.mapLink);
        vName = findViewById(R.id.name);
        vNumber = findViewById(R.id.pnum);


        book_card=findViewById(R.id.book_card);
        household_card= findViewById(R.id.household_card);
        sports_card = findViewById(R.id.sports_card);
        mb_card=findViewById(R.id.mb_card);
        cloth_card= findViewById(R.id.cloth_card);
        f_card = findViewById(R.id.f_card);

        profile_image=findViewById(R.id.profile_image);
        bookShop_image=findViewById(R.id.bookShopImage);
        furnitureShop_image=findViewById(R.id.fShopImage);
        houseShop_image=findViewById(R.id.hhShopImage);
        sportsShop_image=findViewById(R.id.spShopImage);
        clothsShop_image=findViewById(R.id.clothShopImage);
        gadgetShop_image=findViewById(R.id.mbShopImage);






        vBookShopDes= findViewById(R.id.bookDes);
        vBookPrice= findViewById(R.id.bookPrice);
        vBookShopName=findViewById(R.id.bookShopN);

        vHhShopDes= findViewById(R.id.hhDes);
        vHhPrice= findViewById(R.id.hhPrice);
        vHhShopName=findViewById(R.id.hhN);

        vSpShopDes= findViewById(R.id.spDes);
        vSpPrice= findViewById(R.id.spPrice);
        vSpShopName=findViewById(R.id.spN);

        vMbShopDes= findViewById(R.id.mbDes);
        vMbPrice= findViewById(R.id.mbPrice);
        vMbShopName=findViewById(R.id.mbShopN);

        vClothShopDes= findViewById(R.id.clothDes);
        vClothPrice= findViewById(R.id.clothPrice);
        vClothShopName=findViewById(R.id.clothShopN);

        vFShopDes= findViewById(R.id.fDes);
        vFPrice= findViewById(R.id.fPrice);
        vFShopName=findViewById(R.id.fShopN);


        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopKeepC=1;
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(ProfileShopkeeper.this);
            }
        });
        bookShop_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookC=1;
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(ProfileShopkeeper.this);
            }
        });
        furnitureShop_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                furnitureC=1;
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(ProfileShopkeeper.this);
            }
        });
        houseShop_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                houseC=1;
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(ProfileShopkeeper.this);
            }
        });
        sportsShop_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sportsC=1;
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(ProfileShopkeeper.this);
            }
        });
        clothsShop_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clothsC=1;
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(ProfileShopkeeper.this);
            }
        });
        gadgetShop_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gadgetC=1;
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(ProfileShopkeeper.this);
            }
        });
        Intent intent= getIntent();
        username = intent.getStringExtra("fullName");
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference.child("ShopKeeper").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Shopkeeper userProfile = snapshot.getValue(Shopkeeper.class);
                if(userProfile!=null){
                    fName = userProfile.fullName;
                    vFullName.setText(fName);

                    link = userProfile.gLink;
                    vlink.setText(link);

                    des= userProfile.description;
                    vDes.setText(des);

                    businessName =userProfile.shopName;
                    vBusinessName.setText(businessName);
                    vName.setText(businessName);

                    num=userProfile.number;
                    vNumber.setText(num);

                    imageUrl=userProfile.imageUrl;
                    Picasso.get().load(imageUrl).placeholder(R.drawable.sideheader).into(profile_image);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
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

                    imageUrl=userProfilebook.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(bookShop_image);

                }else
                {
                    book_card.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("Household").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                HouseholdsData userProfilehh = snapshot.getValue(HouseholdsData.class);
                if(userProfilehh!=null){
                    hhPrice=userProfilehh.getPrice();
                    vHhPrice.setText(hhPrice.toString());
                    hhDesc=userProfilehh.getShopdescribe();
                    vHhShopDes.setText(hhDesc);
                    hhShopName=userProfilehh.getShopName();
                    vHhShopName.setText(hhShopName);
                    imageUrl= userProfilehh.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(houseShop_image);

                }else {
                    household_card.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("Sports").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                SportsData userProfilesp = snapshot.getValue(SportsData.class);
                if(userProfilesp!=null){
                    spPrice=userProfilesp.getPrice();
                    vSpPrice.setText(spPrice);

                    spDesc=userProfilesp.getShopdescribe();
                    vSpShopDes.setText(spDesc);

                    spShopName=userProfilesp.getShopName();
                    vSpShopName.setText(spShopName);
                    imageUrl=userProfilesp.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(sportsShop_image);
                }else{
                    sports_card.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("Mobile-Gadget").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                GadgetData userProfilemb = snapshot.getValue(GadgetData.class);
                if(userProfilemb!=null){
                    mbPrice=userProfilemb.getPrice();
                    vMbPrice.setText(mbPrice);

                    mbDesc=userProfilemb.getShopdescribe();
                    vMbShopDes.setText(mbDesc);

                    mbShopName=userProfilemb.getShopName();
                    vMbShopName.setText(mbShopName);

                    imageUrl=userProfilemb.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(gadgetShop_image);
                }else {
                    mb_card.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("Cloths").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                DressData userProfileCloth = snapshot.getValue(DressData.class);
                if(userProfileCloth!=null){
                    clothPrice=userProfileCloth.getPrice();
                    clothDesc=userProfileCloth.getShopdescribe();
                    clothShopName=userProfileCloth.getShopName();
                    vClothPrice.setText(clothPrice);
                    vClothShopDes.setText(clothDesc);
                    vClothShopName.setText(clothShopName);
                    imageUrl=userProfileCloth.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(clothsShop_image);
                }else {
                    cloth_card.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
        databaseReference.child("Furniture").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                FurnitureData userProfilef = snapshot.getValue(FurnitureData.class);
                if( userProfilef!=null){
                    fPrice= userProfilef.getPrice();
                    vFPrice.setText(fPrice);
                    fDesc= userProfilef.getShopdescribe();
                    vFShopDes.setText(fDesc);
                    fShopName= userProfilef.getShopName();
                    vFShopName.setText(fShopName);

                    imageUrl=userProfilef.getImage();
                    Picasso.get().load(imageUrl).placeholder(R.drawable.no_image).into(furnitureShop_image);
                }else
                {
                    f_card.setVisibility(View.GONE);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ProfileShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void logoutAcc(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LogInPage.class));
        Animatoo.animateInAndOut(ProfileShopkeeper.this);
        finish();
    }
    public void updateAcc(View view){
        if(isNameChanged()){
            databaseReference.child("ShopKeeper").child(userID).child("fullName").setValue(vFullName.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "Name is same", Toast.LENGTH_SHORT).show();
        }
        if(isNumberChanged() ){
            databaseReference.child("ShopKeeper").child(userID).child("number").setValue(vNumber.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "Number is same", Toast.LENGTH_SHORT).show();
        }
        if(isglinkChanged()){
            databaseReference.child("ShopKeeper").child(userID).child("gLink").setValue(vlink.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "link is same", Toast.LENGTH_SHORT).show();
        }
        if(isdesChanged()){
            databaseReference.child("ShopKeeper").child(userID).child("description").setValue(vDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }
        if(isBusinessNameChanged()){
            String newname =vBusinessName.getEditableText().toString();
            vName.setText(newname);
            databaseReference.child("ShopKeeper").child(userID).child("shopName").setValue(newname);
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "Name is same", Toast.LENGTH_SHORT).show();
        }


        if ( imageUri != null) {
            final StorageReference filepath = storageReference.child(emailUser).child("profile_image");
            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String imageUrl = uri.toString();
                                    // create a Journal Object - model
                                    databaseReference.child("ShopKeeper").child(userID).child("imageUrl").setValue(imageUrl);

                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                        }
                    });
        }
    }

    private boolean isBusinessNameChanged() {

        if(!businessName.equals(vBusinessName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    //checkers
    private boolean isdesChanged() {
        if(!des.equals(vDes.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isglinkChanged() {
        if(!link.equals(vlink.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isNumberChanged() {
        if(!num.equals(vNumber.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isNameChanged(){

        if(!fName.equals(vFullName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isbookPriceChanged() {
        if(!bPrice.equals(vBookPrice.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isbookDesChanged() {
        if(!bookDesc.equals(vBookShopDes.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isbookShopNameChanged() {
        if(!bookShopName.equals(vBookShopName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean ishhPriceChanged() {
        if(!hhPrice.equals(vHhPrice.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean ishhDesChanged() {
        if(!hhDesc.equals(vHhShopDes.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean ishhShopNameChanged() {
        if(!hhShopName.equals(vHhShopName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isspPriceChanged() {
        if(!spPrice.equals(vSpPrice.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isspDesChanged() {
        if(!spDesc.equals(vSpShopDes.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isspShopNameChanged() {
        if(!spShopName.equals(vSpShopName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isMbPriceChanged() {
        if(!mbPrice.equals(vMbPrice.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isMbDesChanged() {
        if(!mbDesc.equals(vMbShopDes.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isMbShopNameChanged() {
        if(!mbShopName.equals(vMbShopName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }


    private boolean isfPriceChanged() {
        if(!fPrice.equals(vFPrice.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isfDesChanged() {
        if(!fDesc.equals(vFShopDes.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isfShopNameChanged() {
        if(!fShopName.equals(vFShopName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isClothPriceChanged() {
        if(!clothPrice.equals(vClothPrice.getText().toString().trim()) && clothPrice!=null){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isClothDesChanged() {
        if(!clothDesc.equals(vClothShopDes.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isClothShopNameChanged() {
        if(!clothShopName.equals(vClothShopName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                if(shopKeepC!=0){
                    imageUri = data.getData(); // we have the actual path to the image
                    profile_image.setImageURI(imageUri);
                    shopKeepC=0;bookC=0;sportsC=0;houseC=0;gadgetC=0;clothsC=0;furnitureC=0;
                }
                if(bookC!=0){
                    imageUri = data.getData(); // we have the actual path to the image
                    bookShop_image.setImageURI(imageUri);//show image
                    shopKeepC=0;bookC=0;sportsC=0;houseC=0;gadgetC=0;clothsC=0;furnitureC=0;
                }
                if(sportsC!=0){
                    imageUri = data.getData(); // we have the actual path to the image
                    sportsShop_image.setImageURI(imageUri);//show image
                    shopKeepC=0;bookC=0;sportsC=0;houseC=0;gadgetC=0;clothsC=0;furnitureC=0;
                }
                if(houseC!=0){
                    imageUri = data.getData(); // we have the actual path to the image
                    houseShop_image.setImageURI(imageUri);//show image
                    shopKeepC=0;bookC=0;sportsC=0;houseC=0;gadgetC=0;clothsC=0;furnitureC=0;
                }  if(gadgetC!=0){
                    imageUri = data.getData(); // we have the actual path to the image
                    gadgetShop_image.setImageURI(imageUri);//show image
                    shopKeepC=0;bookC=0;sportsC=0;houseC=0;gadgetC=0;clothsC=0;furnitureC=0;
                }
                if(clothsC!=0){
                    imageUri = data.getData(); // we have the actual path to the image
                    clothsShop_image.setImageURI(imageUri);//show image
                    shopKeepC=0;bookC=0;sportsC=0;houseC=0;gadgetC=0;clothsC=0;furnitureC=0;
                }
                if(furnitureC!=0){
                    imageUri = data.getData(); // we have the actual path to the image
                    furnitureShop_image.setImageURI(imageUri);//show image
                    shopKeepC=0;bookC=0;sportsC=0;houseC=0;gadgetC=0;clothsC=0;furnitureC=0;
                }
            }
        }
    }
    public void book_update(View view) {

        if(isbookPriceChanged()){
            databaseReference.child("Books").child(userID).child("price").setValue(vBookPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(isbookDesChanged()){
            databaseReference.child("Books").child(userID).child("shopdescribe").setValue(vBookShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(isbookShopNameChanged()){
            databaseReference.child("Books").child(userID).child("shopName").setValue(vBookShopName.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if ( imageUri != null) {
            final StorageReference filepath = storageReference.child(emailUser).child("bookShop_image");
            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    // create a Journal Object - model
                                    databaseReference.child("Books").child(userID).child("image").setValue(imageUrl);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                        }
                    });
        }
    }

    public void household_update(View view) {
        if(ishhPriceChanged()){
            databaseReference.child("Household").child(userID).child("price").setValue(vHhPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(ishhDesChanged()){
            databaseReference.child("Household").child(userID).child("shopdescribe").setValue(vHhShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(ishhShopNameChanged()){
            databaseReference.child("Household").child(userID).child("shopName").setValue(vHhShopName.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }

        if ( imageUri != null) {
            final StorageReference filepath = storageReference.child(emailUser).child("householdsShop_image");
            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    // create a Journal Object - model
                                    databaseReference.child("Household").child(userID).child("image").setValue(imageUrl);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                        }
                    });
        }
    }

    public void sports_update(View view) {
        if(isspPriceChanged()){
            databaseReference.child("Sports").child(userID).child("price").setValue(vSpPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(isspDesChanged()){
            databaseReference.child("Sports").child(userID).child("shopdescribe").setValue(vSpShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(isspShopNameChanged()){
            databaseReference.child("Sports").child(userID).child("shopName").setValue(vSpShopName.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if ( imageUri != null) {
            final StorageReference filepath = storageReference.child(emailUser).child("sportsShop_image");
            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    // create a Journal Object - model
                                    databaseReference.child("Sports").child(userID).child("image").setValue(imageUrl);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }

    public void gadget_update(View view) {

        if(isMbPriceChanged()){
            databaseReference.child("Mobile-Gadget").child(userID).child("price").setValue(vMbPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(isMbDesChanged()){
            databaseReference.child("Mobile-Gadget").child(userID).child("shopdescribe").setValue(vMbShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(isMbShopNameChanged()){
            databaseReference.child("Mobile-Gadget").child(userID).child("shopName").setValue(vMbShopName.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if ( imageUri != null) {
            final StorageReference filepath = storageReference.child(emailUser).child("gadgetShop_image");
            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    // create a Journal Object - model
                                    databaseReference.child("Mobile-Gadget").child(userID).child("image").setValue(imageUrl);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });
        }
    }

    public void cloth_update(View view) {

        if (isClothPriceChanged()) {
            databaseReference.child("Cloths").child(userID).child("price").setValue(vClothPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if (isClothDesChanged()) {
            databaseReference.child("Cloths").child(userID).child("shopdescribe").setValue(vClothShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if (isClothShopNameChanged()) {
            databaseReference.child("Cloths").child(userID).child("shopName").setValue(vClothShopName.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if ( imageUri != null) {
            final StorageReference filepath = storageReference.child(emailUser).child("clothsShop_image");
            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    // create a Journal Object - model
                                    databaseReference.child("Cloths").child(userID).child("image").setValue(imageUrl);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                        }
                    });
        }
    }

    public void furniture_update(View view) {
        if(isfPriceChanged()){
            databaseReference.child("Furniture").child(userID).child("price").setValue(vFPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(isfDesChanged()){
            databaseReference.child("Furniture").child(userID).child("shopdescribe").setValue(vFShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else {
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if(isfShopNameChanged()){
            databaseReference.child("Furniture").child(userID).child("shopName").setValue(vFShopName.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        else{
//            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
//        }
        if ( imageUri != null) {
            final StorageReference filepath = storageReference.child(emailUser).child("furnitureShop_image");
            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String imageUrl = uri.toString();
                                    // create a Journal Object - model
                                    databaseReference.child("Furniture").child(userID).child("image").setValue(imageUrl);
                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
            });
        }
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        startActivity(new Intent(this,HomeShopkeeper.class));
        Animatoo.animateSlideRight(ProfileShopkeeper.this);
        finish();
    }
}

