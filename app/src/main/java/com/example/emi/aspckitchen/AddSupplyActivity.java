package com.example.emi.aspckitchen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * Created by emi on 10/14/15.
 */
public class AddSupplyActivity extends Activity {

    EditText nameEditText, kitchenEditText, notesEditText, amountEditText;
    Button createButton, cancelButton;
    String supplyName, supplyID, kitchenName, supplyNotes, type, amount;
    String INGREDIENT = "ing";
    String EQUIPMENT = "equ";

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

        // Create the amount text box. By default, it should be invisible.
        amountEditText = (EditText) findViewById(R.id.edit_amount);
        amountEditText.setVisibility(View.INVISIBLE);

        // Create the create button.
        createButton = (Button) findViewById(R.id.create_button);

        // Create the cancel button.
        cancelButton = (Button) findViewById(R.id.cancel_button);

    }

    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.select_equipment:
                if (checked)
                    // Set the type to equipment.
                    type = EQUIPMENT;
                    // Hide the amount edit text.
                    amountEditText.setVisibility(View.INVISIBLE);
                    break;
            case R.id.select_ingredient:
                if (checked)
                    // Set the type to ingredient.
                    type = INGREDIENT;
                    // Make the amountEditText visible.
                    amountEditText.setVisibility(View.VISIBLE);
                    break;
        }
    }


    public void cancelSupply(View v) {

        // We don't want to save any data, just return to main screen
        finish();
    }

    public void createSupply(View v) {

        boolean goodToSave = true;

        supplyName = nameEditText.getText().toString();
        if (supplyName.matches("")){
            Toast.makeText(this, "You must give this supply a name", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }

        kitchenName = kitchenEditText.getText().toString();
        if (kitchenName.matches("")){
            Toast.makeText(this, "You must give this supply a kitchen", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }

        supplyNotes = notesEditText.getText().toString();

        if (type.matches("")) {
            Toast.makeText(this, "You must select whether the supply is equipment or an ingredient.", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }

        if (type.matches(INGREDIENT) && goodToSave) {
            amount = amountEditText.getText().toString();
            Ingredient newSupply = new Ingredient(supplyName, kitchenName, supplyNotes, amount);
            finish();
        }

        if (type.matches(EQUIPMENT) && goodToSave) {
            Equipment newSupply = new Equipment(supplyName, kitchenName, supplyNotes, false);
            finish();
        }

//        if (goodToSave) {
//            KitchenSupply newSupply = new KitchenSupply(supplyName, kitchenName, supplyNotes);
//            finish();
//        }

    }

}
