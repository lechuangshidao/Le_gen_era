package com.Lechuang.app.Fragment;

import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.Lechuang.app.Activity.MeInfoActivity;
import com.Lechuang.app.Activity.MyPetActivity;
import com.Lechuang.app.Activity.MyPetIntegralTaskActivty;
import com.Lechuang.app.Activity.MyPetLocationManageAvtivity;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.MeGridViewAdapter;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.MyPetSettingTopBtnFunc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import www.xcd.com.mylibrary.base.fragment.BaseFragment;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;


public class Fragment_main_wode extends BaseFragment implements AdapterView.OnItemClickListener{

    private MeGridViewAdapter adapter;
    private GridView gridview;
    private int[] ItemTexttop = {R.mipmap.image_geren_pet, R.mipmap.image_wode_pet, R.mipmap.integraltask,R.mipmap.image_wode_pet};
    private int[] ItemTextbottom = {R.string.meinfo, R.string.petmessage, R.string.integraltask,R.string.locationmanage};
    private static Class<?> accountrightFuncArray[] = {MyPetSettingTopBtnFunc.class};

    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return accountrightFuncArray;
    }

    @Override
    protected Object getTopbarTitle() {
        return R.string.mypet;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_main_wode;
    }

    @Override
    protected void initView(LayoutInflater inflater, View view) {
        ViewGroup parent= (ViewGroup) view.getParent();
        if(parent!=null){
            parent.removeView(view);
        }
        gridview = (GridView) view.findViewById(R.id.gridview);
        initData();
    }

    private void initData() {
        adapter = new MeGridViewAdapter(getActivity(), ItemTexttop,ItemTextbottom);
        gridview.setAdapter(adapter);
        //添加消息处理
        gridview.setOnItemClickListener(this);
        String user_id = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("user_id");
        String token = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("token");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user_id);
        params.put("token", token);
        okHttpPost(100, GlobalParam.FRAGMENTWODEINFO, params);
    }

    @Override
    protected void onDestroyThread() {
//        handler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        adapter.setSeclection(i);
        adapter.notifyDataSetChanged();
        Message msg = null;
        if (i==0){
            startActivity(new Intent(getActivity(), MeInfoActivity.class));
        }else if (i==1){
            startActivity(new Intent(getActivity(), MyPetActivity.class));
        }else if (i==2){
            startActivity(new Intent(getActivity(), MyPetIntegralTaskActivty.class));
        }else if (i==3){
            startActivity(new Intent(getActivity(), MyPetLocationManageAvtivity.class));
        }

    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (returnCode==1){

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
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 0:
//
//                    break;
//                case 1:
//
//                    break;
//                case 2:
//
//                    break;
//            }
//        }
//    };
}
