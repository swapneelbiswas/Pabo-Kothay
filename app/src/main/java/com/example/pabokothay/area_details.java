package com.example.pabokothay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import android.view.View;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


import org.w3c.dom.Text;

public class area_details extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_details);
        getSupportActionBar().hide();

        TextView t = (TextView)findViewById(R.id.textView9);
        t.setMovementMethod(LinkMovementMethod.getInstance());

    }
    public void gotolink(View view){
        gotoUrl("https://www.google.com/");

    }

    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}