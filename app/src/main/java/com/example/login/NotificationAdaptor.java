package com.example.login;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NotificationAdaptor extends RecyclerView.Adapter<NotificationAdaptor.NotificatonHolder> implements Filterable {
    List<NotificationPojo> notificationList,fullNotificationList;
    Context context;

    public NotificationAdaptor(Context context,List<NotificationPojo> notificationList) {
        this.notificationList = notificationList;
        this.fullNotificationList=new ArrayList<>(notificationList);
        this.context = context;
    }

    @NonNull
    @Override
    public NotificatonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.card_notification,parent,false);
        return new NotificatonHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificatonHolder holder, int position) {
        NotificationPojo notification=notificationList.get(position);
        holder.imageView.setImageBitmap(notification.image);
        holder.time.setText(notification.time);
        holder.heading.setText(notification.heading);
        holder.extraText.setText(notification.extraText);
        holder.offer.setText(notification.offer);
    }


    @Override
    public int getItemCount() {
        return notificationList.size();
    }

    @Override
    public Filter getFilter() {
        return f1;
    }
    Filter f1=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if(charSequence.length()==0||charSequence==null)
            {
                FilterResults filterResults=new FilterResults();
                filterResults.values=fullNotificationList;
                return filterResults;
            }else {

                List<NotificationPojo> filterList=new ArrayList<>();
                Log.d("aa ","aa:  ghjj"+fullNotificationList.size());
                for(int i=0;i<fullNotificationList.size();i++)
                {
                    NotificationPojo note=fullNotificationList.get(i);
                    String search=charSequence.toString().trim().toLowerCase();
                    String target=(note.heading+note.extraText+note.offer).trim().toLowerCase();
                    Log.d("aa ","aa:  ghjj 4 bb:"+search+" cc: "+target);

                    if(target.contains(search)){
                        Log.d("aa ","aa:  ghjj 4");
                        filterList.add(note);
                    }
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=filterList;
                return filterResults;
            }
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            notificationList.clear();
            notificationList.addAll((List<NotificationPojo>)filterResults.values);
            //Log.d("aaa","aa : "+notificationList.get(0).heading);
            notifyDataSetChanged();
        }
    };


    class NotificatonHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView heading,offer,extraText,time;
        public NotificatonHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.img_noti_each);
            offer=itemView.findViewById(R.id.offer1);
            extraText=itemView.findViewById(R.id.extraText1);
            heading=itemView.findViewById(R.id.heading1);
            time=itemView.findViewById(R.id.time1);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

        }
    }
}
