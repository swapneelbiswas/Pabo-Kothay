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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class households_search extends AppCompatActivity {

    EditText editText;
    List<HouseholdsData> householdsDataList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    String Type1Tree="Users",Type2Tree="Shops";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_households_search);

        ShopType shopType = new ShopType("Household");
        shopType.setShopType("Household");
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(households_search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        householdsDataList =new ArrayList<>();

        HouseholdAdapter myAdapter = new HouseholdAdapter(households_search.this,householdsDataList);
        myRv.setAdapter(myAdapter);
//        firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Household");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                householdsDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    HouseholdsData shopData =itemSnapshot.getValue(HouseholdsData.class);
                    householdsDataList.add(shopData);
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
        householdsDataList =new ArrayList<>();
        HouseholdAdapter myAdapter = new HouseholdAdapter(households_search.this,householdsDataList);
        myRv.setAdapter(myAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(households_search.this,1);
        myRv.setLayoutManager(gridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Household");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                householdsDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    HouseholdsData shopData =itemSnapshot.getValue(HouseholdsData.class);
                    if(shopData.getShopName().toLowerCase().contains(text.toLowerCase())){
                        householdsDataList.add(shopData);
                    }
                }
                myAdapter.filteredList(householdsDataList);
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
        Animatoo.animateSlideRight(households_search.this);
    }
}