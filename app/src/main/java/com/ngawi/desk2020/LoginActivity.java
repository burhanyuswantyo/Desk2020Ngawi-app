package com.ngawi.desk2020;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ngawi.desk2020.api.ClientService;
import com.ngawi.desk2020.api.RetrofitClient;
import com.ngawi.desk2020.api.Session;
import com.ngawi.desk2020.model.Desa;
import com.ngawi.desk2020.model.GetDesa;
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
    Spinner spKecamatan, spDesa;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        spKecamatan = (Spinner) findViewById(R.id.sp_kecamatan);
        spDesa = (Spinner) findViewById(R.id.sp_desa);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);

        etPassword.setEnabled(false);
        desa(null);
        kecamatan();

//        btnLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Retrofit retrofit = RetrofitClient.connect();
//                ClientService service = retrofit.create(ClientService.class);
//                final Call<ResponseBody> request = service.loginDesa(desa.get(position).getId(), etPassword.getText().toString(), Session.init(holder.itemView.getContext()).getString("kecamatan"));
//                request.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        if (response.body() != null) {
//                            try {
//                                JSONObject jsonRESULTS = new JSONObject(response.body().string());
//                                Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
//                                JSONObject data = jsonRESULTS.getJSONObject("data");
//                                Session.init(LoginActivity.this)
//                                        .set("id", data.getString("id"))
//                                        .set("kecamatan", data.getString("kecamatan"));
//                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                                startActivity(intent);
//                                finish();
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                Toast.makeText(LoginActivity.this, "Login Gagal, Periksa Koneksi Anda!", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(LoginActivity.this, "Password Salah!", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        Toast.makeText(LoginActivity.this, "Login Gagal, Periksa Koneksi Anda!", Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });


    }

    public void kecamatan() {
        Retrofit retrofit = RetrofitClient.connect();
        ClientService service = retrofit.create(ClientService.class);
        Call<GetKecamatan> kecamatanCall = service.getKecamatan();
        service.getKecamatan().enqueue(new Callback<GetKecamatan>() {
            @Override
            public void onResponse(Call<GetKecamatan> call, Response<GetKecamatan> response) {
                List<Kecamatan> kecamatanList = response.body().getKecamatanList();
                List<String> listSpinner = new ArrayList<String>();
                listSpinner.add(0, "Pilih Kecamatan");
                for (int i = 0; i < kecamatanList.size(); i++) {
                    listSpinner.add(kecamatanList.get(i).getKecamatan().toString());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                        android.R.layout.simple_spinner_item, listSpinner);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spKecamatan.setAdapter(adapter);

                spKecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (adapterView.getItemAtPosition(i).equals("Pilih Kecamatan")) {
                            spDesa.setEnabled(false);
                        } else {
                            spDesa.setEnabled(true);
                            etPassword.setEnabled(true);
                            Log.e("Retrofit Get", "ID : " + kecamatanList.get(i - 1).getId());
                            desa(kecamatanList.get(i - 1).getId().toString());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onFailure(Call<GetKecamatan> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    public void desa(String id) {
        if (id == null) {
            List<String> listSpinner = new ArrayList<String>();
            listSpinner.add("Pilih Desa");
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                    android.R.layout.simple_spinner_item, listSpinner);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spDesa.setAdapter(adapter);
        } else {
            Retrofit retrofit = RetrofitClient.connect();
            ClientService service = retrofit.create(ClientService.class);
            Call<GetDesa> desaCall = service.getDesa(id);
            service.getDesa(id).enqueue(new Callback<GetDesa>() {
                @Override
                public void onResponse(Call<GetDesa> call, Response<GetDesa> response) {
                    List<Desa> desaList = response.body().getDesaList();
                    List<String> listSpinner = new ArrayList<String>();
                    listSpinner.add("Pilih Desa");
                    for (int i = 0; i < desaList.size(); i++) {
                        listSpinner.add(desaList.get(i).getDesa().toString());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                            android.R.layout.simple_spinner_item, listSpinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spDesa.setAdapter(adapter);

                    spDesa.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            if (adapterView.getItemAtPosition(i).equals("Pilih Desa")) {
                                etPassword.setEnabled(false);
                            } else {
                                etPassword.setEnabled(true);
                                Log.e("Retrofit Get", "ID : " + desaList.get(i - 1).getId());
//                                login(desaList.get(i - 1).getId().toString(), etPassword.getText().toString(), spKecamatan.getSelectedItem().toString());
                                btnLogin.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Retrofit retrofit = RetrofitClient.connect();
                                        ClientService service = retrofit.create(ClientService.class);
                                        final Call<ResponseBody> request = service.login(desaList.get(i - 1).getId().toString(), etPassword.getText().toString(), spKecamatan.getSelectedItem().toString());
                                        request.enqueue(new Callback<ResponseBody>() {
                                            @Override
                                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                                if (response.body() != null) {
                                                    try {
                                                        JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                                        Toast.makeText(LoginActivity.this, "Login Berhasil!", Toast.LENGTH_SHORT).show();
                                                        JSONObject data = jsonRESULTS.getJSONObject("data");
                                                        Session.init(LoginActivity.this)
                                                                .set("desa_id", data.getString("id"))
                                                                .set("desa", data.getString("desa"));
                                                        Intent intent = new Intent(LoginActivity.this, TpsActivity.class);
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
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });
                }

                @Override
                public void onFailure(Call<GetDesa> call, Throwable t) {
                    Log.e("Retrofit Get", t.toString());
                }
            });
        }
    }
}