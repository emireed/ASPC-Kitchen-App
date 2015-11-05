package com.example.emi.aspckitchen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.emi.aspckitchen.ParseOperations.saveInDatabase;

/**
 * Created by Keighley on 11/1/2015.
 */

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

        final KitchenSupply supply = data.get(position);

        // Displays the name of the supply.
        TextView supplyNameTextView = (TextView) convertView.findViewById(R.id.supply_name);
        String nameString = supply.getName();
        supplyNameTextView.setText(nameString);

        // Displays the kitchen of the supply.
        final Button supplyStatusButton = (Button) convertView.findViewById(R.id.supply_status_button);
        final Boolean supplyIsInUse = supply.isInUse();
        if (supplyIsInUse) {
            supplyStatusButton.setText("Return");
        } else {
            supplyStatusButton.setText("Use");
        }

        supplyStatusButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (supplyIsInUse) {
                    supply.setInUse(false);
                    supplyStatusButton.setText("Use");
                } else {
                    supply.setInUse(true);
                    supplyStatusButton.setText("Return");
                }
                saveInDatabase(supply);
            }
        });


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


}



