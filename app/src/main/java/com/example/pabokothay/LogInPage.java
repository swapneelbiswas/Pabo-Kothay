package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LogInPage extends AppCompatActivity implements View.OnClickListener{

    EditText vMail,vPassword;
    LinearLayout vButton;
    TextView vNewAccount;
    private FirebaseAuth mAuth;
    Dialog myDialog;

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
        vButton.setOnClickListener(this);
        vNewAccount=findViewById(R.id.go_newAccount);
        myDialog = new Dialog(this);
    }

    public void ShowPopup(View v) {

        Intent intent= new Intent(this,popUp.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(LogInPage.this);
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button:
                userLogin();
                break;
        }

    }
    public void userLogin(){
        String email = vMail.getText().toString().trim();
        String password = vPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            vMail.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(password)){
            vPassword.setError("password is required");
            return;
        }
        if(password.length()<6){
            vPassword.setError("Must be of 6 character");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            vMail.setError("Please provide valid email");
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
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
                    Toast.makeText(LogInPage.this, "Failed to login! Please check your info", Toast.LENGTH_SHORT).show();
                }
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


}