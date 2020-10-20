package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HelpSupportActivity extends AppCompatActivity {

    Button contactus,newtoapp,supportbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        newtoapp=(Button) findViewById(R.id.new_to_app_btn);
        supportbtn=(Button) findViewById(R.id.support_btn);
        contactus=(Button) findViewById(R.id.contact_btn);


        newtoapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewtoApp();
            }


        });

        supportbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensupport();
            }


        });


        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencontact();
            }


        });

    }


    private void opencontact() {

       Intent intent=new Intent(this, ContactUs.class);
        startActivity(intent);
    }
    private void opensupport() {

        Intent intent2=new Intent(this, SupportActivity.class);
        startActivity(intent2);
    }

    private void openNewtoApp() {
        Intent intent1=new Intent(this, NewToApp.class);
        startActivity(intent1);
    }
}
