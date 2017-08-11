package com.Lechuang.app.Activity;

import android.app.AlertDialog;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Lechuang.app.Bean.PeopleNearby;
import com.Lechuang.app.Bean.PetSeek;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.Fujin_Adapter;
import com.Lechuang.app.adapter.PetSeek_Adapter;
import com.Lechuang.app.entity.GlobalParam;
import com.alibaba.fastjson.JSON;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class Fujin_Activity extends SimpleTopbarActivity {

    @Bind(R.id.my_Closest_Itme)
    ListView myClosestItme;
    private GoogleApiClient client;
    private List<PeopleNearby.DataBean> data;
    private String token;
    private Fujin_Adapter fujin_adapter;
    private List<PetSeek.DataBean> pet_data;
    private EditText edit_location;
    private ListView my_pet_seek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fujin_);
        ButterKnife.bind(this);
        my_pet_seek = (ListView) findViewById(R.id.my_Pet_Seek);
        edit_location = (EditText) findViewById(R.id.edit_location);
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        getPeopleNearby();//获取附近宠物
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        //软键盘回车键请求搜索宠物数据
        edit_location.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //这里注意要作判断处理，ActionDown、ActionUp都会回调到这里，不作处理的话就会调用两次
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    getPetSeek();
                    return true;
                }
                    return false;
            }
        });

    }
    //左边功能
    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
    }
    //标题
    @Override
    protected Object getTopbarTitle() {
        return "离我最近";
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode) {
            case 100:
                if (1 == returnCode) {
                    PeopleNearby peopleNearby = JSON.parseObject(returnData, PeopleNearby.class);
                    data = peopleNearby.getData();
                    //适配器
                    fujin_adapter = new Fujin_Adapter(this, data);
                    myClosestItme.setAdapter(fujin_adapter);
                    //弹出
                    myClosestItme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            showCustomizeDialog();
                        }
                    });
                } else {
                    ToastUtil.showToast(returnMsg);
                }
                break;
            case 200:
                if (1 == requestCode) {
                    PetSeek petSeek = JSON.parseObject(returnData, PetSeek.class);
                    pet_data = petSeek.getData();
                    PetSeek_Adapter seekAdapter=new PetSeek_Adapter(this,pet_data);
                    my_pet_seek.setAdapter(seekAdapter);
                    edit_location.addTextChangedListener(textWatcher);
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

    //获取附近人
    public void getPeopleNearby() {
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("mylat", "45.26589741");
        params.put("mylng", "98.2548741");
        okHttpPost(100, GlobalParam.PEOPLENEARBY, params);
    }

    //获取宠物搜索
    private void getPetSeek() {
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("mylat", "45.26589741");
        params.put("mylng", "98.2548741");
        params.put("petname", edit_location.getText().toString());
        okHttpPost(200, GlobalParam.PETSEEK, params);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Fujin_ Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
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
        ImageView image_dialog_back = (ImageView) view.findViewById(R.id.image_fujin_back);
        TextView text_dialog_name = (TextView) view.findViewById(R.id.text_fujin_name);
        Button button_dialog_send = (Button) view.findViewById(R.id.button_dialog_send);
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
    private TextWatcher textWatcher=new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(s.length()==0){
                myClosestItme.setVisibility(View.VISIBLE);
                my_pet_seek.setVisibility(View.GONE);
            }else{
                my_pet_seek.setVisibility(View.GONE);
                myClosestItme.setVisibility(View.VISIBLE);
            }
        }
    };
}
