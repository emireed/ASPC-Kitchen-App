package com.example.emi.aspckitchen;

import java.util.ArrayList;

/**
 * Created by Keighley on 10/11/2015.
 */

public class KitchenSupplyList {
    ArrayList<KitchenSupply> mSupplyList;


    public KitchenSupplyList() {
        mSupplyList = new ArrayList<KitchenSupply>();
    }

    public void setArrayList(ArrayList<KitchenSupply> newSupplyList) {
        mSupplyList = newSupplyList;
    }

    public ArrayList<KitchenSupply> getArrayList() {
        return mSupplyList;
    }

}
