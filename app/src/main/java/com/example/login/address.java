package com.example.login;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class address extends AppCompatActivity {

    TextInputEditText houseno;
    TextInputEditText landmark1;
    TextInputEditText city1;
    TextInputEditText state1;
    TextInputEditText pincode1;
    Button continue1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        final TextInputEditText houseno =  findViewById(R.id.house_no);
        final TextInputEditText landmark1 = findViewById(R.id.landmark);
        final TextInputEditText city1 = findViewById(R.id.city);
        final TextInputEditText state1 = findViewById(R.id.state);
        final TextInputEditText pincode1 =findViewById(R.id.pincode);
        continue1 = findViewById(R.id.adcontinue);


        continue1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
             /* if (!validatehouseno() | !validatelandmark() | !validatecity() | !validatestate() | !validatepincode()) {
                    return;
                }
                // hideKeyboard();


                String input = houseno.getEditText().toString();
                input+=",";
                input+=landmark1.getEditText().toString();
                input+=",";
                input+= city1.getEditText().toString();
                input+=",";
                input+=state1.getEditText().toString();
                input+=",";
                input+=pincode1.getEditText().toString();
                input+=".";
                */
                String comma=",";
                String fullstop=".";
                String house = houseno.getText().toString();
                String landmark = landmark1.getText().toString();
                String city = city1.getText().toString();
                String state = state1.getText().toString();
                String pincode = pincode1.getText().toString();

                if (TextUtils.isEmpty(house)){
                    houseno.setError("Enter Your houseno");
                    houseno.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(landmark)){
                    landmark1.setError("Enter landmark");
                    landmark1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(city)){
                    city1.setError("Enter Your city name");
                    city1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(state)){
                    state1.setError("Enter Your state name");
                    state1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(pincode)){
                    pincode1.setError("Enter Pincode");
                    pincode1.requestFocus();
                    return;
                }

                Intent intent = new Intent(address.this, display_address.class);
                // intent.setType("plain/text");

                intent.putExtra("house", house);
                intent.putExtra(",",comma);
                intent.putExtra(".",fullstop);
                intent.putExtra("landmark", landmark);
                intent.putExtra("city", city);
                intent.putExtra("state", state);
                intent.putExtra("pincode", pincode);

                // intent.putExtra("input",houseno+""+landmark1+""+city1+""+state1+""+pincode1+"");

                startActivity(intent);
                finish();

            }
        });


    }

    public void add(View view) {
        Intent intent = new Intent(address.this, display_address.class);
        startActivity(intent);
        finish();
    }

/*
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public boolean validatehouseno() {
        String house = houseno.getText().toString().trim();
        if (house.isEmpty()) {
            houseno.setError("Field Cant Be Empty");
            return false;
        } else {
            houseno.setError(null);
            return true;
        }

    }

    public boolean validatelandmark() {
        String landmark = landmark1.getText().toString().trim();
        if (landmark.isEmpty()) {
            landmark1.setError("Field Cant Be Empty");
            return false;
        } else {
            landmark1.setError(null);
            return true;
        }

    }

    public boolean validatecity() {
        String city = city1.getText().toString().trim();
        if (city.isEmpty()) {
            city1.setError("Field Cant Be Empty");
            return false;
        } else {
            city1.setError(null);
            return true;
        }

    }

    public boolean validatestate() {
        String state = state1.getText().toString().trim();
        if (state.isEmpty()) {
            state1.setError("Field Cant Be Empty");
            return false;
        } else {
            state1.setError(null);
            return true;
        }

    }

    public boolean validatepincode() {
        String pincode = pincode1.getText().toString().trim();
        if (pincode.isEmpty()) {
            pincode1.setError("Field Cant Be Empty");
            return false;
        } else {
            pincode1.setError(null);
            return true;
        }

    }*/
}



