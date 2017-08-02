package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Lechuang.app.Bean.HavePetRegister;
import com.Lechuang.app.R;
import com.Lechuang.app.Utils.HelpUtils;
import com.Lechuang.app.entity.GlobalParam;
import com.Lechuang.app.func.CommonBackTopBtnFunc_or;
import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.util.TimeUtil;

public class Have_pet_Activity extends SimpleTopbarActivity {

    @Bind(R.id.mypetadd_head)
    ImageView mypetadd_head;
    @Bind(R.id.mypetadd_name)
    EditText mypetadd_name;
    @Bind(R.id.mypetadd_age)
    TextView mypetadd_age;
    @Bind(R.id.button)
    Button button;
    private LinearLayout petadd_include;
    private String callphone;
    private String password;
    private String nickname;
    private String birthday;
    private String verificationcode;
    private int sex;
    private String type;
    private String user_id;
    private String userarea;
    private EditText mypetadd_type;
    private HavePetRegister havePetRegister;
    private static final int REQUEST_TO_DATE_PICKER = 34;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private String[][] textinclude = {{"公", "母"}, {"发情期", "未成年", "绝育"}, {"黑色", "白色", "花色"}};
    private TextView viewExchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetadd);
        ButterKnife.bind(this);
        mypetadd_type = (EditText) findViewById(R.id.mypetadd_type);
        petadd_include = (LinearLayout) findViewById(R.id.petadd_include);
        initGridViewOne();
    }

    @Override
    protected Class<?> getTopbarLeftFunc() {
        return CommonBackTopBtnFunc_or.class;
    }

    @Override
    protected Object getTopbarTitle() {
        return "宠物信息";
    }

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
    switch (requestCode){
        case 100:
            if(1==returnCode){
                havePetRegister = JSON.parseObject(returnData, HavePetRegister.class);
                Intent intent_wancheng=new Intent(Have_pet_Activity.this,Home_Pager.class);
                startActivity(intent_wancheng);
            }else{
                ToastUtil.showToast(returnMsg);
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

    @OnClick({R.id.mypetadd_head, R.id.mypetadd_name, R.id.mypetadd_age, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.mypetadd_head:
                break;
            case R.id.mypetadd_name:
                break;
            case R.id.mypetadd_age:
                toActivity(DatePickerWindow.createIntent(Have_pet_Activity.this, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
                break;
            case R.id.button:
                getRegister();
                break;
        }
    }
    //注册
    private void getRegister(){
       Intent intent=getIntent();
        nickname = intent.getStringExtra("nickname");
        String birthday = intent.getStringExtra("birthday");
        String token = intent.getStringExtra("token");
        sex = intent.getIntExtra("sex",1);
        user_id = intent.getStringExtra("user_id");
        Map<String,Object>params=new HashMap<String,Object>();
        params.put("nickname",nickname);
        params.put("user_id",user_id);
        params.put("token",token);
        params.put("userbirthday",birthday);
        params.put("userpicture","");
        params.put("sex",sex);
        params.put("pet_name",mypetadd_name.getText().toString());
        params.put("pet_type",mypetadd_type.getText().toString());
        params.put("pet_tag","");
        params.put("pet_age",mypetadd_age.getText().toString());
        params.put("pet_img","");
        okHttpPost(100, GlobalParam.USERREGISTRATION,params);
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
                        String time = selectedDate[0] + "-" + (selectedDate[1]+1) + "-" + selectedDate[2];
                        String timeDifference = HelpUtils.getTimeDifference(time);
                        if (timeDifference.indexOf("-1") != -1) {
                            ToastUtil.showToast("请选择正确出生日期");
                        } else {
                            mypetadd_age.setText(timeDifference);
                        }

                    }
                }
                break;
        }

    }
    private void initGridViewOne() {
        for (int i = 0, length = textinclude.length; i < length; i++) {
            LinearLayout layout2 = new LinearLayout(this);
            layout2.setId((i+1)*10);
            for (int j = 0, arr = textinclude[i].length; j < arr; j++) {
                String string_include = textinclude[i][j];
                final LayoutInflater inflater = LayoutInflater.from(this);
                RelativeLayout pet_include = (RelativeLayout) inflater.inflate(
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
                    viewExchange = (TextView) relate.getChildAt(0);
                    Log.e("TAG_1","="+ viewExchange.getText());
                    viewExchange.setBackgroundResource(R.drawable.shape_y_solid_black);
                    ImageView image = (ImageView) relate
                            .getChildAt(1);
                    image.setVisibility(View.INVISIBLE);
                }

            }
        }
    };
}
