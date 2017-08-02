package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.MyPetCompileTopBtnFunc;
import com.Lechuang.app.threelevelganged.ArrayWheelAdapter;
import com.Lechuang.app.threelevelganged.BaseThreeActivity;
import com.Lechuang.app.threelevelganged.OnWheelChangedListener;
import com.Lechuang.app.threelevelganged.WheelView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class MyPetLocationCompileActivity extends BaseThreeActivity implements View.OnClickListener, OnWheelChangedListener, CompoundButton.OnCheckedChangeListener {

    private static Class<?> rightFuncArray[] = {MyPetCompileTopBtnFunc.class};
    private LinearLayout address_select;
    private TextView address;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button btn_confirm, btn_off,dellocation;
    private EditText name, phone, address_all;
    private String address_id;
    private String consignee;
    private String phoneString;
    private String addressString;
    private String x_address;
    private String token;
    private int type = 1;//記錄默認地址臨時變量
    @Override
    protected Object getTopbarTitle() {
        return "编辑地址";
    }

    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return rightFuncArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetlocationcompile);

    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        initView();
        setUpViews();
        setUpListener();
        setUpData();
    }

    private void initView() {
        token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        Intent intent = getIntent();
        address_id = intent.getStringExtra("address_id");
        consignee = intent.getStringExtra("consignee");
        phoneString = intent.getStringExtra("phone");
        addressString = intent.getStringExtra("address");
        String pretermit = intent.getStringExtra("default");
        x_address = intent.getStringExtra("x_address");
        address_select = (LinearLayout) findViewById(R.id.address_select);
        name = (EditText) findViewById(R.id.name);
        name.setHint(consignee == null ? "未知" : consignee);
        phone = (EditText) findViewById(R.id.phone);
        phone.setHint(phoneString == null ? "未知" : phoneString);
        address = (TextView) findViewById(R.id.address);
        address.setOnClickListener(this);
        address.setText(addressString == null ? "未知" : addressString);
        address_all = (EditText) findViewById(R.id.address_all);
        address_all.setText(x_address == null ? "未知" : x_address);
        address_all = (EditText) findViewById(R.id.address_all);
        address_all.setText(x_address == null ? "未知" : x_address);
        //
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_off = (Button) findViewById(R.id.btn_off);
        btn_confirm.setOnClickListener(this);
        btn_off.setOnClickListener(this);
        dellocation = (Button) findViewById(R.id.dellocation);
        dellocation.setOnClickListener(this);
        Switch switch_button = (Switch) findViewById(R.id.switch_button);
        switch_button.setOnCheckedChangeListener(this);
        if ("1".equals(pretermit)) {
            switch_button.setChecked(true);
            type = 1;
        } else {
            switch_button.setChecked(false);
            type = 0;
        }
    }

    private void setUpViews() {
        mViewProvince = (WheelView) findViewById(R.id.id_province);
        mViewCity = (WheelView) findViewById(R.id.id_city);
        mViewDistrict = (WheelView) findViewById(R.id.id_district);
    }

    private void setUpListener() {
        // 添加change事件
        mViewProvince.addChangingListener(this);
        // 添加change事件
        mViewCity.addChangingListener(this);
        // 添加change事件
        mViewDistrict.addChangingListener(this);
    }

    private void setUpData() {
        initProvinceDatas();
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(MyPetLocationCompileActivity.this, mProvinceDatas));
        // 设置可见条目数量
        mViewProvince.setVisibleItems(7);
        mViewCity.setVisibleItems(7);
        mViewDistrict.setVisibleItems(7);
        updateCities();
        updateAreas();
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mViewCity.getCurrentItem();
        mCurrentCityName = mCitisDatasMap.get(mCurrentProviceName)[pCurrent];
        String[] areas = mDistrictDatasMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);

        mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[pCurrent];
        mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {
        int pCurrent = mViewProvince.getCurrentItem();
        mCurrentProviceName = mProvinceDatas[pCurrent];
        String[] cities = mCitisDatasMap.get(mCurrentProviceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.address:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                address_select.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_confirm:
                address_select.setVisibility(View.GONE);
                address.setText(mCurrentProviceName + "-" + mCurrentCityName + "-"
                        + mCurrentDistrictName);
                break;
            case R.id.btn_off:
                address_select.setVisibility(View.GONE);
                break;
            case R.id.dellocation:
                createDialogshow();
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("address_id", address_id);
                params.put("token", token);
                okHttpPost(102, GlobalParam.MYPETDELLOCATION, params);
                break;
        }
    }

    public void getSavaAddress() {
        consignee = name.getText().toString().toString();
        if (TextUtils.isEmpty(consignee)){
            consignee = name.getHint().toString().trim();
        }
        phoneString = phone.getText().toString().toString();
        if (TextUtils.isEmpty(phoneString)){
            phoneString = phone.getHint().toString().trim();
        }
        addressString = address.getText().toString().toString();
        if (TextUtils.isEmpty(addressString)){
            addressString = address.getHint().toString().trim();
        }
        x_address = address_all.getText().toString().toString();
        if (TextUtils.isEmpty(x_address)){
            x_address = address_all.getHint().toString().trim();
        }
        createDialogshow();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("address_id", address_id);
        params.put("token", token);
        params.put("Consignee", consignee);
        params.put("phone",phoneString);
        params.put("address", addressString);
        params.put("x_address", x_address);
        params.put("default", type);
        okHttpPost(101, GlobalParam.MYPETADDLOCATIONCOMPILE, params);
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 101:
                if (returnCode==1){
                    this.setResult(Activity.RESULT_OK);
                    ToastUtil.showToast(returnMsg);
                    finish();
                }else {
                    ToastUtil.showToast(returnMsg);
                }
                break;
            case 102:
                if (returnCode==1){
                    this.setResult(Activity.RESULT_OK);
                    ToastUtil.showToast(returnMsg);
                    finish();
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

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mViewProvince) {
            updateCities();
        } else if (wheel == mViewCity) {
            updateAreas();
        } else if (wheel == mViewDistrict) {
            mCurrentDistrictName = mDistrictDatasMap.get(mCurrentCityName)[newValue];
            mCurrentZipCode = mZipcodeDatasMap.get(mCurrentDistrictName);
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b) {
            type = 1;
        } else {
            type = 0;
        }
    }
}
