package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class profiledetails extends AppCompatActivity{

    //this is the JSON Data URL8
    //make sure you are using the correct ip else it will not work
    private static final String URL_PRODUCTS = "https://run.mocky.io/v3/81bf071a-b556-4a40-90a7-0dc85e3fe7a2";

    //a list to store all the products
    List<Profile> profileList;


    //the recyclerview
    RecyclerView recyclerView;

    ProfileAdapter adapter;

    //Add
    Random mrandom = new Random();
    ImageButton add;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiledetails);




        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //add


        //initializing the productlist
        profileList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();
    }


    private void loadProducts() {

        /*
         * Creating a String Request
         * The request type is GET defined by first parameter
         * The URL is defined in the second parameter
         * Then we have a Response Listener and a Error Listener
         * In response listener we will get the JSON response as a String
         * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_PRODUCTS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject profile = array.getJSONObject(i);

                                //adding the product to product list
                                profileList.add(new Profile(
                                        profile.getString("title"),
                                        profile.getString("desc"),
                                        profile.getString("image")
                                ));
                            }
                            Toast.makeText(getApplicationContext(), profileList.size() + "", Toast.LENGTH_LONG).show();
                            //creating adapter object and setting it to recyclerview
                            adapter = new ProfileAdapter(profiledetails.this, profileList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Good Response but java can't parse json it received" + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(getApplicationContext(), "Unsucessful : Error is :" + error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }



    public void back(View view) {
        startActivity(new Intent(profiledetails.this,myacc.class));
        finish();
    }

    public void change(View view) {
        startActivity(new Intent(profiledetails.this,changepassword.class));
        finish();
    }
}