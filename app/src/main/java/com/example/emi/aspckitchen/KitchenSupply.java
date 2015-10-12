package com.example.emi.aspckitchen;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Keighley on 10/1/2015.
 */

@ParseClassName("KitchenSupply")
public class KitchenSupply extends ParseObject {
    //String name;

    public KitchenSupply(){
        saveInBackground();
    }

    public KitchenSupply(String name){
        this.setName(name);
        saveInBackground();
    }

    public void setName(String name) {
        //this.name = name;
        put("name", name);
    }

    public String getName() {
        return getString("name");
    }

    public String toString() {
        return getName();
    }
}
