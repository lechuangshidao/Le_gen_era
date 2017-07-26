package com.Lechuang.app.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.adapter.MyPetLocationManageAdapter;
import com.Lechuang.app.func.MyPetLocationManageTopRightBtnFunc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;

public class MyPetLocationManageAvtivity extends SimpleTopbarActivity {

    private static Class<?> rightFuncArray[] = {MyPetLocationManageTopRightBtnFunc.class};
    private MyPetLocationManageAdapter adapter;
    private ListView listview;
    private Thread thread,thread2;
    private Context context;
    protected AlertDialog mUpgradeNotifyDialog;
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
        context = MyPetLocationManageAvtivity.this;
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        listview = (ListView) findViewById(R.id.listview);
        initData();
    }

    private void initData() {
        adapter = new
                MyPetLocationManageAdapter(MyPetLocationManageAvtivity.this,handler);
        List<Map<String,String>> list = new ArrayList();
        for (int i = 0; i < 5; i++) {
            Map<String,String> map = new HashMap();
            map.put("name","张"+i);
            map.put("phone",i+"111111111");
            map.put("address",i+"=asdadadadadaasasda");
            map.put("status",i+"");
            map.put("id",i+"");
            list.add(map);
        }
        adapter.setData(list);
        listview.setAdapter(adapter);
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
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(final Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1://设置默认地址
                    final String id = (String) msg.obj;
                    final int position= msg.arg1;
                    //请求异步线程，请求完成后发送2
                    thread = new Thread(){
                        @Override
                        public void run() {
                            super.run();
//                            String result = RequestUtils.getMerchantPlaceLocationStatus(qk_id,id);
                            Message message = handler.obtainMessage();
                            message.what = 2;
//                            message.obj = result;
                            message.arg1= position;
                            handler.sendMessage(message);
                        }
                    };
                    thread.start();
                    break;
                case 2:
                    int position_change= msg.arg1;
                    String result = (String) msg.obj;


                    if (true) {
                        for (int i = 0; i < listview.getChildCount(); i++) {
                            if (position_change==i){
                                RelativeLayout layout = (RelativeLayout) listview.getChildAt(i);
                                ImageView imageView = (ImageView) layout.findViewById(R.id.opt_image);
                                TextView textView = (TextView) layout.findViewById(R.id.opt_text);
                                textView.setTextColor(context.getResources().getColor(R.color.red));
                                imageView.setBackgroundResource(R.mipmap.red_radio);
                            }else {
                                RelativeLayout layout = (RelativeLayout) listview.getChildAt(i);
                                ImageView imageView = (ImageView) layout.findViewById(R.id.opt_image);
                                TextView textView = (TextView) layout.findViewById(R.id.opt_text);
                                textView.setTextColor(context.getResources().getColor(R.color.black_99));
                                imageView.setBackgroundResource(R.mipmap.white_radio);
                            }
                        }

                    }
                    break;
                case 3://编辑功能
                    Intent intent = new Intent(MyPetLocationManageAvtivity.this,MyPetLocationCompileActivity.class);
                    intent.putExtra("id","1");
                    startActivity(intent);
                    break;
                case 4://删除功能
                    final String id_ = (String) msg.obj;
                    final int position_delete= msg.arg1;
                    showUpgradeDialog(id_,position_delete);
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
    private void showUpgradeDialog(final String id, int position_delete) {
        LayoutInflater factor = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View serviceView = factor.inflate(R.layout.simpletitle_dialog, null);
        serviceView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mUpgradeNotifyDialog.isShowing()){
                    mUpgradeNotifyDialog.dismiss();
                }
            }
        });
        serviceView.findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread2 = new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        //执行删除操作
                        ToastUtil.showToast("删除成功");
                    }
                };
                thread2.start();
                mUpgradeNotifyDialog.dismiss();
            }
        });

        try {
            Activity activity = MyPetLocationManageAvtivity.this;
            while (activity.getParent() != null) {
                activity = activity.getParent();
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MyPetLocationManageAvtivity.this);
            mUpgradeNotifyDialog = builder.create();
            mUpgradeNotifyDialog.show();
            mUpgradeNotifyDialog.setContentView(serviceView);
//            Window window = mUpgradeNotifyDialog.getWindow();
//            window.setGravity( Gravity.BOTTOM);
            mUpgradeNotifyDialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
            FrameLayout.LayoutParams layout = new FrameLayout.LayoutParams(Gallery.LayoutParams.FILL_PARENT, Gallery.LayoutParams.WRAP_CONTENT);
            //layout.setMargins(WallspaceUtil.dip2px(this, 10), 0, FeatureFunction.dip2px(this, 10), 0);
            serviceView.setLayoutParams(layout);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
