package com.Lechuang.app.func;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.Lechuang.app.Activity.MyPetAddLocationActivity;
import com.Lechuang.app.Activity.MyPetLocationManageActivity;
import com.Lechuang.app.R;

import www.xcd.com.mylibrary.func.BaseTopImageBtnFunc;

public class MyPetLocationManageTopRightBtnFunc extends BaseTopImageBtnFunc {

	public MyPetLocationManageTopRightBtnFunc(Activity activity) {
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
		getActivity().startActivityForResult(new Intent(getActivity(), MyPetAddLocationActivity.class), MyPetLocationManageActivity.MYPETLOCATIONMANAGETOPRIGHT);
	}
}
