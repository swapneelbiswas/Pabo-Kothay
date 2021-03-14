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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class book_search extends AppCompatActivity {

    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    List<ShopData> shopDataList;
    ShopData mShopData;
    private Context mContext;
    private List<ShopData> myShopList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    RelativeLayout relativeLayoutl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);



        //list data
        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(book_search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        shopDataList =new ArrayList<>();
        mShopData = new ShopData("khub upokar korte parbo","momotaj book store","30000",R.drawable.books);
        shopDataList.add(mShopData);
        mShopData = new ShopData("khub kheladhula hobe","Rjsahi book store","30000",R.drawable.sportsstuff);
        shopDataList.add(mShopData);
        mShopData = new ShopData("khub basha banano hobe","Naraynganj  book store","30000",R.drawable.households);
        shopDataList.add(mShopData);
        mShopData = new ShopData("onk porte hobe","Rk book store","30000",R.drawable.books);
        shopDataList.add(mShopData);

        MyAdapter myAdapter = new MyAdapter(book_search.this,shopDataList);
        myRv.setAdapter(myAdapter);


        //firebase works
//        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child("ShopKeeper");
//        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                shopDataList.clear();
//                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
//                    ShopData shopData =itemSnapshot.getValue(ShopData.class);
//                    shopDataList.add(shopData);
//                }
//                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });



        //search bar

        vSearchView= (SearchView)findViewById(R.id.search_bar);
        vListView=(ListView)findViewById(R.id.mainList);

        list=new ArrayList<String>();
        list.add("momotaj book store");
        list.add("Rjsahi book store");
        list.add("Naraynganj  book store");
        list.add("modumoti book store");
        list.add("Rk book store");
        list.add("BK dash book store");
        list.add("sugondha book store");

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
                if(((TextView) view).getText().equals("momotaj book store")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                }
                else if(((TextView) view).getText().equals("Rjsahi book store")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                }
                else if(((TextView) view).getText().equals("Naraynganj  book store")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                }
                else if(((TextView) view).getText().equals("modumoti book store")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
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

}