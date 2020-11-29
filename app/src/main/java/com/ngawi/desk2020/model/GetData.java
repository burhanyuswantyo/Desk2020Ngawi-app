package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetData {
    @SerializedName("status")
    String status;

    @SerializedName("data")
    List<Data> dataList;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getDataList() {
        return dataList;
    }

    public void setDataList(List<Data> dataList) {
        this.dataList = dataList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
