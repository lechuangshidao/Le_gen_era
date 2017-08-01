package com.Lechuang.app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.Lechuang.app.Bean.MyPetLocationManageInfo;
import com.Lechuang.app.Bean.MyPetLocationManageSinleInfo;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.MyPetLocationManageAdapter;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.MyPetLocationManageTopRightBtnFunc;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.base.view.XListView;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class MyPetLocationManageActivity extends SimpleTopbarActivity implements
        XListView.IXListViewListener,AdapterView.OnItemClickListener{

    private static Class<?> rightFuncArray[] = {MyPetLocationManageTopRightBtnFunc.class};
    private MyPetLocationManageAdapter adapter;
    private XListView listview;
    private Thread thread,thread2;
    private Context context;
    protected AlertDialog mUpgradeNotifyDialog;
    public static final int MYPETLOCATIONMANAGETOPRIGHT = 10000;
    public static final int MYPETLOCATIONMANAGECOMPILE= 10001;
    private Handler mHandler;
    private MyPetLocationManageInfo info ;
    private List<MyPetLocationManageInfo.MyPetLocationManageData> data;
    private String user_id;
    private String token;
    @Override
    protected Object getTopbarTitle() {
        return "我的地址";
    }

    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return rightFuncArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetlocationmanageavtivity);
        context = MyPetLocationManageActivity.this;
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        listview = (XListView) findViewById(R.id.listview);
        adapter = new
                MyPetLocationManageAdapter(MyPetLocationManageActivity.this,handler);
        initData();
        listview.setPullLoadEnable(false);//设置上拉刷新
        listview.setPullRefreshEnable(true);//设置下拉刷新
        listview.setXListViewListener(this); //设置监听事件，重写两个方法
        mHandler = new Handler();
    }

    private void initData() {
        user_id = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("token", token);
        okHttpPost(100, GlobalParam.MYPETLOCATIONMANAGE, params);
        createDialogshow();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==Activity.RESULT_OK){
            switch (requestCode){
                case MYPETLOCATIONMANAGETOPRIGHT:
                    initData();
                    break;
                case MYPETLOCATIONMANAGECOMPILE:
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
                    info = JSON.parseObject(returnData, MyPetLocationManageInfo.class);
                    data = info.getData();
                    adapter.setData(data);
                    listview.setAdapter(adapter);
                }else {
                    ToastUtil.showToast(returnMsg);
                }
                break;
            case 101:
                if (returnCode==1){
                    initData();
                    ToastUtil.showToast(returnMsg);
                }else {
                    ToastUtil.showToast(returnMsg);
                }
                break;
            case 102:
                if (returnCode==1){
                    MyPetLocationManageSinleInfo info = JSON.parseObject(returnData, MyPetLocationManageSinleInfo.class);
                    MyPetLocationManageSinleInfo.MyPetLocationManageSingleData manageData = info.getData();
                    String address_id = manageData.getAddress_id();
                    String consignee = manageData.getConsignee();
                    String phone = manageData.getPhone();
                    String address = manageData.getAddress();
                    String pretermit = manageData.getPretermit();
                    String x_address = manageData.getX_address();
                    Intent intent = new Intent(MyPetLocationManageActivity.this,MyPetLocationCompileActivity.class);
                    intent.putExtra("address_id",address_id);
                    intent.putExtra("consignee",consignee);
                    intent.putExtra("phone",phone);
                    intent.putExtra("address",address);
                    intent.putExtra("x_address",x_address);
                    intent.putExtra("default",pretermit);
                    startActivityForResult(intent,MYPETLOCATIONMANAGECOMPILE);
                }else {
                    ToastUtil.showToast(returnMsg);
                }

                break;
            case 103:
                if (returnCode==1){
                    initData();
                    ToastUtil.showToast(returnMsg);
                }else {
                    ToastUtil.showToast(returnMsg);
                }
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
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            String address_id = (String) msg.obj;
            int position= msg.arg1;
            if (user_id==null||token==null){
                ToastUtil.showToast("登陆超时，请重新登录...");
                return;
            }
            Map<String, Object> params = new HashMap<String, Object>();
            switch (msg.what){
                case 1://设置默认地址
                    Log.e("TAG_默认","ID_"+address_id);
                    params.put("address_id", address_id);
                    params.put("token", token);
                    params.put("default", "1");
                    okHttpPost(101, GlobalParam.MYPETADDLOCATIONCOMPILE, params);
                    createDialogshow();
                    break;
                case 3://编辑功能
                    Log.e("TAG_编辑","ID_"+address_id);
                    params.put("address_id", address_id);
                    params.put("token", token);
                    okHttpPost(102, GlobalParam.MYPETADDLOCATIONSINLE, params);
                    createDialogshow();
                    break;
                case 4://删除功能
                    Log.e("TAG_删除","ID_"+address_id);
                    showUpgradeDialog(address_id);
                    break;

            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (thread!=null)
            thread.interrupt();
        if (thread2!=null)
            thread2.interrupt();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                list.clear();
//                geneItems();//获取数据
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
//                geneItems();//获取数据
                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }
    private void onLoad() {
        listview.stopRefresh();
        listview.stopLoadMore();
        listview.setRefreshTime("刚刚");
    }

    private void showUpgradeDialog(final String address_id) {
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
                //执行删除操作
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("address_id", address_id);
                params.put("token", token);
                okHttpPost(103, GlobalParam.MYPETDELLOCATION, params);
                createDialogshow();
                //关闭dialog
                mUpgradeNotifyDialog.dismiss();
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
}
