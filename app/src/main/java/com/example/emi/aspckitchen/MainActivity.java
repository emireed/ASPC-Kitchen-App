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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    TextView mainTextView;
    Button addButton;
    EditText mainEditText;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList<KitchenSupply> mSupplyList = new ArrayList<KitchenSupply>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Choose the correct XML file to use.
        setContentView(R.layout.activity_main);


        // Access the TextView defined in layout XML and then set its text.
        mainTextView = (TextView) findViewById(R.id.main_textview);
        mainTextView.setText("Enter Kitchen Supplies");

        // Access the addButton defined in the XML file, and create its listener.
        addButton = (Button) findViewById(R.id.main_button);
        addButton.setOnClickListener(this);

        // Access the EditText and ListView
        //mainEditText = (EditText) findViewById(R.id.main_edittext);
        mainListView = (ListView) findViewById(R.id.main_listview);

        // Populate the supply list with the list of supplies currently on Parse.
        updateSupplyList();

        // Create an ArrayAdapter for the ListView so we can display the items.
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mSupplyList);

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);

        // Set this activity to react to list items being pressed
        mainListView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Every time this activity is resumed, update the supply list again.
        updateSupplyList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        // Create a new KitchenSupply based on the item
        //mSupplyList.add(new KitchenSupply(mainEditText.getText().toString()));
        //mArrayAdapter.notifyDataSetChanged();

        // Reset the EditText to be blank.
        //mainEditText.setText("");

        Intent addSupplyIntent = new Intent(this, AddSupplyActivity.class);
        startActivity(addSupplyIntent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Get the supply
        KitchenSupply supply = mSupplyList.get(position);
        String supplyName = supply.getName();
        String supplyID = supply.getObjectId();

        // create an Intent to take you over to a new DetailActivity
        Intent detailIntent = new Intent(this, DetailActivity.class);

        // Save the relevant details about our Supply so they can be used in the new activity.
        detailIntent.putExtra("supplyName", supplyName);
        detailIntent.putExtra("supplyID", supplyID);

        // Start the next Activity using your prepared Intent
        startActivity(detailIntent);

    }

    public void updateSupplyList() {
        ParseQuery<KitchenSupply> query = ParseQuery.getQuery(KitchenSupply.class);
        query.findInBackground(new FindCallback<KitchenSupply>() {

            @Override
            public void done(List<KitchenSupply> supplies, ParseException error) {
                if(supplies != null){
                    mSupplyList.clear();
                    mSupplyList.addAll(supplies);
                    mArrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }

}