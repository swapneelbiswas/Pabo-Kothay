package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class profile extends AppCompatActivity {
    private FirebaseUser fUser;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String userID;
    TextView vFullName,vMail,vName,vPass;
    String fName,emailUser,username,pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        userID=fUser.getUid();

        vFullName=findViewById(R.id.fullNamep);
        vMail = findViewById(R.id.emailText);
        vName = findViewById(R.id.name);
        vPass = findViewById(R.id.password);
        Intent intent= getIntent();
        username = intent.getStringExtra("fullName");
        databaseReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile!=null){
                    fName = userProfile.fullName;
                    emailUser = userProfile.email;
                    pass= userProfile.password;
                    vFullName.setText(fName);
                    vMail.setText(emailUser);
                    vName.setText(fName);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(profile.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void logoutAcc(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LogInPage.class));
        finish();
    }
    public void updateAcc(View view){

        if(isNameChanged()&& isPasswordSame()){
            Toast.makeText(profile.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(this,LogInPage.class));
//        finish();
    }
    private boolean isPasswordSame(){
        if(pass.equals(vPass.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean isNameChanged(){

        if(!fName.equals(vFullName.getText().toString().trim())){
            databaseReference.child(userID).child("fullName").setValue(vFullName.getEditableText().toString());
            return true;
        }
        else{
            return false;
        }
    }
}