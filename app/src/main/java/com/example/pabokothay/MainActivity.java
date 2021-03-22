package com.example.pabokothay;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.method.LinkMovementMethod;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText eText;
    Button btn,btn1;
    SearchView vSearchViewMain;
    ListView vListViewMain;
    private DrawerLayout Dl;
    private ActionBarDrawerToggle dToggle;
    ConstraintLayout constraintLayout;
    LinearLayout linearLayout;
    ArrayList<String> mainList;
    ArrayAdapter<String> adapter;
    private static final int GALLERY_CODE = 1;
    private FirebaseUser fUser;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private String userID;
    TextView vFullName,vMail,vName,vPass,vNumber;
    String fName,emailUser,username,pass,num,imageUrl;
    private Button imageAdd;
    private Uri imageUri;
    private StorageReference storageReference;
    private ImageView profile_image2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //geting user info
        fUser = FirebaseAuth.getInstance().getCurrentUser();
        databaseReference= FirebaseDatabase.getInstance().getReference("Users");
        userID=fUser.getUid();
        vName = findViewById(R.id.name);
        profile_image2=findViewById(R.id.profile_image2);
        Intent intent= getIntent();
        username = intent.getStringExtra("fullName");
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference.child("Customers").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);
                if(userProfile!=null){
                    fName = userProfile.fullName;
                    emailUser = userProfile.email;
                    pass= userProfile.password;
                    num=userProfile.number;
                    imageUrl=userProfile.imageUrl;
                    vName.setText(fName);

                    Picasso.get().load(imageUrl).placeholder(R.drawable.dp_1).into(profile_image2);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MainActivity.this, "Something is wrong", Toast.LENGTH_SHORT).show();
            }
        });


//        Dl=findViewById(R.id.drawer_layout);
//        dToggle = new ActionBarDrawerToggle(this,Dl,R.string.Open,R.string.Close);
//        Dl.addDrawerListener(dToggle);
//        dToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        //searchbar
        vSearchViewMain= (SearchView)findViewById(R.id.search_bar);
        vListViewMain=(ListView)findViewById(R.id.mainList);

        mainList=new ArrayList<String>();
        mainList.add("Books");
        mainList.add("Furniture");
        mainList.add("Sports");
        mainList.add("Household");
        mainList.add("Mobile and Gadgets");
        mainList.add("Dress");

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
                   Animatoo.animateSlideLeft(MainActivity.this);
               }
                else if(((TextView) view).getText().equals("sport")){
                    Intent intent= new Intent(view.getContext(),Sports_Stuff_Search.class);
                    startActivity(intent);
                   Animatoo.animateSlideLeft(MainActivity.this);
                }
                else if(((TextView) view).getText().equals("furniture")){
                    Intent intent= new Intent(view.getContext(),Furniture.class);
                    startActivity(intent);
                   Animatoo.animateSlideLeft(MainActivity.this);
                }
                else if(((TextView) view).getText().equals("household")){
                    Intent intent= new Intent(view.getContext(),households_search.class);
                    startActivity(intent);
                   Animatoo.animateSlideLeft(MainActivity.this);
                }
//                Toast.makeText(getApplicationContext(), ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
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
        Animatoo.animateSlideLeft(MainActivity.this);
        //finish();
    }
    public void goSsearch(View view){
    Intent intent= new Intent(this, Sports_Stuff_Search.class);
    startActivity(intent);
        Animatoo.animateSlideLeft(MainActivity.this);
        //finish();
    }
    public void goFsearch(View view){
    Intent intent= new Intent(this, Furniture.class);
    startActivity(intent);
        Animatoo.animateSlideLeft(MainActivity.this);

    }
    public void goHsearch(View view){
    Intent intent= new Intent(this, households_search.class);
    startActivity(intent);
        Animatoo.animateSlideLeft(MainActivity.this);
        //finish();
    }

    public void goProfile(View view){
        Intent intent= new Intent(this, profile.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(MainActivity.this);
        finish();
        //finish();
    }
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        //fire the slide left animation
        finish();
        Animatoo.animateSlideRight(MainActivity.this);
    }
}