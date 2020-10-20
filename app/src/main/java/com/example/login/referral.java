package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class referral extends AppCompatActivity {
    Button share;
    ImageView copy;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral);

        copy = findViewById(R.id.copy);
        t1 = findViewById(R.id.copylink);

        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (t1.getText().toString().isEmpty())
                {

                }else{
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);

                    ClipData clipData = ClipData.newPlainText("textview",t1.getText().toString());

                    clipboardManager.setPrimaryClip(clipData);

                    Toast.makeText(referral.this,"Copied to Clipboard",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void share(View view) {
        Intent myintent = new Intent(Intent.ACTION_SEND);
        myintent.setType("text/plain");
        String shareBody ="Wanna Buy Books at affordable rate , check this app out ,Enter My Referral Code During registeration and we both have a chance to win points . Enjoy Shopping  https://play.google.com/store/apps/details?id=com.kpshopy.learnweel";
        myintent.putExtra(Intent.EXTRA_TEXT,shareBody);
        startActivity(Intent.createChooser(myintent,"Share Using"));
    }

    public void back(View view) {
        startActivity(new Intent(referral.this,MainActivity2.class));
        finish();
    }
}