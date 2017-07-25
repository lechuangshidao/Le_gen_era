package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.Lechuang.app.R;

public class Login_Pet extends AppCompatActivity implements View.OnClickListener{

    private ImageView image_you;
    private ImageView image_wu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pet);
        getInitView();
    }
    //初始化控件
    private void getInitView() {
        image_you = (ImageView) findViewById(R.id.image_you);
        image_wu = (ImageView) findViewById(R.id.image_wu);
        image_wu.setOnClickListener(this);
        image_you.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.image_you:
                Intent intent_you=new Intent(Login_Pet.this,Add_Information.class);
                startActivity(intent_you);
                break;
            case R.id.image_wu:
                Intent intent_wu=new Intent(Login_Pet.this,Add_Information.class);
                startActivity(intent_wu);
                break;
        }
    }
}
