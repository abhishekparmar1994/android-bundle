
package com.ckinfotech.investor.model.LoanData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanData {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private LoanDataInfo data;

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
    public LoanDataInfo getLoanDataInfo() {
        return data;
    }

    public void setLoanDataInfo(LoanDataInfo data) {
        this.data = data;
    }

}
