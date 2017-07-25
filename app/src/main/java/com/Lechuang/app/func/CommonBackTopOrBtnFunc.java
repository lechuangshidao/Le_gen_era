package com.Lechuang.app.func;

import android.app.Activity;
import android.view.View;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;


public class CommonBackTopOrBtnFunc extends BaseTopImageBtnFunc {

	public CommonBackTopOrBtnFunc(Activity activity) {
		super(activity);
	}

	@Override
	public int getFuncId() {
		return R.id.topbaror_back;
	}

	@Override
	public int getFuncIcon() {
		return R.mipmap.selector_orback_btn;
	}

	@Override
	public void onclick(View v) {
		getActivity().finish();
	}

}
