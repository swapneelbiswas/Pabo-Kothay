package com.example.pabokothay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LogInPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        getSupportActionBar().hide();
    }

    public void goMainActivity(View view){
        Intent intent= new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}