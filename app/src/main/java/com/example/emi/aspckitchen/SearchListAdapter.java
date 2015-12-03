package com.example.emi.aspckitchen;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.emi.aspckitchen.ParseOperations.saveInDatabase;

/**
 * Created by CChen on 12/2/2015.
 */
public class SearchListAdapter extends ArrayAdapter<KitchenSupply> {
    Context mContext;
    ArrayList<KitchenSupply> data = null;


    public SearchListAdapter(Context ctx, ArrayList<KitchenSupply> data) {
        super(ctx, android.R.layout.simple_list_item_1, data);
        this.mContext = ctx;
        this.data = data;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.row_searchlist, parent, false);

        final KitchenSupply supply = data.get(position);

        // Displays the name of the supply.
        TextView supplyNameTextView = (TextView) convertView.findViewById(R.id.supply_name);
        String nameString = supply.getName();
        supplyNameTextView.setText(nameString);

        // Displays the name of the kitchen
        TextView supplyKitchenTextView = (TextView) convertView.findViewById(R.id.kitchen_name);
        String kitchenString = supply.getKitchen();
        supplyNameTextView.setText(kitchenString);

        // Displays the kitchen of the supply.
        Button supplyStatusButton = (Button) convertView.findViewById(R.id.supply_status_button);
        final Boolean supplyIsInUse = supply.isInUse();
        if (supplyIsInUse) {
            supplyStatusButton.setTag("True");
            supplyStatusButton.setText("Return");
            supplyStatusButton.setBackgroundColor(Color.parseColor("#C62828"));
        } else {
            supplyStatusButton.setTag("False");
            supplyStatusButton.setText("Use");
            supplyStatusButton.setBackgroundColor(Color.parseColor("#EF5350"));
        }

        supplyStatusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onItemClick(v, position);
            }
        });


        return convertView;
    }

    public void onItemClick(View view, int position) {
        KitchenSupply supply = data.get(position);
        Button supplyStatusButton = (Button) view.findViewById(R.id.supply_status_button);
        Boolean isInUse = supply.isInUse();
        if (isInUse) {
            supply.setInUse(false);
            supplyStatusButton.setText("Use");
            supplyStatusButton.setBackgroundColor(Color.parseColor("#EF5350"));
        } else {
            supply.setInUse(true);
            supplyStatusButton.setText("Return");
            supplyStatusButton.setBackgroundColor(Color.parseColor("#C62828"));
        }
        saveInDatabase(supply);
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


}
