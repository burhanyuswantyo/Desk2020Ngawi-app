package com.ngawi.desk2020.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    //    private static String URL = "http://192.168.75.1/penghitungansuara/api/";
    private static String URL = "https://yus1.xyz/api/";

    public static Retrofit connect() {
        return new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
