package com.qiwoo.androidjs;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.qiwoo.androidjs.androidcalljs.AndroidJsActivity;
import com.qiwoo.androidjs.jscallandroid.JsAndroidActivity;
import com.qiwoo.androidjs.security.SecurityActivity;

/**
 * @author yangpeng
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void security(View view) {
        SecurityActivity.start(this);
    }

    public void basic(View view) {
        BasicUsageActivity.start(this);
    }

    public void cookieSync(View view) {
        SyncCookieActivity.start(this);
    }

    public void androidJs(View view) {
        AndroidJsActivity.start(this);
    }

    public void jsAndroid(View view) {
        JsAndroidActivity.start(this);
    }
}