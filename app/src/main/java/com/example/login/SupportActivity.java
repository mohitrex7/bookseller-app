package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SupportActivity extends AppCompatActivity {

    String[] users = { "Select Complaint Type","Account Related Queries", "Service Queries", "Others"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);
        final Spinner spin = (Spinner) findViewById(R.id.spinner1);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, users);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);




        ActivityCompat.requestPermissions(SupportActivity.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);

        final EditText your_message     = (EditText) findViewById(R.id.your_message);



        Button email = (Button) findViewById(R.id.post_message);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message   = your_message.getText().toString();
                String subject= spin.getSelectedItem().toString();
                String number ="7391902518";

                if (TextUtils.isEmpty(message)){
                    your_message.setError("Enter Your Message");
                    your_message.requestFocus();
                    return;
                }
                SmsManager mySmsManager = SmsManager.getDefault();
                mySmsManager.sendTextMessage(number,null, message, null, null);

                Intent sendEmail = new Intent(Intent.ACTION_SEND);

                /* Fill it with Data */
                sendEmail.setType("plain/text");
                sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"bhavana.m.learnweel@gmail.com"});
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Complaint : "+subject);
                sendEmail.putExtra(Intent.EXTRA_TEXT,
                        "Message:"+'\n'+message);

                /* Send it off to the Activity-Chooser */
                startActivity(Intent.createChooser(sendEmail, "Send mail..."));


            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        //Get a Tracker (should auto-report)


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }


}