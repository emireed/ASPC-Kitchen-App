package com.example.emi.aspckitchen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Keighley on 10/25/2015.
 */
public class AddKitchenActivity extends Activity {

    EditText nameEditText, campusEditText, accessibilityEditText;
    Button createButton, cancelButton;
    String kitchenName, kitchenCampus, kitchenAccessibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout to use
        setContentView(R.layout.activity_add_kitchen);

        // Create the name text box.
        nameEditText = (EditText) findViewById(R.id.edit_kitchen_name);

        // Create the campus text box.
        campusEditText = (EditText) findViewById(R.id.edit_kitchen_campus);

        // Create the accessibility text box.
        accessibilityEditText = (EditText) findViewById(R.id.edit_kitchen_accessibility);

        // Create the create and cancel buttons.
        createButton = (Button) findViewById(R.id.create_button);
        cancelButton = (Button) findViewById(R.id.cancel_button);
    }


    public void cancelSupply(View v) {
        // We don't want to save any data, just return to main screen
        finish();
    }

    public void createSupply(View v) {

        boolean goodToSave = true;

        kitchenName = nameEditText.getText().toString();
        if (kitchenName.matches("")){
            Toast.makeText(this, "You must give this kitchen a name", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }

        kitchenCampus = campusEditText.getText().toString();
        if (kitchenCampus.matches("")) {
            Toast.makeText(this, "You must give this kitchen a campus", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }

        kitchenAccessibility = accessibilityEditText.getText().toString();
        if (kitchenAccessibility.matches("")) {
            Toast.makeText(this, "You must mark this kitchen's accessibility", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }

        if (goodToSave) {
            Kitchen newKitchen = new Kitchen(kitchenName);
            finish();
        }
    }
}

