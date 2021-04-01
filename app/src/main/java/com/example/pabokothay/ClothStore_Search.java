package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

public class ClothStore_Search extends AppCompatActivity {

    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    List<DressData> clothesDataList;
    ShopData mShopData;
    private Context mContext;
    private List<DressData> myDressList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    String Type1Tree="Users",Type2Tree="Shops";

    RelativeLayout relativeLayoutl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cloth_store__search);
        ShopType shopType = new ShopType("Cloths");
        shopType.setShopType("Cloths");
//        Toast.makeText(ClothStore_Search.this, shopType.getShopType(), Toast.LENGTH_SHORT).show();

        //list data
        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(ClothStore_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        //calling variables
        vSearchView= (SearchView)findViewById(R.id.search_bar);
        vListView=(ListView)findViewById(R.id.mainList);

        //recycler
        clothesDataList =new ArrayList<>();
        DAdapter myAdapter = new DAdapter(ClothStore_Search.this,clothesDataList);
        myRv.setAdapter(myAdapter);

        //search bar
        list=new ArrayList<String>();
        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        vListView.setAdapter(adapter);

        //firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Cloths");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                clothesDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DressData shopData =itemSnapshot.getValue(DressData.class);
                    clothesDataList.add(shopData);
                    list.add(shopData.getShopName());
                }
                myAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        vSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                vListView.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        vListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(((TextView) view).getText().equals("momotaj book store")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(ClothStore_Search.this);
                }
                else if(((TextView) view).getText().equals("Rjsahi book store")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(ClothStore_Search.this);
                }
                else if(((TextView) view).getText().equals("Naraynganj  book store")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(ClothStore_Search.this);
                }
                else if(((TextView) view).getText().equals("modumoti book store")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(ClothStore_Search.this);
                }
            }
        });
        recyclerView = findViewById(R.id.myRecycleView);
        recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                vListView.setVisibility(v.GONE);
            }
        });
        relativeLayout = findViewById(R.id.rl);
        relativeLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                vListView.setVisibility(v.GONE);
            }
        });
        constraintLayout = findViewById(R.id.cl);
        constraintLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                vListView.setVisibility(v.GONE);
            }
        });
        //search bar end
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(ClothStore_Search.this);
    }
}