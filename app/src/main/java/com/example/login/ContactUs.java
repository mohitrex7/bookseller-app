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
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUs extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.SEND_SMS}, PackageManager.PERMISSION_GRANTED);


        final EditText your_name        = (EditText) findViewById(R.id.your_name);
        final EditText your_email       = (EditText) findViewById(R.id.your_email);
        final EditText your_contactno   = (EditText) findViewById(R.id.your_contactno);
        final EditText your_subject     = (EditText) findViewById(R.id.your_subject);
        final EditText your_message     = (EditText) findViewById(R.id.your_message);



        Button email = (Button) findViewById(R.id.post_message);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name      = your_name.getText().toString();
                String email     = your_email.getText().toString();
                String contactno = your_contactno.getText().toString();
                String subject   = your_subject.getText().toString();
                String message   = your_message.getText().toString();
                String number ="9764703952";
                if (TextUtils.isEmpty(name)){
                    your_name.setError("Enter Your Name");
                    your_name.requestFocus();
                    return;
                }


                Boolean check = false;
                if (!isValidContactno(contactno)) {
                    check = true;
                    your_contactno.setError("Invalid contactno");
                    return;
                }

                Boolean onError = false;
                if (!isValidEmail(email)) {
                    onError = true;
                    your_email.setError("Invalid Email");
                    return;
                }

                if (TextUtils.isEmpty(subject)){
                    your_subject.setError("Enter Your Subject");
                    your_subject.requestFocus();
                    return;
                }

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
                sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{"akshay.p.learnweel@gmail.com"});
                sendEmail.putExtra(Intent.EXTRA_SUBJECT, subject);
                sendEmail.putExtra(Intent.EXTRA_TEXT,
                        "name:"+name+'\n'+"Contact No:"+contactno+"\n"+"Email Id: "+email+'\n'+"Message:"+'\n'+message);

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


    // validating email id

    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private boolean isValidContactno(String contactno)
    {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", contactno))
        {
            if(contactno.length() > 10 || contactno.length() < 10)
            {
                check = false;

            }
            else
            {
                check = true;

            }
        }
        else
        {
            check=false;
        }
        return check;
    }

}
