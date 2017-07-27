package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;
import com.Lechuang.app.func.CommonBackTopBtnFunc_right;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class MainActivity extends SimpleTopbarActivity {

    private static Class<?> rightFuncArray1[] = {CommonBackTopBtnFunc_right.class};
    @Bind(R.id.edit_callphone_main)
    EditText editCallphoneMain;
    @Bind(R.id.text_next)
    TextView textNext;
    private UMShareAPI umShareAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.text_qq_log, R.id.text_weixin_log, R.id.text_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_qq_log:
                // 使用哪个平台账号登录
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.text_weixin_log:
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.text_next:
                editCallphoneMain.setText("15010481234");
                String userphone = editCallphoneMain.getText().toString().trim();
                if (TextUtils.isEmpty(userphone)) {
                    Toast.makeText(MainActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean result = Call_Phone_Utils.isPhone(userphone);
                if (result == false && userphone.length() != 11) {
                    Toast.makeText(MainActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!TextUtils.isEmpty(editCallphoneMain.getText().toString().trim())) {
                    Intent intent = new Intent(MainActivity.this, CallPhone_Activity.class);
                    intent.putExtra("callphone", editCallphoneMain.getText().toString());
                    startActivity(intent);
                } else {
                    textNext.setEnabled(true);
                }
                break;
        }
    }

    /**
     * 登录
     */
    public void login(SHARE_MEDIA platform) {

        // 获取UMShareAPI
        umShareAPI = UMShareAPI.get(this);

        umShareAPI.doOauthVerify(this, platform, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            //授权成功会回调onComplete
            @Override
            public void onComplete(SHARE_MEDIA platform, int action,
                                   Map<String, String> data) {
                Toast.makeText(getApplicationContext(), "Authorize succeed",
                        Toast.LENGTH_SHORT).show();
            }

            //授权错误回调
            @Override
            public void onError(SHARE_MEDIA platform, int action, Throwable t) {
                Toast.makeText(getApplicationContext(), "Authorize fail",
                        Toast.LENGTH_SHORT).show();
            }

            //取消授权回调
            @Override
            public void onCancel(SHARE_MEDIA platform, int action) {
                Toast.makeText(getApplicationContext(), "Authorize cancel",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return CommonBackTopBtnFunc_or.class;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        umShareAPI.onActivityResult(requestCode, resultCode, data);
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
