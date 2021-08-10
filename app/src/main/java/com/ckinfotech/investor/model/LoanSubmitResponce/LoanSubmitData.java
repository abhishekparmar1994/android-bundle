
package com.ckinfotech.investor.model.LoanSubmitResponce;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanSubmitData {

    @SerializedName("loan_amount")
    @Expose
    private String loanAmount;
    @SerializedName("emi_month")
    @Expose
    private Integer emiMonth;
    @SerializedName("monthly_interest_amount")
    @Expose
    private Double monthlyInterestAmount;
    @SerializedName("emi_bounce_charge")
    @Expose
    private String emiBounceCharge;
    @SerializedName("loan_total_amount")
    @Expose
    private Double loanTotalAmount;
    @SerializedName("emi_date")
    @Expose
    private String emiDate;

    @SerializedName("loan_status")
    @Expose
    private String loan_status;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Integer getEmiMonth() {
        return emiMonth;
    }

    public void setEmiMonth(Integer emiMonth) {
        this.emiMonth = emiMonth;
    }

    public Double getMonthlyInterestAmount() {
        return monthlyInterestAmount;
    }

    public void setMonthlyInterestAmount(Double monthlyInterestAmount) {
        this.monthlyInterestAmount = monthlyInterestAmount;
    }

    public String getEmiBounceCharge() {
        return emiBounceCharge;
    }

    public void setEmiBounceCharge(String emiBounceCharge) {
        this.emiBounceCharge = emiBounceCharge;
    }

    public Double getLoanTotalAmount() {
        return loanTotalAmount;
    }

    public void setLoanTotalAmount(Double loanTotalAmount) {
        this.loanTotalAmount = loanTotalAmount;
    }

    public String getEmiDate() {
        return emiDate;
    }

    public void setEmiDate(String emiDate) {
        this.emiDate = emiDate;
    }

    public String getloan_status() {
        return loan_status;
    }

    public void setloan_status(String emiDate) {
        this.loan_status = emiDate;
    }
}
