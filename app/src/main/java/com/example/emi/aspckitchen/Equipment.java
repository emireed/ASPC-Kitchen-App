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

    public Equipment(String name, String kitchen, String campus, String notes, boolean inUse) {
        this.setName(name);
        this.setKitchen(kitchen);
        this.setCampus(campus);
        this.setNotes(notes);
        this.setInUse(inUse);
        saveInBackground();
    }

    public String getType() {
        return "equipment";
    }

    public void setInUse(boolean inUse) {
        put("inUse", inUse);
    }
}
