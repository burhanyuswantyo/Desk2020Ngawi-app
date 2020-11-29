package com.ngawi.desk2020;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ngawi.desk2020.adapter.DataAdapter;
import com.ngawi.desk2020.api.ClientService;
import com.ngawi.desk2020.api.RetrofitClient;
import com.ngawi.desk2020.api.Session;
import com.ngawi.desk2020.model.Data;
import com.ngawi.desk2020.model.GetData;
import com.ngawi.desk2020.model.PostPutDelData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataActivity extends AppCompatActivity {

    ClientService clientService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    public static DataActivity dataActivity;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        setTitle("TPS " + getIntent().getStringExtra("tps") + " Desa " + getIntent().getStringExtra("desa"));

        recyclerView = (RecyclerView) findViewById(R.id.rv_data);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        clientService = RetrofitClient.connect().create(ClientService.class);
        dataActivity = this;
        refresh();

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSave = (Button) findViewById(R.id.btn_simpan);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataAdapter adapter = (DataAdapter) recyclerView.getAdapter();

                boolean[] status = new boolean[adapter.getItemCount()];
                for (int i = 0; i < adapter.getItemCount(); i++) {
                    if (TextUtils.isEmpty(((EditText) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.et_jumlahsuara)).getText())) {
                        status[i] = false;
                        ((EditText) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.et_jumlahsuara)).setError("Harus Diisi!");
                    } else {
                        status[i] = true;
                    }
                }

                if (formCheck(status) == false) {
                    Toast.makeText(DataActivity.this, "Suara Masuk Harus Diisi!", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < adapter.getItemCount(); i++) {
                        Data data = (Data) adapter.getItem(i);
                        String jml_suara = ((EditText) recyclerView.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.et_jumlahsuara)).getText().toString();

                        Log.d("Retrofit Get", "jml suara : " + jml_suara + " ID TPS : " + data.getTps_id() + " ID PASLON : " + data.getPaslon_id());
                        Call<PostPutDelData> putDataCall = clientService.putData(
                                jml_suara,
                                data.getTps_id(),
                                data.getPaslon_id(),
                                Session.init(DataActivity.this).getString("kecamatan"),
                                getIntent().getStringExtra("desa"),
                                getIntent().getStringExtra("tps"));
                        putDataCall.enqueue(new Callback<PostPutDelData>() {
                            @Override
                            public void onResponse(Call<PostPutDelData> call, Response<PostPutDelData> response) {
                                Toast.makeText(DataActivity.this, "Data berhasil disimpan!", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<PostPutDelData> call, Throwable t) {
                                Toast.makeText(DataActivity.this, "Data gagal disimpan!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        });
    }

    public static boolean formCheck(boolean[] array) {
        for (boolean b : array) if (!b) return false;
        return true;
    }

    public void refresh() {
        Call<GetData> dataCall = clientService.getData(getIntent().getStringExtra("tps_id"));
        dataCall.enqueue(new Callback<GetData>() {
            @Override
            public void onResponse(Call<GetData> call, Response<GetData> response) {
                List<Data> dataList = response.body().getDataList();
                Log.d("Retrofit Get", "Jumlah data laporan : " + String.valueOf(dataList.size()));
                adapter = new DataAdapter(dataList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetData> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}