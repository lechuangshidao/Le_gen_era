package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Context;
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
import com.Lechuang.app.Utils.Call_Phone_Utils;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.MyPetSaveTextTopBtnFunc;
import com.Lechuang.app.threelevelganged.ArrayWheelAdapter;
import com.Lechuang.app.threelevelganged.BaseThreeActivity;
import com.Lechuang.app.threelevelganged.OnWheelChangedListener;
import com.Lechuang.app.threelevelganged.WheelView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class MyPetAddLocationActivity extends BaseThreeActivity implements CompoundButton.OnCheckedChangeListener,View.OnClickListener,OnWheelChangedListener {

    private static Class<?> rightFuncArray[] = {MyPetSaveTextTopBtnFunc.class};
    private Switch switch_button;
    private String token;
    private String user_id;

    private EditText name,phone,address;
    private TextView region;
    private int tpye = 0;
    private LinearLayout address_select;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button btn_confirm,btn_off;
    @Override
    protected Object getTopbarTitle() {
        return "新增地址";
    }

    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return rightFuncArray;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetaddlocation);
        token = XCDSharePreference.getSharedPreferences("token");
        user_id = XCDSharePreference.getSharedPreferences("user_id");
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
        switch_button = (Switch) findViewById(R.id.switch_button);
        switch_button.setOnCheckedChangeListener(this);
        name = (EditText) findViewById(R.id.name);
        name.setOnFocusChangeListener(this);
        phone = (EditText) findViewById(R.id.phone);
        phone.setOnFocusChangeListener(this);
        region = (TextView) findViewById(R.id.region);
        region.setOnClickListener(this);
        address = (EditText) findViewById(R.id.address);
        address.setOnFocusChangeListener(this);

        address_select = (LinearLayout) findViewById(R.id.address_select);
        //
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_off = (Button) findViewById(R.id.btn_off);
        btn_confirm.setOnClickListener(this);
        btn_off.setOnClickListener(this);
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
        mViewProvince.setViewAdapter(new ArrayWheelAdapter<String>(this, mProvinceDatas));
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
            areas = new String[] { "" };
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
            cities = new String[] { "" };
        }
        mViewCity.setViewAdapter(new ArrayWheelAdapter<String>(this, cities));
        mViewCity.setCurrentItem(0);
        updateAreas();
    }
    public void getSavaAddress(){
        String namestring = name.getText().toString().trim();
        if (TextUtils.isEmpty(namestring)){
            ToastUtil.showToast("收货人姓名不能为空");
            return;
        }

        String phonestring = phone.getText().toString().trim();
                                                                                                                   if (TextUtils.isEmpty(phonestring)){
            ToastUtil.showToast("手机号不能为空");
            return;
        }
        if (!Call_Phone_Utils.isPhone(phonestring)){
            ToastUtil.showToast("您输入的手机号不正确");
            return;
        }
        String regionstring = region.getText().toString().trim();
        if (TextUtils.isEmpty(regionstring)){
            ToastUtil.showToast("所在地区不能为空");
            return;
        }
        String addressstring = address.getText().toString().trim();
        if (TextUtils.isEmpty(addressstring)){
            ToastUtil.showToast("详细地址不能为空");
            return;
        }
        createDialogshow();
        String user_id = XCDSharePreference.getInstantiation(this).getSharedPreferences("user_id");
        String token = XCDSharePreference.getInstantiation(this).getSharedPreferences("token");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", user_id);
        params.put("token", token);
        params.put("Consignee", namestring);
        params.put("phone", phonestring);
        params.put("address", regionstring);
        params.put("x_address", addressstring);
        params.put("default", tpye);
        okHttpPost(100, GlobalParam.MYPETADDLOCATION, params);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.region:
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0); //强制隐藏键盘
                address_select.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_confirm:
                address_select.setVisibility(View.GONE);
                region.setText(mCurrentProviceName + "-" + mCurrentCityName + "-"
                        +mCurrentDistrictName);
                break;
            case R.id.btn_off:
                address_select.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (returnCode==1){
                    this.setResult(Activity.RESULT_OK);
                    finish();
                }
                ToastUtil.showToast(returnMsg);
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
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            tpye = 1;
        }else {
            tpye = 0;
        }
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
    public void onFocusChange(View view, boolean hasFocus) {
        super.onFocusChange(view, hasFocus);
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        if(imm.isActive()) {
            address_select.setVisibility(View.GONE);
        }
    }
}
