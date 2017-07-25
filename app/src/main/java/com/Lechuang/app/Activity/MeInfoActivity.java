package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.base.BaseDataActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.util.TimeUtil;

public class MeInfoActivity extends BaseDataActivity {

    private static final int REQUEST_TO_DATE_PICKER = 33;
    private TextView select_birthday,sex_man,sex_woman;
    private ImageView image_man,image_woman;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private RelativeLayout relat_woman,relat_man;
    private EditText edit_name;
    @Override
    protected Object getTopbarTitle() {
        return R.string.meinfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meinfo);
        initView();
    }

    public void initView() {
        select_birthday = (TextView) findViewById(R.id.select_birthday);
        select_birthday.setOnClickListener(this);
        relat_man = (RelativeLayout) findViewById(R.id.relat_man);
        relat_man.setOnClickListener(this);
        relat_woman = (RelativeLayout) findViewById(R.id.relat_woman);
        relat_woman.setOnClickListener(this);
        sex_man = (TextView) findViewById(R.id.sex_man);
        sex_woman = (TextView) findViewById(R.id.sex_woman);
        image_woman = (ImageView) findViewById(R.id.image_woman);
        image_man = (ImageView) findViewById(R.id.image_man);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_name.setOnFocusChangeListener(this);
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
        switch (v.getId()){
            case R.id.select_birthday:
                Log.e("TAG_","DIANJI");
                toActivity(DatePickerWindow.createIntent(MeInfoActivity.this, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
                break;
            case R.id.relat_man:
                sex_man.setBackgroundResource(R.drawable.shape_y_solid_orange);
                sex_man.setTextColor(getResources().getColor(R.color.orange));
                image_man.setVisibility(View.VISIBLE);
                sex_woman.setBackgroundResource(R.drawable.shape_y_solid_black);
                sex_woman.setTextColor(getResources().getColor(R.color.black_33));
                image_woman.setVisibility(View.INVISIBLE);
                break;
            case R.id.relat_woman:
                sex_man.setBackgroundResource(R.drawable.shape_y_solid_black);
                sex_man.setTextColor(getResources().getColor(R.color.black_33));
                image_man.setVisibility(View.INVISIBLE);
                sex_woman.setBackgroundResource(R.drawable.shape_y_solid_orange);
                sex_woman.setTextColor(getResources().getColor(R.color.orange));
                image_woman.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode){
            case REQUEST_TO_DATE_PICKER:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                    if (list != null && list.size() >= 3) {

                        selectedDate = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            selectedDate[i] = list.get(i);
                        }
                        select_birthday.setText(selectedDate[0]+"-"+(selectedDate[1]+1)+"-"+selectedDate[2]);
                    }
                }
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
    public Activity getActivity() {
        return this;
    }
}
