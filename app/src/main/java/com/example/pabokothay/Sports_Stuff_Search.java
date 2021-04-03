package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Sports_Stuff_Search extends AppCompatActivity {

    EditText editText;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    List<SportsData> sportsDataList;
    String Type1Tree="Users",Type2Tree="Shops";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports__stuff__search);

        ShopType shopType = new ShopType("Sports");
        shopType.setShopType("Sports");

        editText= findViewById(R.id.edit_recycler);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Sports_Stuff_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        sportsDataList =new ArrayList<>();

        SportAdapter myAdapter = new SportAdapter(Sports_Stuff_Search.this,sportsDataList);
        myRv.setAdapter(myAdapter);


//        firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Sports");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sportsDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    SportsData shopData =itemSnapshot.getValue(SportsData.class);
                    sportsDataList.add(shopData);
                }
                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void filter(String text) {
        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        sportsDataList =new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Sports_Stuff_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);
        SportAdapter myAdapter = new SportAdapter(Sports_Stuff_Search.this,sportsDataList);
        myRv.setAdapter(myAdapter);

        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Sports");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sportsDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    SportsData shopData =itemSnapshot.getValue(SportsData.class);
                    if(shopData.getShopName().toLowerCase().contains(text.toLowerCase())){
                        sportsDataList.add(shopData);
                    }
                }
                myAdapter.filteredList(sportsDataList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(Sports_Stuff_Search.this);
    }
}