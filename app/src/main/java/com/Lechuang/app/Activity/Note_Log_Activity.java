package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Note_Log_Activity extends SimpleTopbarActivity {

    @Bind(R.id.edit_callphone_note)
    EditText editCallphoneNote;
    @Bind(R.id.edit_password_note)
    EditText editPasswordNote;
    @Bind(R.id.text_huoqu)
    TextView textHuoqu;
    @Bind(R.id.text_pwd_log)
    TextView textPwdLog;
    @Bind(R.id.button_next_note)
    Button button_next_note;
    private TextView text_pwd_log;
    private ImageView image_next_note;
    private String note_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_log_activity);
        ButterKnife.bind(this);
        getInitData();//初始化数据
    }

    //初始化数据
    private void getInitData() {
        Intent intent = getIntent();
        note_phone = intent.getStringExtra("note_phone");
        editCallphoneNote.setText(note_phone);
        Call_Phone_Utils.setEditTextInhibitInputSpeChat(editCallphoneNote);//手机号禁止特殊字符
        Call_Phone_Utils.setEditTextInhibitInputSpeChat(editPasswordNote);//密码禁止特殊字符
    }

    @OnClick({R.id.text_pwd_log, R.id.button_next_note, R.id.text_huoqu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //密码登录
            case R.id.text_pwd_log:
                /*Intent intent_pwd = new Intent(Note_Log_Activity.this, CallPhone_Activity.class);
                startActivity(intent_pwd);*/
                finish();
                break;
            //登录
            case R.id.button_next_note:
                Intent intent_note=new Intent(Note_Log_Activity.this,Home_Pager.class);
                startActivity(intent_note);
                break;
            //获取验证码
            case R.id.text_huoqu:
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
