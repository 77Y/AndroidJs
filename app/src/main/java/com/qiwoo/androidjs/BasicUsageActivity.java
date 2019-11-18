package com.qiwoo.androidjs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @author yangpeng
 */
public class BasicUsageActivity extends AppCompatActivity {

    private WebView mWebView;

    public static void start(Context context) {
        Intent starter = new Intent(context, BasicUsageActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        getSupportActionBar().hide();

        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        settings.setDefaultTextEncodingName("utf-8");


        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        findViewById(R.id.btn_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mWebView.loadUrl("https://www.so.com/");
            }
        });

        findViewById(R.id.btn_local_url).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mWebView.loadUrl("file:///android_asset/local.html");
            }
        });


        findViewById(R.id.btn_local_html_code).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = "<html>\n" +
                        "<head>\n" +
                        "    <title>Android JS 交互</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h2>loadData 加载演示</h2>\n" +
                        "</body>\n" +
                        "</html>";

                mWebView.loadData(content, "text/html;charset=UTF-8", "UTF-8");
            }
        });

        findViewById(R.id.btn_local_html_code2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = "<html>\n" +
                        "<head>\n" +
                        "    <title>Android JS 交互</title>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h2>loadDataWithBaseURL 加载演示</h2>\n" +
                        "</body>\n" +
                        "</html>";

                mWebView.loadDataWithBaseURL(null, content, "text/html", "UTF-8", null);
            }
        });


        findViewById(R.id.btn_goback).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.goBack();
            }
        });

        findViewById(R.id.btn_goforward).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.goForward();
            }
        });

        findViewById(R.id.btn_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.reload();
            }
        });

        findViewById(R.id.btn_pageup).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.pageUp(true);
            }
        });

        findViewById(R.id.btn_pagedown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.pageDown(true);
            }
        });

        findViewById(R.id.btn_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.saveWebArchive(BasicUsageActivity.this.getCacheDir().getAbsolutePath() + "/basic.html");
            }
        });


    }
}
