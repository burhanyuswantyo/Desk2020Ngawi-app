package com.ngawi.desk2020.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;


import com.ngawi.desk2020.R;
import com.ngawi.desk2020.api.ClientService;
import com.ngawi.desk2020.api.RetrofitClient;
import com.ngawi.desk2020.model.Data;

import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    List<Data> data;
    AdapterView.OnItemClickListener listener;
    ClientService clientService;


    public DataAdapter(List<Data> dataList) {
        data = dataList;
    }

    public Data getItem(int position) {
        return data.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        clientService = RetrofitClient.connect().create(ClientService.class);

        holder.tvNama.setText(data.get(position).getNama());
        holder.etJumlahSuara.setText(data.get(position).getJml_suara());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvNama;
        public EditText etJumlahSuara;
        public Button btnSimpan;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNama = (TextView) itemView.findViewById(R.id.tv_nama);
            etJumlahSuara = (EditText) itemView.findViewById(R.id.et_jumlahsuara);
        }
    }
}
