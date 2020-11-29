package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDesa {
    @SerializedName("status")
    String status;

    @SerializedName("data")
    List<Desa> desaList;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Desa> getDesaList() {
        return desaList;
    }

    public void setDesaList(List<Desa> desaList) {
        this.desaList = desaList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
