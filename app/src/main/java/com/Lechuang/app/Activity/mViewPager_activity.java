package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.base.LCBaseActivity;

public class mViewPager_activity extends LCBaseActivity implements View.OnClickListener {


    private TextView m_text_page_zhuce;
    private TextView m_text_page_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_view_pager_activity);
        getInitView();
    }

    //初始化控件
    private void getInitView() {
        m_text_page_zhuce = (TextView) findViewById(R.id.m_text_page_zhuce);
        m_text_page_login = (TextView) findViewById(R.id.m_text_page_login);
        m_text_page_login.setOnClickListener(this);
        m_text_page_zhuce.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.m_text_page_login:

                Intent intent_log = new Intent(mViewPager_activity.this, MainActivity.class);
//                Intent intent_log = new Intent(mViewPager_activity.this, StartActivity.class);
                startActivity(intent_log);
                break;
            case R.id.m_text_page_zhuce:
                Intent intent_zhuce = new Intent(mViewPager_activity.this, mZhuce_activity.class);
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
