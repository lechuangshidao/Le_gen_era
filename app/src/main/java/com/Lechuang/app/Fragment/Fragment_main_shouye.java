package com.Lechuang.app.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.Lechuang.app.Activity.FuWuActivity;
import com.Lechuang.app.Activity.Fujin_Activity;
import com.Lechuang.app.Activity.Gift_Activity;
import com.Lechuang.app.Bean.HomePager;
import com.Lechuang.app.Bean.MapLocation;
import com.Lechuang.app.Bean.PetClassification;
import com.Lechuang.app.R;
import com.Lechuang.app.RongCloud.Rong_news;
import com.Lechuang.app.adapter.ChildAdapter;
import com.Lechuang.app.adapter.GroupAdapter;
import com.Lechuang.app.adapter.SunAdapter;
import com.Lechuang.app.entity.GlobalParam;
import com.alibaba.fastjson.JSON;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;

public class Fragment_main_shouye extends Fragment implements LocationSource, AMapLocationListener {

    @Bind(R.id.image_gift)
    LinearLayout imageGift;
    @Bind(R.id.image_news)
    LinearLayout imageNews;
    @Bind(R.id.image_shouye_fujin)
    ImageView imageShouyeFujin;
    @Bind(R.id.image_shouye_fuwu)
    ImageView imageShouyeFuwu;
    private View view;
    MapView mMapView = null;
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;
    View showPupWindow = null;
    //初始化地图控制器对象
    AMap aMap;
    boolean isFirstLoc = true;
    OnLocationChangedListener mListener;
    public static StringBuffer buffer;
    private double latitude;
    private double longitude;
    private Marker marker;
    private String token;
    private ImageView image_shouye_seek;
    TranslateAnimation animation;// 出现的动画效果
    // 屏幕的宽高
    public static int screen_width = 0;
    public static int screen_height = 0;
    PopupWindow mPopupWindow = null;
    private boolean[] tabStateArr = new boolean[2];// 标记tab的选中状态，方便设置
    private ListView listView_three;
    private SunAdapter sunadapter;
    private List<PetClassification.DataBean> data;
    private List<PetClassification.DataBean.PetoneBean> petone;
    private TextView areaText;
    private ImageView areaImg;
    private TextView wage_textView;
    private ImageView wage_img;
    private LinearLayout area_layout;
    private LinearLayout wage_layout;
    private Dialog dialog;
    private View inflate;
    private ListView groupListView;
    private ListView childListView;
    private GroupAdapter groupAdapter;
    private ChildAdapter childAdapter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initView(LayoutInflater inflater, View view) {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_fragment_main_shouye, null);
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent != null) {
            parent.removeView(view);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取地图控件引用
        mMapView = (MapView) view.findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);
        if (mMapView == null) {
            aMap = mMapView.getMap();
        }
        //定位
        InitPositioning();
        sHA1(getActivity());
        token = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("token");
        LatLng lng1 = new LatLng(40.103085, 116.294034);
        //绘制marker
        marker = aMap.addMarker(new MarkerOptions()
                .position(lng1)
                .title("服务医院")
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),R.drawable.image_pet_fujin)))
                .draggable(true));
        aMap.addMarker(new MarkerOptions()
                .position(new LatLng(40.104585, 116.296034))
                .title("服务医院")
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.drawable.image_pet_fujin)))
                .draggable(true)
        );
        aMap.addMarker(new MarkerOptions()
                .title("服务医院")
                .position(new LatLng(40.113085, 116.295034))
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(), R.drawable.image_pet_fujin)))
                .draggable(true));
        AMap.OnMarkerClickListener mark=new AMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                String id = marker.getId();
                String title = marker.getTitle();
                ToastUtil.showToast("这是第"+id+title);
                return true;
            }
        };
        aMap.setOnMarkerClickListener(mark);
       /* // 绘制曲线
        aMap.addPolyline((new PolylineOptions())
                .add(new LatLng(40.104883,116.294924 ),new LatLng(40.104683,116.294624))
                .geodesic(true).color(Color.RED));*/
    }

    private void InitPositioning() {
        if (aMap == null) {
            aMap = mMapView.getMap();
            //设置显示定位按钮 并且可以点击
            UiSettings settings = aMap.getUiSettings();
            aMap.setLocationSource(this);//设置了定位的监听,这里要实现LocationSource接口
            // 是否显示定位按钮
            settings.setMyLocationButtonEnabled(false);
            aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase
            settings.setScrollGesturesEnabled(false);
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
        // 自定义系统定位小蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        if (mMapView != null) {
            mMapView.onSaveInstanceState(outState);
        }
    }

    //查找sHA1
    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
            }
            return hexString.toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        mListener = onLocationChangedListener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

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
                    Log.i("result", "维度" + latitude + "精度" + longitude);
                    Toast.makeText(getActivity(), buffer.toString(), Toast.LENGTH_LONG).show();
                    isFirstLoc = false;
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

    @OnClick({R.id.image_gift, R.id.image_news, R.id.image_shouye_fujin, R.id.image_shouye_fuwu})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_gift://礼物
                Intent intent_gift = new Intent(getActivity(), Gift_Activity.class);
                startActivity(intent_gift);
                break;
            case R.id.image_news://消息
                Intent intent_news = new Intent(getActivity(), Rong_news.class);
                startActivity(intent_news);
                break;
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
        }
    }
}
