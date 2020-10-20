package com.example.login.SellWithLearnweel;

import android.content.Context;
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

public class MyOrdersRecyclerViewAdapter extends RecyclerView.Adapter<MyOrdersRecyclerViewAdapter.mViewHolder> {
    private Context mContext;
    private ArrayList<MyOrdersData> mTotalOrders;
    private int ViewId;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        mListener=listener;
    }


    public class mViewHolder extends RecyclerView.ViewHolder{
        public ImageView mImageView;
        public TextView textViewOrdernum;
        public TextView textViewAmount;
        public TextView textViewTrackingid;
        public TextView textViewOrderdate;
        public TextView textViewShipping;
        public TextView textViewTitle;
        public TextView textViewStatus;
        public mViewHolder(@NonNull final View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image_view);
            textViewOrdernum=itemView.findViewById(R.id.text_view_ordernum);
            textViewAmount=itemView.findViewById(R.id.text_view_amount);
            textViewTrackingid=itemView.findViewById(R.id.trackid_odetails);
            textViewOrderdate=itemView.findViewById(R.id.odate_odetails);
            textViewShipping= itemView.findViewById(R.id.shipadd_odetails);
            textViewTitle =itemView.findViewById(R.id.booktitle_odetails);
            textViewStatus=itemView.findViewById(R.id.text_view_status);
itemView.setOnClickListener(new View.OnClickListener() {
                @Override

                public void onClick(View view) {
                    if (listener!= null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }

    public MyOrdersRecyclerViewAdapter(ArrayList<MyOrdersData> data, int id){

        mTotalOrders = data;
        ViewId=id;
    }

    @NonNull
    @Override
    public mViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(ViewId,parent,false);
        mViewHolder viewHolder = new mViewHolder(v,mListener);
        return viewHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull mViewHolder holder, int position) {
        MyOrdersData currentItem= mTotalOrders.get(position);

        String imageUrl= currentItem.getmImageUrl();
        int ordernumber=currentItem.getmOrdernum();
        int amount=currentItem.getmAmount();
        String trackingid= currentItem.getmTrackingid();
        String orderdate = currentItem.getmOrderdate();
        String shipping= currentItem.getmShipping();
        String title=currentItem.getmTitle();
        String status = currentItem.getmStatus();


        holder.textViewOrdernum.setText("Order number:"+ ordernumber);

        holder.textViewAmount.setText("Total Amount:"+amount);

        if(ViewId!=R.layout.delivered_order_list) {
            holder.textViewStatus.setText("Status:" + status);
        }
        Picasso.get().load(imageUrl).into(holder.mImageView);



    }

    @Override
    public int getItemCount() {
        return mTotalOrders.size();
    }
}
