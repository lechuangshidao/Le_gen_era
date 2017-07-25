package com.Lechuang.app.func;

import android.app.Activity;
import android.view.View;

import com.Lechuang.app.Activity.MyPetLocationCompileActivity;

import www.xcd.com.mylibrary.func.BaseTopTextViewFunc;

/**
 * Created by Android on 2017/7/19.
 */

public class MyPetCompileTopBtnFunc extends BaseTopTextViewFunc {

    public MyPetCompileTopBtnFunc(Activity activity) {
        super(activity);
    }

    @Override
    public int getFuncId() {
        return www.xcd.com.mylibrary.R.id.setting;
    }
    /** 功能文本 */
    protected String getFuncText() {
        return "保存";
    }

    protected int getFuncTextRes() {
        return www.xcd.com.mylibrary.R.string.save;
    }

    @Override
    public void onclick(View v) {
        ((MyPetLocationCompileActivity)getActivity()).getSavaAddress();
    }
}

