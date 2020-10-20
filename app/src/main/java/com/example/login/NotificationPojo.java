package com.example.login;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

public class NotificationPojo {
    Bitmap image;
    String heading,offer,extraText,time;

    public NotificationPojo(Bitmap image, String heading, String offer, String extraText, String time) {
        this.image = image;
        this.heading = heading;
        this.offer = offer;
        this.extraText = extraText;
        this.time = time;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getHeading() {
        return heading;
    }

    public String getOffer() {
        return offer;
    }

    public String getExtraText() {
        return extraText;
    }

    public String getTime() {
        return time;
    }
}
