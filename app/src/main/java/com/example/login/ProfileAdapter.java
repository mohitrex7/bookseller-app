package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder> {
    private Context mCtx;
    private List<Profile> profileList;


    public ProfileAdapter(Context mCtx, List<Profile> profileList) {
        this.mCtx = mCtx;
        this.profileList = profileList;
    }
    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.detaillist, null);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ProfileViewHolder holder, int position) {

        Profile profile = profileList.get(position);

        //loading the image
        Glide.with(mCtx)
                .load(profile.getImage())
                .into(holder.image);

        holder.title.setText(profile.getTitle());
        holder.desc.setText(profile.getDesc());


    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {

        public TextView title,desc;
        public ImageView image;
        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.maintitle);
            desc = itemView.findViewById(R.id.subtitle);
            image = itemView.findViewById(R.id.icons);
        }
    }
}
