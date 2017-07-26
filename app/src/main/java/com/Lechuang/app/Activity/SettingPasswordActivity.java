package com.Lechuang.app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Gallery;

import com.Lechuang.app.R;
import com.Lechuang.app.func.SettingPasswordSaveTopBtnFunc;

import java.io.IOException;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.base.application.BaseApplication;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class SettingPasswordActivity extends SimpleTopbarActivity {

    private static Class<?> accountrightFuncArray[] = {SettingPasswordSaveTopBtnFunc.class};
    private EditText old_password, news_password, affirmnews_password;
    protected AlertDialog mUpgradeNotifyDialog;

    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return accountrightFuncArray;
    }

    @Override
    protected Object getTopbarTitle() {
        return R.string.updata_password;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        initView();

    }

    private void initView() {
        old_password = (EditText) findViewById(R.id.old_password);
        old_password.setOnFocusChangeListener(this);
        news_password = (EditText) findViewById(R.id.news_password);
        news_password.setOnFocusChangeListener(this);
        affirmnews_password = (EditText) findViewById(R.id.affirmnews_password);
        affirmnews_password.setOnFocusChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ok:
//                loginDialog = DialogUtil.getProgressDialog(AccountUpDataPasswordActivity.this);
//                dialogshow();
                createDialogshow();
                String strold_password = old_password.getText().toString().trim();
                String strnews_password = news_password.getText().toString().trim();
                String straffirmnews_password = affirmnews_password.getText().toString().trim();

                break;
        }
    }
    public void getSavePassword(){
        ToastUtil.showToast("修改密码按钮");
    }
    @Override
    protected void onDestroyDeatchView() {
        super.onDestroyDeatchView();

        handler.removeCallbacksAndMessages(null);
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    showUpgradeDialog();
                    break;
            }
        }
    };
    private void showUpgradeDialog() {
        LayoutInflater factor = (LayoutInflater) SettingPasswordActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View serviceView = factor.inflate(R.layout.settingpassword_dialog, null);
        serviceView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUpgradeNotifyDialog.isShowing()){
                    mUpgradeNotifyDialog.dismiss();
                }
            }
        });
        serviceView.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(AccountUpDataPasswordActivity.this, LoginActivity.class);
//                startActivityForResult(intent,5);
                BaseApplication.getApp().exitApp();
                mUpgradeNotifyDialog.dismiss();
            }
        });

        try {
            Activity activity = SettingPasswordActivity.this;
            while (activity.getParent() != null) {
                activity = activity.getParent();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(SettingPasswordActivity.this);
            mUpgradeNotifyDialog = builder.create();
            mUpgradeNotifyDialog.show();
            mUpgradeNotifyDialog.setContentView(serviceView);
            mUpgradeNotifyDialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
            FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(Gallery.LayoutParams.FILL_PARENT, Gallery.LayoutParams.WRAP_CONTENT);
            //layout.setMargins(WallspaceUtil.dip2px(this, 10), 0, FeatureFunction.dip2px(this, 10), 0);
            serviceView.setLayoutParams(layout);
        } catch (Exception e) {
            e.printStackTrace();
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

