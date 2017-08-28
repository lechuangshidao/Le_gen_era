package com.Lechuang.app.func;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.Lechuang.app.RongCloud.Rong_news;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;


public class CommonBackTopBtnFunc_shouye extends BaseTopImageBtnFunc {

	public CommonBackTopBtnFunc_shouye(Activity activity) {
		super(activity);
	}

	@Override
	public int getFuncId() {
		return R.id.topbar_left_shouye;
	}
	@Override
	public int getFuncIcon() {
		return R.mipmap.image_news;
	}

	@Override
	public void onclick(View v) {
		Intent intent_news = new Intent(getActivity(), Rong_news.class);
		getActivity().startActivity(intent_news);
	}

}
