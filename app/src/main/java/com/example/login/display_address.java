package com.example.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class display_address extends AppCompatActivity {
    TextView dsaddress;
    CardView cc;
    RadioButton cod;
    Button con;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_address);
        cc = findViewById(R.id.cc);
        dsaddress = findViewById(R.id.display_address);
        Intent intent = getIntent();
        String add = intent.getStringExtra("house");
        add += intent.getStringExtra(",");
        add += intent.getStringExtra("landmark");
        add += intent.getStringExtra(",");
        add += intent.getStringExtra("city");
        add += intent.getStringExtra(",");
        add += intent.getStringExtra("state");
        add += intent.getStringExtra(",");
        add += intent.getStringExtra("pincode");
        add += intent.getStringExtra(".");
        dsaddress.setText(add);
        final RadioButton cod=findViewById(R.id.cod);

        Button con=findViewById(R.id.proceed);
        con.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // boolean checked = ((RadioButton) v).isChecked();
                //String paymentmethod=cod.getText().toString();


                Intent in = new Intent(display_address.this, Cartsumm.class);
                startActivity(in);
                Toast.makeText(getApplicationContext(),"Default COD option selected",Toast.LENGTH_SHORT).show();
                finish();
            }} );

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        switch (view.getId()){
            case R.id.cod:
                if(checked){
                    str="Cash On delivery option selected";
                    Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Please Select Payment Method",Toast.LENGTH_SHORT).show();

                }
                break;
            default:
                Toast.makeText(getApplicationContext(),"Default Cash On delivery Method Selected",Toast.LENGTH_SHORT).show();
        }

    }
}