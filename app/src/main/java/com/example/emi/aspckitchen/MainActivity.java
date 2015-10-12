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
    //KitchenSupplyList mSupplyList;mSupplyList =
    ArrayList<KitchenSupply> mSupplyList = new ArrayList<KitchenSupply>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Access the TextView defined in layout XML and then set its text.
        mainTextView = (TextView) findViewById(R.id.main_textview);
        mainTextView.setText("Enter Kitchen Supplies");

        // Access the addButton defined in the XML file, and create its listener.
        addButton = (Button) findViewById(R.id.main_button);
        addButton.setOnClickListener(this);

        // Access the EditText defined in layout XML
        mainEditText = (EditText) findViewById(R.id.main_edittext);
        // Access the ListView
        mainListView = (ListView) findViewById(R.id.main_listview);

        // Initialize the SupplyList from something random - this is just a placeholder. Should be overwritten below.
        //mSupplyList = ParseObject.createWithoutData(KitchenSupplyList.class, supplyListObjectID);

        // Initialize the KitchenSupplyList from Parse
//        ParseQuery<KitchenSupplyList> query = ParseQuery.getQuery(KitchenSupplyList.class);
//        //query.whereEqualTo("kitchenName", "Kitchen 1");
//        query.findInBackground(new FindCallback<KitchenSupplyList>() {
//            public void done(List<KitchenSupplyList> results, ParseException e) {
//                if (e==null){
//                    Log.d("supply", "Retrieved a supply list");
//                    for (KitchenSupplyList list : results){
//                        mSupplyList = new KitchenSupplyList(list.getId());
//                        Log.d("supply", "supplyList kitchen is:" + list.getKitchen());
//                    }
//                    // mSupplyList is saved as our object
//                }
//                else {
//                    Log.d("score", "Error: " + e.getMessage());
//                }
//            }
//        });


        //mSupplyList = new KitchenSupplyList();



        // Update the data so we have the current supplies.

        updateSupplyList();


        // Create an ArrayAdapter for the ListView
        mArrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mSupplyList);

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);

        // 5. Set this activity to react to list items being pressed
        mainListView.setOnItemClickListener(this);


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
        mSupplyList.add(new KitchenSupply(mainEditText.getText().toString()));
        mArrayAdapter.notifyDataSetChanged();
        mainEditText.setText("");

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        KitchenSupply supply = mSupplyList.get(position);
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
