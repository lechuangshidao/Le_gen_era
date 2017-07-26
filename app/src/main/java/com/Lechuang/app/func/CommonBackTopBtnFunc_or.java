package com.Lechuang.app.func;

import android.app.Activity;
import android.view.View;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;


public class CommonBackTopBtnFunc_or extends BaseTopImageBtnFunc {

	public CommonBackTopBtnFunc_or(Activity activity) {
		super(activity);
	}

	@Override
	public int getFuncId() {
		return R.id.topbar_back_or;
	}
	@Override
	public int getFuncIcon() {
		return R.mipmap.image_log_back;
	}

	@Override
	public void onclick(View v) {
		getActivity().finish();
	}

}
