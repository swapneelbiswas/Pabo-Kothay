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
    EditText editText;
    BookShopData mBookShopData;
    private Context mContext;
    private List<BookShopData> myShopList;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;

    RelativeLayout relativeLayoutl;
    String Type1Tree="Users",Type2Tree="Shops";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

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

        ShopType shopType = new ShopType("Books");
        shopType.setShopType("Books");

        //list data
        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(book_search.this,1);
        myRv.setLayoutManager(gridLayoutManager);


        //recycler
        bookShopDataList =new ArrayList<>();
        MyAdapter myAdapter = new MyAdapter(book_search.this, bookShopDataList);
        myRv.setAdapter(myAdapter);

        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Books");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookShopDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    BookShopData shopData =itemSnapshot.getValue(BookShopData.class);
                    bookShopDataList.add(shopData);
//                    list.add(shopData.getShopName());
//                    listID.add(shopData.getShopkeeperId());

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
        bookShopDataList =new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(book_search.this,1);
        myRv.setLayoutManager(gridLayoutManager);
        MyAdapter myAdapter = new MyAdapter(book_search.this, bookShopDataList);
        myRv.setAdapter(myAdapter);
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Books");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                bookShopDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    BookShopData shopData =itemSnapshot.getValue(BookShopData.class);
                    if(shopData.getShopName().toLowerCase().contains(text.toLowerCase())){
                        bookShopDataList.add(shopData);
                    }
                }
                myAdapter.filteredList(bookShopDataList);
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
        Animatoo.animateSlideRight(book_search.this);
    }
}