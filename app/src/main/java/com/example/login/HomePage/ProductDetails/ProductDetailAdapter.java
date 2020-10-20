package com.example.login.HomePage.ProductDetails;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ProductDetailAdapter extends FragmentPagerAdapter {
    private int totalTabs;

    public ProductDetailAdapter(@NonNull FragmentManager fm, int totalTabs) {
        super(fm, totalTabs);
        this.totalTabs=totalTabs;
    }
//    public ProductDetailAdapter(FragmentManager fm,int totalTabs) {
//        super(fm);
//        this.totalTabs=totalTabs;
//    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                ProductDescriptionFragment productDescriptionFragment1=new ProductDescriptionFragment();
                return productDescriptionFragment1;
            case 1:
                ProductDescriptionFragment productDescriptionFragment2=new ProductDescriptionFragment();
                return productDescriptionFragment2;
            case 2:
                ProductDescriptionFragment returnPolicyFragment=new ProductDescriptionFragment();
                return returnPolicyFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
