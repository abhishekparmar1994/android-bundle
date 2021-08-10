
package com.ckinfotech.investor.model.LoanData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanEmiData {

    @SerializedName("check_date")
    @Expose
    private String checkDate;
    @SerializedName("loan_amount")
    @Expose
    private String loanAmount;
    @SerializedName("loan_amount_with_interest")
    @Expose
    private String loanAmountWithInterest;
    @SerializedName("emi_payment_request_status")
    @Expose
    private String emiPaymentRequestStatus;
    @SerializedName("emi_bounce_charge")
    @Expose
    private String emiBounceCharge;
    @SerializedName("emi_amount_with_intrest")
    @Expose
    private String emiAmountWithIntrest;
    @SerializedName("due_date")
    @Expose
    private String dueDate;

    public String getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getLoanAmountWithInterest() {
        return loanAmountWithInterest;
    }

    public void setLoanAmountWithInterest(String loanAmountWithInterest) {
        this.loanAmountWithInterest = loanAmountWithInterest;
    }

    public String getEmiPaymentRequestStatus() {
        return emiPaymentRequestStatus;
    }

    public void setEmiPaymentRequestStatus(String emiPaymentRequestStatus) {
        this.emiPaymentRequestStatus = emiPaymentRequestStatus;
    }

    public String getEmiBounceCharge() {
        return emiBounceCharge;
    }

    public void setEmiBounceCharge(String emiBounceCharge) {
        this.emiBounceCharge = emiBounceCharge;
    }

    public String getEmiAmountWithIntrest() {
        return emiAmountWithIntrest;
    }

    public void setEmiAmountWithIntrest(String emiAmountWithIntrest) {
        this.emiAmountWithIntrest = emiAmountWithIntrest;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

}
