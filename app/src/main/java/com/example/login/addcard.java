package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class addcard extends AppCompatActivity {
    EditText e1,e3,e4;
    EditText c1,c2,c3,c4;
    Button b1;
    TextView t1,t2,t3,t4;
    String a;
    int keyDel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcard);
        c1 = findViewById(R.id.cardnumber);
        c2 = findViewById(R.id.cardnumber1);
        c3 = findViewById(R.id.cardnumber2);
        c4 = findViewById(R.id.cardnumber3);
        e1 = findViewById(R.id.cardname);
        e3 = findViewById(R.id.MM);
        e4 = findViewById(R.id.YY);
        b1 = findViewById(R.id.save);

       /* c1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                boolean flag = true;
                String eachBlock[] = c1.getText().toString().split("-");
                for (int i = 0; i < eachBlock.length; i++) {
                    if (eachBlock[i].length() > 4) {
                        flag = false;
                    }
                }
                if (flag) {

                    c1.setOnKeyListener(new View.OnKeyListener() {

                        @Override
                        public boolean onKey(View v, int keyCode, KeyEvent event) {

                            if (keyCode == KeyEvent.KEYCODE_DEL)
                                keyDel = 1;
                            return false;
                        }
                    });

                    if (keyDel == 0) {

                        if (((c1.getText().length() + 1) % 5) == 0) {

                            if (c1.getText().toString().split("-").length <= 3) {
                                c1.setText(c1.getText() + "-");
                                c1.setSelection(c1.getText().length());
                            }
                        }
                        a = c1.getText().toString();
                    } else {
                        a = c1.getText().toString();
                        keyDel = 0;
                    }

                } else {
                    c1.setText(a);
                }

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });*/

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.push_down);

                RelativeLayout relativeLayout = findViewById(R.id.cc);
                relativeLayout.setAnimation(animation);*/
                String code,code1,code2,code3,name,mm,yy;

                code = c1.getText().toString();
                code1= c2.getText().toString();
                code2= c3.getText().toString();
                code3 = c4.getText().toString();
                name = e1.getText().toString();
                mm = e3.getText().toString();
                yy = e4.getText().toString();




                Intent intent = new Intent(addcard.this,Savedcards.class);
                intent.putExtra("Extra_num",code);
                intent.putExtra("Extra_num1",code1);
                intent.putExtra("Extra_num2",code2);
                intent.putExtra("Extra_num3",code3);
                intent.putExtra("Extra_name",name);
                intent.putExtra("Extra_mm",mm);
                intent.putExtra("Extra_yy",yy);
                startActivity(intent);



            }
        });

    }

    public void back(View view) {
        Intent intent = new Intent(addcard.this,Savedcards.class);
        startActivity(intent);
        finish();
    }
}