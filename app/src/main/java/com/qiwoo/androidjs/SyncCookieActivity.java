package com.qiwoo.androidjs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * @author yangpeng
 */
public class SyncCookieActivity extends AppCompatActivity {

    private WebView mWebView;

    public static void start(Context context) {
        Intent starter = new Intent(context, SyncCookieActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_cookie_webivew);
        getSupportActionBar().hide();

        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.getSettings().setUserAgentString(
                mWebView.getSettings().getUserAgentString().concat("Android-APP")
        );

        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {

        });

        Map<String, String> headers = new HashMap<>(1);
        headers.put("version", "1.0.0");

        syncCookie("http://democome.com", "abc", "123");
        mWebView.loadUrl("http://democome.com:8080/", headers);
    }

    public void syncCookie(String url, String q, String t) {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.setCookie(url, "Q=" + q + "&T=" + t);
    }
}
