package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.Lechuang.app.R;

import www.xcd.com.mylibrary.activity.PermissionsActivity;
import www.xcd.com.mylibrary.base.activity.BaseActivity;

import static www.xcd.com.mylibrary.activity.PermissionsActivity.PERMISSIONS_GRANTED;

public class ViewPager_Activity extends BaseActivity implements View.OnClickListener {

    private TextView m_text_page_zhuce;
    private TextView m_text_page_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_view_pager_activity);
        getInitView();
        PermissionsActivity.startActivityForResult(this, PERMISSIONS_GRANTED, PERMISSIONS);
    }

    //初始化控件
    private void getInitView() {
        m_text_page_zhuce = (TextView) findViewById(R.id.m_text_page_zhuce);
        m_text_page_login = (TextView) findViewById(R.id.m_text_page_login);
        m_text_page_login.setOnClickListener(this);
        m_text_page_zhuce.setOnClickListener(this);
        AnimationSet set = new AnimationSet(true);
        TranslateAnimation translate = new TranslateAnimation(
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0,
                Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, 0);
        translate.setDuration(1500);
        set.addAnimation(translate);
        set.setFillAfter(true);
        m_text_page_zhuce.offsetTopAndBottom(-m_text_page_zhuce.getHeight() / 2);
        m_text_page_zhuce.startAnimation(set);
        m_text_page_login.offsetTopAndBottom(-m_text_page_login.getHeight() / 2);
        m_text_page_login.startAnimation(set);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.m_text_page_login:
                Intent intent_log = new Intent(ViewPager_Activity.this, MainActivity.class);
                startActivity(intent_log);
                break;
            case R.id.m_text_page_zhuce:
                Intent intent_zhuce = new Intent(ViewPager_Activity.this, Zhuce_Activity.class);
                startActivity(intent_zhuce);
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }
}
