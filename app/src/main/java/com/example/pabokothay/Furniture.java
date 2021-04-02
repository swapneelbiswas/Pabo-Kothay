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

public class Furniture extends AppCompatActivity {
    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;
    ArrayList<String> list;
    ArrayList<String> listID;
    ArrayAdapter<String> adapter;
    EditText editText;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    List<FurnitureData> furnitureDataList;
    String Type1Tree="Users",Type2Tree="Shops";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);

        ShopType shopType = new ShopType("Furniture");
        shopType.setShopType("Furniture");
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Furniture.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        furnitureDataList =new ArrayList<>();

        FAdapter myAdapter = new FAdapter(Furniture.this,furnitureDataList);
        myRv.setAdapter(myAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Furniture");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                furnitureDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    FurnitureData FData =itemSnapshot.getValue(FurnitureData.class);
                    furnitureDataList.add(FData);
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
        furnitureDataList =new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Furniture.this,1);
        myRv.setLayoutManager(gridLayoutManager);
        FAdapter myAdapter = new FAdapter(Furniture.this,furnitureDataList);
        myRv.setAdapter(myAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Furniture");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                furnitureDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    FurnitureData FData =itemSnapshot.getValue(FurnitureData.class);
                    if(FData.getShopName().toLowerCase().contains(text.toLowerCase())){
                        furnitureDataList.add(FData);
                    }
                }
                myAdapter.filteredList(furnitureDataList);
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
        Animatoo.animateSlideRight(Furniture.this);
    }
}