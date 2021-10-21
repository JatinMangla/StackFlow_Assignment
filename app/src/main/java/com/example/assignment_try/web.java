package com.example.assignment_try;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class web extends AppCompatActivity {

    WebView web;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        swipeRefreshLayout= findViewById(R.id.swiperefresh);
        web= findViewById(R.id.web);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                LoadWeb();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        LoadWeb();

    }

    private void LoadWeb() {

        web.setWebViewClient(new WebViewClient());
        String url = getIntent().getStringExtra("service");
        web.loadUrl(String.valueOf(url));
    }

    @Override
    public void onBackPressed() {
        if(web.canGoBack())
        {
            web.goBack();
        }
        else {
            super.onBackPressed();
        }

    }
}