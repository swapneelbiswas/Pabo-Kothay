package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class profile extends AppCompatActivity {

    private static final int GALLERY_CODE = 1;
    private FirebaseUser fUser;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String userID;
    TextView vFullName,vMail,vName,vPass,vNumber;
    String fName,emailUser,username,pass,num,imageUrl;
    private Button imageAdd;
    private Uri imageUri;
    private StorageReference storageReference;
    private ImageView profile_image;

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
        profile_image=findViewById(R.id.profile_image);
        // imageAdd=findViewById(R.id.imageAdd);

        //vPass = findViewById(R.id.passwordConfirm);
        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_CODE);
                Animatoo.animateSlideLeft(profile.this);
            }
        });
        // String imageUri = "https://firebasestorage.googleapis.com/v0/b/pabo-kothay-f16c0.appspot.com/o/journal_images%2Fmy_image_22?alt=media&token=d0755304-8b50-4313-b1ee-e16ddf2ba60e";
        // Picasso.get().load(imageUrl).placeholder(imageUri).into(profile_image);

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
                Toast.makeText(profile.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void logoutAcc(View view){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this,LogInPage.class));
        Animatoo.animateInAndOut(profile.this);
        finish();
    }
    public void updateAcc(View view){
        if(isNameChanged()){
            String newname =vFullName.getEditableText().toString();
            vName.setText(newname);
            databaseReference.child("Customers").child(userID).child("fullName").setValue(newname);
            Toast.makeText(profile.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(profile.this, "Name is same", Toast.LENGTH_SHORT).show();
        }
        if(isNumberChanged() ){
            databaseReference.child("Customers").child(userID).child("number").setValue(vNumber.getEditableText().toString());
            Toast.makeText(profile.this, "Data has been updated", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(profile.this, "Number is same", Toast.LENGTH_SHORT).show();
        }
        if ( imageUri != null) {
            final StorageReference filepath = storageReference
                    .child(emailUser)
                    .child("profile_image");
            filepath.putFile(imageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                            filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    String imageUrl = uri.toString();
                                    // create a Journal Object - model
                                    databaseReference.child("Customers").child(userID).child("imageUrl").setValue(imageUrl);

                                }
                            });
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            //  progressBar.setVisibility(View.INVISIBLE);

                        }
                    });
        }
    }

    private boolean isNumberChanged() {
        if(!num.equals(vNumber.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isNameChanged(){

        if(!fName.equals(vFullName.getText().toString().trim())){
            return true;
        }
        else{
            return false;
        }
    }

    //    private boolean isPassSame() {
//        if(pass.equals(vPass.getText().toString().trim())){
//            return true;
//        }
//        else{
//            return false;
//        }
//    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_CODE && resultCode == RESULT_OK) {
            if (data != null) {
                imageUri = data.getData(); // we have the actual path to the image
                profile_image.setImageURI(imageUri);//show image

            }
        }
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        startActivity(new Intent(this,customer_profile.class));
        finish();
        Animatoo.animateSlideRight(profile.this);


    }
}