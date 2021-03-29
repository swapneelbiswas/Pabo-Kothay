package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
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

import java.util.ArrayList;

public class HomeShopkeeper extends AppCompatActivity {

    SearchView vSearchViewMain;
    ListView vListViewMain;
    private DrawerLayout Dl;
    private ActionBarDrawerToggle dToggle;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;
    ArrayList<String> mainList;
    ArrayAdapter<String> adapter;
    private static final int GALLERY_CODE = 1;
    private FirebaseUser fUser;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String userID;
    TextView vName,vMail,vSName,vNumber,vDescription, vLink;
    String fName,emailUser,username,pass,num,imageUrl, googleLink, description, shopName;
    private Button imageAdd;
    private Uri imageUri;
    private StorageReference storageReference;
    private ImageView profile_image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_shopkeeper);
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        userID=fUser.getUid();
        profile_image2=findViewById(R.id.profile_image2);
        Intent intent= getIntent();
        username = intent.getStringExtra("fullName");
        storageReference = FirebaseStorage.getInstance().getReference();
        vSName = findViewById(R.id.sName);
        vName = findViewById(R.id.skName);
        vMail = findViewById(R.id.sMail);
        vNumber = findViewById(R.id.sNumber);
        vLink = findViewById(R.id.sLink);
        vDescription = findViewById(R.id.sDescription);

        databaseReference.child("ShopKeeper").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Shopkeeper userProfile = snapshot.getValue(Shopkeeper.class);
                if(userProfile!=null){
                    fName = userProfile.fullName;
                    emailUser = userProfile.email;
                    pass= userProfile.password;
                    num=userProfile.number;
                    googleLink = userProfile.gLink;
                    description = userProfile.description;
                    shopName = userProfile.shopName;
                    imageUrl=userProfile.imageUrl;

                    vSName.setText(shopName);
                    vName.setText(fName);
                    vMail.setText(emailUser);
                    vNumber.setText(num);
                    vLink.setText(googleLink);
                    vDescription.setText(description);

                    Picasso.get().load(imageUrl).placeholder(R.drawable.dp_1).into(profile_image2);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(HomeShopkeeper.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


//        Dl=findViewById(R.id.drawer_layout);
//        dToggle = new ActionBarDrawerToggle(this,Dl,R.string.Open,R.string.Close);
//        Dl.addDrawerListener(dToggle);
//        dToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //searchbar
    }


    public void goProfile(View view){
        Intent intent= new Intent(this, ProfileShopkeeper.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(HomeShopkeeper.this);
        //finish();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(HomeShopkeeper.this);
    }
}