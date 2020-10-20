package com.example.login.SellWithLearnweel;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.login.R;
import com.squareup.picasso.Picasso;

public class ProcessingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing);

        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra("PROCESSING_URL");
        int ordernum=intent.getIntExtra("PROCESSING_ORDERNUM",0);
        int amount=intent.getIntExtra("PROCESSING_AMOUNT",0);
        String trackingid=intent.getStringExtra("PROCESSING_TRACKINGID");
        String orderdate=intent.getStringExtra("PROCESSING_ORDERDATE");
        String shipping=intent.getStringExtra("PROCESSING_SHIPPING");
        String title=intent.getStringExtra("PROCESSING_TITLE");
        String status= intent.getStringExtra("PROCESSING_STATUS");

        ImageView imageView=findViewById(R.id.image_processdetails);
        TextView textViewordernum=findViewById(R.id.ordernum_processdetails);
        TextView textViewamount=findViewById(R.id.totalamt_processdetails);
        TextView textViewtrackingid=findViewById(R.id.trackid_processdetails);
        TextView textVieworderdate=findViewById(R.id.odate_processdetails);
        TextView textViewshipping=findViewById(R.id.shipadd_processdetails);
        TextView textViewtitle=findViewById(R.id.booktitle_processdetails);
        TextView textViewstatus=findViewById(R.id.status_processdetails);

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