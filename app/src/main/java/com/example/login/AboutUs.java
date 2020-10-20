package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AboutUs extends AppCompatActivity {
    Toolbar t1;
    DrawerLayout d1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        t1=findViewById(R.id.tool1);
        setSupportActionBar(t1);

        // this is code to ge back using back button (Working)
        t1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AboutUs.this,"About us exit",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.about_us_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.notification1:
                startActivity(new Intent(AboutUs.this,NotificationActivity.class));
                Toast.makeText(this,"Notofication activity",Toast.LENGTH_SHORT).show();
                break;
            case R.id.home1:

                Toast.makeText(this,"Home activity",Toast.LENGTH_SHORT).show();
                finish();
                break;
            case R.id.cart1:
                startActivity(new Intent(AboutUs.this,cart_activity.class));
                Toast.makeText(this,"Cart activity",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}