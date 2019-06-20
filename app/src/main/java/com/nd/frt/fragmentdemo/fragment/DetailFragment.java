package com.nd.frt.fragmentdemo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.model.UserInfo;
import com.nd.frt.fragmentdemo.model.UserInfosResponse;

public class DetailFragment extends Fragment {

    public static final String PARAM_USER_INFO = "user_info";
    public static final String PARAM_USER_INDEX = "user_index";

    public static DetailFragment newInstance(UserInfosResponse.UserInfoResponse userInfo, int index) {
        Bundle args = new Bundle();
        DetailFragment fragment = new DetailFragment();
        args.putSerializable(PARAM_USER_INFO, userInfo);
        args.putInt(PARAM_USER_INDEX, index);
        fragment.setArguments(args);
        return fragment;
    }

    private UserInfosResponse.UserInfoResponse mUserInfo;
    private int mIndex;
    private OnEditUserInfo mEditUserInfo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        assert view != null;
        final EditText etUserName = view.findViewById(R.id.etUsername);
        etUserName.setHint(mUserInfo.name.getName());
        view.findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserInfo.email = etUserName.getText().toString();
                mEditUserInfo.onSuccess(mUserInfo, mIndex);
            }
        });
    }

    @Override
    public void setArguments(@Nullable Bundle args) {
        super.setArguments(args);
        assert args != null;
        mUserInfo = (UserInfosResponse.UserInfoResponse) args.getSerializable(PARAM_USER_INFO);
        mIndex = args.getInt(PARAM_USER_INDEX);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnEditUserInfo) {
            mEditUserInfo = ((OnEditUserInfo) context);
        }
    }

    public interface OnEditUserInfo {
        void onSuccess(UserInfosResponse.UserInfoResponse userInfo, int index);
    }
}
