
package com.ckinfotech.investor.model.emiRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmiRequest {

    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("data")
    @Expose
    private EmiRequestData data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public EmiRequestData getEmiRequestData() {
        return data;
    }

    public void setEmiRequestData(EmiRequestData data) {
        this.data = data;
    }

}
