
package com.ckinfotech.investor.model.UserAllData;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("user")
    @Expose
    private UserAllDataUser user;
    @SerializedName("loans")
    @Expose
    private List<UserAllDataLoan> loans = null;

    public UserAllDataUser getUser() {
        return user;
    }

    public void setUser(UserAllDataUser user) {
        this.user = user;
    }

    public List<UserAllDataLoan> getLoans() {
        return loans;
    }

    public void setLoans(List<UserAllDataLoan> loans) {
        this.loans = loans;
    }

}
