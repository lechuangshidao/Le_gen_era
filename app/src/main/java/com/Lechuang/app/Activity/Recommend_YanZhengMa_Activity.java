package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.Lechuang.app.R;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Recommend_YanZhengMa_Activity extends SimpleTopbarActivity {

    @Bind(R.id.button_next_tijiao)
    Button  button_next_tijiao;
    @Bind(R.id.edit_callphone_note)
    EditText editCallphoneNote;
    @Bind(R.id.edit_password_note)
    EditText editPasswordNote;
    private String callphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce_yanzhengma_activity);
        ButterKnife.bind(this);
        getInitData();//初始化数据
    }

    //初始化数据
    private void getInitData() {
        Intent intent = getIntent();
        callphone = intent.getStringExtra("callphone");
        editCallphoneNote.setText(callphone);
    }
    @OnClick({ R.id.button_next_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_next_tijiao:
                //验证码不能为空
                if (!TextUtils.isEmpty(editPasswordNote.getText().toString())) {
                    Intent intent = new Intent(Recommend_YanZhengMa_Activity.this, Set_password_Activity.class);
                    intent.putExtra("login_password",callphone);
                    startActivity(intent);
                } else {
                    Toast.makeText(Recommend_YanZhengMa_Activity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
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
