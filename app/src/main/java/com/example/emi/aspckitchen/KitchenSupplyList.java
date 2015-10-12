package com.example.emi.aspckitchen;

import java.util.ArrayList;

/**
 * Created by Keighley on 10/11/2015.
 */

//@ParseClassName("KitchenSupplyList")
public class KitchenSupplyList{
    ArrayList<KitchenSupply> mSupplyList;

    public KitchenSupplyList() {
    }

    public void setArrayList(ArrayList<KitchenSupply> newSupplyList) {
        mSupplyList = newSupplyList;
    }

    public ArrayList<KitchenSupply> getArrayList() {
        return mSupplyList;
    }
}
