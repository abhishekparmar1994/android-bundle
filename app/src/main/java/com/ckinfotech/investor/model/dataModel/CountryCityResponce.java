package com.ckinfotech.investor.model.dataModel;

import android.os.Parcel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;


public class CountryCityResponce {



    @Expose
    @SerializedName("status")
    private String status;


    @Expose
    @SerializedName("status")
    private List data = new ArrayList();


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
    }


}



