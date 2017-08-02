package com.Lechuang.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Activity.MeInfoActivity;
import com.Lechuang.app.Activity.MyPetActivity;
import com.Lechuang.app.Activity.MyPetIntegralTaskActivty;
import com.Lechuang.app.Activity.MyPetLocationManageActivity;
import com.Lechuang.app.Bean.FragmentMeInfo;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.MeGridViewAdapter;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.MyPetSettingTopBtnFunc;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

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
    private ImageView image_wode_geren;
    private TextView text_wode_name,text_guanzhu_num,text_jifen_num,text_fensi_num;
    private String sex = "1";
    private String image_head;
    private String nickname;
    private String userbirthday;
    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return accountrightFuncArray;
    }

    @Override
    protected Object getTopbarTitle() {
        return R.string.me;
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
        image_wode_geren = (ImageView) view.findViewById(R.id.image_wode_geren);
        text_wode_name = (TextView) view.findViewById(R.id.text_wode_name);
        text_guanzhu_num = (TextView) view.findViewById(R.id.text_guanzhu_num);
        text_jifen_num = (TextView) view.findViewById(R.id.text_jifen_num);
        text_fensi_num = (TextView) view.findViewById(R.id.text_fensi_num);
        adapter = new MeGridViewAdapter(getActivity(), ItemTexttop,ItemTextbottom);
        gridview.setAdapter(adapter);
        //添加消息处理
        gridview.setOnItemClickListener(this);
        initData();
    }

    private void initData() {
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
           Intent intent = new Intent(getActivity(), MeInfoActivity.class);
            intent.putExtra("MEINFOHEAD",image_head);
            intent.putExtra("NICKNAME",nickname);
            intent.putExtra("SEX",sex);
            intent.putExtra("USERBIRTHDAY",userbirthday);
            startActivityForResult(intent, Activity.RESULT_FIRST_USER);
        }else if (i==1){
            startActivity(new Intent(getActivity(), MyPetActivity.class));
        }else if (i==2){
            startActivity(new Intent(getActivity(), MyPetIntegralTaskActivty.class));
        }else if (i==3){
            startActivity(new Intent(getActivity(), MyPetLocationManageActivity.class));
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Activity.RESULT_FIRST_USER:
                initData();
                break;
        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (returnCode==1){
                    FragmentMeInfo result = JSON.parseObject(returnData,FragmentMeInfo.class);
                    FragmentMeInfo.FragmentMeInfoData data = result.getData();
                    nickname = data.getNickname();
                    text_wode_name.setText(nickname==null?"未知":nickname);
                    String integral = data.getIntegral();
                    text_jifen_num.setText(integral==null?"未知":integral);
                    String fensi = data.getFensi();
                    text_fensi_num.setText(fensi==null?"未知":fensi);
                    String myguanzhu = data.getMyguanzhu();
                    text_guanzhu_num.setText(myguanzhu==null?"未知":myguanzhu);
                    sex = data.getSex();
                    userbirthday = data.getUserbirthday();
                    String userpicture = data.getUserpicture();
                    image_head = GlobalParam.URL+userpicture;
                    Glide.with(getActivity())
                            .load(image_head)
                            .centerCrop()
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.mipmap.image_wode_geren)
                            .error(R.mipmap.image_wode_geren)
                            .into(image_wode_geren);

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
