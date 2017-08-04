package www.xcd.com.mylibrary.base.activity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.activity.AlbumPhotoActivity;
import www.xcd.com.mylibrary.config.HttpConfig;
import www.xcd.com.mylibrary.func.BaseTopFunc;
import www.xcd.com.mylibrary.func.CommonBackTopBtnFunc;
import www.xcd.com.mylibrary.help.OkHttpHelper;
import www.xcd.com.mylibrary.http.HttpInterface;
import www.xcd.com.mylibrary.utils.DialogUtil;
import www.xcd.com.mylibrary.utils.NetUtil;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.YYStorageUtil;

import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_ALBUM;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CAMERA;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CROP;
import static www.xcd.com.mylibrary.utils.ToastUtil.showToast;

/**
 * topbar
 * 目标：
 * 1、可以自定义的增加按钮或者文本按钮（支持右侧最多添加两个图片按钮或者一个文本按钮，左侧只能是一个按钮，返回或者目录按钮）
 * 2、自定义按钮自己初始化自己的view并响应事件
 * 3、支持title名称的改变
 * 4、按钮支持文本按钮和imagebutton
 *
 * @author litfb
 * @date 2014年10月10日
 * @version 1.0
 */
public abstract class SimpleTopbarActivity extends BaseActivity implements OnClickListener,View.OnFocusChangeListener,HttpInterface {

	public static final String IMAGE_UNSPECIFIED = "image/*";
	protected Dialog loginDialog;
	/** 右侧功能对象的MAP，可以通过id获得指定的功能对象 */
	protected Hashtable<Integer, BaseTopFunc> funcMap = new Hashtable<Integer, BaseTopFunc>();

	/** title */
	private TextView viewTitle;


	/** 左侧功能区，用来放置功能按钮 */
	protected LinearLayout leftFuncZone;
	/** 右侧功能区，用来放置功能按钮 */
	protected LinearLayout rightFuncZone;
	/** 中间功能区，用来放置功能按钮或者默认的文本域 */
	protected RelativeLayout middlerFuncZone;

	/**
	 * 获得title文本
	 * 
	 * @return
	 */
	protected Object getTopbarTitle() {
		return "";
	}

	/**
	 * 获得左侧的功能控件
	 * 只能是imagebutton，并且只有一个
	 * 
	 * @return
	 */
	protected Class<?> getTopbarLeftFunc() {
		// 默认使用back
		return CommonBackTopBtnFunc.class;
	}

	/**
	 * 获得右侧的功能控件集合
	 * 如果有文本，那么就不能有imagebutton
	 * 如果是imagebutton，那么可以是一个或者两个
	 * 
	 * @return
	 */
	protected Class<?>[] getTopbarRightFuncArray() {
		return null;
	}

