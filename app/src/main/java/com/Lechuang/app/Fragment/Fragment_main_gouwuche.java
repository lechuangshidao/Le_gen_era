package com.Lechuang.app.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Lechuang.app.R;

import java.io.IOException;
import java.util.Map;

import www.xcd.com.mylibrary.base.fragment.BaseFragment;

public class Fragment_main_gouwuche extends BaseFragment {

    private View view;

    @Override
    protected int getLayoutId() {
        return 0;
    }
    @Override
    protected void initView(LayoutInflater inflater, View view) {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_main_gouwuche, null);
        initView(inflater,view);
        return view;
    }

    @Override
    protected void onDestroyThread() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
