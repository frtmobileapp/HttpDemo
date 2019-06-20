package com.nd.frt.fragmentdemo.model;

import java.io.Serializable;
import java.util.List;

public class UserInfosResponse {

    public List<UserInfoResponse> results;

    public static class UserInfoResponse implements Serializable {
        public Name name;
        public String email;
        public Picture picture;
    }

    public static class Name implements Serializable {
        String title;
        String first;
        String last;

        public String getName() {
            return title + first + last;
        }
    }

    public static class Picture implements Serializable {
        public String medium;
    }
}
