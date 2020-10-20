package com.example.login;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class Settings extends AppCompatActivity {
    ListView listView;
    String mtitle[] = {"Notification","Legal"};
    String mdescription[] = {"Change your Notification Settings","Policy"};
    int image[]={R.drawable.notification,R.drawable.policy};
    private ImageView img;
    private static final int PICK_IMAGE = 1;
    Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        listView = findViewById(R.id.list);

        Settings.MyAdapter myAdapter = new Settings.MyAdapter(this,mtitle,mdescription,image);
        listView.setAdapter(myAdapter);

        //set onclick listener on listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0){
                    startActivity(new Intent(Settings.this,Notifications.class));
                    finish();

                    Toast.makeText(Settings.this,"Notification",Toast.LENGTH_LONG).show();

                }
                if (position == 1){

                    startActivity(new Intent(Settings.this,Policy.class));
                    finish();

                    Toast.makeText(Settings.this,"Policy",Toast.LENGTH_LONG).show();
                }

            }
        });



    }

    public void back(View view) {
        startActivity(new Intent(Settings.this,MainActivity2.class));
        finish();
    }

    //list view

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        String rtitle[];
        String rdescription[];
        int rimage[];

        MyAdapter(Context c,String title[],String description[],int images[]){
            super(c,R.layout.list,R.id.maintitle,title);
            this.context = c;
            this.rtitle = title;
            this.rdescription = description;
            this.rimage = images;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater= (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View list = layoutInflater.inflate(R.layout.list,parent,false);
            ImageView imageView = list.findViewById(R.id.icons);
            TextView textView1 = list.findViewById(R.id.maintitle);
            TextView textView = list.findViewById(R.id.subtitle);

            //setting the resources
            imageView.setImageResource(rimage[position]);
            textView1.setText(rtitle[position]);
            textView.setText(rdescription[position]);


            return list;
        }
    }
}