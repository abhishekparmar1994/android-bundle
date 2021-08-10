
package com.ckinfotech.investor.model.ApllyLoan;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ApplyLoanData {

    @SerializedName("loan_amount")
    @Expose
    private String loanAmount;
    @SerializedName("total_amount_with_intrest")
    @Expose
    private String totalAmountWithIntrest;
    @SerializedName("total_intrest_amount")
    @Expose
    private String totalIntrestAmount;
    @SerializedName("monthly_emi_amount")
    @Expose
    private String monthlyEmiAmount;
    @SerializedName("emi_date")
    @Expose
    private String emiDate;

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getTotalAmountWithIntrest() {
        return totalAmountWithIntrest;
    }

    public void setTotalAmountWithIntrest(String totalAmountWithIntrest) {
        this.totalAmountWithIntrest = totalAmountWithIntrest;
    }

    public String getTotalIntrestAmount() {
        return totalIntrestAmount;
    }

    public void setTotalIntrestAmount(String totalIntrestAmount) {
        this.totalIntrestAmount = totalIntrestAmount;
    }

    public String getMonthlyEmiAmount() {
        return monthlyEmiAmount;
    }

    public void setMonthlyEmiAmount(String monthlyEmiAmount) {
        this.monthlyEmiAmount = monthlyEmiAmount;
    }

    public String getEmiDate() {
        return emiDate;
    }

    public void setEmiDate(String emiDate) {
        this.emiDate = emiDate;
    }

}
