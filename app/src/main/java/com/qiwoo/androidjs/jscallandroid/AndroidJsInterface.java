package com.qiwoo.androidjs.jscallandroid;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import com.google.gson.Gson;
import com.qiwoo.androidjs.FriendDetailActivity;
import com.qiwoo.androidjs.User;

/**
 * @author yangpeng
 */
public class AndroidJsInterface {

    private Context context;

    public AndroidJsInterface(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void login(String json) {
        Toast.makeText(context, json, Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void callHostNativePage(int mode, String classNamePath, String actionPath, String schemePath, String paramsMap) {
        Gson gson = new Gson();
        Intent intent = new Intent();
        User user;
        switch (mode) {
            case 1:
                intent.setClassName(context, classNamePath);
                user = gson.fromJson(paramsMap, User.class);
                intent.putExtra("name", user.name);
                intent.putExtra("mobile", user.mobile);
                context.startActivity(intent);
                break;
            case 2:
                if (actionPath.equals("open_friend_detail")) {
                    intent.setClass(context, FriendDetailActivity.class);
                    user = gson.fromJson(paramsMap, User.class);
                    intent.putExtra("name", user.name);
                    intent.putExtra("mobile", user.mobile);
                    context.startActivity(intent);
                }
                break;
            case 3:
                Uri uri = Uri.parse(schemePath);
                if (uri.getScheme().equals("app")) {
                    if (uri.getAuthority().equals("qihoo.com")) {
                        user = gson.fromJson(paramsMap, User.class);
                        intent.setClass(context, FriendDetailActivity.class);
                        intent.putExtra("name", user.name);
                        intent.putExtra("mobile", user.mobile);
                        context.startActivity(intent);
                    }
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + mode);
        }
    }

    @JavascriptInterface
    public void openNativeApp(String pkg, String classNamePath) {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        ComponentName componentName = new ComponentName(pkg, classNamePath);
        intent.setComponent(componentName);
        context.startActivity(intent);
    }
}
