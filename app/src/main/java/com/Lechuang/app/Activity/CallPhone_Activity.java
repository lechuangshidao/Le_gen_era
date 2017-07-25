package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CallPhone_Activity extends AppCompatActivity{

    @Bind(R.id.edit_password)
    EditText editPassword;
    private TextView text_note_log;
    private String callphone;
    private EditText edit_callphone;
    private TextView image_next_log_note;

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
        image_next_log_note = (TextView) findViewById(R.id.image_next_log_note);
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


    @OnClick({R.id.text_log_quxiao, R.id.text_note_log, R.id.image_next_log_note})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_log_quxiao:
                finish();
                break;
            case R.id.text_note_log:
                Intent intent_note = new Intent(CallPhone_Activity.this, Note_Log_Activity.class);
                intent_note.putExtra("note_phone", callphone);
                startActivity(intent_note);
                break;
            case R.id.image_next_log_note:
                if (!TextUtils.isEmpty(edit_callphone.getText().toString().trim()) && !TextUtils.isEmpty(editPassword.getText().toString().trim())) {
                    Intent intent_main = new Intent(CallPhone_Activity.this, Home_Pager.class);
                    startActivity(intent_main);
                } else {
                    text_note_log.setEnabled(true);
                }
                break;
        }
    }
}
