package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.Lechuang.app.Bean.NoPetRegister;
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
    private NoPetRegister noPetRegister;
    private String user_id;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pet);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        sex = intent.getIntExtra("sex",1);
        callphone = intent.getStringExtra("callphone");
        nickname = intent.getStringExtra("nickname");
        birthday = intent.getStringExtra("birthday");
        user_id = intent.getStringExtra("user_id");
        token = intent.getStringExtra("token");
    }

    @OnClick({R.id.image_you, R.id.image_wu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_you:
                Intent intent_you=new Intent(Have_Or_Not_Pet.this,Have_pet_Activity.class);
                intent_you.putExtra("nickname",nickname);
                intent_you.putExtra("birthday",birthday);
                intent_you.putExtra("sex",sex);
                intent_you.putExtra("user_id",user_id);
                intent_you.putExtra("token",token);
                startActivity(intent_you);
                break;
            case R.id.image_wu:
                getnoPetRegister();
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
        switch (requestCode){
            case 100:
                if(1==returnCode){
                    noPetRegister = JSON.parseObject(returnData, NoPetRegister.class);
                    Intent intent_wu=new Intent(Have_Or_Not_Pet.this,Home_Pager.class);
                    startActivity(intent_wu);
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
    //无宠物注册
    private void getnoPetRegister(){
        Map<String,Object>params=new HashMap<>();
        params.put("nickname",nickname);
        params.put("user_id",user_id);
        params.put("token",token);
        params.put("userbirthday",birthday);
        params.put("userpicture","");
        params.put("sex",sex);
        okHttpPost(100, GlobalParam.USERREGISTRATION,params);
    }
}
