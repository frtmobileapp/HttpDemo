package com.nd.frt.fragmentdemo.service;


import android.content.Context;

import com.google.gson.Gson;
import com.nd.frt.fragmentdemo.model.UserInfosResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class UserInfoService implements IUserInfo {

    @Override
    public List<UserInfosResponse.UserInfoResponse> getUserInfos(Context context) {
        Request request = new Request.Builder()
                .url("https://randomuser.me/api/?results=50")
                .get()
                .build();
        OkHttpClient okHttpClient = new OkHttpClient();
        try {
            Response response = okHttpClient.newCall(request)
                    .execute();
            ResponseBody body = response.body();
            assert body != null;
            String bodyString = body.string();
            System.out.println(bodyString);
            UserInfosResponse userInfosResponse = new Gson().fromJson(bodyString, UserInfosResponse.class);
            return userInfosResponse.results;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


}
