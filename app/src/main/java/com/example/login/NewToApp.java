package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.login.HomePage.ProductDetails.ProductDescriptionFragment;
import com.example.login.HomePage.ProductDetails.ProductDetails;

public class NewToApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_to_app);

        Button btn =(Button) findViewById(R.id.btnOrder);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openorder();
            }


        });
    }

    private void openorder() {

        Intent intent=new Intent(this, Splashscreen.class);
        startActivity(intent);
    }
}
