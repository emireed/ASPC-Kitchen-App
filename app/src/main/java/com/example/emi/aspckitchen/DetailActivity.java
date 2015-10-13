package com.example.emi.aspckitchen;

import android.app.Activity;
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

    TextView detailTextView;
    Button deleteButton;
    String supplyName;
    String supplyID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout to use
        setContentView(R.layout.activity_detail);

        // Get the details about the supply, so we can display them.
        supplyName = this.getIntent().getExtras().getString("supplyName");
        supplyID = this.getIntent().getExtras().getString("supplyID");

        // Create the delete button.
        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(this);

        // Set the textview to display the supply name.
        detailTextView = (TextView) findViewById(R.id.detail_textview);
        detailTextView.setText(supplyName);

    }


    @Override
    public void onClick(View v) {
        // Find the object in Parse using its ID, then delete it.
        ParseQuery<KitchenSupply> query = ParseQuery.getQuery(KitchenSupply.class);
        query.getInBackground(supplyID, new GetCallback<KitchenSupply>() {
            public void done(KitchenSupply supply, ParseException e) {
                if (e == null) {
                    // Delete the object.
                    supply.deleteInBackground();
                } else {
                    // Something went wrong...
                    Log.d("score", "Error: " + e.getMessage());
                }
            }
        });

        // Once deleted, return to the previous page.
        finish();
    }
}
