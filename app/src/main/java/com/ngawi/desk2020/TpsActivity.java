package com.ngawi.desk2020;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ngawi.desk2020.adapter.TpsAdapter;
import com.ngawi.desk2020.api.ClientService;
import com.ngawi.desk2020.api.RetrofitClient;
import com.ngawi.desk2020.api.Session;
import com.ngawi.desk2020.model.GetTps;
import com.ngawi.desk2020.model.Tps;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TpsActivity extends AppCompatActivity {

    ClientService clientService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    public static TpsActivity tpsActivity;
    private List<Tps> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tps);
        setTitle("Desa " + getIntent().getStringExtra("desa"));

        recyclerView = (RecyclerView) findViewById(R.id.rv_tps);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        clientService = RetrofitClient.connect().create(ClientService.class);
        tpsActivity = this;

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab_tps);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TpsActivity.this, RekapActivity.class);
                intent.putExtra("id", Session.init(TpsActivity.this).getString("id"));
                TpsActivity.this.startActivity(intent);
            }
        });

        refresh();
    }

    public String getDesa() {
        String desa = tpsActivity.getIntent().getStringExtra("desa");
        return desa;
    }

    public void refresh() {
        Call<GetTps> tpsCall = clientService.getTps(getIntent().getStringExtra("desa_id"));
        tpsCall.enqueue(new Callback<GetTps>() {
            @Override
            public void onResponse(Call<GetTps> call, Response<GetTps> response) {
                List<Tps> tpsList = response.body().getTpsList();
                Log.d("Retrofit Get", "Jumlah data laporan : " + String.valueOf(tpsList.size()));
                adapter = new TpsAdapter(tpsList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetTps> call, Throwable t) {
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