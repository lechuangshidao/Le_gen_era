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
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.Gallery;

import com.Lechuang.app.Bean.PetMessageInfo;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.PetMessageAdapter;
import com.Lechuang.app.func.MyPetAddTopRightBtnFunc;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.base.view.XListView;

public class MyPetActivity extends SimpleTopbarActivity implements
        XListView.IXListViewListener,AdapterView.OnItemClickListener {

    private static Class<?> rightFuncArray[] = {MyPetAddTopRightBtnFunc.class};
    private XListView mListView;
    private PetMessageAdapter adapter;
    private List<PetMessageInfo> list ;
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;
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
        geneItems();
        mListView = (XListView) findViewById(R.id.xListView);
        mListView.setOnItemClickListener(this);
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (activityIsActivity()){
                    showUpgradeDialog();
                }
                return true;
            }
        });
        initAdapter();

        mListView.setPullLoadEnable(false);//设置上拉刷新
        mListView.setPullRefreshEnable(true);//设置下拉刷新
        mListView.setXListViewListener(this); //设置监听事件，重写两个方法
        mHandler = new Handler();
    }

    private void initAdapter() {
        adapter = new PetMessageAdapter(this, list);
        mListView.setAdapter(adapter);
    }

    private void geneItems() {
        String jsonStr = "[{\"name\":\"张学友\",\"breed\":\"aaa\",\"age\":\"1\",\"label\":\"ssas\"},{\"name\":\"郭富城\",\"breed\":\"aaa\",\"age\":\"1\",\"label\":\"ssas\"}]" ;
        list = JSON.parseArray(jsonStr, PetMessageInfo.class);
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
                list.clear();
                geneItems();
                initAdapter();
                onLoad();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                adapter.notifyDataSetChanged();
                onLoad();
            }
        }, 2000);
    }

    public void addPetButton(){
        startActivity(new Intent(MyPetActivity.this,MyPetAddActivity.class));
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (list!=null&&list.size()>0){
            PetMessageInfo petMessageInfo = list.get(i-1);
            String id = petMessageInfo.getId();
            Intent intent = new Intent(MyPetActivity.this, MyPetMessageActivity.class);
            intent.putExtra("id",id);
            startActivity(intent);
        }
    }

    /**
     * 初始化版本更新对话框
     */
    protected AlertDialog mUpgradeNotifyDialog;
    private void showUpgradeDialog() {
        LayoutInflater factor = (LayoutInflater) MyPetActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

                //关闭dialog
                mUpgradeNotifyDialog.dismiss();
            }
        });
        try {
            Activity activity = MyPetActivity.this;
            while (activity.getParent() != null) {
                activity = activity.getParent();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MyPetActivity.this);
            mUpgradeNotifyDialog = builder.create();
            mUpgradeNotifyDialog.show();
            mUpgradeNotifyDialog.setContentView(serviceView);
            Window window = mUpgradeNotifyDialog.getWindow();
            window.setGravity( Gravity.BOTTOM);
//            WindowManager.LayoutParams lp = window.getAttributes();
//            // 设置透明度 alpha属性
//            lp.alpha = 0.5f;
////            lp.alpha = 1.0f;
//            //设置黑暗度
////            lp.dimAmount = 0.8f;
//            lp.width = FrameLayout.LayoutParams.MATCH_PARENT;
//            lp.height = FrameLayout.LayoutParams.MATCH_PARENT;
//            window.setAttributes(lp);
            mUpgradeNotifyDialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
            FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(Gallery.LayoutParams.FILL_PARENT, Gallery.LayoutParams.WRAP_CONTENT);
            //layout.setMargins(WallspaceUtil.dip2px(this, 10), 0, FeatureFunction.dip2px(this, 10), 0);
            serviceView.setLayoutParams(layout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
