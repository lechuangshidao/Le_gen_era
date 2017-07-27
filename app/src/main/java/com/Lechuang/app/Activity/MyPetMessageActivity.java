package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.HelpUtils;
import com.Lechuang.app.base.BaseDataActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import www.xcd.com.mylibrary.utils.ToastUtil;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.util.TimeUtil;

public class MyPetMessageActivity extends BaseDataActivity {

    private static final int REQUEST_TO_DATE_PICKER = 34;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private TextView select_birthday;
    private EditText edit_name,select_breed;
    private ImageView meinfo_head;
    @Override
    protected Object getTopbarTitle() {
        return R.string.petmessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetmessage);

    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        initView();
    }

    public void initView() {
        select_birthday = (TextView) findViewById(R.id.select_birthday);
        select_birthday.setOnClickListener(this);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_name.setOnFocusChangeListener(this);
        select_breed = (EditText) findViewById(R.id.select_breed);
        select_breed.setOnFocusChangeListener(this);
        meinfo_head = (ImageView) findViewById(R.id.meinfo_head);
        meinfo_head.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.select_birthday:
                toActivity(DatePickerWindow.createIntent(MyPetMessageActivity.this, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
              break;
            case R.id.meinfo_head:
                break;
        }
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_TO_DATE_PICKER:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                    if (list != null && list.size() >= 3) {

                        selectedDate = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            selectedDate[i] = list.get(i);
                        }
                        String time = selectedDate[0] + "-" + (selectedDate[1]+1) + "-" + selectedDate[2];
                        String timeDifference = HelpUtils.getTimeDifference(time);
                        if (timeDifference.indexOf("-1") != -1) {
                            ToastUtil.showToast("请选择正确出生日期");
                        } else {
                            select_birthday.setText(timeDifference);
                        }

                    }
                }
                break;
        }

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
