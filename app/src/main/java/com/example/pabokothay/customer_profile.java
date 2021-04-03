package com.example.pabokothay;

import androidx.annotation.NonNull;
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

public class customer_profile extends AppCompatActivity {
    private static final int GALLERY_CODE = 1;
    private FirebaseUser fUser;
    private DatabaseReference databaseReference;
    private String userID;
    TextView vFullName,vMail,vName,vNumber;
    String fName,emailUser,username,pass,num,imageUrl;
    private StorageReference storageReference;
    private ImageView profile_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        userID=fUser.getUid();

        vFullName=findViewById(R.id.fullNamep);
        vMail = findViewById(R.id.emailText);
        vName = findViewById(R.id.name);
        vNumber = findViewById(R.id.pnum);
        profile_image=findViewById(R.id.profile_image);

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(customer_profile.this);
            }
        });

        Intent intent= getIntent();
        username = intent.getStringExtra("fullName");
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference.child("Customers").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile!=null){
                    fName = userProfile.fullName;
                    emailUser = userProfile.email;
                    pass= userProfile.password;
                    num=userProfile.number;
                    imageUrl=userProfile.imageUrl;
                    vFullName.setText(fName);
                    vMail.setText(emailUser);
                    vName.setText(fName);
                    vNumber.setText(num);
                    Picasso.get().load(imageUrl).placeholder(R.drawable.sideheader).into(profile_image);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(customer_profile.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void goProfile(View view){
        Intent intent= new Intent(this, profile.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(customer_profile.this);
        finish();
    }
    public void logoutAcc(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LogInPage.class));
        Animatoo.animateInAndOut(customer_profile.this);
        finish();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        startActivity(new Intent(this,MainActivity.class));
        finish();
        Animatoo.animateSlideRight(customer_profile.this);


    }
}