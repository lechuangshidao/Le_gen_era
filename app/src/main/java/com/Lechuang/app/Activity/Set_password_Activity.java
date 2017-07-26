package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
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

public class Set_password_Activity extends SimpleTopbarActivity {

    @Bind(R.id.edit_setpassword_callphone)
    EditText editSetpasswordCallphone;
    @Bind(R.id.edit_set_password)
    EditText editSetPassword;
    @Bind(R.id.button_setpwd_wancheng)
    Button buttonSetpwdWancheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password_);
        ButterKnife.bind(this);
        InitData();
    }
    private void InitData() {
        Intent intent=getIntent();
        String login_password = intent.getStringExtra("login_password");
        editSetpasswordCallphone.setText(login_password);
    }

    @OnClick({ R.id.button_setpwd_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_setpwd_wancheng:
                Intent intent_wancheng=new Intent(Set_password_Activity.this,Add_Information.class);
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
