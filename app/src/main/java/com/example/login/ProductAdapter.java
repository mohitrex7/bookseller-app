package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{
    private Context mCtx;
    private List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        Product product = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);

        holder.bookname.setText(product.getProduct_name());
        holder.publication.setText(product.getProduct_Publication());
        holder.Rating.setText(String.valueOf(product.getRating()));
        holder.Price.setText(String.valueOf(product.getProduct_price()));

        //Remove

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                productList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,productList.size());

                Toast.makeText(mCtx,"Removed : ",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

       public TextView bookname, publication, Rating, Price;
       public ImageView imageView;
       public ImageView remove;

        public ProductViewHolder(final View itemView ) {
            super(itemView);

            bookname = itemView.findViewById(R.id.bookname);
            publication = itemView.findViewById(R.id.publication);
            Rating = itemView.findViewById(R.id.textViewRating);
            Price = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);

            //Remove

            remove = itemView.findViewById(R.id.delete);




        }
    }
}

