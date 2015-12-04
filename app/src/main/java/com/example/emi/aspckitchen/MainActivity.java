package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements
        AdapterView.OnItemClickListener {

    Button refreshButton, searchButton;
    EditText searchEditText;


    /*ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList<Kitchen> mKitchenList = new ArrayList<Kitchen>();*/

    ListView pomonaListView;
    ArrayAdapter pomonaArrayAdapter;
    ArrayList<Kitchen> pomonaKitchenList = new ArrayList<Kitchen>();

    ListView muddListView;
    ArrayAdapter muddArrayAdapter;
    ArrayList<Kitchen> muddKitchenList = new ArrayList<Kitchen>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // On creation of the app, set up the relevant views, buttons, and lists.

        super.onCreate(savedInstanceState);

        // Choose the correct XML file to use.
        setContentView(R.layout.activity_main);

        // Define the buttons and listviews used in this activity.
        accessButtons();
        accessListView();
        searchEditText = (EditText)findViewById(R.id.search_listview);


        // Initial population of KitchenList with current kitchens.
        updateKitchenList();
    }


    @Override
    protected void onResume() {
        // On resuming the activity, update the kitchen list.
        super.onResume();
        updateKitchenList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // Create a new Kitchen activity to match the kitchen selected from the list.

        // Get the kitchen
        Kitchen kitchen;

        //Check which campus
        if(parent.getId() == R.id.pomona_listview) {
            kitchen = pomonaKitchenList.get(position);
        }else if(parent.getId() == R.id.hmc_listview){
            kitchen = muddKitchenList.get(position);
        }
        else {
            return;
        }

        String kitchenName = kitchen.getName();
        String campusName = kitchen.getCampus();

        // Create an Intent to go to the Kitchen activity
        Intent kitchenIntent = new Intent(this, KitchenActivity.class);

        // Save the relevant details about our Kitchen so they can be used in the new activity.
        kitchenIntent.putExtra("supplyKitchen", kitchenName);
        kitchenIntent.putExtra("campusName", campusName);

        // Start the next Activity using your prepared Intent
        startActivity(kitchenIntent);

    }

    public void accessButtons() {
        // Access the buttons defined in the xml file.
        refreshButton = (Button) findViewById(R.id.refresh_button);
        searchButton = (Button) findViewById(R.id.search_button);
    }

    public void accessListView() {
        // Set up the ListView and connect it to the ArrayAdapter.
        pomonaListView = (ListView) findViewById(R.id.pomona_listview);
        pomonaArrayAdapter = new KitchenListAdapter(this, pomonaKitchenList);
        pomonaListView.setAdapter(pomonaArrayAdapter);

        // Set this activity to react to list items being pressed
        pomonaListView.setOnItemClickListener(this);


        muddListView = (ListView) findViewById(R.id.hmc_listview);
        muddArrayAdapter = new KitchenListAdapter(this, muddKitchenList);
        muddListView.setAdapter(muddArrayAdapter);
        muddListView.setOnItemClickListener(this);

    }

    public void addKitchen(View v) {
        // Create the intent to add a new kitchen, and go to that activity.
        Intent addKitchenIntent = new Intent(this, AddKitchenActivity.class);
        startActivity(addKitchenIntent);
    }

    public void refreshList(View v) {
        updateKitchenList();
    }

    public void search(View v){
        // Create an Intent to go to the Kitchen activity
        Intent searchIntent = new Intent(this, SearchActivity.class);

        String searchText = searchEditText.getText().toString();
        Log.d("search", searchText);

        // Save the relevant details about our Kitchen so they can be used in the new activity.
        searchIntent.putExtra("searchString", searchText);
        startActivity(searchIntent);
    }

    public void updateKitchenList() {
        //use new parse function once per campus
        ParseOperations.populateKitchenByCampusList(pomonaKitchenList, pomonaArrayAdapter, "Pomona");
        ParseOperations.populateKitchenByCampusList(muddKitchenList, muddArrayAdapter, "Harvey Mudd");
    }

}