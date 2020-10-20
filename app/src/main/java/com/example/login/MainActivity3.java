package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
//Normal Login
TextInputLayout email,password;
    Button register,login;
    TextView forgot;
    CheckBox checkedStatus;

    SharedPreferences sharedPreferences;

    //Google Sign In
    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);



        email = (TextInputLayout) findViewById(R.id.email);
        password = (TextInputLayout) findViewById(R.id.password);
        checkedStatus = findViewById(R.id.checkbox);
        forgot = findViewById(R.id.forgot);
        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState),"");
        if (loginStatus.equals("loggedin")){
            startActivity(new Intent(MainActivity3.this,MainActivity2.class));
            finish();
        }


        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tex_email = email.getEditText().getText().toString();
                String tex_password = password.getEditText().getText().toString();
                if (TextUtils.isEmpty(tex_email) || TextUtils.isEmpty(tex_password)){
                    Toast.makeText(MainActivity3.this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    login(tex_email,tex_password);
                }
            }
        });





        //Initializing Views
        signInButton = findViewById(R.id.signinl);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });
    }

    //Normal SignIn
    private void login(final String email, final String password){
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity3.this);
        progressDialog.setTitle("Logging into your account");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setIndeterminate(false);
        progressDialog.show();
        String uRl = "http://192.168.2.6/loginregisteration/login.php";
        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("Login Success")){
                    Toast.makeText(MainActivity3.this, response, Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (checkedStatus.isChecked()){
                        editor.putString(getResources().getString(R.string.prefLoginState),"loggedin");
                    }
                    else{
                        editor.putString(getResources().getString(R.string.prefLoginState),"loggedout");
                    }
                    editor.apply();
                    progressDialog.dismiss();
                    Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity3.this, response, Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity3.this, error.toString(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("email",email);
                param.put("password",password);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(MainActivity3.this).addToRequestQueue(request);
    }














    private void SignIn() {
        startActivity(new Intent(MainActivity3.this,MainActivity2.class));
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            // Signed in successfully, show authenticated UI.
            startActivity(new Intent(MainActivity3.this, MainActivity2.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity3.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null) {
            startActivity(new Intent(MainActivity3.this, MainActivity2.class));
        }
        super.onStart();
    }


    public void register(View view) {
        Intent intent = new Intent(MainActivity3.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void forgotp(View view) {
        startActivity(new Intent(MainActivity3.this,ForgotPassword.class));
        finish();
    }


   public void direct(View view) {
        startActivity(new Intent(MainActivity3.this,MainActivity2.class));
        finish();
    }
}