package com.qiwoo.androidjs.security;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.qiwoo.androidjs.R;

/**
 * @author yangpeng
 */
public class SecurityActivity extends AppCompatActivity {

    private WebView mWebView;

    public static void start(Context context) {
        Intent starter = new Intent(context, SecurityActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");

        mWebView.addJavascriptInterface(new SecurityInterface(this), "SecurityInterface");
        mWebView.loadUrl("file:///android_asset/security.html");

    }

}