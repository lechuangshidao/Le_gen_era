package com.Lechuang.app.func;

import android.app.Activity;
import android.view.View;

import com.Lechuang.app.Activity.MyPetActivity;
import com.Lechuang.app.R;

import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;

public class MyPetAddTopRightBtnFunc extends BaseTopImageBtnFunc {

	public MyPetAddTopRightBtnFunc(Activity activity) {
		super(activity);
	}

	@Override
	public int getFuncId() {
		return R.id.mypet_topbar_add_right;
	}

	@Override
	public int getFuncIcon() {
		return R.mipmap.mypet_topbar_add_right;
	}

	@Override
	public void onclick(View v) {
		((MyPetActivity)getActivity()).addPetButton();
	}
}
