package com.example.emi.aspckitchen.newtest;

import android.test.InstrumentationTestCase;

import com.example.emi.aspckitchen.Equipment;
import com.example.emi.aspckitchen.Ingredient;
import com.example.emi.aspckitchen.ParseOperations;

/**
 * Created by Keighley on 12/6/2015.
 */
public class FunctionTesting extends InstrumentationTestCase {

    public void testIngredientAddDelete() throws Exception {

        String name, campus, kitchen, notes, amount;

        name = "Pumpkin";
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

        name = "Spatula";
        campus = "Harvey Mudd";
        kitchen = "Drinkward";
        notes = "";
        amount = "";

        Equipment addedEquipment = new Equipment(name, kitchen, campus, notes, false);

        String id = addedEquipment.getObjectId();

        ParseOperations.deleteSupply(id, "equipment");
    }

    public void testIngredientGetters() throws Exception {
        String name, campus, kitchen, notes, amount;

        name = "Cheese";
        campus = "Harvey Mudd";
        kitchen = "Drinkward";
        notes = "slightly melted";
        amount = "2 pounds";

        Ingredient addedIngredient = new Ingredient(name, kitchen, campus, notes, amount);

        String rname, rcampus, rkitchen, rnotes, ramount;

        rname = addedIngredient.getName();
        rcampus = addedIngredient.getCampus();
        rkitchen = addedIngredient.getKitchen();
        rnotes = addedIngredient.getNotes();
        ramount = addedIngredient.getAmount();

        assertEquals(name, rname);
        assertEquals(kitchen, rkitchen);
        assertEquals(campus, rcampus);
        assertEquals(notes, rnotes);
        assertEquals(amount, ramount);


        String id = addedIngredient.getObjectId();
        ParseOperations.deleteSupply(id, "ingredient");
    }


    public void testEquipmentGetters() throws Exception {
        String name, campus, kitchen, notes;
        boolean inUse;

        name = "Spatula";
        campus = "Harvey Mudd";
        kitchen = "Drinkward";
        notes = "slightly melted";
        inUse = false;

        Equipment addedEquipment = new Equipment(name, kitchen, campus, notes, inUse);

        String rname, rcampus, rkitchen, rnotes;
        boolean rinUse;

        rname = addedEquipment.getName();
        rcampus = addedEquipment.getCampus();
        rkitchen = addedEquipment.getKitchen();
        rnotes = addedEquipment.getNotes();
        rinUse = addedEquipment.isInUse();

        assertEquals(name, rname);
        assertEquals(kitchen, rkitchen);
        assertEquals(campus, rcampus);
        assertEquals(notes, rnotes);
        assertEquals(inUse, rinUse);


        String id = addedEquipment.getObjectId();
        ParseOperations.deleteSupply(id, "equipment");
    }



}
