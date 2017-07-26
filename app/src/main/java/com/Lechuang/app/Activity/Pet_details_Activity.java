package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Pet_details_Activity extends SimpleTopbarActivity {
    @Bind(R.id.image_pet_details)
    ImageView imagePetDetails;
    @Bind(R.id.text_pet_details_name)
    TextView textPetDetailsName;
    @Bind(R.id.text_pet_details_label)
    TextView textPetDetailsLabel;
    @Bind(R.id.text_pet_details_date)
    TextView textPetDetailsDate;
    @Bind(R.id.text_pet_details_blood_lineage)
    TextView textPetDetailsBloodLineage;
    @Bind(R.id.text_pet_greet)
    TextView textPetGreet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_details_);
        ButterKnife.bind(this);

    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
    }

    @Override
    protected Object getTopbarTitle() {
        return "宠物详情";
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

    @OnClick(R.id.text_pet_greet)
    public void onViewClicked() {
    }
}
