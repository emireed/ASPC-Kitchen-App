package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    TextView mainTextView;
    Button addButton;
    EditText mainEditText;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    KitchenSupplyList mSupplyList;
    ArrayList<KitchenSupply> mSupplyArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Access the TextView defined in layout XML
        // and then set its text
        mainTextView = (TextView) findViewById(R.id.main_textview);
        mainTextView.setText("Enter Kitchen Supplies");

        // 2. Access the Button defined in layout XML
        // and listen for it here
        addButton = (Button) findViewById(R.id.main_button);
        addButton.setOnClickListener(this);
        // 3. Access the EditText defined in layout XML
        mainEditText = (EditText) findViewById(R.id.main_edittext);
        // Access the ListView
        mainListView = (ListView) findViewById(R.id.main_listview);

        //TODO: Initialize the KitchenSupplyList from Parse
        //TODO: Fix this line, it's breaking the app for some reason
        //Note: It's because KitchenSupplyList was a Parse Object and it didn't like that...figure this out later
        //TODO: KitchenSupply is no longer a parse object, just regular object with data to save into a parse object jsonarray. Thus we do not add supplies to parse, only update teh array itself
        mSupplyList = new KitchenSupplyList();
        mSupplyArrayList = new ArrayList<KitchenSupply>();
        mSupplyArrayList.add(new KitchenSupply("spatula"));

        mSupplyList.setArrayList(mSupplyArrayList);

        // Get the ArrayList from the KitchenSupplyList
       // mSupplyArrayList = mSupplyList.getArrayList();


        // Create an ArrayAdapter for the ListView
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mSupplyArrayList);

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);

        // 5. Set this activity to react to list items being pressed
        mainListView.setOnItemClickListener(this);


        // 6. Populate the KitchenSupply list from Parse


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        // Also add that value to the list shown in the ListView
        mSupplyArrayList.add(new KitchenSupply(mainEditText.getText().toString()));
        mArrayAdapter.notifyDataSetChanged();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        KitchenSupply supply = mSupplyArrayList.get(position);
        String supplyName = supply.getName();

        // create an Intent to take you over to a new DetailActivity
        Intent detailIntent = new Intent(this, DetailActivity.class);
        //Intent detailIntentObject = new Intent()

        // pack away the data about the name
        // into your Intent before you head out
        detailIntent.putExtra("supplyName", supplyName);

        // start the next Activity using your prepared Intent
        startActivity(detailIntent);

    }

}
