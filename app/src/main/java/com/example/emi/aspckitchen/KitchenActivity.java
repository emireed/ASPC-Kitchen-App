package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keighley on 10/25/2015.
 */
public class KitchenActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener{

    String kitchenName;
    Button addSupplyButton, refreshButton;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList<KitchenSupply> mSupplyList = new ArrayList<KitchenSupply>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        kitchenName = this.getIntent().getExtras().getString("kitchenName");

        // Display the title of the page as the kitchen name.
        getActionBar().setTitle(kitchenName);

        // Choose the correct XML file to use.
        setContentView(R.layout.activity_kitchen);


        // Define the buttons defined in the xml file.
        addSupplyButton = (Button) findViewById(R.id.main_button);
        refreshButton = (Button) findViewById(R.id.refresh_button);

        // Access the ListView
        mainListView = (ListView) findViewById(R.id.main_listview);

        // Populate the supply list with the list of supplies currently on Parse.
        updateSupplyList();

        // Create an ArrayAdapter for the ListView so we can display the items.
        mArrayAdapter = new SupplyListAdapter(this, mSupplyList);

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
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        // Get the supply
        KitchenSupply supply = mSupplyList.get(position);
        String supplyName = supply.getName();
        String supplyID = supply.getObjectId();
        String supplyKitchen = supply.getKitchen();
        String supplyNotes = supply.getNotes();

        // Create an Intent to take you over to a new DetailActivity
        Intent detailIntent = new Intent(this, DetailActivity.class);

        // Save the relevant details about our Supply so they can be used in the new activity.
        detailIntent.putExtra("supplyName", supplyName);
        detailIntent.putExtra("supplyID", supplyID);
        detailIntent.putExtra("supplyKitchen", supplyKitchen);
        detailIntent.putExtra("supplyNotes", supplyNotes);

        // Start the next Activity using your prepared Intent
        startActivity(detailIntent);

    }

    public void addKitchenSupply(View v) {
        // Create the intent to add a new supply, and go to that activity.
        Intent addSupplyIntent = new Intent(this, AddSupplyActivity.class);
        startActivity(addSupplyIntent);
    }

    public void refreshList(View v) {
        updateSupplyList();
    }

    public void updateSupplyList() {

        mSupplyList.clear();

        // Get the equipment
        ParseQuery<Equipment> queryE = ParseQuery.getQuery(Equipment.class);
        queryE.whereEqualTo("kitchen", kitchenName);
        queryE.findInBackground(new FindCallback<Equipment>() {

            @Override
            public void done(List<Equipment> supplies, ParseException error) {
                if(supplies != null){
                    mSupplyList.addAll(supplies);
                    mArrayAdapter.notifyDataSetChanged();
                }
            }
        });

        // Get the ingredients
        ParseQuery<Ingredient> queryI = ParseQuery.getQuery(Ingredient.class);
        queryI.whereEqualTo("kitchen", kitchenName);
        queryI.findInBackground(new FindCallback<Ingredient>() {

            @Override
            public void done(List<Ingredient> supplies, ParseException error) {
                if(supplies != null){
                    mSupplyList.addAll(supplies);
                    mArrayAdapter.notifyDataSetChanged();
                }
            }
        });
//        ParseQuery<KitchenSupply> query = ParseQuery.getQuery(KitchenSupply.class);
//        query.whereEqualTo("kitchen", kitchenName);
//        query.findInBackground(new FindCallback<KitchenSupply>() {
//
//            @Override
//            public void done(List<KitchenSupply> supplies, ParseException error) {
//                if(supplies != null){
//                    mSupplyList.clear();
//                    mSupplyList.addAll(supplies);
//                    mArrayAdapter.notifyDataSetChanged();
//                }
//            }
//        });
    }



    public class SupplyListAdapter extends ArrayAdapter<KitchenSupply> {

        Context mContext;
        ArrayList<KitchenSupply> data = null;


        public SupplyListAdapter(Context ctx, ArrayList<KitchenSupply> data) {
            super(ctx, android.R.layout.simple_list_item_1, data);
            this.mContext = ctx;
            this.data = data;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.row_supplylist, parent, false);

            KitchenSupply supply = data.get(position);

            TextView supplyNameTextView = (TextView) convertView.findViewById(R.id.supply_name);
            String nameString = supply.getName();
            supplyNameTextView.setText(nameString);

            TextView supplyKitchenTextView = (TextView) convertView.findViewById(R.id.supply_kitchen);
            String kitchenString = supply.getKitchen();
            supplyKitchenTextView.setText(kitchenString);


//            //TODO: Check if necessary
//            SupplyHolder holder = new SupplyHolder();
//            holder.supply = data.get(position);
//            holder.name = supplyNameTextView;
//            holder.kitchen = supplyKitchenTextView;
//
//            setupItem(holder);
//            convertView.setTag(holder);


            return convertView;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public KitchenSupply getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        //gets the data currently in the list
        public ArrayList<KitchenSupply> getData(){
            return data;
        }

//        private void setupItem(SupplyHolder holder) {
//            holder.name.setText(holder.supply.getName());
//            holder.kitchen.setText(holder.supply.getKitchen());
//        }
//
//        public class SupplyHolder {
//            KitchenSupply supply;
//            TextView name;
//            TextView kitchen;
//        }

    }


}
