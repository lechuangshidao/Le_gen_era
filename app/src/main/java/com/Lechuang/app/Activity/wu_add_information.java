package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class wu_add_information extends AppCompatActivity{

    @Bind(R.id.edit_add_name)
    EditText editAddName;
    @Bind(R.id.edit_age_date)
    EditText editAgeDate;
    @Bind(R.id.Text_next_add)
    TextView Text_next_add;
    private ImageView button_wancheng;

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
    }


    @OnClick(R.id.Text_next_add)
    public void onViewClicked() {
        Intent intent = new Intent(wu_add_information.this, Have_Or_Not_Pet.class);
        startActivity(intent);
    }
}
