package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class StartupActivity extends AppCompatActivity {

    private static int splashTime =3000;
    Animation topanime,animbot,fadein;
    CardView t2;
    TextView tv;
    FirebaseAuth mFirebaseAuth;
    private FirebaseUser fUser;
    private DatabaseReference databaseReference;
    String userID,parentDb="Users",UserType="Customers",UserType2="ShopKeeper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_startup);
        //getSupportActionBar().hide();

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        fadein= AnimationUtils.loadAnimation(this,R.anim.fade_in);

        animbot= AnimationUtils.loadAnimation(this,R.anim.botomanim);
        t2=findViewById(R.id.srt_card);
        t2.setAnimation(animbot);
        tv= findViewById(R.id.srt_tv);
        tv.setAnimation(animbot);
        mFirebaseAuth = FirebaseAuth.getInstance();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               final DatabaseReference RootRef;
                RootRef = FirebaseDatabase.getInstance().getReference();
                fUser = FirebaseAuth.getInstance().getCurrentUser();
                databaseReference= FirebaseDatabase.getInstance().getReference("Users");
                if(fUser!=null) {
                    userID = fUser.getUid();
                }
                FirebaseUser mFirebaseUser =mFirebaseAuth.getCurrentUser();
                if(mFirebaseUser!=null){
                    RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.child(parentDb).child(UserType).child(userID).exists()){
                                snapshot.child(parentDb).child(UserType).child(userID).getValue(User.class);
                                //Toast.makeText(LogInPage.this, "Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                Animatoo.animateSlideLeft(StartupActivity.this);
                                finish();
                            }
                            else if(snapshot.child(parentDb).child(UserType2).child(userID).exists()){
                                snapshot.child(parentDb).child(UserType).child(userID).getValue(Shopkeeper.class);
                                //Toast.makeText(LogInPage.this, "Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),HomeShopkeeper.class));
                                Animatoo.animateSlideLeft(StartupActivity.this);
                                finish();
                            }
                            else{
                                Toast.makeText(StartupActivity.this, "Main Failed to login with ! Please check your info", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(StartupActivity.this, "Failed to login with ! Please check your info", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    Intent intent = new Intent(StartupActivity.this, LogInPage.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(StartupActivity.this);
                    finish();
                }
            }
        }, splashTime);
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(StartupActivity.this);
    }

}