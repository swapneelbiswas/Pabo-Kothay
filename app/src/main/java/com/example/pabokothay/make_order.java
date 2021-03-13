package com.example.pabokothay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class make_order extends AppCompatActivity {

    Button vMakeOrder;
    TextView vViewItem;
    String[] listItems;
    boolean[] checkedItems;
    ArrayList<Integer> vUserItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);


        vMakeOrder = (Button) findViewById(R.id.MakeOrder);
        vViewItem= (TextView) findViewById(R.id.ViewItem);

        listItems = getResources().getStringArray(R.array.shopping_list);
        checkedItems= new boolean[listItems.length];

        vMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(make_order.this);
                mBuilder.setTitle(R.string.dialog_title);
                mBuilder.setMultiChoiceItems(listItems, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int position, boolean isChecked) {
                        if(isChecked){
                            if( ! vUserItems.contains(position)){
                                vUserItems.add(position);
                            }
                        } else if(vUserItems.contains(position)){
                            vUserItems.remove(position);
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton(R.string.ok_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        String item = "";
                        for(int i = 0; i< vUserItems.size();i++){
                            item = item + listItems[vUserItems.get(i)];
                            if(i != vUserItems.size()-1){
                                item= item + ",";
                            }
                        }

                        vViewItem.setText(item);

                    }
                });
//
//                mBuilder.setNegativeButton(R.string.dismiss_label, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialogInterface.dismiss();
//
//                    }
//                });

                mBuilder.setNeutralButton(R.string.clear_all_label, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        for( int i = 0 ; i < checkedItems.length; i++) {

                            checkedItems[i] = false;
                            vUserItems.clear();
                            vViewItem.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });

    }
}