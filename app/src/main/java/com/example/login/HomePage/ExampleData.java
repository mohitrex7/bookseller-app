package com.example.login.HomePage;

import android.graphics.Bitmap;

public class ExampleData {
    private String mUrl;
    private Bitmap mImage;
    private String mTitle,mAuthor;
    private int mPrice;
    private float mRating;
    private boolean mFav;
    private int mid;

    public ExampleData(int id, String url, String title, String author, int price, float rating, boolean fav) {
        mid = id;
        mUrl = url;
        mTitle = title;
        mAuthor = author;
        mPrice = price;
        mRating = rating;
        mFav = fav;
    }
    public String getUrl() {
        return mUrl;
    }
    public String getTitle() {
        return mTitle;
    }
    public String getAuthor() {
        return mAuthor;
    }
    public int getPrice() {
        return mPrice;
    }
    public float getRating() {
        return mRating;
    }
    public boolean getFav() {
        return mFav;
    }
    public int getid(){
        return mid;
    };
}
