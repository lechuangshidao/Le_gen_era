package www.xcd.com.mylibrary.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Hashtable;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.func.BaseTopFunc;

/**
 * fragment基类
 *
 * @author litfb
 * @date 2014年10月8日
 * @version 1.0
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener,View.OnFocusChangeListener {

	/** 右侧功能对象的MAP，可以通过id获得指定的功能对象 */
	protected Hashtable<Integer, BaseTopFunc> funcMap = new Hashtable<Integer, BaseTopFunc>();
	private TextView viewTitle;
	protected LinearLayout leftFuncZone;
	protected LinearLayout rightFuncZone;
	/**
	 * get layout resid
	 *
	 * @return
	 */
	protected abstract int getLayoutId();

	/**
	 * 初始化页面
	 *
	 * @param view
	 */
	protected abstract void initView(LayoutInflater inflater, View view);
	protected Object getTopbarTitle() {
		return "";
	}
	private boolean isActive = true;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		if (getLayoutId() == 0) {
			return null;
		}
		View view = inflater.inflate(getLayoutId(), container, false);
		viewTitle = (TextView) view.findViewById(R.id.topbar_title);
		leftFuncZone = (LinearLayout) view.findViewById(R.id.left_func);
		rightFuncZone = (LinearLayout) view.findViewById(R.id.right_func);
		// 设置title
		if (getTopbarTitle() instanceof Integer) {
			int title = (Integer) getTopbarTitle();
			if (title != 0) {
				viewTitle.setText(title);
			}
		} else if (getTopbarTitle() instanceof String) {
			String title = (String) getTopbarTitle();
			viewTitle.setText(title);
		}
		// 添加左侧控件（默认是返回按钮，但是支持自定义替换此按钮）
		addViewToLeftFunctionZone();
		// 添加右侧控件
		addViewToRightFunctionZone();

		initView(inflater, view);
		return view;
	}
	/**
	 * 获得左侧的功能控件
	 * 只能是imagebutton，并且只有一个
	 *
	 * @return
	 */
	protected Class<?> getTopbarLeftFunc() {
		// 默认使用back
//		return CommonBackTopBtnFunc.class;
		return null;
	}	/**
	 *
	 * 将功能控件添加到左侧功能区域
	 *
	 */
	protected void addViewToLeftFunctionZone() {
		Class<?> customFunc = (Class<?>) getTopbarLeftFunc();
		if (customFunc == null) {
			return;
		}

		View funcView = getFuncView(getActivity().getLayoutInflater(), customFunc);

		if (funcView != null) {
			// 点击事件
			funcView.setOnClickListener(this);
			// 加入页面
			leftFuncZone.addView(funcView);

			// 设置列表显示
			leftFuncZone.setVisibility(View.VISIBLE);
		} else {
			leftFuncZone.setVisibility(View.GONE);
		}
	}
	protected Class<?>[] getTopbarRightFuncArray() {
		return null;
	}
	/**
	 *
	 * 将功能控件添加到右侧功能区域
	 *
	 * @param
	 */
	private void addViewToRightFunctionZone() {
		Class<?>[] customFuncs = getTopbarRightFuncArray();

		// 功能列表为空,隐藏区域
		if (customFuncs == null || customFuncs.length == 0) {
			rightFuncZone.setVisibility(View.GONE);
			return;
		}
		// 初始化功能
		for (Class<?> customFunc : customFuncs) {
			// view
			View funcView = getFuncView(getActivity().getLayoutInflater(), customFunc);
			if (funcView != null) {
				// 点击事件
				funcView.setOnClickListener(this);
				// 加入页面
				rightFuncZone.addView(funcView);
			}
		}
		// 设置列表显示
		rightFuncZone.setVisibility(View.VISIBLE);
	}
	/**
	 * 获得功能View
	 *
	 * @param inflater
	 * @param
	 */
	protected View getFuncView(LayoutInflater inflater, Class<?> funcClazz) {
		BaseTopFunc func = BaseTopFunc.newInstance(funcClazz, getActivity());

		if (func == null) {
			return null;
		}

		funcMap.put(func.getFuncId(), func);

		// view
		View funcView = func.initFuncView(inflater);
		return funcView;
	}
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onStart() {
		super.onStart();
	}

	@Override
	public void onResume() {
		super.onResume();
		isActive = true;
	}

	@Override
	public void onPause() {
		super.onPause();
		isActive = false;
	}
	protected abstract void onDestroyThread();
	@Override
	public void onDestroy() {
		super.onDestroy();
		onDestroyThread();

	}

	public boolean activityIsActivity() {
		return isActive;
	}
	@Override
	public void onClick(View v) {
		BaseTopFunc topFunc = funcMap.get(v.getId());
		if (topFunc != null) {
			topFunc.onclick(v);
		}
	}
	/**
	 * 重新设置title
	 *
	 * @param resId
	 */
	public void resetTopbarTitle(int resId) {
		// 设置title
		viewTitle.setText(resId);
	}

	/**
	 * 重新设置title
	 *
	 * @param text
	 */
	public void resetTopbarTitle(String text) {
		// 设置title
		viewTitle.setText(text);
	}

	@Override
	public void onFocusChange(View view, boolean hasFocus) {
		EditText textView = (EditText) view;
		String hint;
		hint = textView.getHint().toString();

		if (hasFocus) {
			textView.setTag(hint);
			textView.setHint("");
		} else {
			hint = textView.getTag().toString();
			textView.setHint(hint);
		}
		if (!hasFocus) {
			InputMethodManager im = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}else {
			InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
			view.requestFocus();
			imm.showSoftInput(view, 0);
		}
	}
}