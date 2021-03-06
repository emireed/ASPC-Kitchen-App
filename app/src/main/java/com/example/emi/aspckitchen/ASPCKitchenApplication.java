package com.example.emi.aspckitchen;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Keighley on 9/30/2015.
 */
public class ASPCKitchenApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Kitchen.class);
        ParseObject.registerSubclass(KitchenSupply.class);
        ParseObject.registerSubclass(Equipment.class);
        ParseObject.registerSubclass(Ingredient.class);
        Parse.initialize(this, "QaH3ndIMBf5bv9apU0AdGxfwNHeyRPrgRUtAQNc0", "2V9GhXq7l4tBb7rXAZAxpEm7hZOZv8qYPmISQESy");

    }
}
