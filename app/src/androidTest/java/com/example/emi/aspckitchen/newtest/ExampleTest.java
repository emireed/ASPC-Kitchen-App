package com.example.emi.aspckitchen.newtest;

import android.test.InstrumentationTestCase;

import com.example.emi.aspckitchen.Equipment;
import com.example.emi.aspckitchen.Ingredient;
import com.example.emi.aspckitchen.ParseOperations;

/**
 * Created by Keighley on 12/6/2015.
 */
public class ExampleTest extends InstrumentationTestCase {

    public void testIngredientAddDelete() throws Exception {

        String name, campus, kitchen, notes, amount;

        name = "Spatula";
        campus = "Harvey Mudd";
        kitchen = "Drinkward";
        notes = "";
        amount = "";

        Ingredient addedIngredient = new Ingredient(name, kitchen, campus, notes, amount);

        String id = addedIngredient.getObjectId();

        ParseOperations.deleteSupply(id, "ingredient");
    }


    public void testEquipmentAddDelete() throws Exception {

        String name, campus, kitchen, notes, amount;

        name = "Pumpkin";
        campus = "Harvey Mudd";
        kitchen = "Drinkward";
        notes = "";
        amount = "";

        Equipment addedEquipment = new Equipment(name, kitchen, campus, notes, false);

        String id = addedEquipment.getObjectId();

        ParseOperations.deleteSupply(id, "equipment");
    }



}
