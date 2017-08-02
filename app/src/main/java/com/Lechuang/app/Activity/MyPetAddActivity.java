package com.Lechuang.app.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.Lechuang.app.R;
import com.Lechuang.app.Utils.HelpUtils;
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

public class MyPetAddActivity extends BaseDataActivity {

    private static final int REQUEST_TO_DATE_PICKER = 35;
    private int[] selectedDate = new int[]{1971, 0, 1};
    private TextView age;
    private EditText name, type;
    private LinearLayout petadd_include;
    private ImageView mypetadd_head;
    private Thread thread;
    private Context context;
    private Button button;
    private StringBuilder builder;
    private String imageFilepath;
    private String user_id;
    private String token;
    private void uploadImage(final List<File> listimage) {
        // 调用上传
        for (File imagepath : listimage) {
           imageFilepath = imagepath.toString();
            Glide.with(this)
                    .load(imagepath)
                    .centerCrop()
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.mipmap.pethead)
                    .error(R.mipmap.pethead)
                    .into(mypetadd_head);
        }
    }
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

    private String[][] textinclude = {{"公", "母"}, {"发情期", "未成年", "绝育"}, {"黑色", "白色", "其它"}};
    private List<Map<String,Boolean>> list;
    public void initView() {
        user_id = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("user_id");
        token = XCDSharePreference.getInstantiation(getActivity()).getSharedPreferences("token");
        mypetadd_head = (ImageView) findViewById(R.id.mypetadd_head);
        mypetadd_head.setOnClickListener(this);
        age = (TextView) findViewById(R.id.mypetadd_age);
        age.setOnClickListener(this);
        name = (EditText) findViewById(R.id.mypetadd_name);
        name.setOnFocusChangeListener(this);
        type = (EditText) findViewById(R.id.mypetadd_type);
        type.setOnFocusChangeListener(this);
        petadd_include = (LinearLayout) findViewById(R.id.petadd_include);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("token", token);
//        okHttpPost(101, GlobalParam.AAMYPETINFOTAGS, params);
        initGridViewOne();
    }

    private void initGridViewOne()  {
        builder = new StringBuilder();
        list = new ArrayList<>();
        for (int i = 0, length = textinclude.length; i < length; i++) {
            LinearLayout layout2 = new LinearLayout(this);
            layout2.setId((i+1)*10);
            Map map = new HashMap();
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
//                    if (i==0){
//                        builder.append(text.getText());
//                    }else {
//                        builder.append("#"+text.getText());
//                    }
                    map.put(string_include,true);
                }else {
                    text.setBackgroundResource(R.drawable.shape_y_solid_black);
                    image.setVisibility(View.INVISIBLE);
                    map.put(string_include,false);
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
            list.add(map);
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
                //上传头像
                setTpye(AlbumPhotoActivity.TYPE_SINGLE);
                getChoiceDialog().show();
                break;
            case R.id.button:
                String pet_type = type.getText().toString().toString();
                if (TextUtils.isEmpty(pet_type)){
                    ToastUtil.showToast("品种不能为空");
                    return;
                }
                String pet_name = name.getText().toString().toString();
                if (TextUtils.isEmpty(pet_name)){
                    ToastUtil.showToast("昵称不能为空");
                    return;
                }
                String pet_age = age.getText().toString().toString();
                if (TextUtils.isEmpty(pet_age)){
                    ToastUtil.showToast("年龄不能为空");
                    return;
                }
                if (imageFilepath==null){
                    ToastUtil.showToast("您还未选择宠物头像");
                    return;
                }
                for (int j = 0,lengh = list.size(); j <lengh ; j++) {
                    Map<String,Boolean> map = list.get(j);
                    for (String key : map.keySet()) {
                        Boolean value = map.get(key);
                        if (value) {
                            if (j==0){
                                builder.append(key);
                            }else {
                                builder.append("#"+key);
                            }
                        }
                    }
                }
                createDialogshow();
                Log.e("TAG_","imageFilepath="+imageFilepath);
                Map<String, Object> params = new HashMap<String, Object>();
                params.put("user_id", user_id);
                params.put("token", token);
                params.put("pet_img", imageFilepath);
                params.put("pet_name", pet_name);
                params.put("pet_age", pet_age);
                params.put("pet_tag", builder.toString());
                params.put("pet_type", pet_type);
                okHttpImgPost(100, GlobalParam.AAMYPETINFO, params);
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_TO_DATE_PICKER:
                    if (data != null) {
                        ArrayList<Integer> list = data.getIntegerArrayListExtra(DatePickerWindow.RESULT_DATE_DETAIL_LIST);
                        if (list != null && list.size() >= 3) {

                            selectedDate = new int[list.size()];
                            for (int i = 0; i < list.size(); i++) {
                                selectedDate[i] = list.get(i);
                            }
                            String time =selectedDate[0]+"-"+(selectedDate[1]+1)+"-"+selectedDate[2];
                            String timeDifference = HelpUtils.getTimeDifference(time);
                            if (timeDifference.indexOf("-1") != -1) {
                                ToastUtil.showToast("请选择正确出生日期");
                            } else {
                                age.setText(timeDifference);
                            }
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
    View.OnClickListener pChildClick = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            LinearLayout parentView = (LinearLayout) v.getParent();
            for (int i = 0; i < parentView.getChildCount(); i++) {
                RelativeLayout relate = (RelativeLayout) parentView
                        .getChildAt(i);
                TextView viewExchange;

                if (v.getId()==parentView.getChildAt(i).getId()){
                    viewExchange = (TextView) relate
                            .getChildAt(0);
                    String change = viewExchange.getText().toString();

                    viewExchange.setBackgroundResource(R.drawable.shape_y_solid_orange);
                    ImageView image = (ImageView) relate
                            .getChildAt(1);
                    image.setVisibility(View.VISIBLE);
//                    for (int j = 0,lengh = list.size(); j <lengh ; j++) {
                        Map<String,Boolean> map = list.get((parentView.getId()-i)/10);
                        for (String key : map.keySet()) {
                            if (map.get(key) != null&&key.equals(change)) {
                                map.put(key, true);
                            }else {
                                map.put(key, false);
                            }
                        }
//                    }

                } else  {
                    viewExchange = (TextView) relate.getChildAt(0);
                    String change = viewExchange.getText().toString();
                    viewExchange.setBackgroundResource(R.drawable.shape_y_solid_black);
                    ImageView image = (ImageView) relate
                            .getChildAt(1);
                    image.setVisibility(View.INVISIBLE);
                }

            }
            Log.e("TAG_","list="+list.toString());
        }
    };

    @Override
    public void onSuccessResult(int requestCode, int returnCode, String returnMsg, String returnData, Map<String, Object> paramsMaps) {
        switch (requestCode){
            case 100:
                if (returnCode==1){
                    this.setResult(Activity.RESULT_OK);
                    finish();
                }
                ToastUtil.showToast(returnMsg);
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
