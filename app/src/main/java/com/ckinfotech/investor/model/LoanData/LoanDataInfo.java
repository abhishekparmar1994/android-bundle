
package com.ckinfotech.investor.model.LoanData;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanDataInfo {

    @SerializedName("loan_id")
    @Expose
    private String loanId;
    @SerializedName("total_loan_amount")
    @Expose
    private String totalLoanAmount;
    @SerializedName("total_processing_fee")
    @Expose
    private String totalProcessingFee;
    @SerializedName("total_payable_amount_after_processing_fee")
    @Expose
    private String totalPayableAmountAfterProcessingFee;
    @SerializedName("total_loan_amount_with_interest")
    @Expose
    private String totalLoanAmountWithInterest;
    @SerializedName("monthly_interest")
    @Expose
    private String monthlyInterest;
    @SerializedName("emi_bounce_date")
    @Expose
    private String emiBounceDate;
    @SerializedName("total_bounce_emi")
    @Expose
    private String totalBounceEmi;
    @SerializedName("total_success")
    @Expose
    private String totalSuccess;
    @SerializedName("pending_amount")
    @Expose
    private String pendingAmount;
    @SerializedName("monthly_intrest_rate")
    @Expose
    private String monthlyIntrestRate;
    @SerializedName("monthly_bounce_rate")
    @Expose
    private String monthlyBounceRate;
    @SerializedName("current_date")
    @Expose
    private String currentDate;
    @SerializedName("emi_data_list")
    @Expose
    private List<LoanEmiData> LoanEmiData = null;

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(String totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public String getTotalProcessingFee() {
        return totalProcessingFee;
    }

    public void setTotalProcessingFee(String totalProcessingFee) {
        this.totalProcessingFee = totalProcessingFee;
    }

    public String getTotalPayableAmountAfterProcessingFee() {
        return totalPayableAmountAfterProcessingFee;
    }

    public void setTotalPayableAmountAfterProcessingFee(String totalPayableAmountAfterProcessingFee) {
        this.totalPayableAmountAfterProcessingFee = totalPayableAmountAfterProcessingFee;
    }

    public String getTotalLoanAmountWithInterest() {
        return totalLoanAmountWithInterest;
    }

    public void setTotalLoanAmountWithInterest(String totalLoanAmountWithInterest) {
        this.totalLoanAmountWithInterest = totalLoanAmountWithInterest;
    }

    public String getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(String monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public String getEmiBounceDate() {
        return emiBounceDate;
    }

    public void setEmiBounceDate(String emiBounceDate) {
        this.emiBounceDate = emiBounceDate;
    }

    public String getTotalBounceEmi() {
        return totalBounceEmi;
    }

    public void setTotalBounceEmi(String totalBounceEmi) {
        this.totalBounceEmi = totalBounceEmi;
    }

    public String getTotalSuccess() {
        return totalSuccess;
    }

    public void setTotalSuccess(String totalSuccess) {
        this.totalSuccess = totalSuccess;
    }

    public String getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(String pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    public String getMonthlyIntrestRate() {
        return monthlyIntrestRate;
    }

    public void setMonthlyIntrestRate(String monthlyIntrestRate) {
        this.monthlyIntrestRate = monthlyIntrestRate;
    }

    public String getMonthlyBounceRate() {
        return monthlyBounceRate;
    }

    public void setMonthlyBounceRate(String monthlyBounceRate) {
        this.monthlyBounceRate = monthlyBounceRate;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public List<LoanEmiData> getLoanEmiData() {
        return LoanEmiData;
    }

    public void setLoanEmiData(List<LoanEmiData> LoanEmiData) {
        this.LoanEmiData = LoanEmiData;
    }
}
