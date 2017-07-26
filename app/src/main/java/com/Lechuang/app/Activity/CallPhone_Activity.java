package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class CallPhone_Activity extends SimpleTopbarActivity{

    @Bind(R.id.edit_password)
    EditText editPassword;
    private TextView text_note_log;
    private String callphone;
    private EditText edit_callphone;
    private TextView Text_next_log_note;

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
        edit_callphone = (EditText) findViewById(R.id.edit_callphone);
    }

    //初始化数据
    private void getInitData() {
        Intent intent = getIntent();
        callphone = intent.getStringExtra("callphone");
        edit_callphone.setText(callphone);
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
                if (!TextUtils.isEmpty(edit_callphone.getText().toString().trim()) && !TextUtils.isEmpty(editPassword.getText().toString().trim())) {
                    Intent intent_main = new Intent(CallPhone_Activity.this, Home_Pager.class);
                    startActivity(intent_main);
                } else {
                    Toast.makeText(CallPhone_Activity.this, "用户信息不能为空", Toast.LENGTH_SHORT).show();
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
