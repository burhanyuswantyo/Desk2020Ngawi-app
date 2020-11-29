package com.ngawi.desk2020.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ngawi.desk2020.DataActivity;
import com.ngawi.desk2020.R;
import com.ngawi.desk2020.TpsActivity;
import com.ngawi.desk2020.model.Tps;

import java.util.List;

public class TpsAdapter extends RecyclerView.Adapter<TpsAdapter.ViewHolder> {
    List<Tps> tps;
    AdapterView.OnItemClickListener listener;
    TpsActivity tpsActivity = new TpsActivity();

    public TpsAdapter(List<Tps> tpsList) {
        tps = tpsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tps_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvTps.setText("TPS " + tps.get(position).getTps());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DataActivity.class);
                intent.putExtra("tps_id", tps.get(position).getId());
                intent.putExtra("tps", tps.get(position).getTps());
                intent.putExtra("desa", tpsActivity.getDesa());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTps;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTps = (TextView) itemView.findViewById(R.id.tv_tps);
        }
    }
}
