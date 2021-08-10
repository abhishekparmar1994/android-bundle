
package com.ckinfotech.investor.model.responceModel;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CountryResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<CountryList> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CountryList> getData() {
        return data;
    }

    public void setData(List<CountryList> data) {
        this.data = data;
    }

}
