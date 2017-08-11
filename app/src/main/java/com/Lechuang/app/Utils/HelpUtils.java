package com.Lechuang.app.Utils;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;

import com.Lechuang.app.entity.Config;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import www.xcd.com.mylibrary.base.application.BaseApplication;
import www.xcd.com.mylibrary.utils.YYStorageUtil;

/**
 * Created by Android on 2017/7/20.
 */

public class HelpUtils {
    static OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .build();
    /**
     * 获取年月日
     * @return
     */
    public static String getCurrentData(){
        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd");
        Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        String    data    =    formatter.format(curDate);
        return data;
    }
    /**
     * 获取年月日时分秒
     * HH 24小时制
     * hh 12小时制
     * @return
     */
    public static String getCurrentAllData(){
        SimpleDateFormat formatter    =   new    SimpleDateFormat    ("yyyy-MM-dd HH:mm:ss ");
        Date    curDate    =   new Date(System.currentTimeMillis());//获取当前时间
        String    data    =    formatter.format(curDate);
        return data;
    }
    public static String getTimeDifference(String starTime){
        StringBuffer sb = new StringBuffer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String endString = getCurrentData();
        try {
            Date star = dateFormat.parse(starTime);
            Date end = dateFormat.parse(endString);

            long times = end.getTime() - star.getTime();//这样得到的差值是微秒级别
            if (times<0){
                return "-1";
            }
            long yearnumber = 1000L * 60L* 60L * 24L * 365L;
            long monthnumber = 1000L * 60L * 60L * 24L * 30L;
            long year = times/(yearnumber); //换算成年数
            if (year >= 1) {
                sb.append(year+"年");
                long surplus_data = times % (yearnumber);
                long surplus_month = surplus_data/ (monthnumber); //换算成月数
                if (surplus_month>=1){
                    sb.append(surplus_month+"月");
                    long surplus_days = surplus_data% (monthnumber);
                    if (surplus_days!=0){
                        long days = surplus_days/ (1000 * 60* 60 * 24); //换算成天数
                        sb.append((days+1)+"天");
                    }
                }else {
                    sb.append((surplus_month+1)+"天");
                }
            } else {
                long month = times/ (monthnumber); //换算成月数
                if (month >= 1){
                    sb.append(month+"月");
                    long surplus_month = month% (monthnumber);
                    if (surplus_month!=0){
                        long days = surplus_month/ (1000 * 60* 60 * 24); //换算成天数
                        sb.append((days+1)+"天");
                    }

                }else {
                    long days = times/ (1000 * 60* 60 * 24); //换算成天数
                    if (days>=0){
                        sb.append((days+1)+"天");
                    }else {
                        sb.append("不足一天");
                    }
//                    long hours =(times-days*(1000 * 60 * 60 * 24))/(1000* 60 * 60); //换算成小时
//
//                    long minutes =(times-days*(1000 * 60 * 60 * 24)-hours*(1000* 60 * 60))/(1000* 60); //换算成分钟
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return "-1";
        }
        return sb.toString();
    }
    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }
    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
    public static String uploadImg(String qk_id, List<File> listpath, String imagenema,String path) {
        String reString = "";
        try {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);

            //遍历listpath中所有图片绝对路径到builder，并约定key如“img”作为后台接受多张图片的key
            for (File imagepath : listpath) {
                Log.e("TAG_","imagenema="+imagenema+";imagepath="+imagepath);
                builder.addFormDataPart("img", imagenema, RequestBody.create(MEDIA_TYPE_PNG, imagepath));
            }
            builder.addFormDataPart("lesd", qk_id);
            MultipartBody requestBody = builder.build();
            reString = postokHttpClient(path, requestBody);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return reString;
    }
    public static String postokHttpClient(String path, RequestBody formBody) throws Exception {
        Request request = new Request.Builder()
                .url(path)
                .post(formBody)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String string = response.body().string();
            return string;
        } else {
//            throw new IOException("Unexpected code " + response);
            return String.valueOf(response.code());
        }
    }

    /**
     * 获取图片缓存大小
     * @return
     */
    public static String getCacheSize() {
        try {
            return getFormatSize(getFolderSize(
                    new File(BaseApplication.getApp().getCacheDir()
                            + "/" + Config.GLIDE_CARCH_DIR)));
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }
    private static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }
    // 获取指定文件夹内所有文件大小的和
    private static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }
    /**
     * 清楚缓存
     */
    public static boolean cleanCatchDisk(Context context) {
        String cachePath = YYStorageUtil.getSystemDisCachePath(context);
        boolean deleteFolderFile = deleteFolderFile(cachePath, true);
        boolean deleteFolderFile1 = deleteFolderFile(BaseApplication.getApp().getCacheDir() + "/" + Config.GLIDE_CARCH_DIR, true);
        return deleteFolderFile&&deleteFolderFile1;
    }
    // 按目录删除文件夹文件方法
    private static boolean deleteFolderFile(String filePath, boolean deleteThisPath) {
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (File file1 : files) {
                    deleteFolderFile(file1.getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {
                    file.delete();
                } else {
                    if (file.listFiles().length == 0) {
                        file.delete();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String getString(String all, String single)//s是需要删除某个子串的字符串s1是需要删除的子串
    {
        int postion = all.indexOf(single);
        int length = single.length();
        int Length = all.length();
        String newString = all.substring(0,postion) + all.substring(postion + length, Length);
        return newString;//返回已经删除好的字符串
    }

}
