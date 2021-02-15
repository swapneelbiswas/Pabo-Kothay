package com.example.pabokothay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class newAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);
        getSupportActionBar().hide();
    }
    public void goLogInPage(View view){
        Intent intent= new Intent(this,LogInPage.class);
        startActivity(intent);
    }
    public void goArea(View view){
        Intent intent= new Intent(this,area_details.class);
        startActivity(intent);
    }
}