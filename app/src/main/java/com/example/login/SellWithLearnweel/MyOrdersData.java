package com.example.login.SellWithLearnweel;

public class MyOrdersData {
    private String mImageUrl;
    private int mOrdernum;
    private int mAmount;
    private String mTrackingid;
    private String mOrderdate;
    private String mShipping;
    private String mTitle;
    private String mStatus;


    public MyOrdersData(String imageUrl, int ordernum, int amount, String trackingid, String orderdate, String shipping, String title, String status){
        mImageUrl=imageUrl;
        mOrdernum=ordernum;
        mAmount=amount;
        mTrackingid= trackingid;
        mOrderdate= orderdate;
        mShipping= shipping;
        mTitle=title;
        mStatus=status;
    }

    public void changeText1(String text){

    }

    public String getmImageUrl(){
        return mImageUrl;
    }

    public int getmOrdernum(){
        return mOrdernum;
    }
    public int getmAmount(){
        return mAmount;
    }
    public String getmTrackingid(){
        return  mTrackingid;
    }
    public String getmOrderdate(){
        return mOrderdate;
    }
    public String getmShipping(){
        return mShipping;
    }
    public String getmTitle(){
        return mTitle;
    }
    public String getmStatus(){return  mStatus;}

}
