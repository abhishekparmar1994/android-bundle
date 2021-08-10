
package com.ckinfotech.investor.model.LoginUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginUserList {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("data")
    @Expose
    private LoginUserListData data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUserListData getData() {
        return data;
    }

    public void setData(LoginUserListData data) {
        this.data = data;
    }

}
