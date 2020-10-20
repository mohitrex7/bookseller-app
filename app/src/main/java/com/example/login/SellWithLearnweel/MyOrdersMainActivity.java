package com.example.login.SellWithLearnweel;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.login.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MyOrdersMainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_orders_activity_main);

        TabLayout tabLayout=findViewById(R.id.tab_bar);
        TabItem processing=findViewById(R.id.processing_tab);
        TabItem delivered=findViewById(R.id.delivered_tab);
        TabItem cancelled=findViewById(R.id.cancelled_tab);
        final ViewPager viewPager=findViewById(R.id.view_pager);

        PagerAdapter pagerAdapter= new
                PagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pagerAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}