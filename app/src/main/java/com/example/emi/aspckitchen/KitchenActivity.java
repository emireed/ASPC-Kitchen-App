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

/**
 * Created by Keighley on 10/25/2015.
 */
public class KitchenActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    String kitchenName, campusName;
    Button refreshButton;
    ListView ingredientListView, equipmentListView;
    ArrayAdapter mArrayAdapterIngredients, mArrayAdapterEquipment;
    ArrayList<KitchenSupply> mEquipmentList = new ArrayList<KitchenSupply>();
    ArrayList<KitchenSupply> mIngredientList = new ArrayList<KitchenSupply>();
    Intent detailIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kitchenName = this.getIntent().getExtras().getString("supplyKitchen");
        campusName = this.getIntent().getExtras().getString("campusName");

        // Display the title of the page as the kitchen name.
        getActionBar().setTitle(kitchenName);

        // Choose the correct XML file to use.
        setContentView(R.layout.activity_kitchen);

        createButtons();
        createListViewsAndAdapters();

        // Populate the supply list with the list of supplies currently on Parse.
        updateSupplyList();
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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        KitchenSupply supply;

        // Check which list the user selected from.
        if(parent.getId() == R.id.ingredient_listview) {
            supply = mIngredientList.get(position);
        }else if(parent.getId() == R.id.equipment_listview) {
            supply = mEquipmentList.get(position);
        }
        else {
            return;
        }

        // Look at the details of the supply.
        saveSupplyDetails(supply);
        startActivity(detailIntent);
    }

    /* Save the relevant details about our Supply so they can be used in the new activity. */
    public void saveSupplyDetails(KitchenSupply supply) {
        detailIntent = new Intent(this, DetailActivity.class);

        detailIntent.putExtra("supplyName", supply.getName());
        detailIntent.putExtra("supplyID", supply.getObjectId());
        detailIntent.putExtra("campusName", supply.getCampus());
        detailIntent.putExtra("supplyKitchen", supply.getKitchen());
        detailIntent.putExtra("supplyNotes", supply.getNotes());
        detailIntent.putExtra("supplyStatus", supply.isInUse());
    }

    /* Go to a new activity to create a kitchen supply. */
    public void addKitchenSupply(View v) {
        Intent addSupplyIntent = new Intent(this, AddSupplyActivity.class);
        addSupplyIntent.putExtra("kitchenName", kitchenName);
        addSupplyIntent.putExtra("campusName", campusName);
        startActivity(addSupplyIntent);
    }

    /* Refresh the supply list by pulling current supplies from Parse. */
    public void updateSupplyList() {
        ParseOperations.populateSupplyList(kitchenName, campusName, mEquipmentList, mIngredientList,
                mArrayAdapterEquipment, mArrayAdapterIngredients);
    }

    public void refreshList(View v) {
        updateSupplyList();
    }

    /* Access the buttons for this page. */
    public void createButtons() {
        refreshButton = (Button) findViewById(R.id.refresh_button);
    }

    /* Access the list views and adapters and configure their settings. */
    public void createListViewsAndAdapters() {
        // Access the ListView
        ingredientListView = (ListView) findViewById(R.id.ingredient_listview);
        equipmentListView = (ListView) findViewById(R.id.equipment_listview);

        // Create an ArrayAdapter for the ListView so we can display the items.
        mArrayAdapterIngredients = new SupplyListAdapter(this, mIngredientList);
        mArrayAdapterEquipment = new SupplyListAdapter(this, mEquipmentList);

        // Set the ListView to use the ArrayAdapter
        ingredientListView.setAdapter(mArrayAdapterIngredients);
        equipmentListView.setAdapter(mArrayAdapterEquipment);

        // Set this activity to react to list items being pressed
        ingredientListView.setOnItemClickListener(this);
        equipmentListView.setOnItemClickListener(this);
    }

}
