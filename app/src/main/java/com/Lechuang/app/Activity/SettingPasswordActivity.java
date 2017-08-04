package com.Lechuang.app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.SettingPasswordSaveTopBtnFunc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class SettingPasswordActivity extends SimpleTopbarActivity {

    private static Class<?> accountrightFuncArray[] = {SettingPasswordSaveTopBtnFunc.class};
    private EditText old_password, news_password, affirmnews_password;
    protected AlertDialog mUpgradeNotifyDialog;
    private String user_id;
    private String token;
    private String userlogin;
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
        user_id = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        userlogin = XCDSharePreference.getInstantiation(this).getSharedPreferences("userlogin");
        TextView login_account = (TextView) findViewById(R.id.login_account);
        login_account.setText(userlogin);
        old_password = (EditText) findViewById(R.id.old_password);
        old_password.setOnFocusChangeListener(this);
        news_password = (EditText) findViewById(R.id.news_password);
        news_password.setOnFocusChangeListener(this);
        affirmnews_password = (EditText) findViewById(R.id.affirmnews_password);
        affirmnews_password.setOnFocusChangeListener(this);
    }

    public void getSavePassword(){
        String strold_password = old_password.getText().toString().trim();
        if (TextUtils.isEmpty(strold_password)){
            ToastUtil.showToast("密码不能为空!");
            return;
        }
        String strnews_password = news_password.getText().toString().trim();
        if (TextUtils.isEmpty(strnews_password)){
            ToastUtil.showToast("新密码不能为空!");
            return;
        }
        String straffirmnews_password = affirmnews_password.getText().toString().trim();
        if (!strnews_password.equals(straffirmnews_password)){
            ToastUtil.showToast("两次密码不相同");
            return;
        }
        createDialogshow();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userlogin", userlogin);
        params.put("token", token);
        params.put("userpwd", strold_password);
        params.put("newpwd", strnews_password);
        okHttpPost(100, GlobalParam.PWDMODIFY, params);
    }

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
//                BaseApplication.getApp().exitApp();
                Intent in = new Intent("com.Lechuang.app.action.LOGIN");
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(in);
                mUpgradeNotifyDialog.dismiss();
            }
        });

        try {
            Activity activity = this;
            while (activity.getParent() != null) {
                activity = activity.getParent();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DialogTheme);
            mUpgradeNotifyDialog = builder.create();
            mUpgradeNotifyDialog.show();
            mUpgradeNotifyDialog.setContentView(serviceView);
            Window window = mUpgradeNotifyDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.dialog_animation);
            window.getDecorView().setPadding(0, 0, 0 ,0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (returnCode==1){
                    showUpgradeDialog();
                }
                ToastUtil.showToast(returnMsg);
                break;
        }
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

