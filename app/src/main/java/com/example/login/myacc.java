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

public class myacc extends AppCompatActivity {
    ListView listView;
    String mtitle[] = {"Saved Cards","Profile Details","Settings"};
    String mdescription[] = {"Save your card for faster checkout","Change your profile details & password","Manage notification & app settings"};
    int image[]={R.drawable.savedcards,R.drawable.savedaddress,R.drawable.account,R.drawable.settings};

    private ImageView img,imagee;
    private static final int PICK_IMAGE = 1;
    Uri imageuri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myacc);
        //listview
        listView = findViewById(R.id.list);

        MyAdapter myadapter = new MyAdapter(this,mtitle,mdescription,image);
        listView.setAdapter(myadapter);

        //set onclick listener on listview

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                if (position == 0){
                    Intent intent = new Intent(myacc.this,Savedcards.class);
                    startActivity(intent);
                    finish();
                }
                if (position == 1){
                    Intent intent1 = new Intent(myacc.this,profiledetails.class);
                    startActivity(intent1);
                    finish();
                    Toast.makeText(myacc.this,"Profile Details",Toast.LENGTH_LONG).show();
                }
                if (position == 2){
                    startActivity(new Intent(myacc.this,Settings.class));
                    finish();
                    Toast.makeText(myacc.this,"Settings",Toast.LENGTH_LONG).show();
                }
            }
        });


        img = findViewById(R.id.edit);
        imagee = findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery,"Select Picture"),PICK_IMAGE);
            }
        });
    }

    public void back(View view) {
        startActivity(new Intent(myacc.this,MainActivity2.class));
        finish();
    }

    //list view

    class MyAdapter extends ArrayAdapter<String>{
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

    //profile image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){
            imageuri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageuri);
                imagee.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }
}