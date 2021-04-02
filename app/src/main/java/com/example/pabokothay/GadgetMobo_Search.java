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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GadgetMobo_Search extends AppCompatActivity {

    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ArrayList<String> list;
    ArrayList<String> listID;
    ArrayAdapter<String> adapter;
    List<GadgetData> gadgetDataList;

    private Context mContext;
    private List<GadgetData> myGadgetList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    String Type1Tree="Users",Type2Tree="Shops";
    RelativeLayout relativeLayoutl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gadget_mobo__search);
        ShopType shopType = new ShopType("Mobile-Gadget");
        shopType.setShopType("Mobile-Gadget");

        //list data
        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(GadgetMobo_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        //calling variables
        vSearchView= (SearchView)findViewById(R.id.search_bar);
        vListView=(ListView)findViewById(R.id.mainList);

        //recycler
        gadgetDataList =new ArrayList<>();
        GAdapter myAdapter = new GAdapter(GadgetMobo_Search.this,gadgetDataList);
        myRv.setAdapter(myAdapter);

        //search bar
        list=new ArrayList<String>();
        listID=new ArrayList<String>();
        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        vListView.setAdapter(adapter);

        //firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Mobile-Gadget");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                gadgetDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    GadgetData shopData =itemSnapshot.getValue(GadgetData.class);
                    gadgetDataList.add(shopData);
                    list.add(shopData.getShopName());
                    listID.add(shopData.getShopkeeperId());
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
                ShopType.setShopID(listID.get(position));
                Intent intent= new Intent(view.getContext(),area_details2.class);
                startActivity(intent);
                Animatoo.animateSlideLeft(GadgetMobo_Search.this);
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
        Animatoo.animateSlideRight(GadgetMobo_Search.this);
    }
}