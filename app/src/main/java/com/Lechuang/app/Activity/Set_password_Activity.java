package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Lechuang.app.Bean.SetUpPassword;
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

public class Set_password_Activity extends SimpleTopbarActivity {

    @Bind(R.id.text_setpassword_callphone)
    TextView text_setpassword_callphone;
    @Bind(R.id.edit_set_password)
    EditText editSetPassword;
    @Bind(R.id.button_setpwd_wancheng)
    Button buttonSetpwdWancheng;
    private String user_login;
    private String token;
    private String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password_);
        ButterKnife.bind(this);
        InitData();
    }
    private void InitData() {
        Intent intent=getIntent();
        user_login = intent.getStringExtra("userlogin");
        text_setpassword_callphone.setText(user_login);
        token = intent.getStringExtra("token");
        user_id = intent.getStringExtra("user_id");
    }

    @OnClick({ R.id.button_setpwd_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button_setpwd_wancheng:
                getSetUpPassword();
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
                    SetUpPassword setUpPassword = JSON.parseObject(returnData, SetUpPassword.class);
                    Intent intent_wancheng=new Intent(Set_password_Activity.this,Add_Information.class);
                    intent_wancheng.putExtra("user_login",user_login);
                    intent_wancheng.putExtra("user_id",user_id);
                    intent_wancheng.putExtra("token",token);
                    intent_wancheng.putExtra("user_pwd",editSetPassword.getText().toString());
                    startActivity(intent_wancheng);
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
    //设置注册密码
    public void getSetUpPassword(){
    Map<String,Object>params=new HashMap<>();
        params.put("user_id",user_id);
        params.put("userpassword",editSetPassword.getText().toString());
        params.put("phone",user_login);
        params.put("token",token);
        okHttpPost(100, GlobalParam.SETPASSWORD,params);
    }
}
