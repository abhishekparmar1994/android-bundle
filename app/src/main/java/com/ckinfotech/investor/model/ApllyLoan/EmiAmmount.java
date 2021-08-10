
package com.ckinfotech.investor.model.ApllyLoan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmiAmmount {

    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("monthly_emi_amount")
    @Expose
    private String monthlyEmiAmount;

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

    public String getMonthlyEmiAmount() {
        return monthlyEmiAmount;
    }

    public void setMonthlyEmiAmount(String monthlyEmiAmount) {
        this.monthlyEmiAmount = monthlyEmiAmount;
    }

}
