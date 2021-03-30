package com.example.roomandretrofit.retrofit;

import com.google.gson.annotations.SerializedName;

public class pojo {


    //@SerializedName is used to define the key of the json object which is fetched from the url having key-value pair, and UserId is used to access the key by getters.
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

    public pojo(int userId, String title, String text) {
        this.userId = userId;
        this.title = title;
        this.text = text;
    }

    public int getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getEmpName() {
        return empName;
    }

}
