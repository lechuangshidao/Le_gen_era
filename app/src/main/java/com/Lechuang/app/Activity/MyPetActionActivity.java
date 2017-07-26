package com.Lechuang.app.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.Lechuang.app.R;
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
import www.xcd.com.mylibrary.base.activity.SimpleTopbarActivity;
import www.xcd.com.mylibrary.utils.YYStorageUtil;

import static www.xcd.com.mylibrary.activity.AlbumPhotoActivity.IS_ORIGANL;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_ALBUM;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CAMERA;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CROP;

/**
 * Created by Android on 2017/7/24.
 */

public class MyPetActionActivity extends SimpleTopbarActivity implements TextWatcher{

    private EditText action_title,action_context;
    private ImageView action_image;
    private Button action_ok;
    private TextView action_titletext,action_contexttext;
    public static final String IMAGE_UNSPECIFIED = "image/*";
    /** 头像Image */
    private ImageView imageHead;
    /** 头像修改菜单 */
    private View viewChoice;
    /** 头像修改dialog */
    private Dialog dlgChoice;

    /** 照片地址 */
    private String photoPath;
    private Thread thread;
    @Override
    protected Object getTopbarTitle() {
        return R.string.startaction;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypetaction);

    }

    @Override
    protected void afterSetContentView() {
        super.afterSetContentView();
        initView();
    }

    private void initView() {
        action_title = (EditText) findViewById(R.id.action_title);
        action_title.setOnFocusChangeListener(this);
        action_title.addTextChangedListener(this);
        action_context = (EditText) findViewById(R.id.action_context);
        action_context.setOnFocusChangeListener(this);
        action_context.addTextChangedListener(this);
        action_image = (ImageView) findViewById(R.id.action_image);
        action_image.setOnClickListener(this);
        action_ok = (Button) findViewById(R.id.action_ok);
        action_ok.setOnClickListener(this);
        action_titletext = (TextView) findViewById(R.id.action_titletext);
        action_contexttext = (TextView) findViewById(R.id.action_contexttext);

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.action_image:
                getChoiceDialog().show();
                break;
            case R.id.action_ok:
                break;
            case R.id.account_head_choice_cancel:
                // 关闭对话框
                closeChoiceDialog();
                break;
            case R.id.account_head_choice_album:
                // 关闭对话框
                closeChoiceDialog();
                // album
                Intent albumIntent = new Intent(MyPetActionActivity.this, AlbumPhotoActivity.class);
//                albumIntent.putExtra(AlbumPhotoActivity.EXTRA_TYPE, AlbumPhotoActivity.TYPE_SINGLE);
                albumIntent.putExtra(AlbumPhotoActivity.EXTRA_TYPE, "");
                // start
                MyPetActionActivity.this.startActivityForResult(albumIntent, REQUEST_CODE_HEAD_ALBUM);
                break;
            case R.id.account_head_choice_camera://相机
                try {
                    // 关闭对话框
                    closeChoiceDialog();
                    // 调用系统相机
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    // 生成photoPath
                    File photoFile = new File(YYStorageUtil.getImagePath(MyPetActionActivity.this), UUID.randomUUID().toString() + ".jpg");
                    photoPath = photoFile.getPath();
                    // uri
                    Uri photoUri = Uri.fromFile(new File(photoPath));
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    cameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                    // start
                    MyPetActionActivity.this.startActivityForResult(cameraIntent, REQUEST_CODE_HEAD_CAMERA);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

        }
    }
    /**
     * 菜单View
     *
     * @param
     * @return
     */
    private View getChoiceView() {
        if (viewChoice == null) {
            // 初始化选择菜单
            viewChoice = LayoutInflater.from(MyPetActionActivity.this).inflate(R.layout.view_head_choice, null);
            viewChoice.findViewById(R.id.account_head_choice_album).setOnClickListener(this);
            viewChoice.findViewById(R.id.account_head_choice_camera).setOnClickListener(this);
            viewChoice.findViewById(R.id.account_head_choice_cancel).setOnClickListener(this);
        }
        return viewChoice;
    }
    /**
     * 修改头像对话框
     *
     * @return
     */
    private Dialog getChoiceDialog() {
        if (dlgChoice == null) {
            dlgChoice = new Dialog(MyPetActionActivity.this, R.style.DialogStyle);
            dlgChoice.setContentView(getChoiceView());
            return dlgChoice;
        }
        return dlgChoice;
    }
    /**
     * 关闭对话框
     */
    private void closeChoiceDialog() {
        if (dlgChoice != null && dlgChoice.isShowing()) {
            dlgChoice.cancel();
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

                            File cropFile = new File(YYStorageUtil.getImagePath(MyPetActionActivity.this), UUID.randomUUID().toString() + ".jpg");
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (thread!=null){
            thread.interrupt();
        }
    }

    private void startCrop(String imagePath) {
        try {
            Uri uri = Uri.fromFile(new File(imagePath));
            Intent intent = new Intent("com.android.camera.action.CROP");
            intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
            intent.putExtra("crop", "true");
            // aspectX aspectY 是宽高的比例
            intent.putExtra("aspectX", 1);
            intent.putExtra("aspectY", 1);
            // outputX outputY 是裁剪图片宽高
            intent.putExtra("outputX", 160);
            intent.putExtra("outputY", 160);
            intent.putExtra("return-data", true);
            MyPetActionActivity.this.startActivityForResult(intent, REQUEST_CODE_HEAD_CROP);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
//                    String result = (String) msg.obj;
//                    Log.e("TAG_image","image="+result);
//                    try {
//                        JSONObject obj = new JSONObject(result);
//                        String status = obj.optString("status");
//                        if ("10001".equals(status)){
//                            String imgurl = obj.getJSONObject("result").optString("imgurl");
//                            initHead(imgurl);
//                        }else {
//                            ToastUtil.showToast(obj.optString("error"));
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                    break;
            }
        }
    };



    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        action_titletext.setText("(还可以输入"+(6-action_title.length())+"个字)");
        action_contexttext.setText("(还可以输入"+(30-action_context.length())+"个字)");
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
}
