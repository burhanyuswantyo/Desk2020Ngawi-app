package com.ngawi.desk2020.model;

import com.google.gson.annotations.SerializedName;

public class Desa {
    @SerializedName("id")
    private String id;

    @SerializedName("desa")
    private String desa;

    @SerializedName("kecamatan_id")
    private String kecamatan_id;

    public Desa(String id, String desa, String kecamatan_id) {
        this.id = id;
        this.desa = desa;
        this.kecamatan_id = kecamatan_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesa() {
        return desa;
    }

    public void setDesa(String desa) {
        this.desa = desa;
    }

    public String getKecamatan_id() {
        return kecamatan_id;
    }

    public void setKecamatan_id(String kecamatan_id) {
        this.kecamatan_id = kecamatan_id;
    }
}
