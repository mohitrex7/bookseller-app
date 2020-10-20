package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class changepassword extends AppCompatActivity {
    EditText e1,e2,e3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);

        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        e3 = findViewById(R.id.e3);
        b1 = findViewById(R.id.b1);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tex_curr = e1.getText().toString();
                String tex_new = e2.getText().toString();
                String tex_password = e3.getText().toString();
                if (TextUtils.isEmpty(tex_curr)|| TextUtils.isEmpty(tex_new) || TextUtils.isEmpty(tex_password)){
                    Toast.makeText(changepassword.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(tex_curr,tex_new,tex_password);
                }
            }
        });
    }
    private void login(final String e1, final String e2, final String e3){
        String uRl = "http://192.168.2.6/loginregisteration/change.php";
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Password Changed Successfully")){
                    Toast.makeText(changepassword.this, response, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(changepassword.this, response, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(changepassword.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("e1",e1);
                param.put("e2",e2);
                param.put("e3",e3);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(changepassword.this).addToRequestQueue(request);
    }





















    public void done(View view) {
        Intent intent = new Intent(changepassword.this,profiledetails.class);
        startActivity(intent);
        finish();
    }

    public void back(View view) {
        startActivity(new Intent(changepassword.this,profiledetails.class));
        finish();
    }
}