package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Referral1 extends AppCompatActivity {
    TextView referal,sell;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_referral1);
        referal=findViewById(R.id.ref_text_view2);
        sell=findViewById(R.id.selling_text_view2);
        b1=findViewById(R.id.back_button);
        int arr[]=new int[2];
        // temp referral points random no.
        arr[0]= (int) (System.currentTimeMillis()%1000);
        arr[1]= (int) ((System.currentTimeMillis()*7)%1000);
        //getRef(arr); // call this method to get actual referal points
        referal.setText(""+arr[0]);
        sell.setText(""+arr[1]);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void getRef(int[] arr)
    {

        // put code to get the referal points
    }
}