package com.example.pabokothay;

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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;
import java.util.List;

public class Sports_Stuff_Search extends AppCompatActivity {


    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    List<ShopData> shopDataList;
    ShopData mShopData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports__stuff__search);
        vSearchView= (SearchView)findViewById(R.id.search_bar);
        vListView=(ListView)findViewById(R.id.mainList);

        list=new ArrayList<String>();
        list.add("Cricket");
        list.add("Football");
        list.add("Basketball");


        System.out.println(list.get(0));
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

                if(((TextView) view).getText().equals("Cricket")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(Sports_Stuff_Search.this);
                }
                else if(((TextView) view).getText().equals("Football")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(Sports_Stuff_Search.this);
                }
                else if(((TextView) view).getText().equals("Basketball")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(Sports_Stuff_Search.this);
                }
            }
        });

//        linearLayout = findViewById(R.id.line_layout);
//        linearLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                vListView.setVisibility(View.GONE);
//            }
//        });

        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Sports_Stuff_Search.this,1);
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

        MyAdapter myAdapter = new MyAdapter(Sports_Stuff_Search.this,shopDataList);
        myRv.setAdapter(myAdapter);

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        Animatoo.animateSlideRight(Sports_Stuff_Search.this);
    }
}