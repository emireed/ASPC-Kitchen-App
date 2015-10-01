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

        ParseObject.registerSubclass(KitchenSupply.class);
        Parse.initialize(this, "QaH3ndIMBf5bv9apU0AdGxfwNHeyRPrgRUtAQNc0", "2V9GhXq7l4tBb7rXAZAxpEm7hZOZv8qYPmISQESy");
        KitchenSupply spatula = new KitchenSupply();
        spatula.setName("SpatulaTest");
        spatula.saveInBackground();

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
