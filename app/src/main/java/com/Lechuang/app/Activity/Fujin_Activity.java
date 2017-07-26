package com.Lechuang.app.Activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.adapter.Fujin_Adapter;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Fujin_Activity extends SimpleTopbarActivity {

    @Bind(R.id.my_Closest_Itme)
    ListView myClosestItme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fujin_);
        ButterKnife.bind(this);
        //适配器
        Fujin_Adapter fujin_adapter=new Fujin_Adapter(this);
        myClosestItme.setAdapter(fujin_adapter);
        myClosestItme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                /*showCustomizeDialog();*/
            }
        });
    }
    private void showCustomizeDialog() {
    /* @setView 装入自定义View ==> R.layout.dialog_customize
     * 由于dialog_customize.xml只放置了一个EditView，因此和图8一样
     * dialog_customize.xml可自定义更复杂的View
     */
        final AlertDialog.Builder customizeDialog =
                new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.dialog_shop_item, null);
        customizeDialog.setView(view);
         ImageView image_dialog_back=  (ImageView) view.findViewById(R.id.image_fujin_back);
        TextView text_dialog_name=  (TextView) view.findViewById(R.id.text_fujin_name);
         Button button_dialog_send= (Button) view.findViewById(R.id.button_dialog_send);
        button_dialog_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customizeDialog.create().dismiss();
            }
        });
        image_dialog_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customizeDialog.create().dismiss();
            }
        });
        customizeDialog.create().show();
    }
    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
    }

    @Override
    protected Object getTopbarTitle() {
        return "离我最近";
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
