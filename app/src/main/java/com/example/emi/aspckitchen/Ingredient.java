package com.example.emi.aspckitchen;

import com.parse.ParseClassName;

/**
 * Created by Keighley on 10/25/2015.
 */

@ParseClassName("Ingredient")
public class Ingredient extends KitchenSupply {
    // Ingredient has one additional data member, stored on Parse:
    // String amount;

    public Ingredient() {};

    public Ingredient(String name, String kitchen, String campus, String notes, String amount) {
        this.setName(name);
        this.setKitchen(kitchen);
        this.setCampus(campus);
        this.setNotes(notes);
        this.setAmount(amount);
        saveInBackground();
    }

    public String getType() {
        return "ingredient";
    }

    public void setAmount(String amount) {
        put("amount", amount);
    }
    public String getAmount() {return getString("amount");}
}
