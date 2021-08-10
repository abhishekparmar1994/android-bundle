
package com.ckinfotech.investor.model.ApllyLoan;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyLoan {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private ApplyLoanData data;

    public String getsuccess() {
        return success;
    }

    public void setsuccess(String result) {
        this.success = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ApplyLoanData getApplyLoanData() {
        return data;
    }

    public void setApplyLoanData(ApplyLoanData data) {
        this.data = data;
    }

}
