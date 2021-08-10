
package com.ckinfotech.investor.model.GeneralService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralService {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private GeneralServiceData data;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GeneralServiceData getGeneralServiceData() {
        return data;
    }

    public void setGeneralServiceData(GeneralServiceData data) {
        this.data = data;
    }

}
