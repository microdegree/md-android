package org.microdegree.com.app.exp.ui.web;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import org.microdegree.com.app.exp.R;
import org.microdegree.com.app.exp.ui.notification.NotificationActivity;

public class WebActivity extends AppCompatActivity {
    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
        String  url = getIntent().getStringExtra("url");

        ImageView close = findViewById(R.id.close);

        close.setOnClickListener(view -> {
          finish();
        });



        mWebView = findViewById(R.id.webview);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        super.onCreate(savedInstanceState);

        mWebView.loadUrl(url);
    }


}
