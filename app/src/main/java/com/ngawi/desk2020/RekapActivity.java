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
        setTitle("Rekapitulasi Suara Masuk");

        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        WebView web = (WebView) findViewById(R.id.web_view);
        web.loadUrl("https://timdesk2020ngawi.com/cetak/api/" + getIntent().getStringExtra("id"));
        web.setWebViewClient(new WebViewClient());
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}