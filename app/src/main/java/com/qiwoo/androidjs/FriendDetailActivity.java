package com.qiwoo.androidjs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


/**
 * @author yangpeng
 */
public class FriendDetailActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, FriendDetailActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);
        getSupportActionBar().setTitle("好友详情");

        String name = getIntent().getStringExtra("name");
        String mobile = getIntent().getStringExtra("mobile");

        TextView tvName = findViewById(R.id.tv_note_name);
        tvName.setText(name);

        TextView tvMobile = findViewById(R.id.tv_phone_no);
        tvMobile.setText(mobile);
    }

}
