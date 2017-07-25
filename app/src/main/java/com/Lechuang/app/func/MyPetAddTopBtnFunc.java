package com.Lechuang.app.func;

import android.app.Activity;
import android.view.View;

import com.Lechuang.app.R;

import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;
import www.xcd.com.mylibrary.utils.ToastUtil;

/**
 * Created by Android on 2017/7/19.
 */

public class MyPetAddTopBtnFunc extends BaseTopImageBtnFunc {

    public MyPetAddTopBtnFunc(Activity activity) {
        super(activity);
    }

    @Override
    public int getFuncId() {
        return R.id.main_topbar_add;
    }

    @Override
    public int getFuncIcon() {
        return R.mipmap.image_juchi;
    }

    @Override
    public void onclick(View v) {
        ToastUtil.showToast(getActivity().getResources().getString(www.xcd.com.mylibrary.R.string.ing));
    }
}

