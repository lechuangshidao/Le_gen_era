package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.Lechuang.app.R;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class Set_password_Activity extends SimpleTopbarActivity {

    @Bind(R.id.edit_setpassword_callphone)
    EditText editSetpasswordCallphone;
    @Bind(R.id.edit_set_password)
    EditText editSetPassword;
    @Bind(R.id.button_setpwd_wancheng)
    Button buttonSetpwdWancheng;
    private String callphone;
    private String code;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password_);
        ButterKnife.bind(this);
        InitData();
    }
    private void InitData() {
        Intent intent=getIntent();
        callphone = intent.getStringExtra("callphone");
        code = intent.getStringExtra("VERIFICATIONCODE");
        editSetpasswordCallphone.setText(callphone);
    }

    @OnClick({ R.id.button_setpwd_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_setpwd_wancheng:
                String password = editSetPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)){
                    ToastUtil.showToast("密码不能为空");
                    return;
                }
                Intent intent_wancheng=new Intent(Set_password_Activity.this,Add_Information.class);
                intent_wancheng.putExtra("callphone",callphone);
                intent_wancheng.putExtra("VERIFICATIONCODE",code);
                intent_wancheng.putExtra("password",password);
                startActivity(intent_wancheng);
                break;
        }
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return CommonBackTopBtnFunc_or.class;
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
