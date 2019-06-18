package com.nd.frt.fragmentdemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.fragment.DetailFragment;
import com.nd.frt.fragmentdemo.model.UserInfosResponse;
import com.nd.frt.fragmentdemo.viewholder.UserViewHolder;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    public static final String TAG = UserAdapter.class.getSimpleName();

    private List<UserInfosResponse.UserInfoResponse> mUserInfos;

    public UserAdapter(List<UserInfosResponse.UserInfoResponse> userInfos) {
        mUserInfos = userInfos;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        View inflate = layoutInflater.inflate(R.layout.item_user, viewGroup, false);
        Log.d(TAG, "onCreateViewHolder");
        return new UserViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, @SuppressLint("RecyclerView") final int position) {
        Log.d(TAG, "onBindViewHolder");
        final UserInfosResponse.UserInfoResponse userInfo = mUserInfos.get(position);
        Glide.with(userViewHolder.mIvAvatar).load(userInfo.picture.medium).into(userViewHolder.mIvAvatar);
        userViewHolder.mTvUserName.setText(userInfo.name.getName());
        userViewHolder.mTvEmail.setText(userInfo.email);
        userViewHolder.mIvAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), v.getContext().getString(R.string.hello), Toast.LENGTH_LONG).show();
            }
        });
        userViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                ((FragmentActivity) context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.flDetailContent, DetailFragment.newInstance(userInfo, position))
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUserInfos.size();
    }

    public void edit(UserInfosResponse.UserInfoResponse userInfo, int index) {
        mUserInfos.set(index, userInfo);
        notifyDataSetChanged();
    }
}
