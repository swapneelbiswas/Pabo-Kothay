package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class newAccount extends AppCompatActivity implements View.OnClickListener{
    EditText vMail,vPassword,vPassword2,vfullname;
    ProgressBar progressBar;
    Button vButton;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        //getSupportActionBar().hide();
        //sign up work
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
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button:
                registerUser();
                break;
        }
    }
    public void registerUser(){
        String email = vMail.getText().toString().trim();
        String password = vPassword.getText().toString().trim();
        String number ="";
        String password2 = vPassword2.getText().toString().trim();
        String fullname = vfullname.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            vMail.setError("Email is required");
            vMail.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(email)){
            vfullname.setError("Full name is required");
            vfullname.requestFocus();
            return;
        }

        if(TextUtils.isEmpty(password)){
            vPassword.setError("password is required");
            vPassword.requestFocus();
            return;
        }
        if(TextUtils.isEmpty(password2)){
            vPassword2.setError("Re_enter password");
            vPassword2.requestFocus();

            return;
        }

        if(password.length()<6){
            vPassword.setError("Must be of 6 character");
            vPassword.requestFocus();
            return;
        }
        if (!(password.equals(password2)))
        {
            vPassword2.setError("Doesn't match");
            vPassword2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            vMail.setError("Please provide valid email");
            vMail.requestFocus();
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            User user= new User(fullname,email,password,number);
                            //DatabaseReference UserRef =FirebaseDatabase.getInstance().getReference("Users").child(Uid);
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser fuser =FirebaseAuth.getInstance().getCurrentUser();
                                        //Toast.makeText(newAccount.this, "created", Toast.LENGTH_SHORT).show();

                                        fuser.sendEmailVerification();
                                        Toast.makeText(newAccount.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
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
