package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity implements
        AdapterView.OnItemClickListener {

    Button addKitchenButton, refreshButton;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList<Kitchen> mKitchenList = new ArrayList<Kitchen>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // On creation of the app, set up the relevant views, buttons, and lists.

        super.onCreate(savedInstanceState);

        // Choose the correct XML file to use.
        setContentView(R.layout.activity_main);

        // Define the buttons and listviews used in this activity.
        accessButtons();
        accessListView();

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
        Kitchen kitchen = mKitchenList.get(position);
        String kitchenName = kitchen.getName();

        // Create an Intent to go to the Kitchen activity
        Intent kitchenIntent = new Intent(this, KitchenActivity.class);

        // Save the relevant details about our Kitchen so they can be used in the new activity.
        kitchenIntent.putExtra("supplyKitchen", kitchenName);

        // Start the next Activity using your prepared Intent
        startActivity(kitchenIntent);

    }

    public void accessButtons() {
        // Access the buttons defined in the xml file.
        addKitchenButton = (Button) findViewById(R.id.add_kitchen_button);
        refreshButton = (Button) findViewById(R.id.refresh_button);
    }

    public void accessListView() {
        // Set up the ListView and connect it to the ArrayAdapter.
        mainListView = (ListView) findViewById(R.id.ingredient_listview);
        mArrayAdapter = new KitchenListAdapter(this, mKitchenList);
        mainListView.setAdapter(mArrayAdapter);

        // Set this activity to react to list items being pressed
        mainListView.setOnItemClickListener(this);
    }

    public void addKitchen(View v) {
        // Create the intent to add a new kitchen, and go to that activity.
        Intent addKitchenIntent = new Intent(this, AddKitchenActivity.class);
        startActivity(addKitchenIntent);
    }

    public void refreshList(View v) {
        updateKitchenList();
    }

    public void updateKitchenList() {
        ParseOperations.populateKitchenList(mKitchenList, mArrayAdapter);
    }

}