package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

public class Data {
    @SerializedName("tps_id")
    private String tps_id;

    @SerializedName("paslon_id")
    private String paslon_id;

    @SerializedName("tps")
    private String tps;

    @SerializedName("nama")
    private String nama;

    @SerializedName("jml_suara")
    private String jml_suara;

    public String getTps_id() {
        return tps_id;
    }

    public void setTps_id(String tps_id) {
        this.tps_id = tps_id;
    }

    public String getPaslon_id() {
        return paslon_id;
    }

    public void setPaslon_id(String paslon_id) {
        this.paslon_id = paslon_id;
    }

    public String getTps() {
        return tps;
    }

    public void setTps(String tps) {
        this.tps = tps;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJml_suara() {
        return jml_suara;
    }

    public void setJml_suara(String jml_suara) {
        this.jml_suara = jml_suara;
    }

    public Data(String tps_id, String paslon_id, String tps, String nama, String jml_suara) {
        this.tps_id = tps_id;
        this.paslon_id = paslon_id;
        this.tps = tps;
        this.nama = nama;
        this.jml_suara = jml_suara;
    }
}
