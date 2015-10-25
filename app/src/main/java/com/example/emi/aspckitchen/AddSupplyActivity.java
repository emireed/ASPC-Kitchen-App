package com.example.emi.aspckitchen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by emi on 10/14/15.
 */
public class AddSupplyActivity extends Activity {

    EditText nameEditText, kitchenEditText, notesEditText;
    Button createButton, cancelButton;
    String supplyName, supplyID, kitchenName, supplyNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout to use
        setContentView(R.layout.activity_add_supply);

        // Create the name text box.
        nameEditText = (EditText) findViewById(R.id.edit_supply_name);

        // Create the kitchen text box.
        kitchenEditText = (EditText) findViewById(R.id.edit_kitchen);

        // Create the notes text box.
        notesEditText = (EditText) findViewById(R.id.edit_notes);

        // Create the create button.
        createButton = (Button) findViewById(R.id.create_button);

        // Create the cancel button.
        cancelButton = (Button) findViewById(R.id.cancel_button);

    }


    public void cancelSupply(View v) {

        // We don't want to save any data, just return to main screen
        finish();
    }

    public void createSupply(View v) {

        boolean goodToSave = true;

        String supplyName = nameEditText.getText().toString();
        if (supplyName.matches("")){
            Toast.makeText(this, "You must give this supply a name", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }

        String supplyKitchen = kitchenEditText.getText().toString();
        if (supplyKitchen.matches("")){
            Toast.makeText(this, "You must give this supply a kitchen", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }

        String supplyNotes = notesEditText.getText().toString();

        if (goodToSave) {
            KitchenSupply newSupply = new KitchenSupply(supplyName, supplyKitchen, supplyNotes);
            finish();
        }

    }

}
