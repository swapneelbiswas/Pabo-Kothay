package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText eText;
    Button btn,btn1;
    private DrawerLayout Dl;
    private ActionBarDrawerToggle dToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Dl=findViewById(R.id.drawer_layout);
//        dToggle = new ActionBarDrawerToggle(this,Dl,R.string.Open,R.string.Close);
//        Dl.addDrawerListener(dToggle);
//        dToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(dToggle.onOptionsItemSelected(item))
//            return true;
//        return super.onOptionsItemSelected(item);
//    }
    public void goArea(View view){
        Intent intent= new Intent(this,area_details.class);
        startActivity(intent);
    }
    public void goProfile(View view){
        Intent intent= new Intent(this, profile.class);
        startActivity(intent);
    }
}