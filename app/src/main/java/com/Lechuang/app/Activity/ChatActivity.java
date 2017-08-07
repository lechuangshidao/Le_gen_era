package com.Lechuang.app.Activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.Lechuang.app.base.BaseDataActivity;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import www.xcd.com.mylibrary.R;
import www.xcd.com.mylibrary.activity.AlbumPhotoActivity;
import www.xcd.com.mylibrary.utils.YYStorageUtil;

import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_ALBUM;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CAMERA;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CROP;

/**
 * Created by Android on 2017/6/26.
 */

public class ChatActivity extends BaseDataActivity{

    public static final String IMAGE_UNSPECIFIED = "image/*";
    /** 头像Image */
    public ImageView imageHead;
    /** 头像修改菜单 */
    public View viewChoice;
    /** 头像修改dialog */
    public Dialog dlgChoice;

    /** 照片地址 */
    public String photoPath;
    public File photoFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == R.id.account_head_choice_cancel) {// 关闭对话框
            closeChoiceDialog();

        } else if (i == R.id.account_head_choice_album) {// 关闭对话框
            closeChoiceDialog();
            // album
            Intent albumIntent = new Intent(ChatActivity.this, AlbumPhotoActivity.class);
            if (getTpye().equals(AlbumPhotoActivity.TYPE_SINGLE)){
                albumIntent.putExtra(AlbumPhotoActivity.EXTRA_TYPE, AlbumPhotoActivity.TYPE_SINGLE);
            }else {
                albumIntent.putExtra(AlbumPhotoActivity.EXTRA_TYPE, "");
            }
            // start
            ChatActivity.this.startActivityForResult(albumIntent, REQUEST_CODE_HEAD_ALBUM);

        } else if (i == R.id.account_head_choice_camera) {
            try {
                // 关闭对话框
                closeChoiceDialog();
                // 生成photoPath
               photoFile = new File(YYStorageUtil.getImagePath(ChatActivity.this), UUID.randomUUID().toString() + ".jpg");
                photoPath = photoFile.getPath();

                //判断是否是AndroidN以及更高的版本
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {//判断是否有相机应用
                        Log.e("TAG_","照片photoFile="+photoFile+"");
                        if (photoFile != null) {
                            //FileProvider 是一个特殊的 ContentProvider 的子类，
                            //它使用 content:// Uri 代替了 file:/// Uri. ，更便利而且安全的为另一个app分享文件
                            Uri photoURI = FileProvider.getUriForFile(this,
                                    "com.Lechuang.app",
                                    photoFile);
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                            startActivityForResult(takePictureIntent, REQUEST_CODE_HEAD_CAMERA);
                        }
                    }
                } else {
                    // uri
                    Uri photoUri = Uri.fromFile(new File(photoPath));
                    // 调用系统相机
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                    cameraIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                    // start
                    ChatActivity.this.startActivityForResult(cameraIntent, REQUEST_CODE_HEAD_CAMERA);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    /**
     * 菜单View
     *
     * @param
     * @return
     */
    public View getChoiceView() {
        if (viewChoice == null) {
            // 初始化选择菜单
            viewChoice = LayoutInflater.from(ChatActivity.this).inflate(R.layout.view_head_choice, null);
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
    public Dialog getChoiceDialog() {
        if (dlgChoice == null) {
            dlgChoice = new Dialog(ChatActivity.this, R.style.DialogStyle);
            dlgChoice.setContentView(getChoiceView());
            return dlgChoice;
        }
        return dlgChoice;
    }
    /**
     * 关闭对话框
     */
    public void closeChoiceDialog() {
        if (dlgChoice != null && dlgChoice.isShowing()) {
            dlgChoice.cancel();
        }
    }

    public void uploadImage(final List<File> list) {
        // 调用上传

    }
    /**
     * AlbumPhotoActivity.TYPE_SINGLE为单选
     * ""多选
     */

    public void startCrop(String imagePath) {
        try {
            Uri uri = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                uri = FileProvider.getUriForFile(this,
                        "com.Lechuang.app",
                        new File(imagePath));
            } else {
                uri = Uri.fromFile(new File(imagePath));
            }
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
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            ChatActivity.this.startActivityForResult(intent, REQUEST_CODE_HEAD_CROP);
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

    public String getTpye() {
        return AlbumPhotoActivity.TYPE_SINGLE;
    }
}
