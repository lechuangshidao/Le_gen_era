package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.Bean.UpImageBean;
import com.Lechuang.app.R;
import com.Lechuang.app.Utils.GlideCircleTransform;
import com.Lechuang.app.Utils.HelpUtils;
import com.Lechuang.app.entity.GlobalParam;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.util.JSON;
import zuo.biao.library.util.TimeUtil;

public class MyPetMessageActivity extends ChatActivity {

    private static final int REQUEST_TO_DATE_PICKER = 34;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private TextView select_birthday;
    private EditText edit_name, edit_type;
    private ImageView meinfo_head;
    private Button button;
    private String pet_img;
    private String pet_name;
    private String pet_type;
    private String pet_age;
    private String user_id;
    private String token;
    private String id;

    @Override
    protected Object getTopbarTitle() {
        return R.string.petmessage;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetmessage);

    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        initView();
    }

    public void initView() {
        user_id = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        pet_img = intent.getStringExtra("pet_img");
        pet_name = intent.getStringExtra("pet_name");
        pet_type = intent.getStringExtra("pet_type");
        pet_age = intent.getStringExtra("pet_age");
        select_birthday = (TextView) findViewById(R.id.select_birthday);
        select_birthday.setOnClickListener(this);
        select_birthday.setHint(pet_age);
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_name.setHint(pet_name);
        edit_name.setOnFocusChangeListener(this);
        edit_type = (EditText) findViewById(R.id.edit_type);
        edit_type.setHint(pet_type);
        edit_type.setOnFocusChangeListener(this);
        //完成按钮
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        meinfo_head = (ImageView) findViewById(R.id.meinfo_head);
        meinfo_head.setOnClickListener(this);
        Glide.with(context.getApplicationContext())
                .load(GlobalParam.IP + pet_img)
                .centerCrop()
                .crossFade()
                .transform(new GlideCircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.pethead)
                .error(R.mipmap.pethead)
                .into(meinfo_head);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.select_birthday:
                toActivity(DatePickerWindow.createIntent(MyPetMessageActivity.this, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
                break;
            case R.id.meinfo_head:
                getChoiceDialog().show();
                break;
            case R.id.button:

                pet_type = edit_type.getText().toString().toString();
                if (TextUtils.isEmpty(pet_type)) {
                    pet_type = edit_type.getHint().toString().toString();
                    if ((getResources().getString(R.string.pet_type)).equals(pet_name)) {
                        ToastUtil.showToast("宠物品种不能为空");
                        return;
                    }
                }
                pet_name = edit_name.getText().toString().toString();
                if (TextUtils.isEmpty(pet_name)) {
                    pet_name = edit_name.getHint().toString().toString();

                    if ((getResources().getString(R.string.pet_name)).equals(pet_name)) {
                        ToastUtil.showToast("宠物昵称不能为空");
                        return;
                    }
                }
                pet_age = select_birthday.getText().toString().toString();
                if (TextUtils.isEmpty(pet_age)) {
                    pet_age = select_birthday.getHint().toString().toString();
                    if ((getResources().getString(R.string.pet_age)).equals(pet_name)) {
                        ToastUtil.showToast("宠物年龄不能为空");
                        return;
                    }
                }
                createDialogshow();
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("id", id);
                params.put("token", token);
                params.put("pet_type", pet_type);
                params.put("pet_name", pet_name);
                params.put("pet_age", pet_age);
                params.put("pet_img", pet_img);
                okHttpPost(100, GlobalParam.PETMODIFYMYPETINFO, params);
                break;
        }
    }

    @Override
    public void uploadImage(List<File> list) {
        super.uploadImage(list);
        // 调用上传
        for (File imagepath : list) {
            pet_img = imagepath.toString();
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("file", imagepath);
            params.put("type", "user");
            params.put("user_id", user_id);
            okHttpImgPost(101, GlobalParam.UPIMAGE, params);
        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        if (returnCode == 1) {
            switch (requestCode) {
                case 100:
                    this.setResult(Activity.RESULT_OK);
                    ToastUtil.showToast(returnMsg);
                    finish();
                    break;
                case 101:
                    try {
                        UpImageBean imageBean = JSON.parseObject(returnData,UpImageBean.class);
                        pet_img = imageBean.getData().getPicurl();
                        Glide.with(this)
                                .load(GlobalParam.IP+pet_img)
                                .centerCrop()
                                .crossFade()
                                .transform(new GlideCircleTransform(getActivity()))
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .placeholder(R.mipmap.image_wode_geren)
                                .error(R.mipmap.image_wode_geren)
                                .into(meinfo_head);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        } else {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case REQUEST_TO_DATE_PICKER:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                    if (list != null && list.size() >= 3) {

                        selectedDate = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            selectedDate[i] = list.get(i);
                        }
                        String time = selectedDate[0] + "-" + (selectedDate[1] + 1) + "-" + selectedDate[2];
                        String timeDifference = HelpUtils.getTimeDifference(time);
                        if (timeDifference.indexOf("-1") != -1) {
                            ToastUtil.showToast("请选择正确出生日期");
                        } else {
                            select_birthday.setText(timeDifference);
                        }

                    }
                }
                break;
        }

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
