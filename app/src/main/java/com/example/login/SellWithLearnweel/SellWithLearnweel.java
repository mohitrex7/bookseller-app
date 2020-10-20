package com.example.login.SellWithLearnweel;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.login.R;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import java.util.ArrayList;

public class SellWithLearnweel extends AppCompatActivity {

    ArrayList<Bitmap> bitmaps;
    ViewPager vp;
    DotsIndicator dotsIndicator;

    String[] PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };

    private Button sell;
    private EditText title,price,author,details;
    private RadioGroup condition;

    Uri imageUri;
    ArrayList<Uri> imageURIs;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_with_learnweel);



        bitmaps = new ArrayList<>();

        sell = findViewById(R.id.sellButton);
        title = findViewById(R.id.bookTitle);
        price = findViewById(R.id.bookPrice);
        author = findViewById(R.id.bookAuthor);
        details = findViewById(R.id.details);
        condition = findViewById(R.id.conditionRadioGroup);

        dotsIndicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        imageURIs = new ArrayList<>();

        if ((ContextCompat.checkSelfPermission(SellWithLearnweel.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(SellWithLearnweel.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) ||
                (ContextCompat.checkSelfPermission(SellWithLearnweel.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) )  {

            requestPermissions( PERMISSIONS, 1);

        }
        Button clickImage = findViewById(R.id.addImage);
        clickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "New Picture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                imageURIs.add(imageUri);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, 99);
            }
        });

        vp = findViewById(R.id.viewPage);
        AdapterPagerImageSlider adapter = new AdapterPagerImageSlider(SellWithLearnweel.this, bitmaps);
        vp.setAdapter(adapter);
        dotsIndicator.setViewPager(vp);

        sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sell();
            }
        });

    }

    private void sell() {
        if(!title.getText().toString().equals("") && !price.getText().toString().equals("") &&
                !author.getText().toString().equals("") && !details.getText().toString().equals("")) {
            String titleString = title.getText().toString().trim();
            String priceString = price.getText().toString().trim();
            String authorString = author.getText().toString().trim();
            String detailsString = details.getText().toString().trim();

            int selected = condition.getCheckedRadioButtonId();
            RadioButton radioButton = findViewById(selected);

            if(radioButton!=null) {

                String condition = radioButton.getText().toString();

                String[] recipients = {"vivekjangir2321@gmail.com"};
                String subject = "Sell with learnweel";
                String message = "Title : " + titleString + "\nPrice : " + priceString + "\nAuthor : " + authorString + "\ncondition : " + condition + "\nDescription : " + detailsString;

                Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                intent.putExtra(Intent.EXTRA_EMAIL, recipients);
                intent.putExtra(Intent.EXTRA_SUBJECT, subject);
                intent.putExtra(Intent.EXTRA_TEXT, message);

//                for(Uri uri:imageURIs) {
//                    if (uri != null) {
//                        intent.putExtra();
//                    }
//                }
                if(imageURIs!=null) {
                    intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, imageURIs);
                    intent.setType("image/png");
                }

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, "Choose an email client"));
            }
            else {
                Toast.makeText(this, "Please fill in all the required details", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Please fill in all the required details", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 99:
                if (requestCode == 99)
                    if (resultCode == Activity.RESULT_OK) {
                        try {
                            Bitmap capturedimage = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);

                            bitmaps.add(capturedimage);
                            AdapterPagerImageSlider adapter = new AdapterPagerImageSlider(SellWithLearnweel.this, bitmaps);
                            vp.setAdapter(adapter);
                            dotsIndicator.setViewPager(vp);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
        }
    }
}