package com.example.emi.aspckitchen;

import com.parse.ParseClassName;

/**
 * Created by Keighley on 10/25/2015.
 */

@ParseClassName("Equipment")
public class Equipment extends KitchenSupply {
    // Equipment has one additional data member, stored on Parse:
    // boolean inUse;

    public Equipment() {};

    public Equipment(String name, String kitchen, String notes, boolean inUse) {
        this.setName(name);
        this.setKitchen(kitchen);
        this.setNotes(notes);
        this.setInUse(inUse);
        saveInBackground();
    }

    public void setInUse(boolean inUse) {
        put("inUse", inUse);
    }
}
