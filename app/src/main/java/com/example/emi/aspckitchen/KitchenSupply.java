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
    // String details;


    public KitchenSupply(){
        saveInBackground();
    }

    public KitchenSupply(String name){
        this.setName(name);
        saveInBackground();
    }

    public KitchenSupply(String name, String kitchen) {
        this.setName(name);
        this.setKitchen(kitchen);
        saveInBackground();
    }

    public KitchenSupply(String name, String kitchen, String details) {
        this.setName(name);
        this.setKitchen(kitchen);
        this.setDetails(details);
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

    public void setDetails(String details) {
        put("details", details);
    }

    public String getDetails() { return getString("details"); }

    public String toString() {
        return getName();
    }
}
