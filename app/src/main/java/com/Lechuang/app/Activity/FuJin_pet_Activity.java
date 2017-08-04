package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.Lechuang.app.Bean.PetInformation;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.FuJin_pet_Adapter;
import com.Lechuang.app.entity.GlobalParam;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class FuJin_pet_Activity extends SimpleTopbarActivity {

    @Bind(R.id.grid_item_pet)
    GridView gridItemPet;
    private List<PetInformation.DataBean> data;
    private String petid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu__jin_pet_);
        ButterKnife.bind(this);
        getPetInformation();//获取宠物信息
        gridItemPet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent_details=new Intent(FuJin_pet_Activity.this,Pet_details_Activity.class);
                startActivity(intent_details);
            }
        });
    }

    //标题
    @Override
    protected Object getTopbarTitle() {
        return "哈士奇";
    }

    //左边功能
    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if(1==returnCode){
                    PetInformation petInformation = JSON.parseObject(returnData, PetInformation.class);
                    data = petInformation.getData();
                    //列表适配器
                    FuJin_pet_Adapter adapter=new FuJin_pet_Adapter(this, data);
                    gridItemPet.setAdapter(adapter);
                    //点击跳到宠物详情
                    gridItemPet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            petid = data.get(position).getPetid();
                            Intent intent=new Intent(FuJin_pet_Activity.this, Pet_details_Activity.class);
                            intent.putExtra("petid",petid);
                            startActivity(intent);
                        }
                    });
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
    //获取宠物信息
    private void getPetInformation(){
        String token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        Map<String,Object>params=new HashMap<>();
        params.put("token",token);
        params.put("mylat","40.110039");
        params.put("mylng","116.300632");
        params.put("typeid","11");
        okHttpPost(100, GlobalParam.PETINFORMATION,params);
    }
}
