package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cartsumm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartsumm);
    }

    public void proceed(View view) {
        startActivity(new Intent(Cartsumm.this,cartsummary.class));
        finish();
    }

    public void bk(View view) {
        startActivity(new Intent(Cartsumm.this,display_address.class));
        finish();
    }
}