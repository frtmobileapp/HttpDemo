package com.nd.frt.fragmentdemo.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nd.frt.fragmentdemo.R;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public final ImageView mIvAvatar;
    public final TextView mTvUserName;
    public final TextView mTvEmail;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        mIvAvatar = itemView.findViewById(R.id.ivAvatar);
        mTvUserName = itemView.findViewById(R.id.tvUserName);
        mTvEmail = itemView.findViewById(R.id.tvEmail);
    }
}
