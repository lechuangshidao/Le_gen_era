package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.HelpUtils;
import com.Lechuang.app.base.BaseDataActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import www.xcd.com.mylibrary.utils.ToastUtil;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.util.TimeUtil;

public class MyPetAddActivity extends BaseDataActivity {

    private static final int REQUEST_TO_DATE_PICKER = 35;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private TextView age;
    private EditText name, type;
    private LinearLayout petadd_include;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetadd);
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        initView();
    }

    private String[][] textinclude = {{"公", "母"}, {"发情期", "未成年", "绝育"}, {"黑色", "白色", "花色"}};

    public void initView() {
        age = (TextView) findViewById(R.id.mypetadd_age);
        age.setOnClickListener(this);
        name = (EditText) findViewById(R.id.mypetadd_name);
        name.setOnFocusChangeListener(this);
        type = (EditText) findViewById(R.id.mypetadd_type);
        type.setOnFocusChangeListener(this);
        petadd_include = (LinearLayout) findViewById(R.id.petadd_include);
        initGridViewOne();
    }


    private void initGridViewOne() {
        for (int i = 0, length = textinclude.length; i < length; i++) {
            LinearLayout layout2 = new LinearLayout(this);
            layout2.setId((i+1)*10);
            for (int j = 0, arr = textinclude[i].length; j < arr; j++) {
                String string_include = textinclude[i][j];
                final LayoutInflater inflater = LayoutInflater.from(this);
                RelativeLayout  pet_include = (RelativeLayout) inflater.inflate(
                        R.layout.pet_include, null);
                TextView text = (TextView) pet_include.findViewById(R.id.text);
                text.setText(string_include);
                pet_include.setOnClickListener(pChildClick);
                pet_include.setId((i+1)*10+j);
                ImageView image = (ImageView) pet_include.findViewById(R.id.image);
                if (j==0){
                    text.setBackgroundResource(R.drawable.shape_y_solid_orange);
                    image.setVisibility(View.VISIBLE);
                }else {
                    text.setBackgroundResource(R.drawable.shape_y_solid_black);
                    image.setVisibility(View.INVISIBLE);
                }

                ViewGroup parent = (ViewGroup) layout2.getParent();
                if (parent != null) {
                    parent.removeAllViews();
                }
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                lp.rightMargin=30;
//                lp.topMargin=30;
                lp.setMargins(0,20,20,0);
                pet_include.setLayoutParams(lp);
                layout2.setLayoutParams(lp);//设置布局参数
                layout2.addView(pet_include);

            }

            petadd_include.addView(layout2);//全部用父结点的布局参数
        }
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
            case R.id.mypetadd_age:
                toActivity(DatePickerWindow.createIntent(MyPetAddActivity.this, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
                break;
        }
    }

    View.OnClickListener pChildClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            LinearLayout parentView = (LinearLayout) v.getParent();
            for (int i = 0; i < parentView.getChildCount(); i++) {
                Log.e("TAG_",parentView.getId()+""+v.getId());
                RelativeLayout relate = (RelativeLayout) parentView
                        .getChildAt(i);
                if (v.getId()==parentView.getChildAt(i).getId()){
                    TextView viewExchange = (TextView) relate
                            .getChildAt(0);
                    Log.e("TAG_","="+viewExchange.getText());
                    viewExchange.setBackgroundResource(R.drawable.shape_y_solid_orange);
                    ImageView image = (ImageView) relate
                            .getChildAt(1);
                    image.setVisibility(View.VISIBLE);
                }else {
                    TextView viewExchange = (TextView) relate.getChildAt(0);
                    Log.e("TAG_","="+viewExchange.getText());
                    viewExchange.setBackgroundResource(R.drawable.shape_y_solid_black);
                    ImageView image = (ImageView) relate
                            .getChildAt(1);
                    image.setVisibility(View.INVISIBLE);
                }

            }
        }
    };

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
                            age.setText(timeDifference);
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
