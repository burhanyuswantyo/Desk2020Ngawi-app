package com.ngawi.desk2020;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.ngawi.desk2020.adapter.DesaAdapter;
import com.ngawi.desk2020.api.ClientService;
import com.ngawi.desk2020.api.RetrofitClient;
import com.ngawi.desk2020.api.Session;
import com.ngawi.desk2020.model.Desa;
import com.ngawi.desk2020.model.GetDesa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ClientService clientService;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private LinearLayoutManager layoutManager;
    public static MainActivity mainActivity;
    private List<Desa> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Kecamatan " + Session.init(MainActivity.this).getString("kecamatan"));

        recyclerView = (RecyclerView) findViewById(R.id.rv_desa);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        clientService = RetrofitClient.connect().create(ClientService.class);
        mainActivity = this;

        FloatingActionButton fab = findViewById(R.id.fab_desa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RekapActivity.class);
                intent.putExtra("id", Session.init(MainActivity.this).getString("id"));
                MainActivity.this.startActivity(intent);
            }
        });

        refresh();
    }

    public void refresh() {
        Call<GetDesa> desaCall = clientService.getDesa(Session.init(MainActivity.this).getString("id"));
        desaCall.enqueue(new Callback<GetDesa>() {
            @Override
            public void onResponse(Call<GetDesa> call, Response<GetDesa> response) {
                List<Desa> desaList = response.body().getDesaList();
                Log.d("Retrofit Get", "Jumlah data laporan : " + String.valueOf(desaList.size()));
                adapter = new DesaAdapter(desaList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<GetDesa> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
            }
        });
    }

}