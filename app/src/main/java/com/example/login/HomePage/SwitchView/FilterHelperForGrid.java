package com.example.login.HomePage.SwitchView;

import android.content.Context;
import android.widget.Filter;

import java.util.ArrayList;

public class FilterHelperForGrid extends Filter {
    ArrayList<Product> currentList;
    GridViewAdapter gridAdapter;
    Context c;

    public FilterHelperForGrid(ArrayList<Product> currentList, GridViewAdapter gridAdapter , Context c) {
        this.currentList = currentList;
        this.gridAdapter = gridAdapter;
        this.c=c;
    }

    /*
    - Perform actual filtering.
     */
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults filterResults=new FilterResults();

        if(constraint != null && constraint.length()>0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();

            //HOLD FILTERS WE FIND
            ArrayList<Product> foundFilters=new ArrayList<>();

            Product product=null;

            //ITERATE CURRENT LIST
            for (int i=0;i<currentList.size();i++)
            {
                product= currentList.get(i);

                //SEARCH
                if(product.getProduct_name().toUpperCase().contains(constraint) )
                {
                    //ADD IF FOUND
                    foundFilters.add(product);
                }
            }

            //SET RESULTS TO FILTER LIST
            filterResults.count=foundFilters.size();
            filterResults.values=foundFilters;
        }else
        {
            //NO ITEM FOUND.LIST REMAINS INTACT
            filterResults.count=currentList.size();
            filterResults.values=currentList;
        }

        //RETURN RESULTS

        return filterResults;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults filterResults) {
        gridAdapter.setProducts((ArrayList<Product>) filterResults  .values);
        gridAdapter.refresh();

    }
}