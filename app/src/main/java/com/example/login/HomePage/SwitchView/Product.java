package com.example.login.HomePage.SwitchView;

public class Product {
    private int product_id;
    private int catagory_id;
    private int sub_cat_id;
    private String product_name;
    private String product_desc;
    private int product_quantity;
    private String product_edition;
    private String product_price;
    private String product_publication;
    private String product_offer;
    private String product_author;
    private String pic_url;

   /* public Product(int product_id, String product_name, String product_price, String product_publication, String pic_url) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_publication = product_publication;
        this.pic_url = pic_url;
    }
*/
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getProduct_publication() {
        return product_publication;
    }

    public void setProduct_publication(String product_publication) {
        this.product_publication = product_publication;
    }

    public String getPic_url() {
        return pic_url;
    }

    public void setPic_url(String pic_url) {
        this.pic_url = pic_url;
    }
}
