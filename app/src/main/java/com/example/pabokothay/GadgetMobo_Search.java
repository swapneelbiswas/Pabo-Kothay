package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

public class GadgetMobo_Search extends AppCompatActivity {

    List<GadgetData> gadgetDataList;
    EditText editText;
    private Context mContext;
    private List<GadgetData> myGadgetList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    String Type1Tree="Users",Type2Tree="Shops";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget_mobo__search);
        ShopType shopType = new ShopType("Mobile-Gadget");
        shopType.setShopType("Mobile-Gadget");

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

        //list data
        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(GadgetMobo_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        //recycler
        gadgetDataList =new ArrayList<>();
        GAdapter myAdapter = new GAdapter(GadgetMobo_Search.this,gadgetDataList);
        myRv.setAdapter(myAdapter);


        //firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Mobile-Gadget");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gadgetDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    GadgetData shopData =itemSnapshot.getValue(GadgetData.class);
                    gadgetDataList.add(shopData);
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
        gadgetDataList =new ArrayList<>();
        GAdapter myAdapter = new GAdapter(GadgetMobo_Search.this,gadgetDataList);
        myRv.setAdapter(myAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(GadgetMobo_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Mobile-Gadget");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gadgetDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    GadgetData shopData =itemSnapshot.getValue(GadgetData.class);
                    if(shopData.getShopName().toLowerCase().contains(text.toLowerCase())){
                        gadgetDataList.add(shopData);
                    }
                }
                myAdapter.filteredList(gadgetDataList);
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
        Animatoo.animateSlideRight(GadgetMobo_Search.this);
    }
}