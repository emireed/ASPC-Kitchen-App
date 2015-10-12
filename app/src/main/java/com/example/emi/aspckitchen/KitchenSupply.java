package com.example.emi.aspckitchen;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Keighley on 10/1/2015.
 */

@ParseClassName("KitchenSupply")
public class KitchenSupply extends ParseObject {
    String name;

    public KitchenSupply(){
        saveInBackground();
    }
    public KitchenSupply(String name){
        this.name = name;
        saveInBackground();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return this.name;
    }
}
