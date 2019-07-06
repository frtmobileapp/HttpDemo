package com.nd.frt.fragmentdemo.service;

import android.content.Context;
import com.nd.frt.fragmentdemo.model.UserInfosResponse;
import java.util.List;

public interface IUserInfo {

    List<UserInfosResponse.UserInfoResponse> getUserInfos(Context context);

}
