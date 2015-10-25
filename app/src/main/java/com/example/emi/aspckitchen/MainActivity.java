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

public class MainActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    Button addKitchenButton, addSupplyButton, refreshButton;
    ListView mainListView;
    ArrayAdapter mArrayAdapter;
    ArrayList<Kitchen> mKitchenList = new ArrayList<Kitchen>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Choose the correct XML file to use.
        setContentView(R.layout.activity_main);

        // Define the buttons defined in the xml file.
        addKitchenButton = (Button) findViewById(R.id.add_kitchen_button);
        addSupplyButton = (Button) findViewById(R.id.main_button);
        refreshButton = (Button) findViewById(R.id.refresh_button);

        // Access the ListView
        mainListView = (ListView) findViewById(R.id.main_listview);

        // Populate the supply list with the list of supplies currently on Parse.
        updateKitchenList();

        // Create an ArrayAdapter for the ListView so we can display the items.
        mArrayAdapter = new KitchenListAdapter(this, mKitchenList);

        // Set the ListView to use the ArrayAdapter
        mainListView.setAdapter(mArrayAdapter);

        // Set this activity to react to list items being pressed
        mainListView.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Every time this activity is resumed, update the supply list again.
        updateKitchenList();
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

        // Get the kitchen
        Kitchen kitchen = mKitchenList.get(position);
        String kitchenName = kitchen.getName();

        // Create an Intent to go to the Kitchen activity
        Intent kitchenIntent = new Intent(this, KitchenActivity.class);

        // Save the relevant details about our Kitchen so they can be used in the new activity.
        kitchenIntent.putExtra("kitchenName", kitchenName);

        // Start the next Activity using your prepared Intent
        startActivity(kitchenIntent);

    }

    public void addKitchen(View v) {
        Intent addKitchenIntent = new Intent(this, AddKitchenActivity.class);
        startActivity(addKitchenIntent);
    }

    public void addKitchenSupply(View v) {
        // Create the intent to add a new supply, and go to that activity.
        Intent addSupplyIntent = new Intent(this, AddSupplyActivity.class);
        startActivity(addSupplyIntent);
    }

    public void refreshList(View v) {
        updateKitchenList();
    }

    public void updateKitchenList() {
        ParseQuery<Kitchen> query = ParseQuery.getQuery(Kitchen.class);
        query.findInBackground(new FindCallback<Kitchen>() {

            @Override
            public void done(List<Kitchen> kitchens, ParseException error) {
                if(kitchens != null){
                    mKitchenList.clear();
                    mKitchenList.addAll(kitchens);
                    mArrayAdapter.notifyDataSetChanged();
                }
            }
        });
    }



    public class KitchenListAdapter extends ArrayAdapter<Kitchen> {

        Context mContext;
        ArrayList<Kitchen> data = null;


        public KitchenListAdapter(Context ctx, ArrayList<Kitchen> data) {
            super(ctx, android.R.layout.simple_list_item_1, data);
            this.mContext = ctx;
            this.data = data;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.kitchen_list_item, parent, false);

            Kitchen kitchen = data.get(position);

            TextView supplyNameTextView = (TextView) convertView.findViewById(R.id.supply_name);
            String nameString = kitchen.getName();
            supplyNameTextView.setText(nameString);


            //TODO: Check if necessary
            KitchenHolder holder = new KitchenHolder();
            holder.kitchen = data.get(position);
            holder.name = supplyNameTextView;

            setupItem(holder);
            convertView.setTag(holder);


            return convertView;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Kitchen getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        //gets the data currently in the list
        public ArrayList<Kitchen> getData(){
            return data;
        }

        private void setupItem(KitchenHolder holder) {
            holder.name.setText(holder.kitchen.getName());
        }

        public class KitchenHolder {
            Kitchen kitchen;
            TextView name;
        }

    }

}