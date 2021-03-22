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

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class households_search extends AppCompatActivity {

    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;

    SearchView vSearchView;
    ListView vListView;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    List<HouseholdsData> householdsDataList;
    ShopData mShopData;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_households_search);
        vSearchView= (SearchView)findViewById(R.id.search_bar);
        vListView=(ListView)findViewById(R.id.mainList);
        list=new ArrayList<String>();
//        list.add("Fry pan");
//        list.add("kettle");
//        list.add("Pan");
//        list.add("Spoon");
//        System.out.println(list.get(0));

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

                if(((TextView) view).getText().equals("Fry pan")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(households_search.this);
                }
                else if(((TextView) view).getText().equals("kettle")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(households_search.this);
                }
                else if(((TextView) view).getText().equals("Pan")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(households_search.this);
                }
                else if(((TextView) view).getText().equals("Spoon")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                    Animatoo.animateSlideLeft(households_search.this);
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
        GridLayoutManager gridLayoutManager = new GridLayoutManager(households_search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        householdsDataList =new ArrayList<>();
//        mShopData = new ShopData("khub upokar korte parbo","momotaj book store","30000",R.drawable.books);
//        shopDataList.add(mShopData);
//        mShopData = new ShopData("khub kheladhula hobe","Rjsahi book store","30000",R.drawable.sportsstuff);
//        shopDataList.add(mShopData);
//        mShopData = new ShopData("khub basha banano hobe","Naraynganj  book store","30000",R.drawable.households);
//        shopDataList.add(mShopData);
//        mShopData = new ShopData("onk porte hobe","Rk book store","30000",R.drawable.books);
//        shopDataList.add(mShopData);

        HouseholdAdapter myAdapter = new HouseholdAdapter(households_search.this,householdsDataList);
        myRv.setAdapter(myAdapter);


//        firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child("Household");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                householdsDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    HouseholdsData shopData =itemSnapshot.getValue(HouseholdsData.class);
                    householdsDataList.add(shopData);
                    list.add(shopData.getShopName());
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
        Animatoo.animateSlideRight(households_search.this);
    }
}