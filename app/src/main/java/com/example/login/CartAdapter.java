package com.example.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ProductViewHolder> {


    private Context mCtx;
    public static List<CartProduct> productList;
    //OnItemClickListner mListner;
    int quantity;

    public CartAdapter(Context mCtx, List<CartProduct> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.cart_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ProductViewHolder holder, final int position) {
        CartProduct product = productList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(product.getImage_id())
                .into(holder.imageView);

        holder.textViewName.setText(product.getProduct_name());
        holder.textViewPrice.setText(String.valueOf("RS "+product.getProduct_price()));
        holder.textViewDiscount.setText(String.valueOf("RS "+product.getProduct_discount()+"off"));
        //holder.txtQuantity.setText(String.valueOf(product.getProduct_quantity()));
        holder.delete_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                productList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,productList.size());
                Toast.makeText(mCtx,"Removed :", Toast.LENGTH_LONG).show();
            }
        });
        quantity = Integer.parseInt((String.valueOf(product.getProduct_quantity())));

        if (quantity == 1) {
            holder.txtQuantity.setText(String.valueOf(product.getProduct_quantity()));
        }
        holder.btnIncrease.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(String.valueOf(holder.txtQuantity.getText()));
                count++;
                holder.txtQuantity.setText("" + count);
            }
        });

        holder.btnDecrease.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                int count= Integer.parseInt(String.valueOf(holder.txtQuantity.getText()));
                if (count == 1) {
                    holder.txtQuantity.setText("1");
                } else {
                    count -= 1;
                    holder.txtQuantity.setText("" + count);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public double grandtotal(List<CartProduct> productList) {
        int totalPrice =880;
        for (int i = 0; i < CartAdapter.productList.size(); i++) {
            totalPrice += CartAdapter.productList.get(i).getProduct_price();
        }
        return totalPrice;
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {


        TextView textViewName, txtQuantity, textViewDiscount, textViewPrice;
        ImageView imageView,btnIncrease,btnDecrease;
        Button delete_button;

        public ProductViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            txtQuantity = itemView.findViewById(R.id.txtQuantity);
            textViewDiscount = itemView.findViewById(R.id.textViewDiscount);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);
            btnIncrease = itemView.findViewById(R.id.btnIncrease);
            btnDecrease = itemView.findViewById(R.id.btnDecrease);
            delete_button=itemView.findViewById(R.id.delete_button);
        }
    }
}

