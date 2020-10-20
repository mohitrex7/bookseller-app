package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //Normal Registeration


    MaterialEditText birthdate;
    TextInputLayout name,email,phone,password,referral;
    RadioGroup radioGroup;
    Button register;


   //Google SignIn
    int RC_SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient mGoogleSignInClient;

   //Calender
    private  DatePickerDialog.OnDateSetListener dateSetListener;


    //Password Validation
private static final Pattern PASSWORD_PATTERN =
        Pattern.compile("^ " +
                "(?=.*[0-9])" +    //atleast 1 digit
                "(?=.*[a-z])" +    //atleast 1 lower case letter
                "(?=.*[A-Z])" +    //atleast 1 uppercase letter
                "(?=.*[@#$%^&+=])" +  //atleast ! special character
                "(?=\\S+$)" +      //no white spaces
                ".{6,}" +        //atleast 6 characters
                "$");
    private String result
            ;


private DatePickerDialog.OnDateSetListener mDateSetListener;
ImageView cal;
//--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (TextInputLayout)findViewById(R.id.name);
        email = (TextInputLayout)findViewById(R.id.email);
        password = (TextInputLayout) findViewById(R.id.password);
        phone = (TextInputLayout)findViewById(R.id.phone);
        birthdate = findViewById(R.id.birthdate);
        cal = findViewById(R.id.cal);
        referral = (TextInputLayout) findViewById(R.id.referralc);
        radioGroup = findViewById(R.id.radiogp);
        register = findViewById(R.id.register);

        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day
                );

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                Log.d(TAG,"ondataset : date"+month+"/"+day+"/"+year);
                String date = month +"/"+day+"/"+year;
                birthdate.setText(date);
            }
        };



//Validation
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String Name = name.getEditText().getText().toString();
                final String Email = email.getEditText().getText().toString();
                final String Password = password.getEditText().getText().toString();
                final String Phone = phone.getEditText().getText().toString();
                final String Birthdate = birthdate.getText().toString();
                final String Referral = referral.getEditText().getText().toString();
                int checkedId = radioGroup.getCheckedRadioButtonId();
                RadioButton selected_gender = radioGroup.findViewById(checkedId);
                if (selected_gender == null){
                    Toast.makeText(MainActivity.this, "Select gender please", Toast.LENGTH_SHORT).show();
                }
                else {
                    final String Gender = selected_gender.getText().toString();
                    if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password) ||
                            TextUtils.isEmpty(Phone) || TextUtils.isEmpty(Birthdate)){
                        Toast.makeText(MainActivity.this, "All fields are required", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        register(Name,Email,Password,Phone,Gender,Birthdate,Referral);
                    }
                }


            }
        });


        //Initializing Views
       signInButton = findViewById(R.id.signin);

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

    //--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    private void register(final String name, final String email, final String password, final String phone, final String gender,final String birthdate,final String referral){
        final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(false);
        progressDialog.setTitle("Registering your account");
        progressDialog.show();
        String uRl = "http://192.168.2.6/loginregisteration/regist.php";

        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if (response.equals("You are registered successfully")){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,MainActivity3.class));
                    finish();
                }else {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<>();
                param.put("name",name);
                param.put("email",email);
                param.put("password",password);
                param.put("phone",phone);
                param.put("gender",gender);
                param.put("birthdate",birthdate);
                param.put("referral",referral);
                return param;

            }
        };

        request.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getmInstance(MainActivity.this).addToRequestQueue(request);

    }

    //-------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    //Google Login


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
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Google Sign In Error", "signInResult:failed code=" + e.getStatusCode());
            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onStart() {
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null) {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        }
        super.onStart();
    }

    //-----------------------------------------------------------------------------------------------------------------------------------------------------

    public void login(View view) {
        Intent intent = new Intent(MainActivity.this,MainActivity3.class);
        startActivity(intent);
        finish();
    }



}