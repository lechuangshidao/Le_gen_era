package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Note_Log_Activity extends AppCompatActivity {

    @Bind(R.id.edit_callphone_note)
    EditText editCallphoneNote;
    @Bind(R.id.edit_password_note)
    EditText editPasswordNote;
    @Bind(R.id.text_huoqu)
    TextView textHuoqu;
    @Bind(R.id.text_pwd_log)
    TextView textPwdLog;
    @Bind(R.id.image_next_note)
    ImageView imageNextNote;
    @Bind(R.id.text_log_quxiao)
    TextView textLogQuxiao;
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

    @OnClick({R.id.text_pwd_log, R.id.image_next_note, R.id.text_huoqu,R.id.text_log_quxiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //密码登录
            case R.id.text_pwd_log:
                /*Intent intent_pwd = new Intent(Note_Log_Activity.this, CallPhone_Activity.class);
                startActivity(intent_pwd);*/
                finish();
                break;
            //登录
            case R.id.image_next_note:
                Intent intent_note=new Intent(Note_Log_Activity.this,Home_Pager.class);
                startActivity(intent_note);
                break;
            //获取验证码
            case R.id.text_huoqu:
                break;
            case R.id.text_log_quxiao:
                finish();
                break;
        }
    }
}
