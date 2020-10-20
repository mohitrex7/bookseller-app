package com.example.login.SellWithLearnweel;

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
import com.example.login.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DeliveredFragment extends Fragment  {
    private RecyclerView deliveredRecyclerView;
    private MyOrdersRecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutmanager;

    private ArrayList<MyOrdersData> mTotalOrders;

    public static final String DELIVERED_URL="imageUrl";
    public static final String DELIVERED_ORDERNUM="ordernum";
    public static final String DELIVERED_AMOUNT="amount";
    public static final String DELIVERED_TRACKINGID="trackingid";
    public static final String DELIVERED_ORDERDATE="orderdate";
    public static final String DELIVERED_SHIPPING="shipping";
    public static final String DELIVERED_TITLE="title";
    public static final String DELIVERED_STATUS="status";

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_delivered, container, false);

        mTotalOrders = new ArrayList<>();

        deliveredRecyclerView = v.findViewById(R.id.deliveredRecyclerView);
        mLayoutmanager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);//LinearLayoutManager.HORIZONTAL, true

//        String uri = "@drawable/download";  // where myresource (without the extension) is the file
//
//        int imageResource = getResources().getIdentifier(uri, null, getActivity().getPackageName());


        //get data from rest api
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String urlaaa = "https://run.mocky.io/v3/8b1df4e4-cbb7-4459-9a42-78abfe755cbd";





        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlaaa,null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray array = response.getJSONArray("delivered");
                            for(int i =0; i<array.length();i++) {
                                JSONObject obj = array.getJSONObject(i);
                              //  Toast.makeText(getContext(), obj.toString(), Toast.LENGTH_LONG).show();
                                String ImageURL = obj.getString("imageUrl");
                                final int ordernum = (Integer) obj.get("ordernum");
                                final int amount = (Integer) obj.get("amount");
                                final String trackingid = obj.getString("trackingid");
                                final String orderdate = obj.getString("orderdate");
                                final String shipping = obj.getString("shipping");
                                final String title = obj.getString("title");
                                final String status= obj.getString("status");





                                mTotalOrders.add(new MyOrdersData(ImageURL,ordernum,amount,trackingid,orderdate,shipping,title,status));
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

        mAdapter = new MyOrdersRecyclerViewAdapter(mTotalOrders,R.layout.delivered_order_list);


        deliveredRecyclerView.setLayoutManager(mLayoutmanager);
        deliveredRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new MyOrdersRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyOrdersData deliveredProductDetails = mTotalOrders.get(position);


                Intent intent=new Intent(getContext(),DeliveredActivity.class);
                intent.putExtra("DELIVERED_URL",deliveredProductDetails.getmImageUrl());
                intent.putExtra("DELIVERED_ORDERNUM",deliveredProductDetails.getmOrdernum());
                intent.putExtra("DELIVERED_AMOUNT",deliveredProductDetails.getmAmount());
                intent.putExtra("DELIVERED_TRACKINGID",deliveredProductDetails.getmTrackingid());
                intent.putExtra("DELIVERED_ORDERDATE",deliveredProductDetails.getmOrderdate());
                intent.putExtra("DELIVERED_SHIPPING",deliveredProductDetails.getmShipping());
                intent.putExtra("DELIVERED_TITLE",deliveredProductDetails.getmTitle());
                intent.putExtra("DELIVERED_STATUS",deliveredProductDetails.getmStatus());

                startActivity(intent);

            }
        });

        return v;

    }


}