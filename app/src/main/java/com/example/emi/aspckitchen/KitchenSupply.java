package com.example.emi.aspckitchen;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Keighley on 10/1/2015.
 */

@ParseClassName("KitchenSupply")
public class KitchenSupply extends ParseObject {
    // The data members of this class are stored on Parse:
    // String name;
    // String kitchen;
    // String campus;
    // String notes;


    public KitchenSupply(){
        saveInBackground();
    }

    public KitchenSupply(String name){
        this.setName(name);
        this.setInUse(false);
        saveInBackground();
    }

    public KitchenSupply(String name, String kitchen) {
        this.setName(name);
        this.setKitchen(kitchen);
        this.setInUse(false);
        saveInBackground();
    }

    public KitchenSupply(String name, String kitchen, String campus, String notes) {
        this.setName(name);
        this.setKitchen(kitchen);
        this.setCampus(campus);
        this.setNotes(notes);
        this.setInUse(false);
        saveInBackground();
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getName() {
        return getString("name");
    }

    public void setKitchen(String kitchen) {
        put("kitchen", kitchen);
    }

    public String getKitchen() {
        return getString("kitchen");
    }

    public void setCampus(String campus) {
        put("campus", campus);
    }

    public String getCampus() {
        return getString("campus");
    }

    public void setNotes(String notes) {
        put("notes", notes);
    }

    public String getNotes() { return getString("notes"); }

    public void setInUse(boolean isInUse) { put("inUse", isInUse); }

    public boolean isInUse() { return getBoolean("inUse"); }

    public String toString() {
        return getName();
    }

}
