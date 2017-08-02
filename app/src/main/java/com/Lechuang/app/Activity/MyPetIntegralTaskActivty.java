package com.Lechuang.app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Lechuang.app.Bean.MyPetIntegralTaskInfo;
import com.Lechuang.app.R;
import com.Lechuang.app.Utils.HelpUtils;
import com.Lechuang.app.entity.GlobalParam;
import com.alibaba.fastjson.JSON;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class MyPetIntegralTaskActivty extends SimpleTopbarActivity {

    private int PADDING20 = 0;
    private int PADDING10 = 0;
    private int PADDING1 = 0;
    private LinearLayout mypettask_parent;
    private String[] textlist = {"添加宠物", "发起一次宠物活动", "分享给好友"};
    private static final int REQUEST_MYPETACTION = 10000;
    protected AlertDialog mUpgradeNotifyDialog;
    private int actionIntegralnum = 0;
    @Override
    protected Object getTopbarTitle() {
        return R.string.integraltask;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetintegraltask);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        mypettask_parent = (LinearLayout) findViewById(R.id.mypettask_parent);
        PADDING20 = HelpUtils.dp2px(MyPetIntegralTaskActivty.this,20);
        PADDING10 = HelpUtils.dp2px(MyPetIntegralTaskActivty.this,10);
        PADDING1 = HelpUtils.dp2px(MyPetIntegralTaskActivty.this,1);
        initData();
    }

    private void initData() {
        String user_id = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        String token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("token", token);
        okHttpPost(100, GlobalParam.MYPETINTEGRALTASK, params);
    }

    private void initListView(List<MyPetIntegralTaskInfo.DataBean> data ) {
        for (int i = 0, length = data.size(); i < length; i++) {
            final LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout pet_include = (LinearLayout) inflater.inflate(
                    R.layout.petaddtask_include, null);
            pet_include.setId(i);
            TextView text = (TextView) pet_include.findViewById(R.id.parent_add);
            text.setText(data.get(i).getTitle());
            TextView parent_addplan = (TextView) pet_include.findViewById(R.id.parent_addplan);
            if ("".equals(data.get(i).getStatus())){
                parent_addplan.setText("进度0/1");
            }else {
                parent_addplan.setText("进度1/1");
                actionIntegralnum++;
            }
            pet_include.setOnClickListener(this);
//                ImageView image = (ImageView) pet_include.findViewById(R.id.image);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,PADDING1,0,0);
            pet_include.setLayoutParams(lp);
            mypettask_parent.setLayoutParams(lp);//设置布局参数
            pet_include.setPadding(PADDING20,PADDING10,PADDING20,PADDING10);
            mypettask_parent.addView(pet_include);

        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.cancel:
                if (mUpgradeNotifyDialog.isShowing()&&activityIsActivity()){
                    mUpgradeNotifyDialog.dismiss();
                }
                break;
            case R.id.wexin:
                qqLogin();
                break;
            case R.id.wexinfriends:
                break;
            case R.id.qqfriends:
                break;
            case 0:
                startActivity(new Intent(MyPetIntegralTaskActivty.this,MyPetAddActivity.class));
                break;
            case 1:
                startActivityForResult(new Intent(MyPetIntegralTaskActivty.this,MyPetActionActivity.class),REQUEST_MYPETACTION);
                break;
            case 2:
                if (activityIsActivity()){
                    showUpgradeDialog();
                }
                break;
        }
    }

    private void showUpgradeDialog() {
        LayoutInflater factor = (LayoutInflater) MyPetIntegralTaskActivty.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View serviceView = factor.inflate(R.layout.mypetlist_sharedialog, null);
        serviceView.findViewById(R.id.cancel).setOnClickListener(this);

        LinearLayout wexin  = (LinearLayout) serviceView.findViewById(R.id.wexin);
        wexin.setOnClickListener(this);

        LinearLayout wexinfriends  = (LinearLayout) serviceView.findViewById(R.id.wexinfriends);
        wexinfriends.setOnClickListener(this);

        LinearLayout qqfriends  = (LinearLayout)serviceView.findViewById(R.id.qqfriends);
        qqfriends.setOnClickListener(this);
        try {
            Activity activity = MyPetIntegralTaskActivty.this;
            while (activity.getParent() != null) {
                activity = activity.getParent();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MyPetIntegralTaskActivty.this,R.style.DialogTheme);
            mUpgradeNotifyDialog = builder.create();
            mUpgradeNotifyDialog.show();
            mUpgradeNotifyDialog.setContentView(serviceView);
            Window window = mUpgradeNotifyDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.dialog_animation);
            window.getDecorView().setPadding(0, 0, 0 ,0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private Tencent mTencent;
    private String APP_ID = "222222";
    private IUiListener loginListener;
    private String SCOPE = "all";
    public void qqLogin() {
        initQqLogin();
        mTencent.login(this, SCOPE, loginListener);
    }
    //初始化QQ登录分享的需要的资源
    private void initQqLogin(){
        mTencent =  Tencent.createInstance(APP_ID, this);
        //创建QQ登录回调接口
        loginListener = new IUiListener() {
            @Override
            public void onComplete(Object o) {
                //登录成功后调用的方法
                JSONObject jo = (JSONObject) o;
                Toast.makeText(MyPetIntegralTaskActivty.this, "登录成功", Toast.LENGTH_SHORT).show();
                Log.e("TAG_COMPLETE:", jo.toString());
                String openID;
                try {
                    openID = jo.getString("openid");
                    String accessToken = jo.getString("access_token");
                    String expires = jo.getString("expires_in");
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken, expires);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                //登录失败后回调该方法
                Toast.makeText(MyPetIntegralTaskActivty.this, "登录失败", Toast.LENGTH_SHORT).show();
                Log.e("LoginError:", uiError.toString());
            }

            @Override
            public void onCancel() {
                //取消登录后回调该方法
                Toast.makeText(MyPetIntegralTaskActivty.this, "取消登录", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode ==Activity.RESULT_OK){
            switch (requestCode){
                case REQUEST_MYPETACTION:
                    if (actionIntegralnum!=3){
                        mypettask_parent.removeAllViews();
                        initData();
                    }
                    break;
                case Constants.REQUEST_LOGIN:
                    Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
                    Tencent.handleResultData(data, loginListener);
                    UserInfo info = new UserInfo(this, mTencent.getQQToken());
                    info.getUserInfo(loginListener);
                    break;

            }
        }
    }
    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (returnCode==1) {
                    MyPetIntegralTaskInfo info = JSON.parseObject(returnData, MyPetIntegralTaskInfo.class);
                    List<MyPetIntegralTaskInfo.DataBean> data = info.getData();
                    if (data!=null&&data.size()>0){
                        initListView(data);
                    }
                }else {
                    ToastUtil.showToast(returnMsg);
                }
                break;
        }
    }

    @Override
    public void onCancelResult() {

    }

    @Override
    public void onErrorResult(int errorCode, IOException errorExcep) {

    }

    @Override
    public void onParseErrorResult(int errorCode) {

    }

    @Override
    public void onFinishResult() {

    }
}
