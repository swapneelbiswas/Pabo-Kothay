package com.example.pabokothay;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMapOptions;

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
}