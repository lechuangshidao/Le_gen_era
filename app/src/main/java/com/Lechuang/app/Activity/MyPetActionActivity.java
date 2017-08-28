package com.Lechuang.app.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Lechuang.app.Bean.PetMessageInfo;
import com.Lechuang.app.Bean.UpImageBean;
import com.Lechuang.app.R;
import com.Lechuang.app.Utils.GlideCircleTransform;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.view.SelectPopupWindow;
import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.activity.PermissionsActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

import static www.xcd.com.mylibrary.activity.PermissionsActivity.PERMISSIONS_GRANTED;

/**
 * Created by Android on 2017/7/24.
 */

public class MyPetActionActivity extends ChatActivity implements TextWatcher {

    private EditText action_title, action_context;
    private ImageView action_image, action_petarrows;
    private Button action_ok;
    private TextView action_titletext, action_contexttext, action_pet;
    private String picurl;
    private String user_id;
    private String token;
    private List<PetMessageInfo.PetMessageData> data;
    private String pet_id;
    private LinearLayout action_petlinear;
    @Override
    protected Object getTopbarTitle() {
        return R.string.startaction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetaction);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        initView();
    }

    public void initView() {
        action_title = (EditText) findViewById(R.id.action_title);
        action_title.setOnFocusChangeListener(this);
        action_title.addTextChangedListener(this);
        action_context = (EditText) findViewById(R.id.action_context);
        action_context.setOnFocusChangeListener(this);
        action_context.addTextChangedListener(this);
        action_image = (ImageView) findViewById(R.id.action_image);
        action_image.setOnClickListener(this);
        action_ok = (Button) findViewById(R.id.action_ok);
        action_ok.setOnClickListener(this);
        action_titletext = (TextView) findViewById(R.id.action_titletext);
        action_contexttext = (TextView) findViewById(R.id.action_contexttext);
        action_pet = (TextView) findViewById(R.id.action_pet);
        action_pet.setOnClickListener(this);
        user_id = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        action_petarrows = (ImageView) findViewById(R.id.action_petarrows);
        action_petlinear = (LinearLayout) findViewById(R.id.action_petlinear);
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("token", token);
        okHttpPost(101, GlobalParam.MYPETINFO, params);
    }

    private SelectPopupWindow mPopupWindow = null;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.action_image:
                PermissionsActivity.startActivityForResult(this, PERMISSIONS_GRANTED, PERMISSIONS);
                break;
            case R.id.action_pet:
                if (data == null && data.size() == 0) {
                    ToastUtil.showToast("暂未获取到宠物信息");
                }
                if (mPopupWindow == null) {
                    mPopupWindow = new SelectPopupWindow((List<PetMessageInfo.PetMessageData>) data, this, selectCategory);
                }
                mPopupWindow.showAsDropDown(action_pet, -5, 10);
//                showCreateMultiChatActionBar(action_petarrows);
                break;
            case R.id.action_ok:
                if (TextUtils.isEmpty(pet_id)) {
                    ToastUtil.showToast("请选择参加活动的宠物");
                    return;
                }
                String title = action_title.getText().toString().trim();
                if (TextUtils.isEmpty(title)) {
                    ToastUtil.showToast("活动标题不能为空！");
                    return;
                }
                String context_string = action_context.getText().toString().trim();
                if (TextUtils.isEmpty(context_string)) {
                    ToastUtil.showToast("活动内容不能为空！");
                    return;
                }
                if (picurl == null) {
                    picurl = "";
                }
                createDialogshow();
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("user_id", user_id);
                params.put("token", token);
                params.put("title", title);
                params.put("content", context_string);
                params.put("picurl", picurl);
                params.put("pet_id", pet_id);
                okHttpPost(100, GlobalParam.MYPETACTOIN, params);
                break;
        }
    }


    public void uploadImage(final List<File> list) {
        // 调用上传
        for (File imagepath : list) {
            picurl = imagepath.toString();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("file", imagepath);
            params.put("type", "user");
            params.put("user_id", user_id);
            okHttpImgPost(102, GlobalParam.UPIMAGE, params);
        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        if (returnCode == 1) {
            switch (requestCode) {
                case 100:
                    this.setResult(Activity.RESULT_OK);
                    finish();
                    break;
                case 101:

                    PetMessageInfo info = JSON.parseObject(returnData, PetMessageInfo.class);
                    data = info.getData();

                    break;
                case 102:
                    UpImageBean imageBean = zuo.biao.library.util.JSON.parseObject(returnData,UpImageBean.class);
                    picurl = imageBean.getData().getPicurl();
                    Glide.with(this)
                            .load(GlobalParam.IP+picurl)
                            .centerCrop()
                            .crossFade()
                            .transform(new GlideCircleTransform(this))
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.mipmap.photoadd)
                            .error(R.mipmap.photoadd)
                            .into(action_image);
                    break;

            }

        }else {
            ToastUtil.showToast(returnMsg);
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
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        action_titletext.setText("(还可以输入" + (8 - action_title.length()) + "个字)");
        action_contexttext.setText("(还可以输入" + (120 - action_context.length()) + "个字)");
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }


    /**
     * 选择完成回调接口
     */
    private SelectPopupWindow.SelectCategory selectCategory = new SelectPopupWindow.SelectCategory() {

        @Override
        public void selectCategory(int parentSelectposition) {
            PetMessageInfo.PetMessageData petMessageData = data.get(parentSelectposition);
            action_pet.setText(petMessageData.getPet_name());
            pet_id = petMessageData.getPet_id();
        }
    };
}
