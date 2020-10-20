package com.example.login.HomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.mViewHolder> {
    private ArrayList<ExampleData> books;
    private onItemClickListener mListener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public static class mViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView title,author,price;
        public RatingBar ratingBar;
        public FloatingActionButton fav;

        public mViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.bookImageView);
            title = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.bookAuthor);
            price = itemView.findViewById(R.id.bookPrice);
            ratingBar = itemView.findViewById(R.id.bookRating);
            fav = itemView.findViewById(R.id.favorite);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public RecyclerViewAdapter(ArrayList<ExampleData> data){
        books = data;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card_view,parent,false);
        mViewHolder viewHolder = new mViewHolder(v,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        ExampleData bookdetails = books.get(position);
        Picasso.get().load(bookdetails.getUrl()).into(holder.imageView);
        holder.title.setText(bookdetails.getTitle());
        holder.author.setText(bookdetails.getAuthor());
        holder.price.setText(String.valueOf(bookdetails.getPrice()));
        holder.ratingBar.setRating(bookdetails.getRating());


    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
