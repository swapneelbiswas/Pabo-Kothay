package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String userID;
    TextView vFullName,vMail,vName,vPass,vNumber,vDes,vlink,vBookShopDes,vBookPrice,vBookShopName,vHhShopDes,vHhPrice,vHhShopName,vSpShopDes,vSpPrice,vSpShopName;
    String fName,emailUser,username,pass,num,imageUrl,des,link,bookDesc,bPrice,bookShopName,hhDesc,hhPrice,hhShopName,spDesc,spPrice,spShopName;
    private Button imageAdd;
    private Uri imageUri;
    private StorageReference storageReference;
    private ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_shopkeeper);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        userID=fUser.getUid();

        vFullName=findViewById(R.id.fullNamep);
        vMail = findViewById(R.id.emailText);
        vDes = findViewById(R.id.describe);
        vlink = findViewById(R.id.mapLink);
        vName = findViewById(R.id.name);
        vNumber = findViewById(R.id.pnum);
        vPass= findViewById(R.id.passwordCheck);


        profile_image=findViewById(R.id.profile_image);

        vBookShopDes= findViewById(R.id.bookDes);
        vBookPrice= findViewById(R.id.bookPrice);
        vBookShopName=findViewById(R.id.bookShopN);

        vHhShopDes= findViewById(R.id.hhDes);
        vHhPrice= findViewById(R.id.hhPrice);
        vHhShopName=findViewById(R.id.hhN);

        vSpShopDes= findViewById(R.id.spDes);
        vSpPrice= findViewById(R.id.spPrice);
        vSpShopName=findViewById(R.id.spN);
        // imageAdd=findViewById(R.id.imageAdd);

        //vPass = findViewById(R.id.passwordConfirm);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(ProfileShopkeeper.this);
            }
        });
        // String imageUri = "https://firebasestorage.googleapis.com/v0/b/pabo-kothay-f16c0.appspot.com/o/journal_images%2Fmy_image_22?alt=media&token=d0755304-8b50-4313-b1ee-e16ddf2ba60e";
        // Picasso.get().load(imageUrl).placeholder(imageUri).into(profile_image);

        Intent intent= getIntent();
        username = intent.getStringExtra("fullName");
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference.child("ShopKeeper").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Shopkeeper userProfile = snapshot.getValue(Shopkeeper.class);
                if(userProfile!=null){
                    fName = userProfile.fullName;
                    link = userProfile.gLink;
                    des= userProfile.description;
                    num=userProfile.number;
                    imageUrl=userProfile.imageUrl;


                    vFullName.setText(fName);
                    vDes.setText(des);
                    vlink.setText(link);
                    vName.setText(fName);
                    vNumber.setText(num);

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


                BookData userProfilebook = snapshot.getValue(BookData.class);

                if(userProfilebook!=null){
                    bPrice=userProfilebook.price;
                    vBookPrice.setText(bPrice);

                    bookDesc=userProfilebook.shopdescribe;
                    vBookShopDes.setText(bookDesc);

                    bookShopName=userProfilebook.shopName;
                    vBookShopName.setText(bookShopName);


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
                    hhPrice=userProfilehh.price;
                    vHhPrice.setText(hhPrice);

                    hhDesc=userProfilehh.shopdescribe;
                    vHhShopDes.setText(hhDesc);

                    hhShopName=userProfilehh.shopName;
                    vHhShopName.setText(hhShopName);


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
                    spPrice=userProfilesp.price;
                    vSpPrice.setText(spPrice);

                    spDesc=userProfilesp.shopdescribe;
                    vSpShopDes.setText(spDesc);

                    spShopName=userProfilesp.shopName;
                    vSpShopName.setText(spShopName);


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
        startActivity(new Intent(this,LoginShopkeeper.class));
        Animatoo.animateInAndOut(ProfileShopkeeper.this);
        finish();
    }
    public void updateAcc(View view){
        if(isNameChanged()){
            String newname =vFullName.getEditableText().toString();
            vName.setText(newname);
            databaseReference.child("ShopKeeper").child(userID).child("fullName").setValue(newname);
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


        if(isbookPriceChanged()){
            databaseReference.child("Books").child(userID).child("price").setValue(vBookPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }
        if(isbookDesChanged()){
            databaseReference.child("Books").child(userID).child("shopdescribe").setValue(vBookShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }
        if(isbookShopNameChanged()){
            databaseReference.child("Books").child(userID).child("price").setValue(vBookPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }



        if(ishhPriceChanged()){
            databaseReference.child("Household").child(userID).child("price").setValue(vHhPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }
        if(ishhDesChanged()){
            databaseReference.child("Household").child(userID).child("shopdescribe").setValue(vHhShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }
        if(ishhShopNameChanged()){
            databaseReference.child("Household").child(userID).child("price").setValue(vHhPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }


        if(isspPriceChanged()){
            databaseReference.child("Sports").child(userID).child("price").setValue(vSpPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }
        if(isspDesChanged()){
            databaseReference.child("Sports").child(userID).child("shopdescribe").setValue(vSpShopDes.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }
        if(isspShopNameChanged()){
            databaseReference.child("Sports").child(userID).child("price").setValue(vSpPrice.getEditableText().toString());
            Toast.makeText(ProfileShopkeeper.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ProfileShopkeeper.this, "description is same", Toast.LENGTH_SHORT).show();
        }


        if ( imageUri != null) {
            final StorageReference filepath = storageReference
                    .child(emailUser)
                    .child("profile_image");
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
                            //  progressBar.setVisibility(View.INVISIBLE);

                        }
                    });
        }
    }




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
    //    private boolean isPassSame() {
//        if(pass.equals(vPass.getText().toString().trim())){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData(); // we have the actual path to the image
                profile_image.setImageURI(imageUri);//show image
            }
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(ProfileShopkeeper.this);
    }
}

