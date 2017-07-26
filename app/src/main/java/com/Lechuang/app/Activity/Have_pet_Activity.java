package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Have_pet_Activity extends SimpleTopbarActivity {

    @Bind(R.id.image_add_gerentouxiang)
    ImageView imageAddGerentouxiang;
    @Bind(R.id.text_add_pet_name)
    TextView textAddPetName;
    @Bind(R.id.edit_pet_name_add)
    EditText editPetNameAdd;
    @Bind(R.id.edit_pet_age_add)
    EditText editPetAgeAdd;
    @Bind(R.id.text_add_pet_wancheng)
    TextView textAddPetWancheng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_pet_);
        ButterKnife.bind(this);
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return CommonBackTopBtnFunc_or.class;
    }

    @Override
    protected Object getTopbarTitle() {
        return "宠物信息";
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

    @OnClick({R.id.image_add_gerentouxiang, R.id.text_add_pet_name, R.id.edit_pet_age_add, R.id.text_add_pet_wancheng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_add_gerentouxiang:
                break;
            case R.id.text_add_pet_name:
                break;
            case R.id.edit_pet_age_add:
                break;
            case R.id.text_add_pet_wancheng:
                Intent intent_wancheng=new Intent(Have_pet_Activity.this,Home_Pager.class);
                startActivity(intent_wancheng);
                break;
        }
    }
}
