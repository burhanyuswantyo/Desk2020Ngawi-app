package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKecamatan {
    @SerializedName("status")
    String status;

    @SerializedName("data")
    List<Kecamatan> kecamatanList;

    @SerializedName("message")
    String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Kecamatan> getKecamatanList() {
        return kecamatanList;
    }

    public void setKecamatanList(List<Kecamatan> kecamatanList) {
        this.kecamatanList = kecamatanList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
