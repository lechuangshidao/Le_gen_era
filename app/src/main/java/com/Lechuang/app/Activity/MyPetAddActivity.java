package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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
import com.Lechuang.app.base.BaseDataActivity;
import com.yonyou.sns.im.entity.album.YYPhotoItem;
import com.yonyou.sns.im.util.common.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import www.xcd.com.mylibrary.activity.AlbumPhotoActivity;
import www.xcd.com.mylibrary.utils.YYStorageUtil;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.util.TimeUtil;

import static www.xcd.com.mylibrary.activity.AlbumPhotoActivity.IS_ORIGANL;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_ALBUM;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CAMERA;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CROP;

public class MyPetAddActivity extends BaseDataActivity {

    private static final int REQUEST_TO_DATE_PICKER = 35;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private TextView age;
    private EditText name, type;
    private LinearLayout petadd_include;
    private ImageView mypetadd_head;
    private Thread thread;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetadd);
        context = MyPetAddActivity.this;
    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        initView();
    }

    private String[][] textinclude = {{"公", "母"}, {"发情期", "未成年", "绝育"}, {"黑色", "白色", "花色"}};

    public void initView() {
        mypetadd_head = (ImageView) findViewById(R.id.mypetadd_head);
        mypetadd_head.setOnClickListener(this);
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
            case R.id.mypetadd_head:
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CODE_HEAD_ALBUM:
                    boolean is_origanl = data.getBooleanExtra(IS_ORIGANL,true);
                    YYPhotoItem photoItem = null;
                    if (is_origanl){
                        photoItem = (YYPhotoItem) data.getSerializableExtra(AlbumPhotoActivity.BUNDLE_RETURN_PHOTO);
                        if (photoItem !=null){
                            startCrop(photoItem.getPhotoPath());
                        }
                    }else {
                        final List<File> list = new ArrayList<>();
                        List<YYPhotoItem> photoList = (List<YYPhotoItem>) data.getSerializableExtra(AlbumPhotoActivity.BUNDLE_RETURN_PHOTOS);
                        for (YYPhotoItem photo : photoList) {
                            // 存储图片到图片目录
                            list.add(new File(photo.getPhotoPath()));
                        }
                        uploadImage(list);
                    }

                    break;
                case REQUEST_CODE_HEAD_CAMERA:
                    startCrop(photoPath);
                    break;
                case REQUEST_CODE_HEAD_CROP:
                    try {
                        Bundle extras = data.getExtras();
                        if (extras != null) {
                            Bitmap cropPhoto = extras.getParcelable("data");
                            ByteArrayOutputStream stream = new ByteArrayOutputStream();
                            // (0 - 100)压缩文件
                            cropPhoto.compress(Bitmap.CompressFormat.JPEG, 75, stream);

                            File cropFile = new File(YYStorageUtil.getImagePath(context), UUID.randomUUID().toString() + ".jpg");
                            final List<File> list = new ArrayList<>();
                            list.add(cropFile);
                            FileUtils.compressBmpToFile(cropPhoto, cropFile);
                            uploadImage(list);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
        }
    }
    private void uploadImage(final List<File> list) {
        // 调用上传
        thread = new Thread(){
            @Override
            public void run() {
                super.run();
//                String iamgeresult = HelpUtils.uploadImg(qk_id, list,"head.png");
//                Message message = handler.obtainMessage();
//                message.what = 1;
//                message.obj = iamgeresult;
//                handler.sendMessage(message);
            }
        };
        thread.start();
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
    public Activity getActivity() {
        return this;
    }
}
