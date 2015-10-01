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
        Parse.initialize(this, "z4iOYPd7anE4HWUYUnICtEcsr2sAcZoOGu7xuqby", "fbgMzfTb3TWb5T8v7Juf1cKtBdpP2zgl8rxbz8zG");
        KitchenSupply spatula = new KitchenSupply();
        spatula.setName("SpatulaTest");
        spatula.saveInBackground();

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
    }
}
