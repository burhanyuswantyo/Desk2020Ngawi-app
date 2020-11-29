package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

public class Tps {
    @SerializedName("id")
    private String id;

    @SerializedName("tps")
    private String tps;

    @SerializedName("dpt")
    private String dpt;

    @SerializedName("desa_id")
    private String desa_id;

    public Tps(String id, String tps, String dpt, String desa_id) {
        this.id = id;
        this.tps = tps;
        this.dpt = dpt;
        this.desa_id = desa_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }

    public String getDpt() {
        return dpt;
    }

    public void setDpt(String dpt) {
        this.dpt = dpt;
    }

    public String getDesa_id() {
        return desa_id;
    }

    public void setDesa_id(String desa_id) {
        this.desa_id = desa_id;
    }
}
