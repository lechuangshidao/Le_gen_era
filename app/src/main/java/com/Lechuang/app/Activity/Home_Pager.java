package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.Lechuang.app.Fragment.Fragment_main_gouwuche;
import com.Lechuang.app.Fragment.Fragment_main_pet;
import com.Lechuang.app.Fragment.Fragment_main_shouye;
import com.Lechuang.app.Fragment.Fragment_main_wode;
import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Colors;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Home_Pager extends AppCompatActivity {

    @Bind(R.id.main_buttom_shouye)
    RadioButton mainButtomShouye;
    @Bind(R.id.main_buttom_pet)
    RadioButton mainButtomPet;
    @Bind(R.id.main_buttom_gouwuche)
    RadioButton mainButtomGouwuche;
    @Bind(R.id.main_buttom_wode)
    RadioButton mainButtomWode;
    @Bind(R.id.main_rg)
    RadioGroup mainRg;
    private Fragment_main_shouye fragment_main_shouye;
    private Fragment_main_pet fragment_main_pet;
    private Fragment_main_gouwuche fragment_main_gouwuche;
    private Fragment_main_wode fragment_main_wode;
    private FragmentManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pager);
        ButterKnife.bind(this);
        getInitData();//初始化数据
        //创建事务管理器
        manager = getSupportFragmentManager();
        //开启事务
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_mian_home, fragment_main_shouye);
        transaction.add(R.id.fragment_mian_home, fragment_main_pet);
        transaction.add(R.id.fragment_mian_home, fragment_main_gouwuche);
        transaction.add(R.id.fragment_mian_home, fragment_main_wode);
        transaction.commit();
        transaction.show(fragment_main_shouye);
        transaction.hide(fragment_main_pet);
        transaction.hide(fragment_main_gouwuche);
        transaction.hide(fragment_main_wode);
        mainButtomShouye.setSelected(true);
        mainButtomPet.setSelected(false);
        mainButtomGouwuche.setSelected(false);
        mainButtomWode.setSelected(false);
        mainButtomShouye.setTextColor(Colors.ORANGE);
        mainButtomPet.setTextColor(Colors.GRAY);
        mainButtomGouwuche.setTextColor(Colors.GRAY);
        mainButtomWode.setTextColor(Colors.GRAY);
        mainRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.main_buttom_shouye:
                        //开始事物
                        FragmentTransaction beginTransaction_shouye = manager.beginTransaction();
                        beginTransaction_shouye.show(fragment_main_shouye);
                        beginTransaction_shouye.hide(fragment_main_pet);
                        beginTransaction_shouye.hide(fragment_main_gouwuche);
                        beginTransaction_shouye.hide(fragment_main_wode);
                        beginTransaction_shouye.commit();
                        mainButtomShouye.setSelected(true);
                        mainButtomPet.setSelected(false);
                        mainButtomGouwuche.setSelected(false);
                        mainButtomWode.setSelected(false);
                        mainButtomShouye.setTextColor(Colors.ORANGE);
                        mainButtomPet.setTextColor(Colors.GRAY);
                        mainButtomGouwuche.setTextColor(Colors.GRAY);
                        mainButtomWode.setTextColor(Colors.GRAY);
                        break;
                    case R.id.main_buttom_pet:
                        //开始事物
                        FragmentTransaction beginTransaction_pet = manager.beginTransaction();
                        beginTransaction_pet.show(fragment_main_pet);
                        beginTransaction_pet.hide(fragment_main_shouye);
                        beginTransaction_pet.hide(fragment_main_gouwuche);
                        beginTransaction_pet.hide(fragment_main_wode);
                        beginTransaction_pet.commit();
                        mainButtomShouye.setSelected(false);
                        mainButtomPet.setSelected(true);
                        mainButtomGouwuche.setSelected(false);
                        mainButtomWode.setSelected(false);
                        mainButtomShouye.setTextColor(Colors.GRAY);
                        mainButtomPet.setTextColor(Colors.ORANGE);
                        mainButtomGouwuche.setTextColor(Colors.GRAY);
                        mainButtomWode.setTextColor(Colors.GRAY);
                        break;
                    case R.id.main_buttom_gouwuche:
                        //开始事物
                        FragmentTransaction beginTransaction_gouwuche = manager.beginTransaction();
                        beginTransaction_gouwuche.show(fragment_main_gouwuche);
                        beginTransaction_gouwuche.hide(fragment_main_shouye);
                        beginTransaction_gouwuche.hide(fragment_main_pet);
                        beginTransaction_gouwuche.hide(fragment_main_wode);
                        beginTransaction_gouwuche.commit();
                        mainButtomShouye.setSelected(false);
                        mainButtomPet.setSelected(false);
                        mainButtomGouwuche.setSelected(true);
                        mainButtomWode.setSelected(false);
                        mainButtomShouye.setTextColor(Colors.GRAY);
                        mainButtomPet.setTextColor(Colors.GRAY);
                        mainButtomGouwuche.setTextColor(Colors.ORANGE);
                        mainButtomWode.setTextColor(Colors.GRAY);
                        break;
                    case R.id.main_buttom_wode:
                        //开始事物
                        FragmentTransaction beginTransaction_wode = manager.beginTransaction();
                        beginTransaction_wode.show(fragment_main_wode);
                        beginTransaction_wode.hide(fragment_main_shouye);
                        beginTransaction_wode.hide(fragment_main_gouwuche);
                        beginTransaction_wode.hide(fragment_main_pet);
                        beginTransaction_wode.commit();
                        mainButtomShouye.setSelected(false);
                        mainButtomPet.setSelected(false);
                        mainButtomGouwuche.setSelected(false);
                        mainButtomWode.setSelected(true);
                        mainButtomShouye.setTextColor(Colors.GRAY);
                        mainButtomPet.setTextColor(Colors.GRAY);
                        mainButtomGouwuche.setTextColor(Colors.GRAY);
                        mainButtomWode.setTextColor(Colors.ORANGE);
                        break;
                }
            }
        });
    }

    private void getInitData() {
        fragment_main_shouye = new Fragment_main_shouye();
        fragment_main_pet = new Fragment_main_pet();
        fragment_main_gouwuche = new Fragment_main_gouwuche();
        fragment_main_wode = new Fragment_main_wode();
    }
}
