package com.example.pabokothay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class popUp extends AppCompatActivity{
    Button bt;
    Button bt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        bt = (Button)findViewById(R.id.btngonewC);
        bt.setOnClickListener(this::goNewAccount);
        bt2 = (Button)findViewById(R.id.btngonewS);
        bt2.setOnClickListener(this::goNewShopAccount);
    }

    public void goNewAccount(View view){
        Intent intent= new Intent(this,newAccount.class);
        startActivity(intent);
    }
    public void goNewShopAccount(View view){
        Intent intent= new Intent(this,new_shop_account.class);
        startActivity(intent);
    }


}