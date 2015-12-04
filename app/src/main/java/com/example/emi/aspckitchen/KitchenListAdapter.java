package com.example.emi.aspckitchen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Keighley on 11/1/2015.
 */
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

        // Display the name of the kitchen.
        TextView kitchenNameTextView = (TextView) convertView.findViewById(R.id.kitchen_name);
        String nameString = kitchen.getName();
        kitchenNameTextView.setText(nameString);

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

    public ArrayList<Kitchen> getData(){
        return data;
    }

}