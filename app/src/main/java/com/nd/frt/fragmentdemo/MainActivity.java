package com.nd.frt.fragmentdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.nd.frt.fragmentdemo.fragment.DetailFragment;
import com.nd.frt.fragmentdemo.fragment.ListFragment;
import com.nd.frt.fragmentdemo.model.UserInfosResponse;

public class MainActivity extends AppCompatActivity implements DetailFragment.OnEditUserInfo {

    private ListFragment mListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListFragment = new ListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.flContent, mListFragment)
                .commit();
    }

    @Override
    public void onSuccess(UserInfosResponse.UserInfoResponse userInfo, int index) {
        mListFragment.edit(userInfo, index);
    }
}
