package com.qiwoo.androidjs.security;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

/**
 * @author yangpeng
 */
public class SecurityInterface {

    private Context context;

    public SecurityInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void add(int a, int b) {
        Toast.makeText(context, "计算结果是" + (a + b), Toast.LENGTH_SHORT).show();
    }
}
