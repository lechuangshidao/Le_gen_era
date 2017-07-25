package www.xcd.com.mylibrary.utils;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by xcd15 on 2017/5/9.
 */

public class ClassUtils {
    //判断手机号是否为手机号
    public static boolean isMobileNO(String mobiles) {

        Pattern p = Pattern.compile("^((17[0-9])|(14[5,7])|(13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$");

        Matcher m = p.matcher(mobiles);

        return m.matches();
    }
    //保留两位小数
    public static String doubledigit(String reString) {
        Double d = Double.valueOf(reString);
        DecimalFormat f1 = new DecimalFormat("######0.00");
        reString = f1.format(d);
        return reString;
    }
//    public static boolean cleanCatchDisk(Context context) {
//        String cachePath = YYStorageUtil.getSystemDisCachePath(context);
//        boolean deleteFolderFile = deleteFolderFile(cachePath, true);
//        boolean deleteFolderFile1 = deleteFolderFile(BaseApplication.getApp().getCacheDir() + "/" + Config.GLIDE_CARCH_DIR, true);
//        return deleteFolderFile&&deleteFolderFile1;
//    }
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

//    public static String getCacheSize() {
//        try {
//            return getFormatSize(getFolderSize(
//                    new File(BaseApplication.getApp().getCacheDir()
//                            + "/" + Config.GLIDE_CARCH_DIR)));
//        } catch (Exception e) {
//            e.printStackTrace();
//            return "获取失败";
//        }
//    }
    // 格式化单位
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

}
