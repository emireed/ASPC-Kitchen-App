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
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.activity_detail);


        supplyName = this.getIntent().getExtras().getString("supplyName");
        supplyID = this.getIntent().getExtras().getString("supplyID");
        position = this.getIntent().getExtras().getInt("supplyPos");

        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(this);

        detailTextView = (TextView) findViewById(R.id.detail_textview);
        detailTextView.setText(supplyName);

        //TODO: delete this
        // Create an Intent to tell the MainActivity if we've deleted an item.
//        mainIntent = new Intent(this, MainActivity.class);
//        mainIntent.putExtra("supplyDeleted", false);
//        mainIntent.putExtra("supplyPos", position);

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
                    // TODO: Delete the object from the local supplyList, as well, so it shows up in the immediate app.
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
