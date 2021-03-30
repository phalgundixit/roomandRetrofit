package com.example.roomandretrofit.retrofit;

import com.google.gson.annotations.SerializedName;

public class hrms {

    @SerializedName("empName")
    private String empName;



    @SerializedName("UserName")
    private String UserName;



    public String getEmpName() {
        return empName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    @SerializedName("Password")
    private String Password;

    public hrms(String empName, String userName, String password) {
        this.empName = empName;
        UserName = userName;
        Password = password;
    }
}
