package com.example.login.HomePage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MaxViewedRecyclerViewAdapter extends RecyclerView.Adapter<MaxViewedRecyclerViewAdapter.mMaxViewHolder> {
    private ArrayList<ExampleData> books;
    private RecyclerViewAdapter.onItemClickListener mListener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnClickListener(RecyclerViewAdapter.onItemClickListener listener){
        mListener = listener;
    }


    public static class mMaxViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView title;


        public mMaxViewHolder(@NonNull View itemView, final RecyclerViewAdapter.onItemClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.maxBookImageView);
            title = itemView.findViewById(R.id.maxBookTitle);

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

    public MaxViewedRecyclerViewAdapter(ArrayList<ExampleData> data){
        books = data;
    }

    @NonNull
    @Override
    public mMaxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.max_viewed_card_view,parent,false);
        mMaxViewHolder viewHolder = new mMaxViewHolder(v,mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull mMaxViewHolder holder, int position) {
        ExampleData bookdetails = books.get(position);
        Picasso.get().load(bookdetails.getUrl()).into(holder.imageView);
        holder.title.setText(bookdetails.getTitle());
    }

    @Override
    public int getItemCount() {
        return books.size();
    }
}
