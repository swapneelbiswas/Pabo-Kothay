package com.example.pabokothay;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

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

import java.util.ArrayList;

public class HomeShopkeeper extends AppCompatActivity {

    SearchView vSearchViewMain;
    ListView vListViewMain;
    private DrawerLayout Dl;
    private ActionBarDrawerToggle dToggle;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;
    ArrayList<String> mainList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_shopkeeper);
        vSearchViewMain= (SearchView)findViewById(R.id.search_bar);
        vListViewMain=(ListView)findViewById(R.id.mainList);

        mainList=new ArrayList<String>();
        mainList.add("book");
        mainList.add("boss");
        mainList.add("bonk");
        mainList.add("sport");
        mainList.add("furniture");
        mainList.add("household");

//        System.out.println(mainList.get(0));
        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mainList);
        vListViewMain.setAdapter(adapter);
        vSearchViewMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                vListViewMain.setVisibility(View.VISIBLE);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        /*
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
         */
        vListViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(((TextView) view).getText().equals("book")){
                    Intent intent= new Intent(view.getContext(),book_search.class);
                    startActivity(intent);
                }
                else if(((TextView) view).getText().equals("sport")){
                    Intent intent= new Intent(view.getContext(),Sports_Stuff_Search.class);
                    startActivity(intent);
                }
                else if(((TextView) view).getText().equals("furniture")){
                    Intent intent= new Intent(view.getContext(),Furniture.class);
                    startActivity(intent);
                }
                else if(((TextView) view).getText().equals("household")){
                    Intent intent= new Intent(view.getContext(),households_search.class);
                    startActivity(intent);
                }
                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
            }
        });
        constraintLayout = findViewById(R.id.constraint_layout);
        constraintLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    vListViewMain.setVisibility(View.GONE);
                }
            }
        });
        linearLayout = findViewById(R.id.lin_layout);
        linearLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                vListViewMain.setVisibility(View.GONE);
            }
        });
//        Dl=findViewById(R.id.drawer_layout);
//        dToggle = new ActionBarDrawerToggle(this,Dl,R.string.Open,R.string.Close);
//        Dl.addDrawerListener(dToggle);
//        dToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    //    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if(dToggle.onOptionsItemSelected(item))
//            return true;
//        return super.onOptionsItemSelected(item);
//    }
    public void goArea(View view){
        Intent intent= new Intent(this, book_search.class);
        startActivity(intent);
        //finish();
    }
    public void goSsearch(View view){
        Intent intent= new Intent(this, Sports_Stuff_Search.class);
        startActivity(intent);
        //finish();
    }
    public void goFsearch(View view){
        Intent intent= new Intent(this, Furniture.class);
        startActivity(intent);
        //finish();
    }
    public void goHsearch(View view){
        Intent intent= new Intent(this, households_search.class);
        startActivity(intent);
        //finish();
    }

    public void goProfile(View view){
        Intent intent= new Intent(this, profile.class);
        startActivity(intent);
        //finish();
    }
}