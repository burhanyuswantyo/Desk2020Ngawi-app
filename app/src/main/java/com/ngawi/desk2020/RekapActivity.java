package com.ngawi.desk2020;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class RekapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rekap);
        setTitle("Data Suara Masuk");

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView web = (WebView) findViewById(R.id.web_view);
        web.loadUrl("http://192.168.75.1/penghitungansuara/cetak/api/" + getIntent().getStringExtra("desa_id"));
        web.loadUrl("https://yus1.xyz/cetak/api/" + getIntent().getStringExtra("desa_id"));
        web.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}