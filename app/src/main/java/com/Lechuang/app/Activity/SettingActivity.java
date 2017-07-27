package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.HelpUtils;

import java.io.IOException;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

import static www.xcd.com.mylibrary.utils.DialogUtil.handler;

public class SettingActivity extends SimpleTopbarActivity {

    private RelativeLayout updata_password, clear_cache, about;
    private Button exit;
    private TextView cache_size;
    private Thread thread;
    protected final int CLEANCATCHDISK_OK = 100000;
    protected final int CLEANCATCHDISK_FAILURE = 100001;

    @Override
    protected Object getTopbarTitle() {
        return R.string.setting;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        initView();
    }

    private void initView() {
        updata_password = (RelativeLayout) findViewById(R.id.updata_password);
        updata_password.setOnClickListener(this);
        clear_cache = (RelativeLayout) findViewById(R.id.clear_cache);
        clear_cache.setOnClickListener(this);
        cache_size = (TextView) findViewById(R.id.cache_size);
        about = (RelativeLayout) findViewById(R.id.about);
        about.setOnClickListener(this);
        exit = (Button) findViewById(R.id.exit);
        exit.setOnClickListener(this);
        String cacheSize = HelpUtils.getCacheSize();
        cache_size.setText(cacheSize);
    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.updata_password:
                startActivity(new Intent(SettingActivity.this, SettingPasswordActivity.class));
                break;
            case R.id.clear_cache:
                createDialogshow();
                thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            boolean cleanCatchDisk = HelpUtils.cleanCatchDisk(SettingActivity.this);
                            if (cleanCatchDisk) {
                                handler.sendEmptyMessage(CLEANCATCHDISK_OK);
                            } else {
                                handler.sendEmptyMessage(CLEANCATCHDISK_FAILURE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            handler.sendEmptyMessage(CLEANCATCHDISK_FAILURE);
                        }
                    }
                });
                thread.start();
                break;

            case R.id.about:
                Intent intent = new Intent(SettingActivity.this, AboutWebViewActivity.class);
                intent.putExtra("title", getString(R.string.about));
                intent.putExtra("type", "1");
                intent.putExtra("WebViewURL", "https://www.baidu.com");
                startActivity(intent);
                break;
            case R.id.exit:
                Intent in = new Intent("android.intent.action.LOGIN");
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                break;
        }
    }
    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {

    }

    @Override
    public void onCancelResult() {

    }

    @Override
    public void onErrorResult(int errorCode, IOException errorExcep) {

    }

    @Override
    public void onParseErrorResult(int errorCode) {

    }

    @Override
    public void onFinishResult() {

    }
}
