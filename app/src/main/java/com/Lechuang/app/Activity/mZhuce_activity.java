package com.Lechuang.app.Activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.Call_Phone_Utils;
import com.Lechuang.app.base.LCBaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class mZhuce_activity extends LCBaseActivity {

    @Bind(R.id.button_next_zhuce)
    TextView button_next_zhuce;
    @Bind(R.id.edit_callphone_zhuce)
    EditText editCallphoneZhuce;
    private PopupWindow ppw;
    private View parentView;
    private TextView tv_sure;
    private TextView tv_cancel;
    private TextView text_quxiao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_zhuce_activity);
        ButterKnife.bind(this);
        getInitData();//初始化数据
    }

    //初始化数据
    private void getInitData() {
        String callphone = editCallphoneZhuce.getText().toString();
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_item, null);
        TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
        TextView tv_sure = (TextView) view.findViewById(R.id.tv_sure);
        tv_cancel = (TextView) view.findViewById(R.id.tv_cancel);
        tv_title.setText("确认手机号码");
        TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
        tv_content.setText("我们将发送验证码短信到这个手机号码\n+86" + callphone);
        ppw = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ppw.setFocusable(true);
        ppw.setOutsideTouchable(true);
        ppw.setBackgroundDrawable(new BitmapDrawable());
        parentView = findViewById(android.R.id.content);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new Intent(mZhuce_activity.this, zhuce_yanzhengma_activity.class);
                intent.putExtra("callphone",editCallphoneZhuce.getText().toString());
                startActivity(intent);
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ppw.dismiss();
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
    /*
    * 跳转事件
    * */
    @OnClick(R.id.button_next_zhuce)
    public void onViewClicked() {
        String userphone = editCallphoneZhuce.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(mZhuce_activity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean result = Call_Phone_Utils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(mZhuce_activity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        // 设置背景颜色变暗
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.7f;
        this.getWindow().setAttributes(lp);
        ppw.showAtLocation(parentView, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onClick(View view) {

    }
}

