package com.Lechuang.app.RongCloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.HttpUtil;
import com.Lechuang.app.fakeserver.FakeServer;

import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import io.rong.imlib.RongIMClient;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class Rong_news extends SimpleTopbarActivity {
    private final static String TAG = "TAG_融云";
    private String token = "XdolEQCwglBOfOWIzO6iaMbNMu74vLz1mThVQmV31omKdWZbvzP/CjlsuOXjJSc/Fu+XxIrHexXTjvkRD3mnarbSFA+3oLbw"; //通过融云Server API接口，获取的token
    private static String userHead = "http://static.yingyonghui.com/screenshots/1657/1657011_5.jpg"; //获取发送信息者头像的url
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rong_news);
        getToken();
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return super.getTopbarLeftFunc();
    }

    @Override
    protected Object getTopbarTitle() {
        return "消息";
    }
    /**
     * 通过服务器端请求获取token，客户端不提供获取token的接口
     */
    private void getToken() {
        String userid = XCDSharePreference.getInstantiation(this).getSharedPreferences("userlogin");
        String userNick = XCDSharePreference.getInstantiation(this).getSharedPreferences("userNick");
        userHead = XCDSharePreference.getInstantiation(this).getSharedPreferences("userHead");
        FakeServer.getToken(userid, "豆豆", userHead, new HttpUtil.OnResponse() {
            @Override
            public void onResponse(int code, String body) {
                if (code == 200) {
                    JSONObject jsonObj = null;
                    try {
                        jsonObj = new JSONObject(body);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    token = jsonObj.optString("token");
                    handler.sendEmptyMessage(1);
                    Log.e(TAG, "获取的 token 值为:\n" + token + '\n');
                } else {
                    Log.e(TAG, "获取 token 失败" + '\n');
                    handler.sendEmptyMessage(1);
                }
            }
        });
    }
    /**
     * 连接融云服务器
     */
    private void connect() {
        RongIMClient.connect(token, new RongIMClient.ConnectCallback() {

            /**
             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
             */
            @Override
            public void onTokenIncorrect() {
                Log.e(TAG, "Token 错误---onTokenIncorrect---" + '\n');
            }

            /**
             * 连接融云成功
             * @param userid 当前 token
             */
            @Override
            public void onSuccess(String userid) {
                Log.e(TAG, "连接融云成功---onSuccess---用户ID:" + userid + '\n');
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e(TAG, "连接融云失败, 错误码: " + errorCode + '\n');
            }
        });
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
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    connect();
                    break;
            }
        }
    };
}