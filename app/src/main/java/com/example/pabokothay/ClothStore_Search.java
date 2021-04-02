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

public class ClothStore_Search extends AppCompatActivity {

    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ArrayList<String> list;
    ArrayList<String> listID;
    ArrayAdapter<String> adapter;
    List<DressData> clothesDataList;
    private Context mContext;
    private List<DressData> myDressList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    String Type1Tree="Users",Type2Tree="Shops";
    EditText editText;
    RelativeLayout relativeLayoutl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_store__search);
        ShopType shopType = new ShopType("Cloths");
        shopType.setShopType("Cloths");

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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ClothStore_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);


        //recycler
        clothesDataList =new ArrayList<>();
        DAdapter myAdapter = new DAdapter(ClothStore_Search.this,clothesDataList);
        myRv.setAdapter(myAdapter);


        //firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Cloths");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clothesDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DressData shopData =itemSnapshot.getValue(DressData.class);
                    clothesDataList.add(shopData);
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
        clothesDataList =new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ClothStore_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);
        DAdapter myAdapter = new DAdapter(ClothStore_Search.this,clothesDataList);
        myRv.setAdapter(myAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Cloths");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clothesDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DressData shopData =itemSnapshot.getValue(DressData.class);
                    if(shopData.getShopName().toLowerCase().contains(text.toLowerCase())){
                        clothesDataList.add(shopData);
                    }
                }
                myAdapter.filteredList(clothesDataList);
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
        Animatoo.animateSlideRight(ClothStore_Search.this);
    }
}