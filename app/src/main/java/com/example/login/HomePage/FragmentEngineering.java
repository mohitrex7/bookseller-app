package com.example.login.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.HomePage.ProductDetails.ProductDetails;
import com.example.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentEngineering extends Fragment {
    private RecyclerView engRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;

    private ArrayList<ExampleData> books;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_engineering, container, false);

        books = new ArrayList<>();

        engRecyclerView = v.findViewById(R.id.engiRecyclerView);
        mLayoutmanager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);//LinearLayoutManager.HORIZONTAL, true

        String uri = "@drawable/download";  // where myresource (without the extension) is the file

        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());


        //get data from rest api
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String urlaaa = "https://run.mocky.io/v3/1136660a-7cde-4c47-84bf-13eb6236aafe";



        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlaaa,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("engineering");
                            for(int i =0; i<array.length();i++) {
                                JSONObject obj = array.getJSONObject(i);
                                //Toast.makeText(getContext(), obj.toString(), Toast.LENGTH_LONG).show();
                                String ImageURL = obj.getString("image");
                                final int id = obj.getInt("id");
                                final String Title = obj.getString("title");
                                final String Author = obj.getString("author");
                                final int Price = (Integer) obj.get("price");
                                final double rating = (Double) obj.get("rating");
                                boolean fav = ((Integer)obj.get("fav") == 1);

                                books.add(new ExampleData(id,ImageURL,Title,Author,Price, (float) rating,false));
                                mAdapter.notifyDataSetChanged();



                            }
                        } catch (JSONException e) {
                            Toast.makeText(getContext(), "A", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Error in getting data", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

        mAdapter = new RecyclerViewAdapter(books);

        engRecyclerView.setLayoutManager(mLayoutmanager);
        engRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(new RecyclerViewAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //pass data of the selected item through intent
                ExampleData productDetails = books.get(position);

                Intent intent=new Intent(getContext(), ProductDetails.class);
                intent.putExtra("productID",productDetails.getid());
                intent.putExtra("productImageURL",productDetails.getUrl());
                intent.putExtra("productTitle",productDetails.getTitle());
                intent.putExtra("productRating", String.valueOf(productDetails.getRating()));
                intent.putExtra("productPrice", String.valueOf(productDetails.getPrice()));
                startActivity(intent);
            }
        });

        return v;
    }

}