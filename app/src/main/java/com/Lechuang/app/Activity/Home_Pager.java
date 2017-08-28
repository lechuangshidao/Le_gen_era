package com.Lechuang.app.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.Lechuang.app.Bean.RongYBean;
import com.Lechuang.app.Bean.RongYunUserInfo;
import com.Lechuang.app.Fragment.Fragment_main_gouwuche;
import com.Lechuang.app.Fragment.Fragment_main_pet;
import com.Lechuang.app.Fragment.Fragment_main_shouye;
import com.Lechuang.app.Fragment.Fragment_main_wode;
import com.Lechuang.app.R;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.view.NoScrollViewPager;
import com.alibaba.fastjson.JSON;
import com.xyz.tabitem.BottmTabItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.base.application.BaseApplication;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class Home_Pager extends SimpleTopbarActivity implements RongIM.UserInfoProvider{
    private List<Fragment> list_f = new ArrayList<>();
    private List<BottmTabItem> list_b = new ArrayList<>();
    @Bind(R.id.main_buttom_shouye)
    BottmTabItem mainButtomShouye;
    @Bind(R.id.main_buttom_pet)
    BottmTabItem mainButtomPet;
    @Bind(R.id.main_buttom_gouwuche)
    BottmTabItem mainButtomGouwuche;
    @Bind(R.id.main_buttom_wode)
    BottmTabItem mainButtomWode;
    /*@Bind(R.id.viewpager)
    NoScrollViewPager viewpager;*/
    private Fragment_main_shouye fragment_main_shouye;
    private Fragment_main_pet fragment_main_pet;
    private Fragment_main_gouwuche fragment_main_gouwuche;
    private Fragment_main_wode fragment_main_wode;
    private FragmentManager manager;
    /**
     * 第一次返回按钮时间
     */
    private long firstTime;
    private NoScrollViewPager viewpager;
    @Override
    public boolean isTopbarVisibility() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pager);
        RongIM.setUserInfoProvider(this,true);
        viewpager = (NoScrollViewPager) findViewById(R.id.viewpager);
        ButterKnife.bind(this);
        getInitData();//初始化数据
        getToken();//获取融云token

    }

    private void getInitData() {
        fragment_main_shouye = new Fragment_main_shouye();
        fragment_main_pet = new Fragment_main_pet();
        fragment_main_gouwuche = new Fragment_main_gouwuche();
        fragment_main_wode = new Fragment_main_wode();
        //Fragment
        list_f.add(fragment_main_shouye);
        list_f.add(fragment_main_pet);
        list_f.add(fragment_main_gouwuche);
        list_f.add(fragment_main_wode);
        //按钮
        list_b.add(mainButtomShouye);
        list_b.add(mainButtomPet);
        list_b.add(mainButtomGouwuche);
        list_b.add(mainButtomWode);
        //viewpager滚动监听
        viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < list_b.size(); i++) {
                    if (i == position) {
                        list_b.get(i).setSelectState(true);
                    } else {
                        list_b.get(i).setSelectState(false);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        viewpager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        //碎片适配器
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return list_f.get(position);
            }

            @Override
            public int getCount() {
                return list_f.size();
            }
        });
    }

    @OnClick({R.id.main_buttom_shouye, R.id.main_buttom_pet, R.id.main_buttom_gouwuche, R.id.main_buttom_wode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_buttom_shouye:
                viewpager.setCurrentItem(0);
                mainButtomShouye.setSelectState(true);
                mainButtomPet.setSelectState(false);
                mainButtomGouwuche.setSelectState(false);
                mainButtomWode.setSelectState(false);
                break;
            case R.id.main_buttom_pet:
                viewpager.setCurrentItem(1);
                mainButtomShouye.setSelectState(false);
                mainButtomPet.setSelectState(true);
                mainButtomGouwuche.setSelectState(false);
                mainButtomWode.setSelectState(false);
                break;
            case R.id.main_buttom_gouwuche:
                viewpager.setCurrentItem(2);
                mainButtomShouye.setSelectState(false);
                mainButtomPet.setSelectState(false);
                mainButtomGouwuche.setSelectState(true);
                mainButtomWode.setSelectState(false);
                break;
            case R.id.main_buttom_wode:
                viewpager.setCurrentItem(3);
                mainButtomShouye.setSelectState(false);
                mainButtomPet.setSelectState(false);
                mainButtomGouwuche.setSelectState(false);
                mainButtomWode.setSelectState(true);
                break;
        }
    }

    @Override
    public void onBackPressed() {

        if (System.currentTimeMillis() - firstTime < 3000) {
            BaseApplication.getApp().exitApp();
        } else {
            firstTime = System.currentTimeMillis();
            // 再按一次返回桌面
            ToastUtil.showToast(getString(R.string.main_press_again_back));
        }
    }

    /**
     * 通过服务器端请求获取token，客户端不提供获取token的接口
     */
    private final static String TAG = "TAG_融云";
    private static String rongyuntoken = ""; //通过融云Server API接口，获取的token
    private static String userHead = ""; //获取发送信息者头像的url
    private String userid;
    private String token;

    private void getToken() {
        userid = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        String userlogin = XCDSharePreference.getInstantiation(this).getSharedPreferences("userlogin");
        String userNick = XCDSharePreference.getInstantiation(this).getSharedPreferences("userNick");
        userHead = XCDSharePreference.getInstantiation(this).getSharedPreferences("userpicture");
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        createDialogshow();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        params.put("name", (userNick == null || "".equals(userNick)) ? "null" : userNick);
        params.put("userid", userid);
        params.put("nameurl", (userHead == null || "".equals(userHead)) ? "1" : userHead);
        okHttpPost(100, GlobalParam.RONGYUNKOKEN, params);

    }

    public static boolean isRongYunConnect = false;

    /**
     * 连接融云服务器
     */
    private void connect() {
        Log.e("TAG_", "rongyuntoken=" + rongyuntoken);
        RongIM.connect(rongyuntoken, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
             */
            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "Token 错误---onTokenIncorrect---" + '\n');
                isRongYunConnect = false;
            }

            /**
             * 连接融云成功
             * @param userid 当前 token
             */
            @Override
            public void onSuccess(final String userid) {
                Log.e(TAG, "连接融云成功---onSuccess---用户ID:" + userid + '\n');
                isRongYunConnect = true;
//                handler.sendEmptyMessage(1);
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "连接融云失败, 错误码: " + errorCode + '\n');
                isRongYunConnect = false;
            }
        });
    }
