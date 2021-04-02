package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class LogInPage extends AppCompatActivity  implements View.OnClickListener{

    EditText vMail,vPassword;
    LinearLayout vButton;
    TextView vNewAccount,log_in_error;
    private FirebaseAuth mAuth;
    Dialog myDialog;
    private FirebaseUser fUser;
    private DatabaseReference databaseReference;
    LottieAnimationView lv;

    String userID,parentDb="Users",UserType="Customers",UserType2="ShopKeeper";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        //getSupportActionBar().hide();

        //login stuff
        mAuth = FirebaseAuth.getInstance();
        vMail = findViewById(R.id.login_mail);
        vPassword=findViewById(R.id.login_pass);
        vButton=findViewById(R.id.button);
        log_in_error=findViewById(R.id.loginerror);
        vButton.setOnClickListener(this);
        vNewAccount=findViewById(R.id.go_newAccount);
        myDialog = new Dialog(this);
        lv = findViewById(R.id.loooad);
    }

    public void ShowPopup(View v) {

        Intent intent= new Intent(this,popUp.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(LogInPage.this);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.button) {//load();
            userLogin();
        }
    }
    public void userLogin(){
        String email = vMail.getText().toString().trim();
        String password = vPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            vMail.setError("Email is required");

        }
        else if(TextUtils.isEmpty(password)){
            vPassword.setError("password is required");

        }
        else if(password.length()<6){
            vPassword.setError("Must be of 6 character");

        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            vMail.setError("Please provide valid email");
        }
        else{
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        fUser = FirebaseAuth.getInstance().getCurrentUser();
                        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
                        if(fUser!=null){
                            userID=fUser.getUid();
                            lv.setVisibility(View.VISIBLE);
                            allowAccessToId(email,password);

                        }
                    }else{
//                        Toast.makeText(LogInPage.this, "First Failed to login with "+email+"! Please check your info", Toast.LENGTH_SHORT).show();
                        log_in_error.setText("⚠ Email or Password doesn't match ");
                    }
                }
            });
        }
    }

    private void allowAccessToId(String email, String password) {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        log_in_error.setVisibility(View.INVISIBLE);

        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child(parentDb).child(UserType).child(userID).exists()){

                    User userProfile = snapshot.child(parentDb).child(UserType).child(userID).getValue(User.class);
                    if(userProfile.getEmail().equals(email) && userProfile.getPassword().equals(password)){

                        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                        if(user.isEmailVerified()){
                            //Toast.makeText(LogInPage.this, "Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            Animatoo.animateSlideLeft(LogInPage.this);
                            finish();
                        }
                        else {
                            user.sendEmailVerification();
                            Toast.makeText(LogInPage.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(LogInPage.this, "Failed to login! Please check your info", Toast.LENGTH_SHORT).show();
                    }
                }
                 else if(snapshot.child(parentDb).child(UserType2).child(userID).exists()){
                    Shopkeeper userShop = snapshot.child(parentDb).child(UserType2).child(userID).getValue(Shopkeeper.class);
                    if(userShop.getEmail().equals(email) && userShop.getPassword().equals(password)){

                        FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                        if(user.isEmailVerified()){
                            startActivity(new Intent(getApplicationContext(),HomeShopkeeper.class));
                            Animatoo.animateSlideLeft(LogInPage.this);
                            finish();
                        }
                        else {
                            user.sendEmailVerification();
                            Toast.makeText(LogInPage.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        FirebaseAuth.getInstance().signOut();
                        Toast.makeText(LogInPage.this, "Failed to login! Please check your info", Toast.LENGTH_SHORT).show();
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
//                    Toast.makeText(LogInPage.this, "Failed to login with "+email+"! Please check your info", Toast.LENGTH_SHORT).show();
                Toast.makeText(LogInPage.this, "Failed to login with "+email+"! Please check your info", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        finish();
        Animatoo.animateSlideRight(LogInPage.this);
    }
//    public void load() {
//        lv.setVisibility(View.VISIBLE);
//    }


}