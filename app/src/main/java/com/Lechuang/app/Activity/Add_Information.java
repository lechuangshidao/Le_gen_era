package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Add_Information extends SimpleTopbarActivity {

    @Bind(R.id.edit_add_name)
    EditText editAddName;
    @Bind(R.id.edit_age_date)
    EditText editAgeDate;
    @Bind(R.id.image_next_add)
    ImageView imageNextAdd;
    @Bind(R.id.image_wu_callback)
    ImageView imageWuCallback;
    private ImageView button_wancheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wu_add_information);
        ButterKnife.bind(this);
        getInitData();//初始化数据
    }

    //初始化数据
    private void getInitData() {
        Call_Phone_Utils.setEditTextInhibitInputSpeChat(editAddName);//宠物昵称禁止为特殊字符
    }

    @OnClick({R.id.image_wu_callback, R.id.image_next_add,R.id.image_wu_gerentouxiang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //回退
            case R.id.image_wu_callback:
                finish();
                break;
            //下一步
            case R.id.image_next_add:
                Intent intent = new Intent(Add_Information.this, Have_Or_Not_Pet.class);
                startActivity(intent);
                break;
            //个人头像
            case R.id.image_wu_gerentouxiang:
                break;
        }
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
