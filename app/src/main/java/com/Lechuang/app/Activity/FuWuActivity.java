package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.widget.ListView;

import com.Lechuang.app.R;
import com.Lechuang.app.adapter.FuwuAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class FuWuActivity extends SimpleTopbarActivity {

    @Bind(R.id.list_item)
    ListView listItem;
    List<String> list_date = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuwu);
        ButterKnife.bind(this);
        InitData();
        //适配器
        FuwuAdapter adapter=new FuwuAdapter(this,list_date);
        listItem.setAdapter(adapter);
    }
    //初始化数据
    private void InitData() {
        for (int i = 0; i < 10; i++) {
            list_date.add("爱心宠物医院" + i);
        }
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
