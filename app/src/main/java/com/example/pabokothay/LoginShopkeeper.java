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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginShopkeeper extends AppCompatActivity implements View.OnClickListener {

    EditText sMail,sPassword;
    LinearLayout sButton;
    TextView sNewAccount;
    private FirebaseAuth sAuth;
    Dialog syDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_shopkeeper);

        sAuth = FirebaseAuth.getInstance();
        sMail = findViewById(R.id.login_mail_shop);
        sPassword=findViewById(R.id.login_pass_shop);
        sButton=findViewById(R.id.button_shop);
        sButton.setOnClickListener((View.OnClickListener) this);
        sNewAccount=findViewById(R.id.go_newAccount_shop);
        syDialog = new Dialog(this);
    }

    public void ShowPopup(View v) {

        Intent intent= new Intent(this,popUp.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(LoginShopkeeper.this);
    }

    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button_shop:
                userLogin();
                break;
        }

    }


    public void userLogin(){
        String email = sMail.getText().toString().trim();
        String password = sPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            sMail.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(password)){
            sPassword.setError("password is required");
            return;
        }
        if(password.length()<6){
            sPassword.setError("Must be of 6 character");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            sMail.setError("Please provide valid email");
            return;
        }
        sAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user =FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        //Toast.makeText(LogInPage.this, "Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeShopkeeper.class));
                        Animatoo.animateSlideLeft(LoginShopkeeper.this);
                        finish();
                    }
                    else {
                        user.sendEmailVerification();
                        Toast.makeText(LoginShopkeeper.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(LoginShopkeeper.this, "Failed to login! Please check your info", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        finish();
        Animatoo.animateSlideRight(LoginShopkeeper.this);
    }
}