package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

public class Auth {
    @SerializedName("kecamatan")
    private String kecamatan;

    @SerializedName("password")
    private String password;

    public Auth(String kecamatan, String password) {
        this.kecamatan = kecamatan;
        this.password = password;
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
