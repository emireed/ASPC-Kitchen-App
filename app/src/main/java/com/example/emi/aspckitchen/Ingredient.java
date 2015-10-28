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

    public Ingredient(String name, String kitchen, String notes, String amount) {
        this.setName(name);
        this.setKitchen(kitchen);
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
}
