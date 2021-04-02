package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

        vSearchView= (SearchView)findViewById(R.id.search_bar);
        vListView=(ListView)findViewById(R.id.mainList);
        list=new ArrayList<String>();
        listID=new ArrayList<String>();

        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        vListView.setAdapter(adapter);
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

                ShopType.setShopID(listID.get(position));
                Intent intent= new Intent(view.getContext(),area_details2.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(Furniture.this);
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
                    list.add(FData.getShopName());
                    listID.add(FData.getShopkeeperId());
                }
                myAdapter.notifyDataSetChanged();
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