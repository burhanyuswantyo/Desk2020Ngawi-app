package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;


public class Kecamatan {
    @SerializedName("id")
    private String id;

    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("password")
    private String password;

    public Kecamatan() {
        this.id = id;
        this.kecamatan = kecamatan;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
