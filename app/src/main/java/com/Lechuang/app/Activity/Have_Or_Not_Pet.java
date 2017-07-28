package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.Lechuang.app.R;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Have_Or_Not_Pet extends SimpleTopbarActivity {

    @Bind(R.id.image_you)
    ImageView imageYou;
    @Bind(R.id.image_wu)
    ImageView imageWu;
    private String callphone;
    private String password;
    private String nickname;
    private String birthday;
    private String code;
    private int sex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pet);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        code = intent.getStringExtra("VERIFICATIONCODE");
        sex = intent.getIntExtra("sex",1);
        callphone = intent.getStringExtra("callphone");
        password = intent.getStringExtra("password");
        nickname = intent.getStringExtra("nickname");
        birthday = intent.getStringExtra("birthday");
    }

    @OnClick({R.id.image_you, R.id.image_wu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_you:
                Intent intent_you=new Intent(Have_Or_Not_Pet.this,Have_pet_Activity.class);
                intent_you.putExtra("callphone",callphone);
                intent_you.putExtra("password",password);
                intent_you.putExtra("nickname",nickname);
                intent_you.putExtra("birthday",birthday);
                intent_you.putExtra("VERIFICATIONCODE",code);
                intent_you.putExtra("sex",sex);
                intent_you.putExtra("type","");
                intent_you.putExtra("user_id","id");
                intent_you.putExtra("userarea","中国");
                startActivity(intent_you);
                break;
            case R.id.image_wu:
                Intent intent_wu=new Intent(Have_Or_Not_Pet.this,Home_Pager.class);
                startActivity(intent_wu);
                break;
        }
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return CommonBackTopBtnFunc_or.class;
    }

    @Override
    protected Object getTopbarTitle() {
        return "宠物资料";
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
