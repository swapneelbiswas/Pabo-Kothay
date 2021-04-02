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

public class book_search extends AppCompatActivity {

    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    ArrayList<String> list;
    ArrayList<String> listID;
    ArrayAdapter<String> adapter;
    List<BookShopData> bookShopDataList;
    private Context mContext;
    private List<BookShopData> myShopList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    String Type1Tree="Users",Type2Tree="Shops";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        ShopType shopType = new ShopType("Books");
        shopType.setShopType("Books");


        //list data
        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(book_search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        //calling variables
        vSearchView= (SearchView)findViewById(R.id.search_bar);
        vListView=(ListView)findViewById(R.id.mainList);

        //recycler
        bookShopDataList =new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(book_search.this, bookShopDataList);
        myRv.setAdapter(myAdapter);

        //search bar
        list=new ArrayList<String>();
        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        vListView.setAdapter(adapter);
        listID=new ArrayList<String>();

        //firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Books");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookShopDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    BookShopData shopData =itemSnapshot.getValue(BookShopData.class);
                    bookShopDataList.add(shopData);
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
                Animatoo.animateSlideLeft(book_search.this);
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
        Animatoo.animateSlideRight(book_search.this);
    }
}