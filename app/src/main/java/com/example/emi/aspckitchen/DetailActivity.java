package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

/**
 * Created by Keighley on 10/5/2015.
 */


public class DetailActivity extends Activity implements View.OnClickListener {

    TextView detailTextView, supplyNameTextView, supplyKitchenTextView, supplyNotesTextView;
    Button deleteButton;
    String supplyName, supplyID, kitchenName, supplyNotes, supplyType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout to use
        setContentView(R.layout.activity_detail);

        // Get the details about the supply, so we can display them.
        supplyName = this.getIntent().getExtras().getString("supplyName");
        supplyID = this.getIntent().getExtras().getString("supplyID");
        kitchenName = this.getIntent().getExtras().getString("supplyKitchen");
        supplyNotes = this.getIntent().getExtras().getString("supplyNotes");
        supplyType = this.getIntent().getExtras().getString("supplyType");


        // Display the title of the page as the supply name.
        getActionBar().setTitle("Supply Details: " + supplyName);

        // Create the delete button.
        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(this);

        // Set the textview to display the supply name.
        detailTextView = (TextView) findViewById(R.id.detail_textview);

        supplyNameTextView = (TextView) findViewById(R.id.supply_name);
        supplyNameTextView.setText(supplyName);

        supplyKitchenTextView = (TextView) findViewById(R.id.supply_kitchen);
        supplyKitchenTextView.setText(kitchenName);

        supplyNotesTextView = (TextView) findViewById(R.id.supply_notes);
        supplyNotesTextView.setText(supplyNotes);

        // Create an intent so that we can return to the previous page when we're done.
        Intent kitchenIntent = new Intent(this, KitchenActivity.class);
        // Save the relevant details about our Kitchen so they can be used in the new activity.
        kitchenIntent.putExtra("supplyKitchen", kitchenName);



    }

    @Override
    public void onPause() {
        super.onPause();
        // Create an intent so that we can return to the previous page when we're done.
        Intent kitchenIntent = new Intent(this, KitchenActivity.class);
        // Save the relevant details about our Kitchen so they can be used in the new activity.
        kitchenIntent.putExtra("supplyKitchen", kitchenName);
        // Start the next Activity using your prepared Intent
        startActivity(kitchenIntent);
    }

    @Override
    public void onClick(View v) {
        // Find the object in Parse using its ID, then delete it.

        switch(supplyType) {
            case "ingredient":
                ParseQuery<Ingredient> queryI = ParseQuery.getQuery(Ingredient.class);
                queryI.getInBackground(supplyID, new GetCallback<Ingredient>() {
                    public void done(Ingredient supply, ParseException e) {
                        if (e == null) {
                            // Delete the object.
                            supply.deleteInBackground();
                        } else {
                            // Something went wrong...
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });
                break;
            case "equipment":
                ParseQuery<Equipment> queryE = ParseQuery.getQuery(Equipment.class);
                queryE.getInBackground(supplyID, new GetCallback<Equipment>() {
                    public void done(Equipment supply, ParseException e) {
                        if (e == null) {
                            // Delete the object.
                            supply.deleteInBackground();
                        } else {
                            // Something went wrong...
                            Log.d("score", "Error: " + e.getMessage());
                        }
                    }
                });
        }



        // Once deleted, return to the previous page.
        finish();
    }
}
