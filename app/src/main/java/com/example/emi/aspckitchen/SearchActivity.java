package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by CChen on 12/2/2015.
 */
public class SearchActivity extends Activity implements
        AdapterView.OnItemClickListener {

    ListView ingredientListView, equipmentListView;
    ArrayAdapter mArrayAdapterIngredients, mArrayAdapterEquipment;
    ArrayList<KitchenSupply> mEquipmentList = new ArrayList<KitchenSupply>();
    ArrayList<KitchenSupply> mIngredientList = new ArrayList<KitchenSupply>();
    Intent detailIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        handleIntent(getIntent());

        // Choose the correct XML file to use.
        setContentView(R.layout.activity_search);
        createListViewAndAdapter();

        String searchText = this.getIntent().getExtras().getString("searchString");
        Log.d("search2", searchText);
        doSearch(searchText);



    }

    private void doSearch(String queryStr) {
        ParseOperations.searchByName(queryStr, mEquipmentList, mIngredientList, mArrayAdapterEquipment, mArrayAdapterIngredients);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        KitchenSupply supply;

        // Check which ListView was selected, so we can select the correct supply.
        if(parent.getId() == R.id.ingredient_listview) {
            supply = mIngredientList.get(position);
        }else if(parent.getId() == R.id.equipment_listview) {
            supply = mEquipmentList.get(position);
        }
        else {
            return;
        }

        saveSupplyDetails(supply);
        startActivity(detailIntent);
    }
    public void saveSupplyDetails(KitchenSupply supply) {
        // Create an Intent to take you over to a new DetailActivity
        detailIntent = new Intent(this, DetailActivity.class);

        // Save the relevant details about our Supply so they can be used in the new activity.
        detailIntent.putExtra("supplyName", supply.getName());
        detailIntent.putExtra("supplyID", supply.getObjectId());
        detailIntent.putExtra("supplyKitchen", supply.getKitchen());
        detailIntent.putExtra("supplyNotes", supply.getNotes());
        detailIntent.putExtra("supplyType", supply.getType());
        detailIntent.putExtra("supplyStatus", supply.isInUse());
    }

    private void createListViewAndAdapter() {
        // Access the ListView
        ingredientListView = (ListView) findViewById(R.id.ingredient_listview);
        equipmentListView = (ListView) findViewById(R.id.equipment_listview);

        // Create an ArrayAdapter for the ListView so we can display the items.
        mArrayAdapterIngredients = new SearchListAdapter(this, mIngredientList);
        mArrayAdapterEquipment = new SearchListAdapter(this, mEquipmentList);

        // Set the ListView to use the ArrayAdapter
        ingredientListView.setAdapter(mArrayAdapterIngredients);
        equipmentListView.setAdapter(mArrayAdapterEquipment);

        // Set this activity to react to list items being pressed
        ingredientListView.setOnItemClickListener(this);
        equipmentListView.setOnItemClickListener(this);
    }

}