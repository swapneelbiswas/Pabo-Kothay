package com.example.pabokothay;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class Furniture extends AppCompatActivity {


    SearchView vSearchView;
    ListView vListView;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_furniture);
        vSearchView= (SearchView)findViewById(R.id.searchViewBook);
        vListView=(ListView)findViewById(R.id.bookStoreList);

        list=new ArrayList<String>();
        list.add("Chair");
        list.add("Table");
        list.add("Sofa");


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

                if(((TextView) view).getText().equals("Chair")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                }
                else if(((TextView) view).getText().equals("Table")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                }
                else if(((TextView) view).getText().equals("Sofa")){
                    Intent intent= new Intent(view.getContext(),area_details.class);
                    startActivity(intent);
                }
            }
        });
        constraintLayout = findViewById(R.id.constraint_layout);
        constraintLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    vListView.setVisibility(View.GONE);
                }
            }
        });
        linearLayout = findViewById(R.id.lin_layout);
        linearLayout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                vListView.setVisibility(View.GONE);
            }
        });

    }
}