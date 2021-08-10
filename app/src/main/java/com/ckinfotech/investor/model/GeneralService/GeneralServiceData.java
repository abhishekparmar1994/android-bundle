
package com.ckinfotech.investor.model.GeneralService;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralServiceData {

    @SerializedName("user")
    @Expose
    private GeneralServiceUser user;
    @SerializedName("payment_amount")
    @Expose
    private String paymentAmount;
    @SerializedName("offer_details")
    @Expose
    private String offerDetails;
    @SerializedName("share_mobile_number")
    @Expose
    private String shareMobileNumber;
    @SerializedName("emi_month")
    @Expose
    private String emiMonth;
    @SerializedName("payment_request_status")
    @Expose
    private String paymentRequestStatus;
    @SerializedName("membership")
    @Expose
    private GeneralServiceMembership membership;
    @SerializedName("loan")
    @Expose
    private GeneralServiceLoan loan;

    public GeneralServiceUser getGeneralServiceUser() {
        return user;
    }

    public void setGeneralServiceUser(GeneralServiceUser user) {
        this.user = user;
    }

    public String getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(String paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }

    public String getShareMobileNumber() {
        return shareMobileNumber;
    }

    public void setShareMobileNumber(String shareMobileNumber) {
        this.shareMobileNumber = shareMobileNumber;
    }

    public String getEmiMonth() {
        return emiMonth;
    }

    public void setEmiMonth(String emiMonth) {
        this.emiMonth = emiMonth;
    }

    public String getPaymentRequestStatus() {
        return paymentRequestStatus;
    }

    public void setPaymentRequestStatus(String paymentRequestStatus) {
        this.paymentRequestStatus = paymentRequestStatus;
    }

    public GeneralServiceMembership getGeneralServiceMembership() {
        return membership;
    }

    public void setGeneralServiceMembership(GeneralServiceMembership membership) {
        this.membership = membership;
    }

    public GeneralServiceLoan getGeneralServiceLoan() {
        return loan;
    }

    public void setLoan(GeneralServiceLoan loan) {
        this.loan = loan;
    }

}
