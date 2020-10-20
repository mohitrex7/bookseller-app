package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    List<NotificationPojo> notificationList;
    RecyclerView rv;
    public List<NotificationPojo> getNotificationList() {
        Bitmap b1= BitmapFactory.decodeResource(getResources(),R.drawable.img_noti_each);
        Bitmap b2= BitmapFactory.decodeResource(getResources(),R.drawable.img_noti_each2);
        Bitmap b3= BitmapFactory.decodeResource(getResources(),R.drawable.img_noti_each);
        Bitmap b4= BitmapFactory.decodeResource(getResources(),R.drawable.img_noti_each2);
        Bitmap b5= BitmapFactory.decodeResource(getResources(),R.drawable.img_noti_each2);
        Bitmap b6= BitmapFactory.decodeResource(getResources(),R.drawable.img_noti_each);
        Bitmap b7= BitmapFactory.decodeResource(getResources(),R.drawable.img_noti_each2);
        Bitmap b8= BitmapFactory.decodeResource(getResources(),R.drawable.img_noti_each);
        String off1="20% off price on purchse before 15 June";
        String time1="2 days befour";
        String extra1="get it now";
        String head1="The Alchemist",head2="Chemistry",head3="Physics",head4="Maths",head5="Hindi",head6="english",head7="geo",head8="bio";

        notificationList.add(new NotificationPojo(b1,head1,off1,extra1,time1));
        notificationList.add(new NotificationPojo(b2,head2,off1,extra1,time1));
        notificationList.add(new NotificationPojo(b3,head3,off1,extra1,time1));
        notificationList.add(new NotificationPojo(b4,head4,off1,extra1,time1));
        notificationList.add(new NotificationPojo(b5,head5,off1,extra1,time1));
        notificationList.add(new NotificationPojo(b6,head6,off1,extra1,time1));
        notificationList.add(new NotificationPojo(b7,head7,off1,extra1,time1));
        notificationList.add(new NotificationPojo(b8,head8,off1,extra1,time1));
        return notificationList;
    }
    Toolbar toolbar;
    NotificationAdaptor notificationAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        notificationList=new ArrayList<>();
        rv=findViewById(R.id.recycle1);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NotificationActivity.this,"Notofication activity",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        setSupportActionBar(toolbar);

        getNotificationList();

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        notificationAdaptor=new NotificationAdaptor(this,notificationList);
        rv.setAdapter(notificationAdaptor);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu,menu);
        MenuItem mSearch = menu.findItem(R.id.search1);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                notificationAdaptor.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.like1:
                startActivity(new Intent(NotificationActivity.this,wishlist.class));
                Toast.makeText(this,"Favorite",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void back(View view) {
        startActivity(new Intent(NotificationActivity.this,MainActivity2.class));
    }
}