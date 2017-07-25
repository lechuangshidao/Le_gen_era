package com.Lechuang.app.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.Lechuang.app.Fragment.Fragment_tab;
import com.Lechuang.app.R;

import java.util.ArrayList;
import java.util.List;

public class Recommend_Activity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mviewPager;
    private String[]title=new String[]{"全部","小型动物","两栖动物","猫狗","鸟"};
    private List<Fragment> list_f=new ArrayList<>();
    private FragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_activity);
        getInitView();
        getInitData();
    }
    //初始化控件
    private void getInitView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mviewPager = (ViewPager) findViewById(R.id.mViewPager);
    }
    //数据展示
    private void getInitData() {
        initData();
        //将fragment添加给viewpager
        adapter = new FragmentAdapter(getSupportFragmentManager());
        //设置适配器
        mviewPager.setAdapter(adapter);
        //将viewpager设置给tablayout
        mTabLayout.setupWithViewPager(mviewPager);
        //viewpager设置加载后三页
        mviewPager.setOffscreenPageLimit(3);
        //设置标题显示模式
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);

    }
    private void initData() {
        for (int i = 0; i <title.length ; i++) {
            Fragment_tab fragment_tab=new Fragment_tab();
            list_f.add(fragment_tab);
        }
    }
    class FragmentAdapter extends FragmentPagerAdapter {


        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list_f.get(position);
        }

        @Override
        public int getCount() {
            return list_f.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
