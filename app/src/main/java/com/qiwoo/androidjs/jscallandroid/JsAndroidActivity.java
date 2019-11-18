package com.qiwoo.androidjs.jscallandroid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.qiwoo.androidjs.R;


/**
 * @author yangpeng
 */
public class JsAndroidActivity extends AppCompatActivity {

    private WebView mWebView;

    public static void start(Context context) {
        Intent starter = new Intent(context, JsAndroidActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js_interface);

        getSupportActionBar().hide();

        mWebView = findViewById(R.id.webview);

        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDefaultTextEncodingName("utf-8");

        mWebView.addJavascriptInterface(new AndroidJsInterface(this), "AndroidJsInterface");

        mWebView.setWebViewClient(new WebViewClient() {
                                      @Override
                                      public boolean shouldOverrideUrlLoading(WebView view, String url) {
                                          Uri uri = Uri.parse(url);

                                          if (uri.getScheme().equals("qihoo")) {
                                              if (uri.getAuthority().equals("login")) {
                                                  String mobile = uri.getQueryParameter("mobile");
                                                  String code = uri.getQueryParameter("code");
                                                  Toast.makeText(JsAndroidActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                                              }

                                              return true;
                                          }
                                          return super.shouldOverrideUrlLoading(view, url);
                                      }
                                  }
        );

        mWebView.setWebChromeClient(new WebChromeClient() {
                                        @Override
                                        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
//                                            Uri uri = Uri.parse(message);
//
//                                            if (uri.getScheme().equals("qihoo")) {
//                                                if (uri.getAuthority().equals("login")) {
//                                                    String mobile = uri.getQueryParameter("mobile");
//                                                    String code = uri.getQueryParameter("code");
//                                                    result.confirm("登录成功");
//                                                }
//                                                return true;
//                                            }
                                            return super.onJsPrompt(view, url, message, defaultValue, result);
                                        }

                                        @Override
                                        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
//                                            result.confirm();
                                            return super.onJsAlert(view, url, message, result);
                                        }

                                        @Override
                                        public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
//                                            result.cancel();
//                                            result.confirm();
                                            return super.onJsConfirm(view, url, message, result);
                                        }
                                    }
        );

//        mWebView.loadUrl("http://democome.com:8080/");
        mWebView.loadUrl("file:///android_asset/local.html");
    }
}
