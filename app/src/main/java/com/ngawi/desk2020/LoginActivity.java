package com.ngawi.desk2020;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ngawi.desk2020.api.ClientService;
import com.ngawi.desk2020.api.RetrofitClient;
import com.ngawi.desk2020.api.Session;
import com.ngawi.desk2020.model.GetKecamatan;
import com.ngawi.desk2020.model.Kecamatan;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    EditText etPassword;
    Spinner spKecamatan;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spKecamatan = (Spinner) findViewById(R.id.sp_kecamatan);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        kecamatan();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spKecamatan.getSelectedItem() == "Pilih Kecamatan") {
                    Toast.makeText(LoginActivity.this, "Pilih Kecamatan Terlebih Dahulu!", Toast.LENGTH_LONG).show();
                } else {
                    if (TextUtils.isEmpty(etPassword.getText())) {
                        etPassword.setError("Password Harus Diisi!");
                        Toast.makeText(LoginActivity.this, "Password Harus Diisi!", Toast.LENGTH_LONG).show();
                    } else {
                        Retrofit retrofit = RetrofitClient.connect();
                        ClientService service = retrofit.create(ClientService.class);
                        final Call<ResponseBody> request = service.login(spKecamatan.getSelectedItem().toString(), etPassword.getText().toString());
                        request.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.body() != null) {
                                    try {
                                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                        Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                                        JSONObject data = jsonRESULTS.getJSONObject("data");
                                        Session.init(LoginActivity.this)
                                                .set("id", data.getString("id"))
                                                .set("kecamatan", data.getString("kecamatan"));
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        finish();

                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        Toast.makeText(LoginActivity.this, "Login Gagal, Periksa Koneksi Anda!", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Password Salah!", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "Login Gagal, Periksa Koneksi Anda!", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
        });


    }

    public void kecamatan() {
        Retrofit retrofit = RetrofitClient.connect();
        ClientService service = retrofit.create(ClientService.class);
        Call<GetKecamatan> kecamatanCall = service.getKecamatan();
        service.getKecamatan().enqueue(new Callback<GetKecamatan>() {
            @Override
            public void onResponse(Call<GetKecamatan> call, Response<GetKecamatan> response) {
                List<Kecamatan> kecamatanList = response.body().getKecamatanList();
                Log.d("Retrofit Get", "Jumlah data laporan : " + String.valueOf(kecamatanList.size()));
                List<String> listSpinner = new ArrayList<String>();
                listSpinner.add("Pilih Kecamatan");
                for (int i = 0; i < kecamatanList.size(); i++) {
                    listSpinner.add(kecamatanList.get(i).getKecamatan().toString());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spKecamatan.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetKecamatan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }
}