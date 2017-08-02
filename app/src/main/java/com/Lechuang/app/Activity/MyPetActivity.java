package com.Lechuang.app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.Lechuang.app.Bean.PetMessageInfo;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.PetMessageAdapter;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.MyPetAddTopRightBtnFunc;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.base.view.XListView;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class MyPetActivity extends SimpleTopbarActivity implements
        XListView.IXListViewListener,AdapterView.OnItemClickListener,AdapterView.OnItemLongClickListener {

    private static Class<?> rightFuncArray[] = {MyPetAddTopRightBtnFunc.class};
    private XListView mListView;
    private PetMessageAdapter adapter;
    private PetMessageInfo info ;
    private Handler mHandler;
    protected AlertDialog mUpgradeNotifyDialog;
    private int start = 0;
    private static int refreshCnt = 0;
    public static final int REQUEST_MYPETADD = 10000;
    public static final int REQUEST_MYPETPETMODIFY = 10001;
    private List<PetMessageInfo.PetMessageData> data;
    private String user_id;
    private String token;
    @Override
    protected Class<?>[] getTopbarRightFuncArray() {

        return rightFuncArray;
    }

    @Override
    protected Object getTopbarTitle() {
        return R.string.mypetmessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_message);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        user_id = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(this);
        initData();
        adapter = new PetMessageAdapter(this);
        mListView.setPullLoadEnable(false);//设置上拉刷新
        mListView.setPullRefreshEnable(true);//设置下拉刷新
        mListView.setXListViewListener(this); //设置监听事件，重写两个方法
        mHandler = new Handler();
    }
    private void initData() {
        createDialogshow();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("token", token);
        okHttpPost(100, GlobalParam.MYPETINFO, params);
    }


    private void onLoad() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    public void addPetButton(){
        startActivityForResult(new Intent(MyPetActivity.this,MyPetAddActivity.class),REQUEST_MYPETADD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case REQUEST_MYPETADD:
                    initData();
                    break;
                case REQUEST_MYPETPETMODIFY:
                    initData();
                    break;
            }
        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (returnCode==1){
                    info = JSON.parseObject(returnData, PetMessageInfo.class);
                    data = info.getData();
                    adapter.setData(data);
                    mListView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }else {
                    ToastUtil.showToast(returnMsg);
                }
                break;
            case 101:
                if (returnCode==1){
                   initData();
                }
                ToastUtil.showToast(returnMsg);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (data!=null&&data.size()>0){
            PetMessageInfo.PetMessageData petMessageData = data.get(i - 1);
            String id = petMessageData.getPet_id();
            String pet_name = petMessageData.getPet_name();
            String pet_type = petMessageData.getPet_type();
            String pet_age = petMessageData.getPet_age();
            String pet_img = petMessageData.getPet_img();
            Intent intent = new Intent(MyPetActivity.this, MyPetMessageActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("pet_name",pet_name);
            intent.putExtra("pet_type",pet_type);
            intent.putExtra("pet_age",pet_age);
            intent.putExtra("pet_img",pet_img);
            startActivityForResult(intent,REQUEST_MYPETPETMODIFY);
        }
    }

    private void showUpgradeDialog(final int pos) {
        LayoutInflater factor = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View serviceView = factor.inflate(R.layout.mypetlist_dialog, null);
        serviceView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUpgradeNotifyDialog.isShowing()){
                    mUpgradeNotifyDialog.dismiss();
                }
            }
        });
        serviceView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭dialog
                mUpgradeNotifyDialog.dismiss();
                //执行删除操作
                String pet_id= data.get(pos-1).getPet_id();
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("id", pet_id);
                params.put("token", token);
                okHttpPost(101, GlobalParam.DELPETMYPETINFO, params);
            }
        });
        try {
            Activity activity = this;
            while (activity.getParent() != null) {
                activity = activity.getParent();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.DialogTheme);
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

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (activityIsActivity()){
            showUpgradeDialog(i);
        }
        return true;
    }
}
