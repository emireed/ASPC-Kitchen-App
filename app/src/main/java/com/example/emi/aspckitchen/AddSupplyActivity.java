package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Intent;
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
    String supplyName, supplyID, supplyKitchen, supplyNotes, type, amount, currentKitchen;
    String INGREDIENT = "ingredient";
    String EQUIPMENT = "equipment";
    boolean goodToSave = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout to use
        setContentView(R.layout.activity_add_supply);

        currentKitchen = this.getIntent().getExtras().getString("kitchenName");

        createButtons();
        createEditTexts();

        // Initialize the supply type to be empty.
        type = "";
    }

    @Override
    public void onPause() {
        super.onPause();

        // Create an intent so that we can return to the previous page when we're done
        Intent kitchenIntent = new Intent(this, KitchenActivity.class);
        kitchenIntent.putExtra("supplyKitchen", currentKitchen);
        // Return to this correct Kitchen Activity
        startActivity(kitchenIntent);
    }

    public void onRadioButtonClicked(View v) {
        // Is the button now checked?
        boolean checked = ((RadioButton) v).isChecked();

        // Check which radio button was clicked
        switch(v.getId()) {
            case R.id.select_equipment:
                if (checked)
                    type = EQUIPMENT;
                    amountEditText.setVisibility(View.INVISIBLE);
                    break;
            case R.id.select_ingredient:
                if (checked)
                    type = INGREDIENT;
                    amountEditText.setVisibility(View.VISIBLE);
                    break;
        }
    }


    public void cancelSupply(View v) {
        // We don't want to save any data, just return to main screen
        finish();
    }

    public void createSupply(View v) {

        saveSupplyName();
        saveSupplyKitchen();
        saveSupplyNotes();
        saveSupplyType();
        if (type.matches(INGREDIENT)) {
            createIngredient();
        }
        if (type.matches(EQUIPMENT)) {
            createEquipment();
        }
    }

    public void saveSupplyName() {
        supplyName = nameEditText.getText().toString();
        if (supplyName.matches("")){
            Toast.makeText(this, "You must give this supply a name", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }
    }

    public void saveSupplyKitchen() {
        supplyKitchen = kitchenEditText.getText().toString();
        if (supplyKitchen.matches("")){
            Toast.makeText(this, "You must give this supply a kitchen", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }
    }

    public void saveSupplyNotes() {
        supplyNotes = notesEditText.getText().toString();
    }

    public void saveSupplyType() {
        if (type.matches("")) {
            Toast.makeText(this, "You must select whether the supply is equipment or an ingredient.", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }
    }

    public void createIngredient() {
        if (goodToSave) {
            amount = amountEditText.getText().toString();
            Ingredient newSupply = new Ingredient(supplyName, supplyKitchen, supplyNotes, amount);
            finish();
        }
    }

    public void createEquipment() {
        if (goodToSave) {
            Equipment newSupply = new Equipment(supplyName, supplyKitchen, supplyNotes, false);
            finish();
        }
    }

    public void createButtons() {
        // Create the supply-create button.
        createButton = (Button) findViewById(R.id.create_button);
        // Create the cancel button.
        cancelButton = (Button) findViewById(R.id.cancel_button);
    }

    public void createEditTexts() {
        // Create the name text box.
        nameEditText = (EditText) findViewById(R.id.edit_supply_name);

        // Create the kitchen text box.
        kitchenEditText = (EditText) findViewById(R.id.edit_kitchen);

        // Create the notes text box.
        notesEditText = (EditText) findViewById(R.id.edit_notes);

        // Create the amount text box. By default, it should be invisible.
        amountEditText = (EditText) findViewById(R.id.edit_amount);
        amountEditText.setVisibility(View.INVISIBLE);
    }

}
