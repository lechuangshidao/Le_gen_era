package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import com.Lechuang.app.R;
import com.Lechuang.app.func.MyPetSaveTextTopBtnFunc;

import java.io.IOException;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class MyPetAddLocationActivity extends SimpleTopbarActivity implements CompoundButton.OnCheckedChangeListener{

    private static Class<?> rightFuncArray[] = {MyPetSaveTextTopBtnFunc.class};
    private Switch switch_button;
    private EditText name,phone,address, region;
    @Override
    protected Object getTopbarTitle() {
        return "新增地址";
    }

    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return rightFuncArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetaddlocation);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        switch_button = (Switch) findViewById(R.id.switch_button);
        switch_button.setOnCheckedChangeListener(this);
        name = (EditText) findViewById(R.id.name);
        name.setOnFocusChangeListener(this);
        phone = (EditText) findViewById(R.id.phone);
        phone.setOnFocusChangeListener(this);
        region = (EditText) findViewById(R.id.region);
        region.setOnFocusChangeListener(this);
        address = (EditText) findViewById(R.id.address);
        address.setOnFocusChangeListener(this);
    }
    public void getSavaAddress(){
        ToastUtil.showToast("点击保存按钮");
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

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        ToastUtil.showToast(String.valueOf(b));
    }
}