//    private Handler handler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            switch (msg.what){
//                case 1:
//                    Map<String, Object> params = new HashMap<String, Object>();
////                            params.put("id", userid);
//                    params.put("id", "20");
//                    params.put("token", token);
//                    okHttpPost(101, GlobalParam.FRAGMENTWODEINFO, params);
//                    break;
//            }
//        }
//    };
    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        if (returnCode == 1) {
            switch (requestCode) {
                case 100:
                    Log.e("TAG_", "returnData" + returnData);
                    RongYBean bean = JSON.parseObject(returnData, RongYBean.class);
                    rongyuntoken = bean.getData().getR_token();
                    connect();
                    break;
                case 101:
                    RongYunUserInfo result = JSON.parseObject(returnData, RongYunUserInfo.class);
                    RongYunUserInfo.FragmentMeInfoData userdata = result.getData();
                    String nickname = userdata.getNickname();
                    String image_head = userdata.getUserpicture();
                    String userid = userdata.getUser_id();
                    RongIM.getInstance().refreshUserInfoCache(new UserInfo(userid, nickname, Uri.parse(GlobalParam.IP+image_head)));
                    break;
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

    @Override
    public UserInfo getUserInfo(String userid) {
        Map<String, Object> params = new HashMap<String, Object>();
                    params.put("id", userid);
                    params.put("token", token);
                    okHttpPost(101, GlobalParam.FRAGMENTWODEINFO, params);
        return null;
    }
}
