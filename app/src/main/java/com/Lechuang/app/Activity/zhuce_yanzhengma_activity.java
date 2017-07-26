package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Lechuang.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class zhuce_yanzhengma_activity extends AppCompatActivity {

    @Bind(R.id.button_next_tijiao)
    TextView button_next_tijiao;
    @Bind(R.id.edit_callphone_note)
    EditText editCallphoneNote;
    @Bind(R.id.edit_password_note)
    EditText editPasswordNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce_yanzhengma_activity);
        ButterKnife.bind(this);
        getInitData();//初始化数据
    }

    //初始化数据
    private void getInitData() {
        Intent intent = getIntent();
        String callphone = intent.getStringExtra("callphone");
        editCallphoneNote.setText(callphone);
    }

    @OnClick(R.id.button_next_tijiao)
    public void onViewClicked() {
        //验证码不能为空
        if(!TextUtils.isEmpty(editPasswordNote.getText().toString())){
            Intent intent = new Intent(zhuce_yanzhengma_activity.this, wu_add_information.class);
            startActivity(intent);
        }else{
            Toast.makeText(zhuce_yanzhengma_activity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

    }
}
