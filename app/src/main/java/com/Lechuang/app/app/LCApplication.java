package com.Lechuang.app.app;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;
import com.yonyou.sns.im.core.YYIMChat;

import io.rong.imkit.RongIM;
import www.xcd.com.mylibrary.base.application.BaseApplication;

/**
 * Created by Android on 2017/7/17.
 */

public class LCApplication extends BaseApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);//初始化融云
//        PlatformConfig.setWeixin("wx967daebe835fbeac","5bb696d9ccd75a38c8a0bfe0675559b3");//微信
//        PlatformConfig.setSinaWeibo("3921700954","04b48b094faeb16683c32669824ebdad","");//新浪微博
//        PlatformConfig.setQQZone("100424468","c7394704798a158208a74ab60104f0ba");//
        String versionName = null;
        try {
            versionName = getVersionName();
            Context appContext = getApplicationContext();
            CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(appContext);
            strategy.setAppVersion(versionName);
            CrashReport.initCrashReport(appContext, "7c0ffed339", false,strategy);
            //初始化第三方jar
            YYIMChat.getInstance().init(getApplicationContext());
            YYIMChat.getInstance().configLogger(Log.VERBOSE, true, true, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private String getVersionName() throws Exception{
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(getPackageName(),0);
        String version = packInfo.versionName;
        return version;
    }
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();

        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {

            if (appProcess.pid == pid) {

                return appProcess.processName;

            }

        }
        return null;
    }
}
