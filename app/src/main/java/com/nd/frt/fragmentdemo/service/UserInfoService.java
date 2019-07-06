package com.nd.frt.fragmentdemo.service;

import android.content.Context;

import com.google.gson.Gson;
import com.nd.frt.fragmentdemo.model.UserInfo;
import com.nd.frt.fragmentdemo.R;
import com.nd.frt.fragmentdemo.model.UserInfosResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
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
