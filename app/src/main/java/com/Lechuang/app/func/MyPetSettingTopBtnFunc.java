package com.Lechuang.app.func;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.Lechuang.app.Activity.SettingActivity;

import www.xcd.com.mylibrary.func.BaseTopTextViewFunc;

/**
 * Created by Android on 2017/7/19.
 */

public class MyPetSettingTopBtnFunc extends BaseTopTextViewFunc {

    public MyPetSettingTopBtnFunc(Activity activity) {
        super(activity);
    }

    @Override
    public int getFuncId() {
        return www.xcd.com.mylibrary.R.id.setting;
    }
    /** 功能文本 */
    protected String getFuncText() {
        return "设置";
    }

    protected int getFuncTextRes() {
        return www.xcd.com.mylibrary.R.string.setting;
    }

    @Override
    public void onclick(View v) {
        getActivity().startActivity(new Intent(getActivity(), SettingActivity.class));
    }
}

