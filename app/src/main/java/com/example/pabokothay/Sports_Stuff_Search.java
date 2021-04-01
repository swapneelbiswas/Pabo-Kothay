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

public class Sports_Stuff_Search extends AppCompatActivity {
    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    private DatabaseReference databaseReference;
    private ValueEventListener eventListener;
    List<SportsData> sportsDataList;
    Furniture mFurniture;
    String Type1Tree="Users",Type2Tree="Shops";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports__stuff__search);

        ShopType shopType = new ShopType("Sports");
        shopType.setShopType("Sports");
//        Toast.makeText(Sports_Stuff_Search.this, shopType.getShopType(), Toast.LENGTH_SHORT).show();

        vSearchView= (SearchView)findViewById(R.id.search_bar);
        vListView=(ListView)findViewById(R.id.mainList);
        list=new ArrayList<String>();

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


        RecyclerView myRv = (RecyclerView) findViewById(R.id.myRecycleView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Sports_Stuff_Search.this,1);
        myRv.setLayoutManager(gridLayoutManager);

        sportsDataList =new ArrayList<>();

        SportAdapter myAdapter = new SportAdapter(Sports_Stuff_Search.this,sportsDataList);
        myRv.setAdapter(myAdapter);


//        firebase works
        databaseReference= FirebaseDatabase.getInstance().getReference(Type1Tree).child("Sports");
        eventListener =databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sportsDataList.clear();
                for(DataSnapshot itemSnapshot: snapshot.getChildren()){
                    SportsData shopData =itemSnapshot.getValue(SportsData.class);
                    sportsDataList.add(shopData);
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
        Animatoo.animateSlideRight(Sports_Stuff_Search.this);
    }
}