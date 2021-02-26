package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText eText;
    Button btn,btn1;
    SearchView vSearchViewMain;
    ListView vListViewMain;
    private DrawerLayout Dl;
    private ActionBarDrawerToggle dToggle;

    ArrayList<String> mainList;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Dl=findViewById(R.id.drawer_layout);
//        dToggle = new ActionBarDrawerToggle(this,Dl,R.string.Open,R.string.Close);
//        Dl.addDrawerListener(dToggle);
//        dToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vSearchViewMain= (SearchView)findViewById(R.id.search_bar);
        vListViewMain=(ListView)findViewById(R.id.mainList);

        mainList=new ArrayList<String>();
        mainList.add(" book ");
        mainList.add("sport");
        mainList.add("furmitre");
        mainList.add("food");



        System.out.println(mainList.get(0));
        adapter= new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,mainList);

        vListViewMain.setAdapter(adapter);

        vSearchViewMain.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


        vListViewMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Intent intent= new Intent(view.getContext(),book_search.class);
                    startActivity(intent);
                }
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
    }

    public void goProfile(View view){
        Intent intent= new Intent(this, profile.class);
        startActivity(intent);
    }
}