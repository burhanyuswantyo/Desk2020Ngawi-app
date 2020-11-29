package com.ngawi.desk2020.api;


import com.ngawi.desk2020.model.GetData;
import com.ngawi.desk2020.model.GetDesa;
import com.ngawi.desk2020.model.GetKecamatan;
import com.ngawi.desk2020.model.GetTps;
import com.ngawi.desk2020.model.PostPutDelData;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ClientService {
    @FormUrlEncoded
    @POST("login")
    Call<ResponseBody> login(@Field("kecamatan") String kecamatan, @Field("password") String password);

    @FormUrlEncoded
    @POST("logindesa")
    Call<ResponseBody> loginDesa(@Field("id") String id, @Field("password") String password, @Field("kecamatan") String kecamatan);

    @GET("kecamatan")
    Call<GetKecamatan> getKecamatan();

    @GET("{kecamatan_id}")
    Call<GetDesa> getDesa(@Query("kecamatan_id") String kecamatan_id);

    @GET("desa/{desa_id}")
    Call<GetTps> getTps(@Query("desa_id") String desa_id);

    @GET("tps/{tps_id}")
    Call<GetData> getData(@Query("tps_id") String tps_id);

    @FormUrlEncoded
    @PUT("tps")
    Call<PostPutDelData> putData(@Field("jml_suara") String jml_suara,
                                 @Field("tps_id") String tps_id,
                                 @Field("paslon_id") String paslon_id,
                                 @Field("kecamatan") String kecamatan,
                                 @Field("desa") String desa,
                                 @Field("tps") String tps);
}
