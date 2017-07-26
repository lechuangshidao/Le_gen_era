package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.Lechuang.app.R;
import com.Lechuang.app.adapter.FuJin_pet_Adapter;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class FuJin_pet_Activity extends SimpleTopbarActivity {

    @Bind(R.id.grid_item_pet)
    GridView gridItemPet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fu__jin_pet_);
        ButterKnife.bind(this);
        //列表适配器
        FuJin_pet_Adapter adapter=new FuJin_pet_Adapter(this);
        gridItemPet.setAdapter(adapter);
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
