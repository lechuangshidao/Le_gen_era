package com.Lechuang.app.func;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.Lechuang.app.Activity.Gift_Activity;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;


public class CommonBackTopBtnFunc_shouye_right extends BaseTopImageBtnFunc {

	public CommonBackTopBtnFunc_shouye_right(Activity activity) {
		super(activity);
	}

	@Override
	public int getFuncId() {
		return R.id.topbar_right_shouye;
	}

	@Override
	public int getFuncIcon() {
		return R.mipmap.image_gift;
	}

	@Override
	public void onclick(View v) {
		Intent intent_news = new Intent(getActivity(), Gift_Activity.class);
		getActivity().startActivity(intent_news);
	}
}
