package com.example.emi.aspckitchen;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by Keighley on 10/25/2015.
 */

@ParseClassName("Kitchen")
public class Kitchen extends ParseObject {
    // The data members of this class are stored on Parse:
    // String name;

    public Kitchen() {
        saveInBackground();
    }

    public Kitchen(String name) {
        this.setName(name);
        saveInBackground();
    }

    public void setName(String name) {
        put("name", name);
    }

    public String getName() {
        return getString("name");
    }

    public String toString() {
        return getName();
    }
}
