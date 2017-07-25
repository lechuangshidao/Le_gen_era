package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.widget.ListView;

import com.Lechuang.app.R;
import com.Lechuang.app.adapter.FuWu_Adapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.BaseActivity;

public class FuWuActivity extends BaseActivity {

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
        FuWu_Adapter  adapter=new FuWu_Adapter(this,list_date);
        listItem.setAdapter(adapter);
    }
    //初始化数据
    private void InitData() {
        for (int i = 0; i < 10; i++) {
            list_date.add("爱心宠物医院" + i);
        }
    }
}
