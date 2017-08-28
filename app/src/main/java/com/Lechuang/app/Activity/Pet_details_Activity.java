package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Bean.PetDetails;
import com.Lechuang.app.R;
import com.Lechuang.app.Utils.GlideCircleTransform;
import com.Lechuang.app.entity.GlobalParam;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

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
        getPetDetails();
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
        switch (requestCode) {
            case 100:
                if (1 == returnCode) {
                    PetDetails petDetails = JSON.parseObject(returnData, PetDetails.class);
                    List<PetDetails.DataBean> data = petDetails.getData();
                    for (int i = 0; i < data.size(); i++) {
                        Glide.with(this).load(GlobalParam.IP + data.get(i).getPet_img()).transform(new GlideCircleTransform(this)).into(imagePetDetails);//宠物详情图片
                        textPetDetailsName.setText(data.get(i).getPet_name());//宠物详情名字
                        textPetDetailsLabel.setText(data.get(i).getPet_tag());//宠物详情标签x
                        textPetDetailsDate.setText(data.get(i).getPet_age());//宠物详情出生日期
                        textPetDetailsBloodLineage.setText(data.get(i).getType_name());//宠物详情品种
                    }
                } else {
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

    //打招呼
    @OnClick(R.id.text_pet_greet)
    public void onViewClicked() {
    }

    //获取宠物详情信息
    private void getPetDetails() {
        Intent intent = getIntent();
        String petid = intent.getStringExtra("petid");
        String token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("petid", petid);
        okHttpPost(100, GlobalParam.PETDETAILS, params);
    }
}
