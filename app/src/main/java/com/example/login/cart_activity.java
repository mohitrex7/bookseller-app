package com.example.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class cart_activity extends AppCompatActivity {


    // private static final String URL_PRODUCTS = "http://192.168.93.1/MyCartApi/cart.json";
    private static final String URL_PRODUCTS = "https://run.mocky.io/v3/ddfa996a-743d-4fdc-9cb7-c38a96696e5d";
    List<CartProduct> productList;
    // private CartAdapter cAdapter;
    //the recyclerview
    RecyclerView recyclerView;
    private TextView tv_total;
    private double total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_activity);
        //Toast.makeText(this., Toast.LENGTH_SHORT).show();

        tv_total =(TextView) findViewById(R.id.tv_total);


        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        CartAdapter adapter = new CartAdapter(cart_activity.this, productList);
        recyclerView.setAdapter(adapter);

        //this method will fetch and parse json
        //to display it in recyclerview
        loadProducts();

        total=adapter.grandtotal(productList);
        tv_total.setText(String.valueOf(total));
        //getIntentData();
        //calculateTotal();
        //tv_total.grandTotal(toal);
    }

    private void loadProducts() {
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
                                JSONObject product = array.getJSONObject(i);

                                //adding the product to product list
                                productList.add(new CartProduct(
                                        product.getInt("product_id"),

                                        product.getString("product_name"),
                                        product.getDouble("product_price"),

                                        product.getInt("product_quantity"),
                                        product.getInt("product_discount"),


                                        product.getString("image_id")

                                ));
                            }
                            //creating adapter object and setting it to recyclerview
                            CartAdapter adapter = new CartAdapter(cart_activity.this, productList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
/*
    private void getIntentData(){
        if(getIntent()!=null && getIntent().getExtras()!=null){
            // Get the Required Parameters for sending Order to server.
        }
    }*/

    /*
        private void calculateTotal() { int i=0;
            total=0;
            while(i< CartAdapter.productList.size()){
                total=total + ( Integer.valueOf((int) CartAdapter.productList.get(i).getProduct_price()) * Integer.valueOf(CartAdapter.productList.get(i).getProduct_quantity()));
                i++;
            }
            tv_total.setText(""+total);
        }
        */
    public void proceedtobuy(View view) {


        Intent in=new Intent(cart_activity.this, address.class);
        startActivity(in);
    }

}

/*

 */