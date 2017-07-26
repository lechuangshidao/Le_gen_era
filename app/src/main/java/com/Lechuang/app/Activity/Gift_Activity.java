package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.widget.GridView;

import com.Lechuang.app.R;
import com.Lechuang.app.adapter.Gift_Adapter;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Gift_Activity extends SimpleTopbarActivity {

    @Bind(R.id.grid_itme)
    GridView gridItme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_);
        ButterKnife.bind(this);
        //条目适配器
        Gift_Adapter gift_adapter=new Gift_Adapter(this);
        gridItme.setAdapter(gift_adapter);
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
    }

    @Override
    protected Object getTopbarTitle() {
        return "积分商城";
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
