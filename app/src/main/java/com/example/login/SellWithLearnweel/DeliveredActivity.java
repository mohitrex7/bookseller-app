package com.example.login.SellWithLearnweel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.squareup.picasso.Picasso;

public class DeliveredActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivered);

        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra("DELIVERED_URL");
        int ordernum=intent.getIntExtra("DELIVERED_ORDERNUM",0);
        int amount=intent.getIntExtra("DELIVERED_AMOUNT",0);
        String trackingid=intent.getStringExtra("DELIVERED_TRACKINGID");
        String orderdate=intent.getStringExtra("DELIVERED_ORDERDATE");
        String shipping=intent.getStringExtra("DELIVERED_SHIPPING");
        String title=intent.getStringExtra("DELIVERED_TITLE");
        String status=intent.getStringExtra("DELIVERED_STATUS");

        ImageView imageView=findViewById(R.id.image_odetails);
        TextView textViewordernum=findViewById(R.id.ordernum_odetails);
        TextView textViewamount=findViewById(R.id.totalamt_odetails);
        TextView textViewtrackingid=findViewById(R.id.trackid_odetails);
        TextView textVieworderdate=findViewById(R.id.odate_odetails);
        TextView textViewshipping=findViewById(R.id.shipadd_odetails);
        TextView textViewtitle=findViewById(R.id.booktitle_odetails);
        TextView textViewstatus=findViewById(R.id.status_odetails);

        Picasso.get().load(imageUrl).fit().centerInside().into(imageView);
        textViewordernum.setText("Order no:"+ordernum);
        textViewamount.setText("Total Amount:"+amount);
        textViewtrackingid.setText("Tracking id:"+trackingid);
        textVieworderdate.setText("Order Date:"+orderdate);
        textViewshipping.setText("Shipping Address:"+shipping);
        textViewtitle.setText(title);
        textViewstatus.setText("Status:"+status);
    }
}