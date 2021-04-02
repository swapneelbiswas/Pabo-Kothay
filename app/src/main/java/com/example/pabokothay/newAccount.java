package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class newAccount extends AppCompatActivity implements View.OnClickListener{
    EditText vMail,vPassword,vPassword2,vfullname;
    ProgressBar progressBar;
    LinearLayout vButton;
    LottieAnimationView lv;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        //getSupportActionBar().hide();

        //sign up work
        lv = findViewById(R.id.loooad);
        mAuth = FirebaseAuth.getInstance();
        vfullname=findViewById(R.id.fullName);
        vMail = findViewById(R.id.login_mail);
        vPassword=findViewById(R.id.login_pass);
        vPassword2=findViewById(R.id.editTextTextPassword);
        vButton=findViewById(R.id.button);
        vButton.setOnClickListener(this);
    }

    public void goLogInPage(View view){
        Intent intent= new Intent(this,LogInPage.class);
        startActivity(intent);
        Animatoo.animateSlideRight(newAccount.this);
        finish();
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            registerUser();
        }
    }
    public void registerUser(){
        String email = vMail.getText().toString().trim();
        String password = vPassword.getText().toString().trim();
        String number ="01000000000";
        String password2 = vPassword2.getText().toString().trim();
        String fullname = vfullname.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            vMail.setError("Email is required");
            vMail.requestFocus();
        }
        else if(TextUtils.isEmpty(fullname)){
            vfullname.setError("Full name is required");
            vfullname.requestFocus();
        }

        else if(TextUtils.isEmpty(password)){
            vPassword.setError("password is required");
            vPassword.requestFocus();
        }
        else if(TextUtils.isEmpty(password2)){
            vPassword2.setError("Re_enter password");
            vPassword2.requestFocus();
        }

        else if(password.length()<6){
            vPassword.setError("Must be of 6 character");
            vPassword.requestFocus();
        }
        else if (!(password.equals(password2)))
        {
            vPassword2.setError("Doesn't match");
            vPassword2.requestFocus();
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            vMail.setError("Please provide valid email");
            vMail.requestFocus();
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                User user= new User(fullname,email,number,password);
                                lv.setVisibility(View.VISIBLE);
                                //DatabaseReference UserRef =FirebaseDatabase.getInstance().getReference("Users").child(Uid);
                                FirebaseDatabase.getInstance().getReference("Users").child("Customers")
                                        .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            FirebaseUser fuser =FirebaseAuth.getInstance().getCurrentUser();
                                            fuser.sendEmailVerification();
                                            Toast.makeText(newAccount.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                            Animatoo.animateSlideLeft(newAccount.this);
                                            finish();
                                        }else{
                                            Toast.makeText(newAccount.this, "Try again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                Toast.makeText(newAccount.this, "Try again.Error", Toast.LENGTH_SHORT).show();

                            }
                        }
            });
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(newAccount.this);
    }
}
