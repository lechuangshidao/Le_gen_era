package com.Lechuang.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.Lechuang.app.Activity.FuJin_pet_Activity;
import com.Lechuang.app.Activity.FuWuActivity;
import com.Lechuang.app.Activity.Fujin_Activity;
import com.Lechuang.app.Bean.MapLocation;
import com.Lechuang.app.Bean.PetClassification;
import com.Lechuang.app.Bean.PetHospital;
import com.Lechuang.app.Bean.PetInfo;
import com.Lechuang.app.Bean.PetSeek;
import com.Lechuang.app.R;
import com.Lechuang.app.adapter.ChildAdapter;
import com.Lechuang.app.adapter.GroupAdapter;
import com.Lechuang.app.adapter.SunAdapter;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.CommonBackTopBtnFunc_shouye;
import com.Lechuang.app.func.CommonBackTopBtnFunc_shouye_right;
import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.Projection;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import io.rong.imkit.RongIM;
import www.xcd.com.mylibrary.base.fragment.BaseFragment;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class Fragment_main_shouye extends BaseFragment implements LocationSource, AMapLocationListener, View.OnClickListener, AMap.OnMarkerClickListener {
    private View view;
    MapView mMapView = null;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    AMap aMap;
    boolean isFirstLoc = true;
    OnLocationChangedListener mListener;
    public static StringBuffer buffer;
    private double latitude;
    private double longitude;
    private Marker marker;
    private String token;
    TranslateAnimation animation;// 出现的动画效果
    PopupWindow mPopupWindow = null;
    private SunAdapter sunadapter;
    private List<PetClassification.DataBean.PetoneBean> petone;
    private LinearLayout area_layout;
    private LinearLayout wage_layout;
    private GroupAdapter groupAdapter;
    private ChildAdapter childAdapter;
    private TextView text_communication;
    private String user_id;
    private TextView text_pet_name_info;
    private TextView text_pet_age;
    private String mylng;
    private String mylat;
    private ListView groupListView;
    private ListView childListView;
    private ListView listView_three;
    private View inflate_pop;
    private List<PetClassification.DataBean.PetoneBean.PettwoBean> pettwo;
    private TextView text_pet_tag;
    private PetInfo.DataBean data_info;
    private LinearLayout linear_per;
    private LinearLayout linear_hospital;
    private TextView text_pet_hospiatl_name;
    private ImageView img_pet_hospiat;
    private EditText edit_location;
    private TextView button_cancle;
    private TextView tv_cancel;
    private TextView tv_content;
    /**
     * Topbar功能列表
     */
    private static Class<?> rightFuncArray[] = {CommonBackTopBtnFunc_shouye_right.class};
    private ImageView image_shouye_seek;
    private ImageView image_shouye_fujin;
    private ImageView image_shouye_fuwu;
    private List<PetHospital.DataBeanXX.PetBean.DataBean> dataPet;
    private List<PetHospital.DataBeanXX.HospitalBean.DataBeanX> dataHospatil;
    private List<PetClassification.DataBean> data;
    private String id;
    private String lat;
    private String lon;
    private String nickname;
    private String pet_name;
    private String pet_age;
    private String pet_tag;
    private String userlogin;
    private String userpicture;
    private String tel;
    private String h_name;
    private String lat_hospital;
    private String lon_hospital;
    private String h_img;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_fragment_main_shouye;
    }

    /*
    * 初始化控件
    * */
    @Override
    protected void initView(LayoutInflater inflater, View view) {
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        token = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("token");
        user_id = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("user_id");
        mMapView = (MapView) view.findViewById(R.id.map);// 获取mapView实例
        image_shouye_seek = (ImageView) view.findViewById(R.id.image_shouye_seek);
        image_shouye_fujin = (ImageView) view.findViewById(R.id.image_shouye_fujin);
        image_shouye_fuwu = (ImageView) view.findViewById(R.id.image_shouye_fuwu);
        image_shouye_seek.setOnClickListener(this);
        image_shouye_fujin.setOnClickListener(this);
        image_shouye_fuwu.setOnClickListener(this);
    }

    /*
    * 地图
    * */
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图

        mMapView.onCreate(savedInstanceState);
        if (mMapView == null) {
            aMap = mMapView.getMap();
        }
        InitPositioning();//定位
        setUpMap();//设置一些amap的属性
        //点击覆盖物显示气泡
        aMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            /**
             * 监听自定义infowindow窗口的infocontents事件回调
             */
            @Override
            public View getInfoWindow(Marker marker) {
                String title = marker.getTitle();
                int position = (int) marker.getObject();
                View infoWindow = render(title, position);
                return infoWindow;
            }

            /**
             * 监听自定义infowindow窗口的infowindow事件回调
             */
            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });

    }

    //标题
    @Override
    protected Object getTopbarTitle() {
        return "首页";
    }

    //左边功能
    @Override
    protected Class<?> getTopbarLeftFunc() {
        return CommonBackTopBtnFunc_shouye.class;
    }

    //右边
    @Override
    protected Class<?>[] getTopbarRightFuncArray() {
        return rightFuncArray;
    }

    //定位
    private void InitPositioning() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听,这里要实现LocationSource接口
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(false);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
            settings.setScrollGesturesEnabled(true);
            settings.setZoomControlsEnabled(false);
        }
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
        //设置定位回调监听，这里要实现AMapLocationListener接口，AMapLocationListener接口只有onLocationChanged方法可以实现，用于接收异步返回的定位结果，参数是AMapLocation类型。
        mLocationClient.setLocationListener(this);
        //初始化定位参数
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否只定位一次,默认为false
        mLocationOption.setOnceLocation(false);
        //设置是否强制刷新WIFI，默认为强制刷新
        mLocationOption.setWifiActiveScan(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //设置定位间隔,单位毫秒,默认为2000ms
        mLocationOption.setInterval(2000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    /**
     * 设置一些amap的属性
     */
    private void setUpMap() {
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory
                .fromResource(R.mipmap.image_pet));// 设置小蓝点的图标
        myLocationStyle.strokeColor(Color.BLUE);// 设置圆形的边框颜色         32
        myLocationStyle.radiusFillColor(Color.argb(100, 0, 0, 180));// 设置圆形的填充颜色
        myLocationStyle.strokeWidth(1.0f);// 设置圆形的边框粗细
        aMap.setMyLocationStyle(myLocationStyle);
        aMap.setLocationSource(this);// 设置定位资源。如果不设置此定位资源则定位按钮不可点击。并且实现activate激活定位,停止定位的回调方法
        aMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onDestroyThread() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    /*
    * 地图定位信息事件
    * */
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //定位成功回调信息，设置相关消息
                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见官方定位类型表
                //获取纬度
                latitude = aMapLocation.getLatitude();
                //获取经度
                longitude = aMapLocation.getLongitude();
                aMapLocation.getAccuracy();//获取精度信息
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date = new Date(aMapLocation.getTime());
                df.format(date);//定位时间
                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                aMapLocation.getCountry();//国家信息
                aMapLocation.getProvince();//省信息
                aMapLocation.getCity();//城市信息
                aMapLocation.getDistrict();//城区信息
                aMapLocation.getStreet();//街道信息
                aMapLocation.getStreetNum();//街道门牌号信息
                aMapLocation.getCityCode();//城市编码
                aMapLocation.getAdCode();//地区编码
                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
                if (isFirstLoc) {
                    //设置缩放级别
                    aMap.moveCamera(CameraUpdateFactory.zoomTo(17));
                    //将地图移动到定位点
                    aMap.moveCamera(CameraUpdateFactory.changeLatLng(new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude())));
                    //点击定位按钮 能够将地图的中心移动到定位点
                    mListener.onLocationChanged(aMapLocation);
                    //获取定位信息
                    buffer = new StringBuffer();
                    buffer.append(aMapLocation.getCountry() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getCity() + ""
                            + aMapLocation.getProvince() + ""
                            + aMapLocation.getDistrict() + ""
                            + aMapLocation.getStreet() + ""
                            + aMapLocation.getStreetNum());
                    Log.i("data", "维度" + latitude + "精度" + longitude);
                    Toast.makeText(getActivity(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
                    getHomePager();//首页地图展示宠物的信息、医院信息、定位信息。
                }
            } else {
                //显示错误信息ErrCode是错误码，errInfo是错误信息，详见错误码表。
                Log.e("AmapError", "location Error, ErrCode:"
                        + aMapLocation.getErrorCode() + ", errInfo:"
                        + aMapLocation.getErrorInfo());
                Toast.makeText(getActivity(), "定位失败", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.image_shouye_fujin://附近人
                Intent intent_fujin = new Intent(getActivity(), Fujin_Activity.class);
                startActivity(intent_fujin);
                break;
            case R.id.image_shouye_fuwu://附近服务
                Intent intent_fuwu = new Intent(getActivity(), FuWuActivity.class);
                intent_fuwu.putExtra("lng", longitude);
                intent_fuwu.putExtra("lat", latitude);
                startActivity(intent_fuwu);
                break;
            case R.id.image_shouye_seek://搜索
                show();
                break;
        }
    }

    //点击搜索弹框
    private void show() {
        LayoutInflater factor = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = factor.inflate(R.layout.layout_camera_control, null);
        edit_location = (EditText) inflate.findViewById(R.id.edit_location);
        final TextView image_dialog_back = (TextView) inflate.findViewById(R.id.area_textView);
        ImageView text_dialog_name = (ImageView) inflate.findViewById(R.id.area_img);
        TextView wage_textView = (TextView) inflate.findViewById(R.id.wage_textView);
        ImageView wage_img = (ImageView) inflate.findViewById(R.id.wage_img);
        area_layout = (LinearLayout) inflate.findViewById(R.id.area_layout);
        wage_layout = (LinearLayout) inflate.findViewById(R.id.wage_layout);
        button_cancle = (TextView) inflate.findViewById(R.id.button_cancle);
        final AlertDialog.Builder customizeDialog = new AlertDialog.Builder(getActivity(), R.style.DialogTheme);
        AlertDialog alertDialog;
        alertDialog = customizeDialog.create();
        alertDialog.show();
        alertDialog.setContentView(inflate);
        Window window = alertDialog.getWindow();
        window.setGravity(Gravity.TOP);
        window.setWindowAnimations(R.style.dialog_animation);
        window.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        // 设置点击外围解散
        alertDialog.setCanceledOnTouchOutside(true);
        int[] location = new int[2];
        //全部宠物
        wage_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //附近宠物
        area_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPupupWindow();
            }
        });
        //取消按钮
        button_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edit_location.setText("");
            }
        });
        edit_location.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //这里注意要作判断处理，ActionDown、ActionUp都会回调到这里，不作处理的话就会调用两次
                if (KeyEvent.KEYCODE_ENTER == keyCode && KeyEvent.ACTION_DOWN == event.getAction()) {
                    getSeekPet();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode) {
            case 100://首页定位信息展示
                if (1 == returnCode) {
                    PetHospital homePager = JSON.parseObject(returnData, PetHospital.class);
                    dataPet = homePager.getData().getPet().getData();
                    dataHospatil = homePager.getData().getHospital().getData();
                    //宠物
                    for (int i = 0; i < dataPet.size(); i++) {
                        String pet_img = dataPet.get(i).getPet_img();
                        id = dataPet.get(i).getId();
                        lat = dataPet.get(i).getLat();
                        lon = dataPet.get(i).getLon();
                        nickname = dataPet.get(i).getNickname();
                        pet_name = dataPet.get(i).getPet_name();
                        pet_age = dataPet.get(i).getPet_age();
                        pet_tag = dataPet.get(i).getPet_tag();
                        userlogin = dataPet.get(i).getUserlogin();
                        userpicture = dataPet.get(i).getUserpicture();
                        double dlon = Double.parseDouble(lon);
                        double dlat = Double.parseDouble(lat);
                        //首页展示宠物
                        Marker marker = aMap.addMarker(new MarkerOptions()
                                .position(new LatLng(dlat, dlon))
                                .anchor(0.5f,0.5f)
                                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                        .decodeResource(getResources(), R.mipmap.image_myclosest)))
                                .title("附近宠物")
                                .draggable(true));
                        aMap.setOnMarkerClickListener(this);
                        marker.setObject(i);
                    }
                    //宠物医院
                    for (int i = 0; i < dataHospatil.size(); i++) {
                        h_img = (String) dataHospatil.get(i).getH_img();
                        lat_hospital = dataHospatil.get(i).getLatitude();
                        lon_hospital = dataHospatil.get(i).getLongitude();
                        double dlon = Double.parseDouble(lat_hospital);
                        double dlat = Double.parseDouble(lon_hospital);
                        //首页展示宠物医院
                        Marker marker = aMap.addMarker(new MarkerOptions()
                                .position(new LatLng(dlon, dlat))
                                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                                        .decodeResource(getResources(), R.mipmap.image_pet_hospital)))
                                .title("服务医院")
                                .draggable(true));
                        marker.setObject(i);
                    }
                } else {
                    ToastUtil.showToast(returnMsg);
                }
                break;
            case 200://地图定位
                if (1 == returnCode) {
                    MapLocation mapLocation = JSON.parseObject(returnData, MapLocation.class);
                } else {
                    ToastUtil.showToast(returnMsg);
                }
                break;
            case 300://宠物分类
                if (1 == returnCode) {
                    PetClassification petClassification = JSON.parseObject(returnData, PetClassification.class);
                    data = petClassification.getData();
                    groupAdapter = new GroupAdapter(getActivity(), data);
                    groupListView.setAdapter(groupAdapter);
                    //一级列表
                    groupListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            petone = data.get(position).getPetone();
                            groupAdapter.setSelectedPosition(position);
                            if (childAdapter == null) {
                                childAdapter = new ChildAdapter(getActivity(), petone);
                                childListView.setAdapter(childAdapter);
                            }
                            childAdapter.notifyDataSetChanged();
                            groupAdapter.notifyDataSetChanged();
                        }
                    });
                    //二级列表
                    childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view,
                                                final int position, long id) {
                            pettwo = petone.get(position).getPettwo();
                            if (sunadapter == null) {
                                sunadapter = new SunAdapter(getActivity(), pettwo);
                                listView_three.setAdapter(sunadapter);
                            }
                            childAdapter.notifyDataSetChanged();
                        }
                    });
                    //三级列表
                    listView_three.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String id_pet = pettwo.get(position).getId();
                            String type_name = pettwo.get(position).getType_name();
                            Intent intent = new Intent(getActivity(), FuJin_pet_Activity.class);
                            intent.putExtra("type_name", type_name);
                            intent.putExtra("id_pet", id_pet);
                            startActivity(intent);
                        }
                    });
                } else {
                    ToastUtil.showToast(returnMsg);

                }
                break;
            case 400:
                if (1 == returnCode) {
                    PetSeek petSeek = JSON.parseObject(returnData, PetSeek.class);

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

    /**
     * 设置tab的状态
     *
     * @param img      // ImageView对象
     * @param textView // TextView对象
     * @param state    // 状态
     */
    private void setTabState(ImageView img, TextView textView, boolean state) {
        if (state) {// 选中状态
            img.setBackgroundResource(R.mipmap.up);
            textView.setTextColor(getResources().getColor(
                    R.color.tab_text_pressed_color));
        } else {
            img.setBackgroundResource(R.mipmap.down);
            textView.setTextColor(getResources().getColor(
                    R.color.tab_text_color));
        }
    }

    //展示区域选择的对话框
    private void showPupupWindow() {
        getPetClassIfication();
        if (mPopupWindow == null) {
            inflate_pop = getActivity().getLayoutInflater().inflate(R.layout.bottom_layout, null);
            groupListView = (ListView) inflate_pop.findViewById(R.id.listView_one);
            childListView = (ListView) inflate_pop.findViewById(R.id.listView_two);
            listView_three = (ListView) inflate_pop.findViewById(R.id.listView_thre);
            // TODO: 2016/5/17 创建PopupWindow对象，指定宽度和高度
            PopupWindow window = new PopupWindow(inflate_pop, 1000, 1000);
            // TODO: 2016/5/17 设置背景颜色
            window.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#F8F8F8")));
            inflate_pop.setAnimation(animation);
            inflate_pop.startAnimation(animation);
            // TODO: 2016/5/17 设置可以获取焦点
            window.setFocusable(true);
            // TODO: 2016/5/17 设置可以触摸弹出框以外的区域
            window.setOutsideTouchable(true);
            // TODO：更新popupwindow的状态
            window.update();
            /**
             * 1.解决再次点击MENU键无反应问题 2.sub_view是PopupWindow的子View
             */
            view.setFocusableInTouchMode(true);
            // TODO: 2016/5/17 以下拉的方式显示，并且可以设置显示的位置
            window.showAsDropDown(area_layout, -5, 10);
        }
    }
    private String bubble_id;
    private String bubble_nuckname;
    //自定义infowinfow窗口
    public View render( String title,int position) {
        Log.e("TAG_","title="+title+";position="+position);
        View infoWindow = null;
        if ("附近宠物".equals(title)){
            infoWindow = LayoutInflater.from(getActivity()).inflate(R.layout.custom_info_window, null);
            linear_per = (LinearLayout) infoWindow.findViewById(R.id.linear_pet);
            text_pet_tag = (TextView) infoWindow.findViewById(R.id.text_pet_tag);
            text_pet_name_info = (TextView) infoWindow.findViewById(R.id.text_pet_name_info);
            text_pet_age = (TextView) infoWindow.findViewById(R.id.text_pet_age);
            text_communication = (TextView) infoWindow.findViewById(R.id.text_communication);
            PetHospital.DataBeanXX.PetBean.DataBean dataBean = dataPet.get(position);
            text_pet_name_info.setText(dataBean.getPet_age());//宠物姓名
            text_pet_age.setText(dataBean.getPet_age());//宠物年龄
            text_pet_tag.setText(dataBean.getPet_tag());//宠物标签
            bubble_nuckname = dataBean.getNickname();
            bubble_id = dataBean.getId();
            //沟通请求
            text_communication.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUtil.showToast("这个可以点击");
                    if (RongIM.getInstance() != null) {

                        RongIM.getInstance().startPrivateChat(getActivity(), bubble_id,bubble_nuckname);

                    }
                }
            });
        }else if ("服务医院".equals(title)){
            infoWindow = LayoutInflater.from(getActivity()).inflate(R.layout.custom_info_window_hospiatl, null);
            text_pet_hospiatl_name = (TextView) infoWindow.findViewById(R.id.text_pet_hospiatl_name);
            img_pet_hospiat = (ImageView) infoWindow.findViewById(R.id.img_pet_hospiatl);
            PetHospital.DataBeanXX.HospitalBean.DataBeanX dataBeanX = dataHospatil.get(position);
            text_pet_hospiatl_name.setText(dataBeanX.getH_name());//医院名称
            tel =dataBeanX.getTel();//医院电话
            //服务医院跳转电话
            img_pet_hospiat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showNormalDialog();
                    tv_content.setText("呼叫:" + tel);
                }
            });
        }

        return infoWindow;
    }

    //获取展示首页信息和定位信息
    private void getHomePager() {
        mylng = String.valueOf(longitude);
        mylat = String.valueOf(latitude);
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("mylng", mylng);
        params.put("mylat", mylat);
        okHttpPost(100, GlobalParam.PETHOSPITALSHOW, params);
        okHttpPost(200, GlobalParam.MAPLOCATION, params);
    }

    //宠物分类
    private void getPetClassIfication() {
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        okHttpPost(300, GlobalParam.PETCLASSIFICATION, params);
    }

    //搜索框
    private void getSeekPet() {
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("mylat", mylat);
        params.put("mylng", mylng);
        params.put("text", edit_location.getText().toString());
        okHttpPost(400, GlobalParam.SEEKPET, params);
    }

    private void showNormalDialog() {
        final AlertDialog.Builder showDialog = new AlertDialog.Builder(getActivity());
        View inflate = View.inflate(getActivity(), R.layout.dialog_item, null);
        showDialog.setView(inflate);
        TextView tv_sure = (TextView) inflate.findViewById(R.id.tv_sure);
        tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
        tv_content = (TextView) inflate.findViewById(R.id.tv_content);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tn = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + tel));
                getActivity().startActivity(tn);
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        showDialog.create().show();
    }

    /**
     * marker点击时跳动一下
     */
    public void jumpPoint(final Marker marker) {
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = aMap.getProjection();
        final LatLng markerLatlng = marker.getPosition();
        Point markerPoint = proj.toScreenLocation(markerLatlng);
        markerPoint.offset(0, -100);
        final LatLng startLatLng = proj.fromScreenLocation(markerPoint);
        final long duration = 1500;

        final Interpolator interpolator = new BounceInterpolator();
        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed
                        / duration);
                double lng = t * markerLatlng.longitude + (1 - t)
                        * startLatLng.longitude;
                double lat = t * markerLatlng.latitude + (1 - t)
                        * startLatLng.latitude;
                marker.setPosition(new LatLng(lat, lng));
                if (t < 1.0) {
                    handler.postDelayed(this, 16);
                }
            }
        });
    }

    @Override//marker点击事件
    public boolean onMarkerClick(final Marker marker) {
        if (aMap != null) {
            jumpPoint(marker);
        }
        marker.showInfoWindow();
        if (marker.getTitle().equals("服务医院")) {
            ToastUtil.showToast("这是" + marker.getTitle());
        } else if (marker.getTitle().equals("附近宠物")) {
            ToastUtil.showToast("这是" +marker.getTitle());
        }
        return true;
    }
}
