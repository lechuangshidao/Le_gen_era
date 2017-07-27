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

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.HelpUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class MyPetIntegralTaskActivty extends SimpleTopbarActivity {

    private int PADDING20 = 0;
    private int PADDING10 = 0;
    private int PADDING1 = 0;
    private LinearLayout mypettask_parent;
    private String[] textlist = {"添加宠物", "发起一次宠物活动", "分享给好友"};

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
        initListView();
    }

    private void initListView() {
        for (int i = 0, length = textlist.length; i < length; i++) {
            final LayoutInflater inflater = LayoutInflater.from(this);
            LinearLayout pet_include = (LinearLayout) inflater.inflate(
                    R.layout.petaddtask_include, null);
            pet_include.setId(i);
            TextView text = (TextView) pet_include.findViewById(R.id.parent_add);
            text.setText(textlist[i]);
            TextView parent_addplan = (TextView) pet_include.findViewById(R.id.parent_addplan);
            parent_addplan.setText("进度"+i+"/"+i);
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
                startActivity(new Intent(MyPetIntegralTaskActivty.this,MyPetActionActivity.class));
                break;
            case 2:
                if (activityIsActivity()){
                    showUpgradeDialog();
                }
                break;
        }
    }

//    View.OnClickListener pChildClick = new View.OnClickListener() {
//
//        @Override
//        public void onClick(View v) {
//            Log.e("TAG_",v.getId()+"");
//            switch (v.getId()){
//
//            }
//        }
//    };
    /**
     * 初始化版本更新对话框
     */
    protected AlertDialog mUpgradeNotifyDialog;
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
//            builder.setView(serviceView);
            mUpgradeNotifyDialog = builder.create();
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            Window window = mUpgradeNotifyDialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.dialog_animation);
            window.getDecorView().setPadding(0, 0, 0 ,0);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);

            mUpgradeNotifyDialog.show();
            mUpgradeNotifyDialog.setContentView(serviceView);
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
//  官方文档上面的是错误的
//        if(requestCode == Constants.REQUEST_API) {
//            if(resultCode == Constants.RESULT_LOGIN) {
//                mTencent.handleLoginData(data, loginListener);
//            }
//  resultCode 是log出来的，官方文档里给的那个属性是没有的

//        if (requestCode == Constants.REQUEST_LOGIN) {
//            if (resultCode == -1) {
//                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
//                Tencent.handleResultData(data, loginListener);
//            }
//        }
        if (requestCode == Constants.REQUEST_LOGIN) {
            if (resultCode == -1) {
                Tencent.onActivityResultData(requestCode, resultCode, data, loginListener);
                Tencent.handleResultData(data, loginListener);
                UserInfo info = new UserInfo(this, mTencent.getQQToken());
                info.getUserInfo(loginListener);
            }
        }
    }
    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {

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
