package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity4 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
    }

    public void delivered(View view) {
    }

    public void processing(View view) {
    }

    public void cancelled(View view) {
    }

    public void back(View view) {
        Intent intent = new Intent(MainActivity4.this,MainActivity2.class);
        startActivity(intent);
    }

    public void show(View view) {
        Intent intent2 = new Intent(MainActivity4.this,MainActivity2.class);
        startActivity(intent2);
    }
}
