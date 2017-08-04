package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.Lechuang.app.Bean.MapHospital;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.FuwuAdapter;
import com.Lechuang.app.entity.GlobalParam;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class FuWuActivity extends SimpleTopbarActivity {

    @Bind(R.id.list_item)
    ListView listItem;
    List<String> list_date = new ArrayList<>();
    private List<MapHospital.DataBean> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuwu);
        ButterKnife.bind(this);
        getServiceInformation();
    }
    //左边功能
    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
    }

    @Override
    protected Object getTopbarTitle() {
        return "服务";
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if(1==returnCode){
                    MapHospital mapHospital = JSON.parseObject(returnData, MapHospital.class);
                    data = mapHospital.getData();
                    //适配器
                    FuwuAdapter adapter=new FuwuAdapter(this,data);
                    listItem.setAdapter(adapter);
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
    //获取服务的信息
    private void getServiceInformation(){
        createDialogshow();
        Intent intent=getIntent();
        String lng = intent.getStringExtra("lng");
        String lat = intent.getStringExtra("lat");
        String token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        String user_id = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        Map<String,Object>params=new HashMap<>();
        params.put("lng","116.40389843345");
        params.put("lat","39.915065193348");
        params.put("user_id",user_id);
        params.put("token",token);
        okHttpPost(100, GlobalParam.PETHOSPITAL,params);
    }
}
