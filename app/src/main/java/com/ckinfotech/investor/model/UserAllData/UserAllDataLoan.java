
package com.ckinfotech.investor.model.UserAllData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserAllDataLoan {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("mobile_number1")
    @Expose
    private String mobileNumber1;
    @SerializedName("mobile_number2")
    @Expose
    private String mobileNumber2;
    @SerializedName("country_id")
    @Expose
    private Integer countryId;
    @SerializedName("state_id")
    @Expose
    private Integer stateId;
    @SerializedName("district_id")
    @Expose
    private Integer districtId;
    @SerializedName("taluka_id")
    @Expose
    private Integer talukaId;
    @SerializedName("village_id")
    @Expose
    private Object villageId;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("aadhar_card_front_img_path")
    @Expose
    private String aadharCardFrontImgPath;
    @SerializedName("aadhar_card_front_img_name")
    @Expose
    private String aadharCardFrontImgName;
    @SerializedName("aadhar_card_back_img_path")
    @Expose
    private String aadharCardBackImgPath;
    @SerializedName("aadhar_card_back_img_name")
    @Expose
    private String aadharCardBackImgName;
    @SerializedName("aadhar_card_number")
    @Expose
    private String aadharCardNumber;
    @SerializedName("pan_card_img_path")
    @Expose
    private String panCardImgPath;
    @SerializedName("pan_card_img_name")
    @Expose
    private String panCardImgName;
    @SerializedName("pan_card_number")
    @Expose
    private String panCardNumber;
    @SerializedName("nominee_name1")
    @Expose
    private String nomineeName1;
    @SerializedName("nominee_mobile_number1")
    @Expose
    private Integer nomineeMobileNumber1;
    @SerializedName("nominee_name2")
    @Expose
    private String nomineeName2;
    @SerializedName("nominee_mobile_number2")
    @Expose
    private Integer nomineeMobileNumber2;
    @SerializedName("required_loan_amount")
    @Expose
    private String requiredLoanAmount;
    @SerializedName("monthly_emi")
    @Expose
    private String monthlyEmi;
    @SerializedName("emi_payment_date")
    @Expose
    private String emiPaymentDate;
    @SerializedName("monthly_income")
    @Expose
    private String monthlyIncome;
    @SerializedName("monthly_interest")
    @Expose
    private String monthlyInterest;
    @SerializedName("emi_bounce_charge")
    @Expose
    private String emiBounceCharge;
    @SerializedName("loan_intrest")
    @Expose
    private String loanIntrest;
    @SerializedName("loan_total_amount")
    @Expose
    private String loanTotalAmount;
    @SerializedName("loan_status")
    @Expose
    private String loanStatus;
    @SerializedName("step")
    @Expose
    private Object step;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("deleted_by")
    @Expose
    private Object deletedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_by")
    @Expose
    private Integer createdBy;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("updated_by")
    @Expose
    private Integer updatedBy;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber1() {
        return mobileNumber1;
    }

    public void setMobileNumber1(String mobileNumber1) {
        this.mobileNumber1 = mobileNumber1;
    }

    public String getMobileNumber2() {
        return mobileNumber2;
    }

    public void setMobileNumber2(String mobileNumber2) {
        this.mobileNumber2 = mobileNumber2;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Integer districtId) {
        this.districtId = districtId;
    }

    public Integer getTalukaId() {
        return talukaId;
    }

    public void setTalukaId(Integer talukaId) {
        this.talukaId = talukaId;
    }

    public Object getVillageId() {
        return villageId;
    }

    public void setVillageId(Object villageId) {
        this.villageId = villageId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAadharCardFrontImgPath() {
        return aadharCardFrontImgPath;
    }

    public void setAadharCardFrontImgPath(String aadharCardFrontImgPath) {
        this.aadharCardFrontImgPath = aadharCardFrontImgPath;
    }

    public String getAadharCardFrontImgName() {
        return aadharCardFrontImgName;
    }

    public void setAadharCardFrontImgName(String aadharCardFrontImgName) {
        this.aadharCardFrontImgName = aadharCardFrontImgName;
    }

    public String getAadharCardBackImgPath() {
        return aadharCardBackImgPath;
    }

    public void setAadharCardBackImgPath(String aadharCardBackImgPath) {
        this.aadharCardBackImgPath = aadharCardBackImgPath;
    }

    public String getAadharCardBackImgName() {
        return aadharCardBackImgName;
    }

    public void setAadharCardBackImgName(String aadharCardBackImgName) {
        this.aadharCardBackImgName = aadharCardBackImgName;
    }

    public String getAadharCardNumber() {
        return aadharCardNumber;
    }

    public void setAadharCardNumber(String aadharCardNumber) {
        this.aadharCardNumber = aadharCardNumber;
    }

    public String getPanCardImgPath() {
        return panCardImgPath;
    }

    public void setPanCardImgPath(String panCardImgPath) {
        this.panCardImgPath = panCardImgPath;
    }

    public String getPanCardImgName() {
        return panCardImgName;
    }

    public void setPanCardImgName(String panCardImgName) {
        this.panCardImgName = panCardImgName;
    }

    public String getPanCardNumber() {
        return panCardNumber;
    }

    public void setPanCardNumber(String panCardNumber) {
        this.panCardNumber = panCardNumber;
    }

    public String getNomineeName1() {
        return nomineeName1;
    }

    public void setNomineeName1(String nomineeName1) {
        this.nomineeName1 = nomineeName1;
    }

    public Integer getNomineeMobileNumber1() {
        return nomineeMobileNumber1;
    }

    public void setNomineeMobileNumber1(Integer nomineeMobileNumber1) {
        this.nomineeMobileNumber1 = nomineeMobileNumber1;
    }

    public String getNomineeName2() {
        return nomineeName2;
    }

    public void setNomineeName2(String nomineeName2) {
        this.nomineeName2 = nomineeName2;
    }

    public Integer getNomineeMobileNumber2() {
        return nomineeMobileNumber2;
    }

    public void setNomineeMobileNumber2(Integer nomineeMobileNumber2) {
        this.nomineeMobileNumber2 = nomineeMobileNumber2;
    }

    public String getRequiredLoanAmount() {
        return requiredLoanAmount;
    }

    public void setRequiredLoanAmount(String requiredLoanAmount) {
        this.requiredLoanAmount = requiredLoanAmount;
    }

    public String getMonthlyEmi() {
        return monthlyEmi;
    }

    public void setMonthlyEmi(String monthlyEmi) {
        this.monthlyEmi = monthlyEmi;
    }

    public String getEmiPaymentDate() {
        return emiPaymentDate;
    }

    public void setEmiPaymentDate(String emiPaymentDate) {
        this.emiPaymentDate = emiPaymentDate;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getMonthlyInterest() {
        return monthlyInterest;
    }

    public void setMonthlyInterest(String monthlyInterest) {
        this.monthlyInterest = monthlyInterest;
    }

    public String getEmiBounceCharge() {
        return emiBounceCharge;
    }

    public void setEmiBounceCharge(String emiBounceCharge) {
        this.emiBounceCharge = emiBounceCharge;
    }

    public String getLoanIntrest() {
        return loanIntrest;
    }

    public void setLoanIntrest(String loanIntrest) {
        this.loanIntrest = loanIntrest;
    }

    public String getLoanTotalAmount() {
        return loanTotalAmount;
    }

    public void setLoanTotalAmount(String loanTotalAmount) {
        this.loanTotalAmount = loanTotalAmount;
    }

    public String getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(String loanStatus) {
        this.loanStatus = loanStatus;
    }

    public Object getStep() {
        return step;
    }

    public void setStep(Object step) {
        this.step = step;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Object getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(Object deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

}
