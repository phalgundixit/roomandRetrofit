package com.example.roomandretrofit.retrofit;

import com.google.gson.annotations.SerializedName;

public class weather {
    @SerializedName("userId")
    private int userId;

    @SerializedName("id")
    private Integer id;

    @SerializedName("title")
    private String title;

    @SerializedName("body")
    private String text;

    @SerializedName("empName")
    private String empName;
}
