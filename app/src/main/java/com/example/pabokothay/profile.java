package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
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
    TextView vFullName,vMail,vName,vPass,vNumber;
    String fName,emailUser,username,pass,num;
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
        vNumber = findViewById(R.id.pnum);


        //vPass = findViewById(R.id.passwordConfirm);
        Intent intent= getIntent();
        username = intent.getStringExtra("fullName");
        databaseReference.child("Customers").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile!=null){
                    fName = userProfile.fullName;
                    emailUser = userProfile.email;
                    pass= userProfile.password;
                    num=userProfile.number;
                    vFullName.setText(fName);
                    vMail.setText(emailUser);
                    vName.setText(fName);
                    vNumber.setText(num);
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

        if(isNameChanged()){
            databaseReference.child("Customers").child(userID).child("fullName").setValue(vFullName.getEditableText().toString());
            Toast.makeText(profile.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(profile.this, "Data can not be updated", Toast.LENGTH_SHORT).show();
        }
        if(isNumberChanged()){
            databaseReference.child("Customers").child(userID).child("number").setValue(vNumber.getEditableText().toString());
//            User user= new User(fullname,email,phoneNum);
//            //DatabaseReference UserRef =FirebaseDatabase.getInstance().getReference("Users").child(Uid);
//            FirebaseDatabase.getInstance().getReference("Users")
//                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
//                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        FirebaseUser fuser =FirebaseAuth.getInstance().getCurrentUser();
//                        //Toast.makeText(newAccount.this, "created", Toast.LENGTH_SHORT).show();
//
//                    }else{
//
//                    }
//                }
//            });

            Toast.makeText(profile.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(profile.this, "Data can not be updated", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNumberChanged() {
        if(!num.equals(vNumber.getText().toString().trim())){
//            databaseReference.child(userID).child("fullName").setValue(vFullName.getEditableText().toString());
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isNameChanged(){

        if(!fName.equals(vFullName.getText().toString().trim())){
//            databaseReference.child(userID).child("fullName").setValue(vFullName.getEditableText().toString());
            return true;
        }
        else{
            return false;
        }
    }
}