package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetTps {
    @SerializedName("status")
    String status;

    @SerializedName("data")
    List<Tps> tpsList;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Tps> getTpsList() {
        return tpsList;
    }

    public void setTpsList(List<Tps> tpsList) {
        this.tpsList = tpsList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
