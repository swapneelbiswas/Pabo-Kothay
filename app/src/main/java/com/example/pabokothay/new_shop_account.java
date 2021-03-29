package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class new_shop_account extends AppCompatActivity implements View.OnClickListener {

    EditText vMail, vPassword, vPassword2, vfullname, vShopName,vMapLink,vSdescription;
    ProgressBar progressBar;
    LinearLayout vButton;
    private FirebaseAuth mAuth;
    LinearLayout vMakeOrder;
    TextView vViewItem;
    String currentTime;
    String[] listItems;
    boolean[] checkedItems;
    String f;
    int c=0;
    ArrayList<Integer> vUserItems = new ArrayList<>();
    public String selectedItems[] = new String[vUserItems.size()] ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_shop_account);
        //getSupportActionBar().hide();
        currentTime= DateFormat.getDateTimeInstance()
                .format(Calendar.getInstance().getTime());
        mAuth = FirebaseAuth.getInstance();
        vfullname = findViewById(R.id.login_fName);
        vMail = findViewById(R.id.login_mail);
        vPassword = findViewById(R.id.login_pass);
        vPassword2 = findViewById(R.id.editTextTextPassword);
        vShopName = findViewById(R.id.editShopName);
        vMapLink = findViewById(R.id.gMap_link);
        vButton = findViewById(R.id.button);
        vButton.setOnClickListener(this);
        vSdescription=findViewById(R.id.s_description);
        vMakeOrder = (LinearLayout) findViewById(R.id.MakeOrder);
        vViewItem = (TextView) findViewById(R.id.ViewItem);

        listItems = getResources().getStringArray(R.array.shopping_list);
        checkedItems = new boolean[listItems.length];

        vMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(new_shop_account.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!vUserItems.contains(position)) {
                                vUserItems.add(position);
                            }
                        } else if (vUserItems.contains(position)) {
                            vUserItems.remove(position);
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String item = "";
                        for (int i = 0; i < vUserItems.size(); i++) {
                            item = item + listItems[vUserItems.get(i)];
                            f = item;
                            if (i != vUserItems.size() - 1) {
                                item = item + ",";
                            }
                        }
                        vViewItem.setText(item);
                    }
                });
//
//                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialogInterface.dismiss();
//
//                    }
//                });
                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for (int i = 0; i < checkedItems.length; i++) {

                            checkedItems[i] = false;
                            vUserItems.clear();
                            vViewItem.setText("");
                        }
                    }
                });
                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
                // c = fuck.length();
//                values = fuck.split(",");

//                for(int i=0; i< checkedItems.length;i++)
//                {
//                    selectedItems[i] = String.valueOf(vUserItems.get(i));
//                    System.out.println(selectedItems[i]);
//                }
            }
        });
    }

    public void goLogInPage(View view) {
        Intent intent = new Intent(this, LogInPage.class);
        startActivity(intent);
        Animatoo.animateSlideRight(new_shop_account.this);
        finish();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {
            registerUser();
        }
    }

    public void registerUser() {
        String email = vMail.getText().toString().trim();
        String password = vPassword.getText().toString().trim();
        String number = "01********";
        String password2 = vPassword2.getText().toString().trim();
        String gLink = vMapLink.getText().toString().trim();
        String shopName = vShopName.getText().toString().trim();
        String fullname =vfullname.getText().toString().trim();
        String description =vSdescription.getText().toString().trim();

        if(TextUtils.isEmpty(fullname)){
            vfullname.setError("Full name is required");
            vfullname.requestFocus();
        }
        else if (TextUtils.isEmpty(email)) {
            vMail.setError("Email is required");
            vMail.requestFocus();
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            vMail.setError("Please provide valid email");
            vMail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)) {
            vPassword.setError("password is required");
            vPassword.requestFocus();
        }
        else if (password.length() < 6) {
            vPassword.setError("Must be of 6 character");
            vPassword.requestFocus();
        }
        else if (TextUtils.isEmpty(password2)) {
            vPassword2.setError("Re_enter password");
            vPassword2.requestFocus();
        }
        else if (!(password.equals(password2))) {
            vPassword2.setError("Doesn't match");
            vPassword2.requestFocus();

        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Shopkeeper shopkeeper = new Shopkeeper(fullname, email, password, number, shopName, description,gLink);
                        ShopData shopData = new ShopData(description, shopName, gLink);
                        //DatabaseReference UserRef =FirebaseDatabase.getInstance().getReference("Users").child(Uid);
                        String[] values = f.split(",");
                        for(int i=0; i<values.length;i++){
                            FirebaseDatabase.getInstance().getReference("Users").child(values[i])
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(shopData).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(new_shop_account.this, "created sub accounts", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(new_shop_account.this, "Try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });};
                        FirebaseDatabase.getInstance().getReference("Users").child("ShopKeeper")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(shopkeeper).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {

                                    FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
//                                        Toast.makeText(newAccount.this, "created", Toast.LENGTH_SHORT).show();
                                    fuser.sendEmailVerification();
                                    Toast.makeText(new_shop_account.this, "Check your email to verify account", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                    Animatoo.animateSlideLeft(new_shop_account.this);
                                    finish();
                                } else {
                                    Toast.makeText(new_shop_account.this, "Try again", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }  else {
                        Toast.makeText(new_shop_account.this, "Give proper info", Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }

    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(new_shop_account.this);
    }
}