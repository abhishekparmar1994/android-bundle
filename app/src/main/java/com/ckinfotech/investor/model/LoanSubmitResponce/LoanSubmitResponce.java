
package com.ckinfotech.investor.model.LoanSubmitResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanSubmitResponce {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("message")
    @Expose

    private String result;
    @SerializedName("result")
    @Expose


    private String message;
    @SerializedName("data")
    @Expose
    private LoanSubmitData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoanSubmitData getloanSubmitData() {
        return data;
    }

    public void setloanSubmitData(LoanSubmitData data) {
        this.data = data;
    }


    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LoanSubmitData getData() {
        return data;
    }

    public void setData(LoanSubmitData data) {
        this.data = data;
    }
}