	protected Class<?> getTopbarMiddleFunc() {
		return null;
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
	protected void afterSetContentView() {
		super.afterSetContentView();

		viewTitle = (TextView) findViewById(R.id.topbar_title);
		leftFuncZone = (LinearLayout) findViewById(R.id.left_func);
		rightFuncZone = (LinearLayout) findViewById(R.id.right_func);
		// 判断使用默认的文字title还是使用自定义的title
		if (getTopbarMiddleFunc() != null) {
			viewTitle.setVisibility(View.GONE);

			addCustomViewToMiddleFunctionZone();
		} else {
			viewTitle.setVisibility(View.VISIBLE);

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
		}

		// 添加左侧控件（默认是返回按钮，但是支持自定义替换此按钮）
		addViewToLeftFunctionZone();
		// 添加右侧控件
		addViewToRightFunctionZone();
	}

	/**
	 * AlbumPhotoActivity.TYPE_SINGLE为单选
	 * ""多选
	 */
	private String tpye;

	public String getTpye() {
		return tpye;
	}

	public void setTpye(String tpye) {
		this.tpye = tpye;
	}

	@Override
	public void onClick(View v) {
		int i = v.getId();
		 if (i == R.id.account_head_choice_cancel) {// 关闭对话框
			closeChoiceDialog();

		} else if (i == R.id.account_head_choice_album) {// 关闭对话框
			closeChoiceDialog();
			// album
			Intent albumIntent = new Intent(SimpleTopbarActivity.this, AlbumPhotoActivity.class);
			 if (getTpye().equals(AlbumPhotoActivity.TYPE_SINGLE)){
				 albumIntent.putExtra(AlbumPhotoActivity.EXTRA_TYPE, AlbumPhotoActivity.TYPE_SINGLE);
			 }else {
				 albumIntent.putExtra(AlbumPhotoActivity.EXTRA_TYPE, "");
			 }
			// start
			SimpleTopbarActivity.this.startActivityForResult(albumIntent, REQUEST_CODE_HEAD_ALBUM);

		} else if (i == R.id.account_head_choice_camera) {
			try {
				// 关闭对话框
				closeChoiceDialog();
				// 调用系统相机
				Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				// 生成photoPath
				File photoFile = new File(YYStorageUtil.getImagePath(SimpleTopbarActivity.this), UUID.randomUUID().toString() + ".jpg");
				photoPath = photoFile.getPath();
				// uri
				Uri photoUri = Uri.fromFile(new File(photoPath));
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
				cameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
				// start
				SimpleTopbarActivity.this.startActivityForResult(cameraIntent, REQUEST_CODE_HEAD_CAMERA);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			BaseTopFunc topFunc = funcMap.get(v.getId());
			if (topFunc != null) {
				topFunc.onclick(v);
			}

		}
	}
	/**
	 * 在中间放置自定义的控件
	 * 
	 */
	private void addCustomViewToMiddleFunctionZone() {
		Class<?> customFunc = getTopbarMiddleFunc();
		View funcView = getFuncView(getLayoutInflater(), customFunc);

		if (funcView != null) {
			// 点击事件
			funcView.setOnClickListener(this);
			// 加入页面
			middlerFuncZone.addView(funcView);

			// 设置列表显示
			middlerFuncZone.setVisibility(View.VISIBLE);
		} else {
			middlerFuncZone.setVisibility(View.GONE);
		}
	}

	/**
	 * 
	 * 将功能控件添加到左侧功能区域
	 * 
	 */
	protected void addViewToLeftFunctionZone() {
		Class<?> customFunc = (Class<?>) getTopbarLeftFunc();
		if (customFunc == null) {
			return;
		}

		View funcView = getFuncView(getLayoutInflater(), customFunc);

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
			View funcView = getFuncView(getLayoutInflater(), customFunc);
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
		BaseTopFunc func = BaseTopFunc.newInstance(funcClazz, this);

		if (func == null) {
			return null;
		}

		funcMap.put(func.getFuncId(), func);

		// view
		View funcView = func.initFuncView(inflater);
		return funcView;
	}

	/**
	 * 
	 * 更新topbar控件，暂时全部更新，以后考虑指定更新
	 * 
	 */
	public void refreshFuncView() {
		for (Map.Entry<Integer, BaseTopFunc> entity : funcMap.entrySet()) {
			BaseTopFunc topFunc = entity.getValue();
			topFunc.reView();
		}
	}



	@Override
	public void onFocusChange(View view, boolean hasFocus) {
		EditText textView = (EditText) view;
		if (textView==null){
			return;
		}
		if (textView.getHint()==null){
			return;
		}
		String hint = textView.getHint().toString();

		if (hasFocus) {
			textView.setTag(hint);
			textView.setHint("");
		} else {
			hint = textView.getTag().toString();
			textView.setHint(hint);
		}
		if (!hasFocus) {
			InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}else {
			InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			view.requestFocus();
			imm.showSoftInput(view, 0);
		}

	}

	/**打开新的Activity
	 * @param intent
	 * @param requestCode
	 * @param showAnimation
	 */
	public void toActivity(final Intent intent, final int requestCode, final boolean showAnimation) {
		runUiThread(new Runnable() {
			@Override
			public void run() {
				if (intent == null) {
					Log.e("TAG_", "toActivity  intent == null >> return;");
					return;
				}
				//fragment中使用context.startActivity会导致在fragment中不能正常接收onActivityResult
				if (requestCode < 0) {
					startActivity(intent);
				} else {
					startActivityForResult(intent, requestCode);
				}
				if (showAnimation) {
					overridePendingTransition(R.anim.right_push_in, R.anim.hold);
				} else {
					overridePendingTransition(R.anim.null_anim, R.anim.null_anim);
				}
			}
		});
	}
	/**在UI线程中运行，建议用这个方法代替runOnUiThread
	 * @param action
	 */
	public void runUiThread(Runnable action) {
		if (activityIsActivity() == false) {
			Log.e("TAG_", "runUiThread  isAlive() == false >> return;");
			return;
		}
		runOnUiThread(action);
	}

	public void createDialogshow(){
		// 登录中dialog
		loginDialog = DialogUtil.getProgressDialog(this);
		dialogshow();
	}
	/**
	 * 关闭登录中dialog
	 */
	public void dialogDissmiss() {
		if (loginDialog != null && loginDialog.isShowing()) {
			loginDialog.dismiss();
		}
	}

	/**
	 * 显示登录中dialog
	 */
	public void dialogshow() {
		if (loginDialog != null && !loginDialog.isShowing()) {
			loginDialog.show();
		}
	}
	/**
	 * 菜单View
	 *
	 * @param
	 * @return
	 */
	/** 头像Image */
	public ImageView imageHead;
	/** 头像修改菜单 */
	public View viewChoice;
	/** 头像修改dialog */
	public Dialog dlgChoice;

	/** 照片地址 */
	public String photoPath;
	public View getChoiceView() {
		if (viewChoice == null) {
			// 初始化选择菜单
			viewChoice = LayoutInflater.from(SimpleTopbarActivity.this).inflate(R.layout.view_head_choice, null);
			viewChoice.findViewById(R.id.account_head_choice_album).setOnClickListener(this);
			viewChoice.findViewById(R.id.account_head_choice_camera).setOnClickListener(this);
			viewChoice.findViewById(R.id.account_head_choice_cancel).setOnClickListener(this);
		}
		return viewChoice;
	}
	/**
	 * 修改头像对话框
	 *
	 * @return
	 */
	public Dialog getChoiceDialog() {
		if (dlgChoice == null) {
			dlgChoice = new Dialog(SimpleTopbarActivity.this, R.style.DialogStyle);
			dlgChoice.setContentView(getChoiceView());
			return dlgChoice;
		}
		return dlgChoice;
	}
	/**
	 * 关闭对话框
	 */
	public void closeChoiceDialog() {
		if (dlgChoice != null && dlgChoice.isShowing()) {
			dlgChoice.cancel();
		}
	}
	public void startCrop(String imagePath) {
		try {
			Uri uri = Uri.fromFile(new File(imagePath));
			Intent intent = new Intent("com.android.camera.action.CROP");
			intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
			intent.putExtra("crop", "true");
			// aspectX aspectY 是宽高的比例
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			// outputX outputY 是裁剪图片宽高
			intent.putExtra("outputX", 160);
			intent.putExtra("outputY", 160);
			intent.putExtra("return-data", true);
			SimpleTopbarActivity.this.startActivityForResult(intent, REQUEST_CODE_HEAD_CROP);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * POST网络请求
	 *
	 * @param url        地址
	 * @param paramsMaps 参数
	 */
	public void okHttpPost(final int requestCode, String url, final Map<String, Object> paramsMaps) {
		if (NetUtil.getNetWorking(SimpleTopbarActivity.this) == false) {
			showToast("请检查网络。。。");
			return;
		}
		OkHttpHelper.getInstance().postAsyncHttp(requestCode, url, paramsMaps,new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
					//请求错误
					case HttpConfig.REQUESTERROR:
						IOException error = (IOException) msg.obj;
						onErrorResult(HttpConfig.REQUESTERROR, error);
						ToastUtil.showToast("请求错误");
						dialogDissmiss();
						break;
					//解析错误
					case HttpConfig.PARSEERROR:
						onParseErrorResult(HttpConfig.PARSEERROR);
						ToastUtil.showToast("解析错误");
						dialogDissmiss();
						break;
					//网络错误
					case HttpConfig.NETERROR:
						ToastUtil.showToast("网络错误");
						dialogDissmiss();
						break;
					//请求成功
					case HttpConfig.SUCCESSCODE:
						dialogDissmiss();
						Bundle bundle = msg.getData();
						int requestCode = bundle.getInt("requestCode");
						int returnCode = bundle.getInt("returnCode");
						String returnMsg = bundle.getString("returnMsg");
						String returnData = bundle.getString("returnData");
						Map<String, Object> paramsMaps = (Map) msg.obj;
						onSuccessResult(requestCode, returnCode, returnMsg, returnData, paramsMaps);
						break;
				}
			}
		});
	}

	/**
	 * GET网络请求
	 *
	 * @param url        地址
	 * @param paramsMaps 参数
	 */
	public void okHttpGet(final int requestCode, String url, final Map<String, Object> paramsMaps) {
		if (NetUtil.getNetWorking(SimpleTopbarActivity.this) == false) {
			showToast("请检查网络。。。");
			return;
		}
		OkHttpHelper.getInstance().getAsyncHttp(requestCode, url, paramsMaps, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
					//请求错误
					case HttpConfig.REQUESTERROR:
						IOException error = (IOException) msg.obj;
						onErrorResult(HttpConfig.REQUESTERROR, error);
						dialogDissmiss();
						break;
					//解析错误
					case HttpConfig.PARSEERROR:
						onParseErrorResult(HttpConfig.PARSEERROR);
						dialogDissmiss();
						break;
					//网络错误
					case HttpConfig.NETERROR:
						dialogDissmiss();
						break;
					//请求成功
					case HttpConfig.SUCCESSCODE:
						Bundle bundle = msg.getData();
						int requestCode = bundle.getInt("requestCode");
						int returnCode = bundle.getInt("returnCode");
						String returnMsg = bundle.getString("returnMsg");
						String returnData = bundle.getString("returnData");
						Map<String, Object> paramsMaps = (Map) msg.obj;
						onSuccessResult(requestCode, returnCode, returnMsg, returnData, paramsMaps);
						dialogDissmiss();
						break;
				}
			}
		});
	}

	/**
	 * POST网络请求上传图片
	 *
	 * @param url        地址
	 * @param paramsMaps 参数
	 */
	public void okHttpImgPost(final int requestCode, String url, final Map<String, Object> paramsMaps) {
		if (NetUtil.getNetWorking(SimpleTopbarActivity.this) == false) {
			showToast("请检查网络。。。");
			return;
		}
		OkHttpHelper.getInstance().postAsyncPicHttp(requestCode, url, paramsMaps, new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
					//请求错误
					case HttpConfig.REQUESTERROR:
						IOException error = (IOException) msg.obj;
						onErrorResult(HttpConfig.REQUESTERROR, error);
						dialogDissmiss();
						ToastUtil.showToast("请求错误");
						break;
					//解析错误
					case HttpConfig.PARSEERROR:
						onParseErrorResult(HttpConfig.PARSEERROR);
						dialogDissmiss();
						ToastUtil.showToast("解析错误");
						break;
					//网络错误
					case HttpConfig.NETERROR:
						ToastUtil.showToast("网络错误");
						dialogDissmiss();
						break;
					//请求成功
					case HttpConfig.SUCCESSCODE:
						Bundle bundle = msg.getData();
						int requestCode = bundle.getInt("requestCode");
						int returnCode = bundle.getInt("returnCode");
						String returnMsg = bundle.getString("returnMsg");
						String returnData = bundle.getString("returnData");
						Map<String, Object> paramsMaps = (Map) msg.obj;
						onSuccessResult(requestCode, returnCode, returnMsg, returnData, paramsMaps);
						dialogDissmiss();
						break;
				}
			}
		});
	}

}
