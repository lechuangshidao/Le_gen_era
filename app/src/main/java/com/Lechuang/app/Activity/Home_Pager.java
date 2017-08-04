package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.Lechuang.app.Fragment.Fragment_main_gouwuche;
import com.Lechuang.app.Fragment.Fragment_main_pet;
import com.Lechuang.app.Fragment.Fragment_main_shouye;
import com.Lechuang.app.Fragment.Fragment_main_wode;
import com.Lechuang.app.R;
import com.xyz.tabitem.BottmTabItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;

public class Home_Pager extends SimpleTopbarActivity {
    private List<Fragment>list_f=new ArrayList<>();
    private List<BottmTabItem>list_b=new ArrayList<>();
    @Bind(R.id.main_buttom_shouye)
    BottmTabItem mainButtomShouye;
    @Bind(R.id.main_buttom_pet)
    BottmTabItem mainButtomPet;
    @Bind(R.id.main_buttom_gouwuche)
    BottmTabItem mainButtomGouwuche;
    @Bind(R.id.main_buttom_wode)
    BottmTabItem mainButtomWode;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private Fragment_main_shouye fragment_main_shouye;
    private Fragment_main_pet fragment_main_pet;
    private Fragment_main_gouwuche fragment_main_gouwuche;
    private Fragment_main_wode fragment_main_wode;
    private FragmentManager manager;

    @Override
    public boolean isTopbarVisibility() {
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pager);
        ButterKnife.bind(this);
        getInitData();//初始化数据
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
                for (int i = 0; i <list_b.size() ; i++) {
                    if(i==position){
                        list_b.get(i).setSelectState(true);
                    }else{
                        list_b.get(i).setSelectState(false);
                    }
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
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
