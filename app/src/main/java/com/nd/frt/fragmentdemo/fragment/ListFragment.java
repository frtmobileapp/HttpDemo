package com.nd.frt.fragmentdemo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.adapter.UserAdapter;
import com.nd.frt.fragmentdemo.model.UserInfosResponse;
import com.nd.frt.fragmentdemo.service.UserInfoService;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private UserAdapter mUserAdapter;
    private Disposable mDisposable;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSwipeRefreshLayout = view.findViewById(R.id.srUsers);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mSwipeRefreshLayout.setRefreshing(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
        refreshData();
    }

    private void refreshData() {
        mDisposable = Single.create(new SingleOnSubscribe<List<UserInfosResponse.UserInfoResponse>>() {
            @Override
            public void subscribe(SingleEmitter<List<UserInfosResponse.UserInfoResponse>> emitter) throws Exception {
                UserInfoService userInfoService = new UserInfoService();
                final List<UserInfosResponse.UserInfoResponse> userInfos = userInfoService.getUserInfos(getContext());
                emitter.onSuccess(userInfos);
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<UserInfosResponse.UserInfoResponse>>() {
                    @Override
                    public void accept(List<UserInfosResponse.UserInfoResponse> userInfoResponses) throws Exception {
                        mSwipeRefreshLayout.setRefreshing(false);
                        mUserAdapter = new UserAdapter(userInfoResponses);
                        View view = getView();
                        assert view != null;
                        RecyclerView recyclerView = view.findViewById(R.id.rvRecyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                        recyclerView.setAdapter(mUserAdapter);
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    public void edit(UserInfosResponse.UserInfoResponse userInfo, int index) {
        mUserAdapter.edit(userInfo, index);
    }
}
