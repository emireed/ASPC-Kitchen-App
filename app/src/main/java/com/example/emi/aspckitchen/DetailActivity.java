package com.example.emi.aspckitchen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Keighley on 10/5/2015.
 */


public class DetailActivity extends Activity implements View.OnClickListener {

    TextView detailTextView;
    Button deleteButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tell the activity which XML layout is right
        setContentView(R.layout.activity_detail);


        String supplyName = this.getIntent().getExtras().getString("supplyName");

        deleteButton = (Button) findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(this);

        detailTextView = (TextView) findViewById(R.id.detail_textview);
        detailTextView.setText(supplyName);


    }


    @Override
    public void onClick(View v) {
        //This should delete the object. However, we have no way of accessing it currently.

    }
}
