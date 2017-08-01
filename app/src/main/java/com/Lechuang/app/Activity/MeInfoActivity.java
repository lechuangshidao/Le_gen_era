package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.base.BaseDataActivity;
import com.Lechuang.app.entity.GlobalParam;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.yonyou.sns.im.entity.album.YYPhotoItem;
import com.yonyou.sns.im.util.common.FileUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import www.xcd.com.mylibrary.activity.AlbumPhotoActivity;
import www.xcd.com.mylibrary.utils.ToastUtil;
import www.xcd.com.mylibrary.utils.XCDSharePreference;
import www.xcd.com.mylibrary.utils.YYStorageUtil;
import zuo.biao.library.ui.DatePickerWindow;
import zuo.biao.library.util.TimeUtil;

import static www.xcd.com.mylibrary.activity.AlbumPhotoActivity.IS_ORIGANL;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_ALBUM;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CAMERA;
import static www.xcd.com.mylibrary.func.IFuncRequestCode.REQUEST_CODE_HEAD_CROP;

public class MeInfoActivity extends BaseDataActivity {

    private static final int REQUEST_TO_DATE_PICKER = 33;
    private TextView select_birthday,sex_man,sex_woman;
    private ImageView image_man,image_woman,meinfo_head;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private RelativeLayout relat_woman,relat_man;
    private EditText edit_name;
    private Button button;
    private int type = 1;
    private String sex;
    private String image_head;
    private String nickname;
    private String userbirthday;
    @Override
    protected Object getTopbarTitle() {
        return R.string.meinfo;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meinfo);
        initView();
    }

    public void initView() {
        Intent intent = getIntent();
        image_head = intent.getStringExtra("MEINFOHEAD");
        nickname = intent.getStringExtra("NICKNAME");
        sex = intent.getStringExtra("SEX");
        userbirthday = intent.getStringExtra("USERBIRTHDAY");
        //修改生日
        select_birthday = (TextView) findViewById(R.id.select_birthday);
        select_birthday.setHint(userbirthday==null?select_birthday.getHint().toString():userbirthday);
        select_birthday.setOnClickListener(this);
        relat_man = (RelativeLayout) findViewById(R.id.relat_man);
        relat_man.setOnClickListener(this);
        relat_woman = (RelativeLayout) findViewById(R.id.relat_woman);
        relat_woman.setOnClickListener(this);
        sex_man = (TextView) findViewById(R.id.sex_man);
        sex_woman = (TextView) findViewById(R.id.sex_woman);
        image_woman = (ImageView) findViewById(R.id.image_woman);
        image_man = (ImageView) findViewById(R.id.image_man);
        //修改昵称
        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_name.setOnFocusChangeListener(this);
        edit_name.setHint(nickname==null?"未知":nickname);
        //修改头像
        meinfo_head = (ImageView) findViewById(R.id.meinfo_head);
        meinfo_head.setOnClickListener(this);
        Glide.with(getActivity())
                .load(image_head)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.image_wode_geren)
                .error(R.mipmap.image_wode_geren)
                .into(meinfo_head);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
        switchSex(Integer.parseInt(sex));
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
        switch (v.getId()){
            case R.id.select_birthday:
                Log.e("TAG_","DIANJI");
                toActivity(DatePickerWindow.createIntent(MeInfoActivity.this, new int[]{1971, 0, 1}
                        , TimeUtil.getDateDetail(System.currentTimeMillis())), REQUEST_TO_DATE_PICKER, false);
                break;
            case R.id.relat_man:
                type = 1;
                switchSex(type);
                break;
            case R.id.relat_woman:
                type = 0;
                switchSex(type);
                break;
            case R.id.meinfo_head:
                //上传头像
                setTpye(AlbumPhotoActivity.TYPE_SINGLE);
                getChoiceDialog().show();
                break;
            case R.id.button:
                nickname = edit_name.getText().toString().trim();
                if (TextUtils.isEmpty(nickname)){
                    ToastUtil.showToast("昵称不能为空");
                    return;
                }
                userbirthday = select_birthday.getText().toString().trim();
                if (TextUtils.isEmpty(userbirthday)){
                    ToastUtil.showToast("生日不能为空");
                    return;
                }
                String user_id = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("user_id");
                String token = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("token");
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("id", user_id);
                params.put("token", token);
                params.put("nickname", nickname);
                params.put("userbirthday", userbirthday);
                params.put("sex", type);//默认男性
                okHttpPost(100, GlobalParam.MEINFO, params);
                createDialogshow();
                break;
        }
    }
    private void switchSex( int type){
        if (type == 1){
            sex_man.setBackgroundResource(R.drawable.shape_y_solid_orange);
            sex_man.setTextColor(getResources().getColor(R.color.orange));
            image_man.setVisibility(View.VISIBLE);
            sex_woman.setBackgroundResource(R.drawable.shape_y_solid_black);
            sex_woman.setTextColor(getResources().getColor(R.color.black_33));
            image_woman.setVisibility(View.INVISIBLE);
        }else {
            sex_man.setBackgroundResource(R.drawable.shape_y_solid_black);
            sex_man.setTextColor(getResources().getColor(R.color.black_33));
            image_man.setVisibility(View.INVISIBLE);
            sex_woman.setBackgroundResource(R.drawable.shape_y_solid_orange);
            sex_woman.setTextColor(getResources().getColor(R.color.orange));
            image_woman.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode){
            case REQUEST_TO_DATE_PICKER:
                if (data != null) {
                    ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                    if (list != null && list.size() >= 3) {

                        selectedDate = new int[list.size()];
                        for (int i = 0; i < list.size(); i++) {
                            selectedDate[i] = list.get(i);
                        }
                        select_birthday.setText(selectedDate[0]+"-"+(selectedDate[1]+1)+"-"+selectedDate[2]);
                    }
                }
                break;
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

                        File cropFile = new File(YYStorageUtil.getImagePath(MeInfoActivity.this), UUID.randomUUID().toString() + ".jpg");
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
    private void uploadImage(final List<File> list) {
        // 调用上传

    }
    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (returnCode ==1){
                    Intent intent = new Intent();
                    intent.putExtra("MEINFOHEAD",image_head);
                    intent.putExtra("NICKNAME",nickname);
                    intent.putExtra("SEX",sex);
                    intent.putExtra("USERBIRTHDAY",userbirthday);
                    // 设置结果，并进行传送
                    this.setResult(Activity.RESULT_FIRST_USER, intent);
                    ToastUtil.showToast(returnMsg);
                    finish();
                }else {
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

    @Override
    public Activity getActivity() {
        return this;
    }
}
