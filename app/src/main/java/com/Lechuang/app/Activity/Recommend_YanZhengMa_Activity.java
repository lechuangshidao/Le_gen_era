package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Lechuang.app.Bean.VerificationCode;
import com.Lechuang.app.R;
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

public class Recommend_YanZhengMa_Activity extends SimpleTopbarActivity {

    @Bind(R.id.button_next_tijiao)
    Button  button_next_tijiao;
    @Bind(R.id.text_callphone_note_register)
    TextView text_callphone_note_register;
    @Bind(R.id.edit_password_note)
    EditText editPasswordNote;
    private String callphone;
    private String token;
    private String user_id;

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
        text_callphone_note_register.setText(callphone);
        token = intent.getStringExtra("token");
        user_id = intent.getStringExtra("user_id");
    }
    @OnClick({ R.id.button_next_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_next_tijiao:
                //验证码不能为空
                if (!TextUtils.isEmpty(editPasswordNote.getText().toString().trim())) {
                    getVerificationCode();
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
        switch (requestCode){
            case 100:
                if(1==returnCode){
                    VerificationCode verificationCode = JSON.parseObject(returnData, VerificationCode.class);
                    Intent intent = new Intent(Recommend_YanZhengMa_Activity.this, Set_password_Activity.class);
                    intent.putExtra("userlogin",callphone);
                    intent.putExtra("token",token);
                    intent.putExtra("user_id",user_id);
                    startActivity(intent);
                }else{
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
    //验证码验证
    private void getVerificationCode(){
        Map<String,Object>params=new HashMap<>();
        params.put("phone",callphone);
        params.put("code",editPasswordNote.getText().toString().trim());
        okHttpPost(100, GlobalParam.VERIFICATIONCODE,params);
    }
}
