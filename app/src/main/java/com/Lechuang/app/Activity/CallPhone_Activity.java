package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Lechuang.app.Bean.LoginInfo;
import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;


public class CallPhone_Activity extends SimpleTopbarActivity{

    @Bind(R.id.edit_password)
    EditText editPassword;
    private TextView text_note_log;
    private String callphone;
    private TextView Text_next_log_note;
    private LoginInfo logininfo ;
    private TextView text_callphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_phone_activity);
        ButterKnife.bind(this);
        getInitView();
        getInitData();
    }

    //初始化控件
    private void getInitView() {
        Text_next_log_note = (TextView) findViewById(R.id.Text_next_log_note);
        text_note_log = (TextView) findViewById(R.id.text_note_log);
        text_callphone = (TextView) findViewById(R.id.Text_callphone);

    }

    //初始化数据
    private void getInitData() {
        Intent intent = getIntent();
        callphone = intent.getStringExtra("callphone");
        text_callphone.setText(callphone);
        Call_Phone_Utils.setEditTextInhibitInputSpeChat(editPassword);//密码框禁止特殊字符
    }


    @OnClick({ R.id.text_note_log, R.id.Text_next_log_note})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_note_log:
                Intent intent_note = new Intent(CallPhone_Activity.this, Note_Log_Activity.class);
                intent_note.putExtra("note_phone", callphone);
                startActivity(intent_note);
                break;
            case R.id.Text_next_log_note:
                editPassword.setText("123");
                String userlogin = text_callphone.getText().toString().trim();
                String userpwd = editPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(userlogin) && !TextUtils.isEmpty(userpwd)) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("userlogin", userlogin);
                    params.put("userpwd", userpwd);
                    params.put("type", "pwd");
                    okHttpPost(100, GlobalParam.LOGIN, params);
                } else {
                    Toast.makeText(CallPhone_Activity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    text_note_log.setEnabled(true);
                }
                break;
        }
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return CommonBackTopBtnFunc_or.class;
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (1==returnCode){
                    logininfo = JSON.parseObject(returnData, LoginInfo.class);
                    String token = logininfo.getData().getToken();
                    String user_id = logininfo.getData().getUser_id();
                    XCDSharePreference.getInstantiation(this).setSharedPreferences("user_id", user_id);
                    XCDSharePreference.getInstantiation(this).setSharedPreferences("token", token);
                    Intent intent_main = new Intent(CallPhone_Activity.this, Home_Pager.class);
                    startActivity(intent_main);
                }else {
                    ToastUtil.showToast(returnMsg);
                }
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
