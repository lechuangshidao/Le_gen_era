package com.Lechuang.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.Lechuang.app.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Have_Or_Not_Pet extends AppCompatActivity {

    @Bind(R.id.image_you)
    ImageView imageYou;
    @Bind(R.id.image_wu)
    ImageView imageWu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_pet);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image_you, R.id.image_wu,R.id.image_callback})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_you:
                Intent intent_you=new Intent(Have_Or_Not_Pet.this,Have_pet_Activity.class);
                startActivity(intent_you);
                break;
            case R.id.image_wu:
                Intent intent_wu=new Intent(Have_Or_Not_Pet.this,Home_Pager.class);
                startActivity(intent_wu);
                break;
            case R.id.image_callback:
                finish();
                break;
        }
    }
}
