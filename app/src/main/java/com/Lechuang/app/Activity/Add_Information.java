package com.Lechuang.app.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;
import com.Lechuang.app.base.BaseDataActivity;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;
import com.Lechuang.app.func.CommonBackTopBtnFunc_right;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.utils.ToastUtil;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.util.TimeUtil;

import static com.Lechuang.app.R.id.edit_add_name;
import static com.Lechuang.app.R.id.image_womanman;

public class Add_Information extends BaseDataActivity {

    private static Class<?> rightFuncArray1[] = {CommonBackTopBtnFunc_right.class};
    @Bind(edit_add_name)
    EditText editAddName;
    @Bind(R.id.edit_age_date)
    TextView editAgeDate;
    @Bind(R.id.Text_next_add)
    TextView TextNextAdd;
    private String callphone;
    private String password;
    private static final int REQUEST_TO_DATE_PICKER = 33;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private String code;
    @Bind(R.id.image_man)
     ImageView imageman;
    @Bind(image_womanman)
    ImageView imagewomanman;
    private int sex = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wu_add_information);
        ButterKnife.bind(this);
        getInitData();//初始化数据
    }

    //初始化数据
    private void getInitData() {
        Call_Phone_Utils.setEditTextInhibitInputSpeChat(editAddName);//宠物昵称禁止为特殊字符
        Intent intent = getIntent();
        callphone = intent.getStringExtra("callphone");
        password = intent.getStringExtra("password");
        code = intent.getStringExtra("VERIFICATIONCODE");
    }

    @OnClick({R.id.Text_next_add, R.id.image_wu_gerentouxiang,R.id.edit_age_date,R.id.image_man, image_womanman})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_man:
                imageman.setImageResource(R.mipmap.image_man);
                imagewomanman.setImageResource(R.mipmap.image_woman);
                sex = 1;
                break;
            case image_womanman:
                imageman.setImageResource(R.mipmap.image_woman);
                imagewomanman.setImageResource(R.mipmap.image_man);
                sex = 0;
                break;
            //下一步
            case R.id.Text_next_add:
                String nickname = editAddName.getText().toString().trim();
                if (TextUtils.isEmpty(nickname)){
                    ToastUtil.showToast("昵称不能为空");
                    return;
                }
                String birthday = editAgeDate.getText().toString().trim();
                if (TextUtils.isEmpty(birthday)){
                    ToastUtil.showToast("请选择出生日期");
                    return;
                }
                Intent intent = new Intent(Add_Information.this, Have_Or_Not_Pet.class);
                intent.putExtra("callphone",callphone);
                intent.putExtra("password",password);
                intent.putExtra("VERIFICATIONCODE",code);
                intent.putExtra("nickname",nickname);
                intent.putExtra("birthday",birthday);
                intent.putExtra("sex",sex);
                startActivity(intent);
                break;
            //个人头像
            case R.id.image_wu_gerentouxiang:
                break;
            case R.id.edit_age_date://出生日期
                toActivity(DatePickerWindow.createIntent(Add_Information.this, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode){
            case REQUEST_TO_DATE_PICKER:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                    if (list != null && list.size() >= 3) {

                        selectedDate = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            selectedDate[i] = list.get(i);
                        }
                        editAgeDate.setText(selectedDate[0]+"-"+(selectedDate[1]+1)+"-"+selectedDate[2]);
                    }
                }
                break;
            default:
                break;
        }
    }
    @Override
    protected Class<?> getTopbarLeftFunc() {
        return CommonBackTopBtnFunc_or.class;
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
    public Activity getActivity() {
        return this;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initEvent() {

    }
}

