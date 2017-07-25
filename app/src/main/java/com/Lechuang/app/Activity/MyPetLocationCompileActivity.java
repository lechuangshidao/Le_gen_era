package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.func.MyPetCompileTopBtnFunc;
import com.Lechuang.app.threelevelganged.ArrayWheelAdapter;
import com.Lechuang.app.threelevelganged.BaseThreeActivity;
import com.Lechuang.app.threelevelganged.OnWheelChangedListener;
import com.Lechuang.app.threelevelganged.WheelView;

import java.io.IOException;
import java.util.Map;

import www.xcd.com.mylibrary.utils.ToastUtil;

public class MyPetLocationCompileActivity extends BaseThreeActivity implements View.OnClickListener,OnWheelChangedListener {

    private static Class<?> rightFuncArray[] = {MyPetCompileTopBtnFunc.class};
    private LinearLayout address_select;
    private TextView address;
    private WheelView mViewProvince;
    private WheelView mViewCity;
    private WheelView mViewDistrict;
    private Button btn_confirm,btn_off;
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
        address_select = (LinearLayout) findViewById(R.id.address_select);
        address = (TextView) findViewById(R.id.address);
        address.setOnClickListener(this);
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
            areas = new String[] { "" };
        }
        mViewDistrict.setViewAdapter(new ArrayWheelAdapter<String>(this, areas));
        mViewDistrict.setCurrentItem(0);
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
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.address:
                address_select.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_confirm:
                address_select.setVisibility(View.GONE);
                address.setText(mCurrentProviceName + "-" + mCurrentCityName + "-"
                        +mCurrentDistrictName);
                break;
            case R.id.btn_off:
                address_select.setVisibility(View.GONE);
                break;
        }
    }

    public void getSavaAddress(){
        ToastUtil.showToast("点击保存按钮");
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
}
