package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Savedcards extends AppCompatActivity {
TextView t1,t2,t3,t4,t5,t6,t7;
CardView cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedcards);
        cc = findViewById(R.id.cc);
        t1 = (TextView)findViewById(R.id.num);
        t5 = findViewById(R.id.num1);
        t6 = findViewById(R.id.num2);
        t7 = findViewById(R.id.num3);
        t2 = findViewById(R.id.cardholder);
        t3 = findViewById(R.id.mm);
        t4 = findViewById(R.id.yy);

        Intent intent = getIntent();
       String code  = intent.getStringExtra("Extra_num");
        t1.setText(code );
        String code1  = intent.getStringExtra("Extra_num1");
        t5.setText(code1 );
        String code2= intent.getStringExtra("Extra_num2");
        t6.setText(code2);
        String code3 = intent.getStringExtra("Extra_num3");
        t7.setText(code3 );
        String name = intent.getStringExtra("Extra_name");
        t2.setText(name);
        String mm = intent.getStringExtra("Extra_mm");
        t3.setText(mm);
        String yy = intent.getStringExtra("Extra_yy");
        t4.setText(yy);
    }

    public void add(View view) {
        Intent intent = new Intent(Savedcards.this,addcard.class);
        startActivity(intent);
        finish();
    }
    public void back(View view) {
        Intent intent1 = new Intent(Savedcards.this,myacc.class);
        startActivity(intent1);
        finish();
    }
}