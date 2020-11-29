package com.ngawi.desk2020.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ngawi.desk2020.MainActivity;
import com.ngawi.desk2020.R;
import com.ngawi.desk2020.TpsActivity;
import com.ngawi.desk2020.api.ClientService;
import com.ngawi.desk2020.api.RetrofitClient;
import com.ngawi.desk2020.api.Session;
import com.ngawi.desk2020.model.Desa;

import org.json.JSONObject;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class DesaAdapter extends RecyclerView.Adapter<DesaAdapter.ViewHolder> {
    List<Desa> desa;
    AdapterView.OnItemClickListener listener;

    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;
    EditText etPassword;
    TextView tvJudul;
    Button btnLogin;

    public DesaAdapter(List<Desa> desaList) {
        desa = desaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desa_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvDesa.setText(desa.get(position).getDesa());
        holder.tvNomor.setText(String.valueOf(position + 1));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new AlertDialog.Builder(holder.itemView.getContext());
                inflater = (LayoutInflater) holder.itemView.getContext().getSystemService(holder.itemView.getContext().LAYOUT_INFLATER_SERVICE);
                dialogView = inflater.inflate(R.layout.login_dialog, null);
                dialog.setView(dialogView);
                dialog.setCancelable(true);

                AlertDialog alertDialog = dialog.create();


                tvJudul = (TextView) dialogView.findViewById(R.id.tv_judul);
                etPassword = (EditText) dialogView.findViewById(R.id.et_password2);
                btnLogin = (Button) dialogView.findViewById(R.id.btn_login2);

                etPassword.setText("");
                tvJudul.setText("Desa " + holder.tvDesa.getText());

                btnLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(etPassword.getText())) {
                            etPassword.setError("Password Harus Diisi!");
                            Toast.makeText(holder.itemView.getContext(), "Password Harus Diisi!", Toast.LENGTH_LONG).show();
                        } else {
                            Log.d("test", "id : " + desa.get(position).getId() + "password : " + etPassword.getText().toString());
                            Retrofit retrofit = RetrofitClient.connect();
                            ClientService service = retrofit.create(ClientService.class);
                            final Call<ResponseBody> request = service.loginDesa(desa.get(position).getId(), etPassword.getText().toString(), Session.init(holder.itemView.getContext()).getString("kecamatan"));
                            request.enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.body() != null) {
                                        try {
                                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                            Toast.makeText(holder.itemView.getContext(), "Login Berhasil!", Toast.LENGTH_SHORT).show();
                                            JSONObject data = jsonRESULTS.getJSONObject("data");
                                            Intent intent = new Intent(holder.itemView.getContext(), TpsActivity.class);
                                            intent.putExtra("desa_id", desa.get(position).getId());
                                            intent.putExtra("desa", desa.get(position).getDesa());
                                            holder.itemView.getContext().startActivity(intent);
                                            alertDialog.dismiss();
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            Toast.makeText(holder.itemView.getContext(), "Login Gagal, Periksa Koneksi Anda!", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(holder.itemView.getContext(), "Password Salah!", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(holder.itemView.getContext(), "Login Gagal, Periksa Koneksi Anda", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return desa.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDesa, tvNomor;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNomor = (TextView) itemView.findViewById(R.id.tv_nomor);
            tvDesa = (TextView) itemView.findViewById(R.id.tv_Desa);

        }
    }
}
