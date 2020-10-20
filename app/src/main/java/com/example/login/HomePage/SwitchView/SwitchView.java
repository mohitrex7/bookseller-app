package com.example.login.HomePage.SwitchView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SwitchView extends AppCompatActivity {
    private ViewStub stubGrid;
    private ViewStub stubList;

    private ListView listView;
    private GridView gridView;

    private ListViewAdapter listViewAdapter;
    private GridViewAdapter gridViewAdapter;

    private ArrayList<Product> productList;

    private int currentViewMode = 0;

    static final int VIEW_MODE_LISTVIEW = 0;
    static final int VIEW_MODE_GRIDVIEW = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_view);

        stubList = (ViewStub) findViewById(R.id.stub_list);
        stubGrid = (ViewStub) findViewById(R.id.stub_grid);

        final ProgressBar myProgressBar= findViewById(R.id.myProgressBar);
        SearchView mySearchView=findViewById(R.id.mySearchView);

        //Inflate ViewStub before get view

        stubList.inflate();
        stubGrid.inflate();

        listView = (ListView) findViewById(R.id.mylistview);
        gridView = (GridView) findViewById(R.id.mygridview);

        //get list of product
        productList = new ArrayList<>();

        //get data from rest api
        RequestQueue requestQueue = Volley.newRequestQueue(SwitchView.this);

        String urlaaa = "https://run.mocky.io/v3/1136660a-7cde-4c47-84bf-13eb6236aafe";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlaaa,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("engineering");
                            for(int i =0; i<array.length();i++) {
                                JSONObject obj = array.getJSONObject(i);
                                //Toast.makeText(c, obj.toString(), Toast.LENGTH_LONG).show();
                                String ImageURL = obj.getString("image");
                                final int id = obj.getInt("id");
                                final String Title = obj.getString("title");
                                final String Author = obj.getString("author");
                                final int Price = (Integer) obj.get("price");
                                final double rating = (Double) obj.get("rating");
                                boolean fav = ((Integer)obj.get("fav") == 1);

                                Product p=new Product();
                                p.setProduct_id(id);
                                p.setProduct_name(Title);
                                p.setProduct_publication(Author);
                                p.setProduct_price(String.valueOf(Price));
                                p.setPic_url(ImageURL);

                                productList.add(p);

                            }
                            switchView();
                        } catch (JSONException e) {
                            Toast.makeText(SwitchView.this, "Error in parsing json", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(SwitchView.this, "Error in getting data", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);



        //Get current view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        currentViewMode = sharedPreferences.getInt("currentViewMode", VIEW_MODE_GRIDVIEW);//Default is view listview

        listViewAdapter = new ListViewAdapter(this, productList);
        gridViewAdapter = new GridViewAdapter(this, productList);


        mySearchView.setIconified(true);
        mySearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                listViewAdapter.getFilter().filter(s);
                gridViewAdapter.getFilter().filter(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                listViewAdapter.getFilter().filter(query);
                gridViewAdapter.getFilter().filter(query);
                return false;
            }
        });
    }

    private void switchView() {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            //Display listview
            stubList.setVisibility(View.VISIBLE);
            //Hide gridview
            stubGrid.setVisibility(View.GONE);
            //Toast.makeText(this, String.valueOf(currentViewMode), Toast.LENGTH_SHORT).show();
        } else {
            //Hide listview
            stubList.setVisibility(View.GONE);
            //Display gridview
            stubGrid.setVisibility(View.VISIBLE);
        }
        setAdapters();
    }

    private void setAdapters() {
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            listView.setAdapter(listViewAdapter);

        } else {
            gridView.setAdapter(gridViewAdapter);

        }
    }


    public void switchMyView(View view){
        if(VIEW_MODE_LISTVIEW == currentViewMode) {
            currentViewMode = VIEW_MODE_GRIDVIEW;
        } else {
            currentViewMode = VIEW_MODE_LISTVIEW;
        }
        //Switch view
        switchView();
        //Save view mode in share reference
        SharedPreferences sharedPreferences = getSharedPreferences("ViewMode", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("currentViewMode", currentViewMode);
        editor.commit();
    }
}