package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.widget.GridView;

import com.Lechuang.app.Bean.ShoppingMall;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.Gift_Adapter;
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

public class Gift_Activity extends SimpleTopbarActivity {

    @Bind(R.id.grid_itme)
    GridView gridItme;
    private List<ShoppingMall.DataBean> data;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gift_);
        ButterKnife.bind(this);
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        getShoppingmall();
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
        switch (requestCode){
            case 100:
                if(1==returnCode){
                    ShoppingMall shoppingMall = JSON.parseObject(returnData, ShoppingMall.class);
                    data = shoppingMall.getData();
                    //条目适配器
                    Gift_Adapter gift_adapter=new Gift_Adapter(this,data,token);
                    gridItme.setAdapter(gift_adapter);
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
    private void getShoppingmall(){
        createDialogshow();
        Map<String,Object>params=new HashMap<>();
        params.put("token",token);
        okHttpPost(100, GlobalParam.SHOPPINGMALL,params);
    }
}
