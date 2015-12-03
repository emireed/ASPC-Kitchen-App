package com.example.emi.aspckitchen;

import android.util.Log;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Keighley on 11/1/2015.
 */
public class ParseOperations {

    public static void searchByName(String itemName, final ArrayList<KitchenSupply> mEquipmentList, final ArrayList<KitchenSupply> mIngredientList,
                                    final ArrayAdapter mArrayAdapterEquipment, final ArrayAdapter mArrayAdapterIngredients){
        //Create query to for searching through equipment and ingredients
        ParseQuery<Equipment> queryE = ParseQuery.getQuery(Equipment.class);
        ParseQuery<Ingredient> queryI = ParseQuery.getQuery(Ingredient.class);

        queryI.whereEqualTo("name", itemName);
        queryE.whereEqualTo("name", itemName);

        queryE.findInBackground(new FindCallback<Equipment>() {

            @Override
            public void done(List<Equipment> supplies, ParseException error) {
                if(supplies != null){
                    mEquipmentList.clear();
                    mEquipmentList.addAll(supplies);
                    mArrayAdapterEquipment.notifyDataSetChanged();
                }
            }
        });
        queryI.findInBackground(new FindCallback<Ingredient>() {
            @Override
            public void done(List<Ingredient> supplies, ParseException error) {
                if(supplies != null){
                    mIngredientList.clear();
                    mIngredientList.addAll(supplies);
                    mArrayAdapterIngredients.notifyDataSetChanged();
                }
            }
        });
    }

    public static void populateKitchenList(final ArrayList<Kitchen> mKitchenList, final ArrayAdapter mArrayAdapter) {
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

    public static void populateKitchenByCampusList(final ArrayList<Kitchen> mKitchenList, final ArrayAdapter mArrayAdapter, String campusName){
        ParseQuery<Kitchen> query = ParseQuery.getQuery(Kitchen.class);
        query.whereEqualTo("campus", campusName);
        query.findInBackground(new FindCallback<Kitchen>() {

            @Override
            public void done(List<Kitchen> kitchens, ParseException error) {
                if (kitchens != null) {
                    mKitchenList.clear();
                    mKitchenList.addAll(kitchens);
                    mArrayAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    public static void populateSupplyList (String kitchenName, final ArrayList<KitchenSupply> mEquipmentList, final ArrayList<KitchenSupply> mIngredientList,
                                    final ArrayAdapter mArrayAdapterEquipment, final ArrayAdapter mArrayAdapterIngredients) {
        // Get the equipment
        ParseQuery<Equipment> queryE = ParseQuery.getQuery(Equipment.class);
        queryE.whereEqualTo("kitchen", kitchenName);
        queryE.findInBackground(new FindCallback<Equipment>() {

            @Override
            public void done(List<Equipment> supplies, ParseException error) {
                if(supplies != null){
                    mEquipmentList.clear();
                    mEquipmentList.addAll(supplies);
                    mArrayAdapterEquipment.notifyDataSetChanged();
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
                    mIngredientList.clear();
                    mIngredientList.addAll(supplies);
                    mArrayAdapterIngredients.notifyDataSetChanged();
                }
            }
        });
    }

    public static void deleteSupply(String supplyID, String supplyType) {
        switch(supplyType) {

            case "ingredient":
                ParseQuery<Ingredient> queryI = ParseQuery.getQuery(Ingredient.class);
                queryI.getInBackground(supplyID, new GetCallback<Ingredient>() {
                    public void done(Ingredient supply, ParseException e) {
                        if (e == null) {
                            // Delete the object.
                            supply.deleteInBackground();
                        } else {
                            // Something went wrong...
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });
                break;

            case "equipment":
                ParseQuery<Equipment> queryE = ParseQuery.getQuery(Equipment.class);
                queryE.getInBackground(supplyID, new GetCallback<Equipment>() {
                    public void done(Equipment supply, ParseException e) {
                        if (e == null) {
                            // Delete the object.
                            supply.deleteInBackground();
                        } else {
                            // Something went wrong...
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });
        }
    }

    public static void saveInDatabase(KitchenSupply supply) {
        supply.saveInBackground();
    }
}
