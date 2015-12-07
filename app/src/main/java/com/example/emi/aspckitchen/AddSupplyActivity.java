package com.example.emi.aspckitchen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Created by emi on 10/14/15.
 */
public class AddSupplyActivity extends Activity implements AdapterView.OnItemSelectedListener {

    EditText nameEditText, kitchenEditText, campusEditText, notesEditText, amountEditText;
    Button createButton, cancelButton;
    Spinner campusSpinner, kitchenSpinner;
    ArrayAdapter<CharSequence> campusAdapter, kitchenAdapter;
    String supplyName, supplyCampus, supplyKitchen, supplyNotes, type, amount, currentKitchen, currentCampus;
    String INGREDIENT = "ingredient";
    String EQUIPMENT = "equipment";
    boolean goodToSave = true;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout to use
        setContentView(R.layout.activity_add_supply);

        currentKitchen = this.getIntent().getExtras().getString("kitchenName");
        currentCampus = this.getIntent().getExtras().getString("campusName");

        createButtons();
        createEditTexts();
        createSpinners();
        setSpinnersToCurrent();

        // Initialize the supply type and campus to be empty so we can check for user input.
        type = "";
        supplyCampus = "";
        supplyKitchen = "";
    }

    @Override
    public void onPause() {
        super.onPause();

        // Create an intent so that we can return to the previous page when we're done
        Intent kitchenIntent = new Intent(this, KitchenActivity.class);
        kitchenIntent.putExtra("supplyKitchen", currentKitchen);
        kitchenIntent.putExtra("campusName", currentCampus);
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

        goodToSave = true;

        saveSupplyName();
        saveSupplyKitchen();
        saveSupplyCampus();
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
        //supplyKitchen = kitchenEditText.getText().toString();
        if (supplyKitchen.matches("")){
            Toast.makeText(this, "You must give this supply a kitchen", Toast.LENGTH_SHORT).show();
            goodToSave = false;
        }
    }

    public void saveSupplyCampus() {
        if (supplyCampus.matches("")){
            Toast.makeText(this, "You must give this supply a campus", Toast.LENGTH_SHORT).show();
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
            Ingredient newSupply = new Ingredient(supplyName, supplyKitchen, supplyCampus, supplyNotes, amount);
            ParseOperations.confirmSupply(newSupply.getObjectId(), INGREDIENT);
            finish();
        }
    }

    public void createEquipment() {
        if (goodToSave) {
            Equipment newSupply = new Equipment(supplyName, supplyKitchen, supplyCampus, supplyNotes, false);
            ParseOperations.confirmSupply(newSupply.getObjectId(), EQUIPMENT);
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

        // Create the notes text box.
        notesEditText = (EditText) findViewById(R.id.edit_notes);

        // Create the amount text box. By default, it should be invisible.
        amountEditText = (EditText) findViewById(R.id.edit_amount);
        amountEditText.setVisibility(View.INVISIBLE);
    }

    public void createSpinners() {
        campusSpinner = (Spinner) findViewById(R.id.campus_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        campusAdapter = ArrayAdapter.createFromResource(this,
                R.array.campus_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        campusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        campusSpinner.setAdapter(campusAdapter);

        campusSpinner.setOnItemSelectedListener(this);

        kitchenSpinner = (Spinner) findViewById(R.id.kitchen_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        kitchenAdapter = ArrayAdapter.createFromResource(this,
                R.array.pomona_kitchen_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        kitchenAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        kitchenSpinner.setAdapter(kitchenAdapter);

        kitchenSpinner.setOnItemSelectedListener(this);
    }
    
    public void setSpinnersToCurrent() {
        // Set the campus spinner.
        int index = 0;

        for (int i=0;i<campusSpinner.getCount();i++){
            if (campusSpinner.getItemAtPosition(i).toString().equalsIgnoreCase(currentCampus)){
                index = i;
                break;
            }
        }
        
        campusSpinner.setSelection(index);
        

    }

    public void setKitchenSpinnerToCurrent() {
        // Set the kitchen spinner.
        int index2 = 0;

        for (int i=0;i<kitchenSpinner.getCount();i++){
            if (kitchenSpinner.getItemAtPosition(i).toString().equalsIgnoreCase(currentKitchen)){
                index2 = i;
                break;
            }
        }

        kitchenSpinner.setSelection(index2);
    }


    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        if(parent.getId() == R.id.campus_spinner) {

            supplyCampus = parent.getItemAtPosition(pos).toString();
            if (supplyCampus.equals("Pomona")) {
                kitchenAdapter = ArrayAdapter.createFromResource(this,
                        R.array.pomona_kitchen_array, android.R.layout.simple_spinner_item);
            }
            else if (supplyCampus.equals("Harvey Mudd")) {
                kitchenAdapter = ArrayAdapter.createFromResource(this,
                        R.array.harveymudd_kitchen_array, android.R.layout.simple_spinner_item);
            }
            kitchenSpinner.setAdapter(kitchenAdapter);
            if (supplyCampus.equals(currentCampus)) {
                setKitchenSpinnerToCurrent();
            }

        }else if(parent.getId() == R.id.kitchen_spinner){
            supplyKitchen = parent.getItemAtPosition(pos).toString();        }

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

}
