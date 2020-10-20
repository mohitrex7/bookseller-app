package com.example.login;

public class Product {
    private String product_name;
    private String product_Publication;
    private double rating;
    private double product_price;
    private String image;

    public Product(String product_name, String product_Publication, double rating,double product_price, String image) {
        this.product_name = product_name;
        this.product_Publication = product_Publication;
        this.rating = rating;
        this.product_price = product_price;
        this.image = image;
    }


    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_Publication() {
        return product_Publication;
    }

    public double getRating() {
        return rating;
    }

    public double getProduct_price() {
        return product_price;
    }

    public String getImage() {
        return image;
    }
}
