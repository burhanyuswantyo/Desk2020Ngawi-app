package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

public class PostPutDelData {
    @SerializedName("status")
    String status;
    @SerializedName("data")
    Data data;
    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
