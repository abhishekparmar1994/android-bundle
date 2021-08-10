
package com.ckinfotech.investor.model.SignUp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SignUp {

    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("data")
    @Expose
    private SignUpData data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public SignUpData getSignUpData() {
        return data;
    }

    public void setSignUpData(SignUpData data) {
        this.data = data;
    }

}
