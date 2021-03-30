package com.example.roomandretrofit.retrofit;

import com.google.gson.annotations.SerializedName;

public class commentpojo {


    //@SerializedName is used to define the key of the json object which is fetched from the url having key-value pair, and UserId is used to access the key by getters.
    @SerializedName("postId")
    private int postId;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;


    @SerializedName("body")
    private String text;

    public int getPostId() {
        return postId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getText() {
        return text;
    }
}
