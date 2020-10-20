package com.example.login;

public class CartProduct {
    public int txtQuantity;
    private int product_id;
   private String product_name;
   private double product_price;
   private int product_quantity;
   private int product_discount;
   private String image_id;

    public CartProduct(int product_id, String product_name, double product_price, int product_quantity, int product_discount, String image_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price= product_price;
        this.product_quantity =product_quantity;
        this.product_discount =product_discount;
        this.image_id = image_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public int getProduct_quantity() {
        return product_quantity;
    }

    public int getProduct_discount() {
        return product_discount;
    }

    public String getImage_id() {
        return image_id;
    }
}

