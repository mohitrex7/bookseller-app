package com.example.login.HomePage.SwitchView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.HomePage.ProductDetails.ProductDetails;
import com.example.login.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {
    Context c;
    ArrayList<Product> products;
    public ArrayList<Product> currentList;
    FilterHelperForList filterHelperForList;

    public ListViewAdapter(Context c, ArrayList<Product> products) {
        this.c = c;
        this.products = products;
        this.currentList=products;
    }
    @Override
    public int getCount() {
        return products.size();
    }
    @Override
    public Object getItem(int i) {
        return products.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null)
        {
            view= LayoutInflater.from(c).inflate(R.layout.list_item,viewGroup,false);
        }

        TextView bookName = view.findViewById(R.id.bookName);
        TextView publication = view.findViewById(R.id.publication);
        ImageView bookImage = view.findViewById(R.id.book_image);
        TextView price=view.findViewById(R.id.price);

        final Product p= (Product) this.getItem(i);

        bookName.setText(p.getProduct_name());
        publication.setText(p.getProduct_publication());
        price.setText(p.getProduct_price());


        if(p.getPic_url() != null && p.getPic_url().length()>0)
        {
            Picasso.get().load(p.getPic_url()).placeholder(R.drawable.imageholder).into(bookImage);
        }else {
            Toast.makeText(c, "Empty Image URL", Toast.LENGTH_LONG).show();
            Picasso.get().load(R.drawable.imageholder).into(bookImage);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(c, p.getProduct_name(), Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(c, ProductDetails.class);
                intent.putExtra("productID",p.getProduct_id());
                intent.putExtra("productImageURL",p.getPic_url());
                intent.putExtra("productTitle",p.getProduct_name());
                intent.putExtra("productRating", String.valueOf(4.0));
                intent.putExtra("productPrice", String.valueOf(p.getProduct_price()));
                c.startActivity(intent);
            }
        });

        return view;
    }
    public void setProducts(ArrayList<Product> filteredProducts)
    {
        this.products=filteredProducts;
    }

    @Override
    public Filter getFilter() {
        if(filterHelperForList ==null)
        {
            filterHelperForList =new FilterHelperForList(currentList,this,c);
        }

        return filterHelperForList;
    }
    public void refresh(){
        notifyDataSetChanged();
    }
}
