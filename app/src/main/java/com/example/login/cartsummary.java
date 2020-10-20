package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class cartsummary extends AppCompatActivity {
    TextView myorder;
    Button continueshop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartsummary);
        TextView myorder=(TextView)findViewById(R.id.c_details);
        Button continueshop=findViewById(R.id.cshopping);

    }
    public void setMyorder(View V){
        Intent in =new Intent(cartsummary.this,OrdersActivity.class);
        startActivity(in);
        finish();

    }
    public void setContinueshop(View V){
        Intent in=new Intent(cartsummary.this,MainActivity2.class);
        startActivity(in);
        finish();

    }
}