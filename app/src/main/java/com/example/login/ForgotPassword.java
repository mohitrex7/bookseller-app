package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ForgotPassword extends AppCompatActivity {
Button forgotp;
EditText phone,name;
String Phone,Name;
StringRequest stringRequest;
String URL = "http://192.168.2.6/loginregisteration/forgot.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        phone = findViewById(R.id.phone);
        name = findViewById(R.id.name);
        forgotp = findViewById(R.id.forgotp);

        submit();
    }

    private void submit() {
        forgotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phone = phone.getText().toString();
                Name = name.getText().toString();

                stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("SUCCESS")){
                            Toast.makeText(getApplicationContext(),"Email Successfully Sent! Please Check your Inbox",Toast.LENGTH_LONG).show();

                        }else{
                            Toast.makeText(getApplicationContext(),"Failed",Toast.LENGTH_LONG).show();

                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ForgotPassword.this,"Please Check Connection"+error.toString(),Toast.LENGTH_LONG).show();

                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        params.put("phone",Phone);
                        params.put("name",Name);
                        return super.getParams();
                    }

                };
            }
        });
    }


    public void back(View view) {
        startActivity(new Intent(ForgotPassword.this,MainActivity3.class));
        finish();
    }
}