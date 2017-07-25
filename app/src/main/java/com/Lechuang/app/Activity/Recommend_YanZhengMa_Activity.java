package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.Lechuang.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Recommend_YanZhengMa_Activity extends AppCompatActivity {

    @Bind(R.id.image_tijiao)
    ImageView imageTijiao;
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
    @OnClick({R.id.text_back, R.id.image_tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_back:
                finish();
                break;
            case R.id.image_tijiao:
                //验证码不能为空
                if (!TextUtils.isEmpty(editPasswordNote.getText().toString())) {
                    Intent intent = new Intent(Recommend_YanZhengMa_Activity.this, Add_Information.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Recommend_YanZhengMa_Activity.this, "验证码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                break;
        }
    }
}
