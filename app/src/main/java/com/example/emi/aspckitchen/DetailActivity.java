package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Keighley on 10/5/2015.
 */


public class DetailActivity extends Activity implements View.OnClickListener {

    TextView detailTextView, supplyNameTextView, supplyKitchenTextView, supplyNotesTextView,
            supplyStatusTextView;
    Button deleteButton;
    String supplyName, campusName, supplyID, kitchenName, supplyNotes, supplyType;
    Intent kitchenIntent;
    Boolean supplyIsInUse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout to use
        setContentView(R.layout.activity_detail);

        getSupplyDetails();
        createButtons();
        createTextViews();

        // Display the title of the page as the supply name.
        getActionBar().setTitle("Supply Details: " + supplyName);
    }

    @Override
    public void onPause() {
        super.onPause();
        // Save the kitchen name so we can return to the correct kitchen page.
        saveKitchenNameInIntent();
        startActivity(kitchenIntent);
    }

    @Override
    public void onClick(View v) {
        // Find the object in Parse using its ID, then delete it.
        ParseOperations.deleteSupply(supplyID, supplyType);
        // Once deleted, return to the previous page.
        finish();
    }

    /* Create an intent so that we can return to the previous page when we're done. */
    public void saveKitchenNameInIntent() {
        kitchenIntent = new Intent(this, KitchenActivity.class);
        // Save the relevant details about our Kitchen.
        kitchenIntent.putExtra("supplyKitchen", kitchenName);
        kitchenIntent.putExtra("campusName", campusName);
    }

    /* Access the buttons for this page */
    public void createButtons() {
        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(this);
    }

    /* Access and set up the textviews for this page. */
    public void createTextViews() {
        detailTextView = (TextView) findViewById(R.id.detail_textview);

        supplyNameTextView = (TextView) findViewById(R.id.supply_name);
        supplyNameTextView.setText(supplyName);

        supplyKitchenTextView = (TextView) findViewById(R.id.supply_kitchen);
        supplyKitchenTextView.setText(kitchenName);

        supplyNotesTextView = (TextView) findViewById(R.id.supply_notes);
        supplyNotesTextView.setText(supplyNotes);

        // For the status, we need to check it's current status before setting text
        supplyStatusTextView = (TextView) findViewById(R.id.supply_status);
        if (supplyIsInUse) {
            supplyStatusTextView.setText("Currently In Use");
            supplyStatusTextView.setTextColor(Color.parseColor("#F44336"));
        }
        else {
            supplyStatusTextView.setText("Not In Use");
        }
    }

    /* Get the details about the supply, so we can display them. */
    public void getSupplyDetails() {
        supplyName = this.getIntent().getExtras().getString("supplyName");
        supplyID = this.getIntent().getExtras().getString("supplyID");
        kitchenName = this.getIntent().getExtras().getString("supplyKitchen");
        campusName = this.getIntent().getExtras().getString("campusName");
        supplyNotes = this.getIntent().getExtras().getString("supplyNotes");
        supplyType = this.getIntent().getExtras().getString("supplyType");
        supplyIsInUse = this.getIntent().getExtras().getBoolean("supplyStatus");
    }
}
