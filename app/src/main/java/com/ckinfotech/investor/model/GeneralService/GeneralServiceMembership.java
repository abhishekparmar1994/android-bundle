
package com.ckinfotech.investor.model.GeneralService;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeneralServiceMembership {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("membership_by")
    @Expose
    private String membershipBy;
    @SerializedName("money")
    @Expose
    private String money;
    @SerializedName("referele_code")
    @Expose
    private String refereleCode;
    @SerializedName("membership_status")
    @Expose
    private String membershipStatus;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;
    @SerializedName("deleted_by")
    @Expose
    private String deletedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("created_by")
    @Expose
    private String createdBy;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("updated_by")
    @Expose
    private String updatedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMembershipBy() {
        return membershipBy;
    }

    public void setMembershipBy(String membershipBy) {
        this.membershipBy = membershipBy;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRefereleCode() {
        return refereleCode;
    }

    public void setRefereleCode(String refereleCode) {
        this.refereleCode = refereleCode;
    }

    public String getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(String membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeletedBy() {
        return deletedBy;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

}
