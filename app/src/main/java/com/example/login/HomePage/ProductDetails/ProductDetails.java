package com.example.login.HomePage.ProductDetails;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.login.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {

    List<String> productImages=new ArrayList<>();

    //private List<String> ImageId;

    //private ArrayList<Product> productList;


    private ViewPager productImagesViewPager;
    private TextView productTitle;
    private TextView avgRatingMiniView;
    private TextView productPrice;
    private TextView cuttedPrice;
    private TabLayout viewPagerIndicator;

    private ViewPager productDetailsViewpager;
    private TabLayout productDetailTabLayout;

    //Rating Layout
    private LinearLayout rateNowContainer;

    private static boolean ALREADY_ADDED_TO_WISHLIST=false;
    private FloatingActionButton addToWishlistButton;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);



        Intent intent = getIntent();
        String Id=intent.getStringExtra("productID");
        String ImageURL=intent.getStringExtra("productImageURL");
        String Title=intent.getStringExtra("productTitle");
        String Rating=intent.getStringExtra("productRating");
        String Price=intent.getStringExtra("productPrice");


//        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        productImagesViewPager=findViewById(R.id.product_image_viewpager);
        viewPagerIndicator=findViewById(R.id.veiwpager_indicator);
        addToWishlistButton=findViewById(R.id.add_to_wishlist_button);
        productDetailsViewpager=findViewById(R.id.product_detail_viewpager);
        productDetailTabLayout=findViewById(R.id.product_detail_tabLayout);
        productTitle=findViewById(R.id.product_title);
        avgRatingMiniView=findViewById(R.id.tv_product_rating_miniview);
        productPrice=findViewById(R.id.product_price);
        cuttedPrice=findViewById(R.id.cutted_price);

        //setOnProductDetail(ProductId);



        //ImageId= new JSONImageIDDownloader(this).retrieve(ProductId);
        //productImages=new JSONImagesDownloader(this).retrieve(ImageId);
        //productList=new JSONDownloader(this).retrieve(ProductId);



        //final Product obj= (Product) this.getItem(id);


        //Toast.makeText(this,productList.size()+" ",Toast.LENGTH_LONG).show();

        productImages.add(ImageURL);
        productImages.add(ImageURL);
        productImages.add(ImageURL);

        ProductImagesAdapter productImagesAdapter=new ProductImagesAdapter(productImages);
        //Toast.makeText(this,productList.get(Integer.parseInt(Id)).getProduct_name(),Toast.LENGTH_LONG).show();
        productImagesViewPager.setAdapter(productImagesAdapter);

        productTitle.setText(Title);
        avgRatingMiniView.setText(Rating);
        productPrice.setText("Rs."+Price+"/-");
        cuttedPrice.setText("Rs."+Price+"/-");


        viewPagerIndicator.setupWithViewPager(productImagesViewPager,true);

        addToWishlistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ALREADY_ADDED_TO_WISHLIST){
                    ALREADY_ADDED_TO_WISHLIST=false;
                    //Code for removal of item from wishlist
                    addToWishlistButton.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFFF")));
                }else{
                    ALREADY_ADDED_TO_WISHLIST=true;
                    //Code for removal of item from wishlist
                    addToWishlistButton.setSupportBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#F10000")));
                }
            }
        });

        productDetailsViewpager.setAdapter(new ProductDetailAdapter(getSupportFragmentManager(),productDetailTabLayout.getTabCount()));
        productDetailsViewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(productDetailTabLayout));
        productDetailTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                productDetailsViewpager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //rating layout
        rateNowContainer=findViewById(R.id.rate_now_container);
        for(int i=0;i<rateNowContainer.getChildCount();i++)
        {
            final int starPosition=i;
            rateNowContainer.getChildAt(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setRating(starPosition);
                }
            });
        }



    }


    private void setRating(int starPosition) {
        for(int i=0;i<rateNowContainer.getChildCount();i++)
        {
            ImageView starBtn=(ImageView)rateNowContainer.getChildAt(i);
            starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#bebebe")));
            if(i<=starPosition){
                starBtn.setImageTintList(ColorStateList.valueOf(Color.parseColor("#ffbb00")));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_and_cart_icon,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)    {
        int id=item.getItemId();

        if(id==android.R.id.home){
            finish();
            return true;
        }else if(id==R.id.main_search_icon){
            return true;
        }else if(id==R.id.main_cart_icon){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}