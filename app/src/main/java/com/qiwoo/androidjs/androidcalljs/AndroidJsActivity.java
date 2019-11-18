package com.qiwoo.androidjs.androidcalljs;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.qiwoo.androidjs.R;


/**
 * @author yangpeng
 */
public class AndroidJsActivity extends AppCompatActivity {

    private WebView mWebView;

    public static void start(Context context) {
        Intent starter = new Intent(context, AndroidJsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_url);
        getSupportActionBar().hide();

        mWebView = findViewById(R.id.webview);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");

        mWebView.loadUrl("http://democome.com:8080/");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });

        // 设置为黑色
        findViewById(R.id.btn_load_url_black).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String method = "javascript:updateBackgroundColor(\"#000000\")";
                mWebView.loadUrl(method);
            }
        });

        // 设置为黑色
        findViewById(R.id.btn_evaluate_white).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                mWebView.evaluateJavascript("updateBackgroundColor(\"#FFFFFF\")", new ValueCallback<String>() {

                    @Override
                    public void onReceiveValue(String value) {
                    }
                });

            }
        });

        // 设置为白色
        findViewById(R.id.btn_evaluate_get).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                mWebView.evaluateJavascript("readBackgroundColor()", new ValueCallback<String>() {

                    @Override
                    public void onReceiveValue(String value) {
                        Toast.makeText(AndroidJsActivity.this, "当前背景颜色是 :" + value, Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
