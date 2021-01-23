package com.example.food_donation_dissertation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LocationConfirm extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMap, tvAddress;
    private ImageView imgCheck;
    private Button btnContinue;
    private final String TAG = "LocationConfirm started";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_confirm);

        tvMap = findViewById(R.id.tvMap);
        tvAddress = findViewById(R.id.tvAddress);
        btnContinue = findViewById(R.id.btnContinue);
        imgCheck = findViewById(R.id.imgCheck);

        getAddressFromSharedPreference();

        tvMap.setOnClickListener(this);
        tvAddress.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.tvMap:
                 intent = new Intent(getApplicationContext(), MapsActivity.class);
                 startActivity(intent);
                 break;
            case R.id.tvAddress:
                Log.i(TAG, "tvAddress is pressed");
                intent = new Intent(getApplicationContext(), MapsActivity.class);
                 startActivity(intent);
                 break;
            case R.id.btnContinue:
                Log.i(TAG, "btnContinue is pressed");
                break;
        }
    }

    private void getAddressFromSharedPreference() {
        SharedPreferences savedData = getSharedPreferences("USER_LOCATION", Context.MODE_PRIVATE);
        String addressLine =  savedData.getString("address_line", "");
        if (addressLine.isEmpty()) {
            tvAddress.setText("Address has not been set ...");
            imgCheck.setVisibility(View.INVISIBLE);
            return;
        } else if (addressLine.length() > 35) {
            //shorten addressLine to fit in card view ...
            String addressLineInShort =  addressLine.substring(0, 30);
            tvAddress.setText(addressLineInShort.concat(" ...."));
        } else {
            tvAddress.setText(addressLine);
        }
        //show check mark ...
        imgCheck.setVisibility(View.VISIBLE);
    }
}