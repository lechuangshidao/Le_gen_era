package com.Lechuang.app.entity;

/**
 * Created by dell on 2015-11-23.
 */
public class GlobalParam {
    //绑定头URL   http://69.165.64.194/chongai/index.php?
    public final static String IP = "http://69.165.64.194";
    public final static String URL = IP+"/chongai/index.php?";
    //登陆
    public final static String LOGIN =URL+"s=/index/index";
    //注册
    public final static String REGISTER =URL+"s=/Register/userAdd";
    //获取验证码
    public final static String VERIFICATIONCODE =IP+"/api/user/register";
    //我的Fragment
    public final static String FRAGMENTWODEINFO =URL+"s=My/Message";
    //我的个人信息
    public final static String MEINFO =URL+"s=Register/userSave";
    //我的宠物信息
    public final static String MYPETINFO =URL+"s=index/myPet";
    //修改宠物信息
    public final static String PETMODIFYMYPETINFO =URL+"s=/index/petModify";
    //删除宠物信息
    public final static String DELPETMYPETINFO =URL+"s=/index/delPet";
    //添加宠物信息
    public final static String AAMYPETINFO =URL+"s=index/addPet";
    //添加宠物信息标签
    public final static String AAMYPETINFOTAGS =URL+"s=index/tags";
    //地址管理
    public final static String MYPETLOCATIONMANAGE =URL+"s=index/Locations";
    //添加地址
    public final static String MYPETADDLOCATION =URL+"s=index/addLocation";
    //编辑地址
    public final static String MYPETADDLOCATIONCOMPILE =URL+"s=/index/modLocation";
    //编辑地址
    public final static String MYPETDELLOCATION =URL+"s=/index/delLocation";
    //单个地址详情
    public final static String MYPETADDLOCATIONSINLE =URL+"s=/index/Locations";
    //单个地址详情
    public final static String MYPETINTEGRALTASK =URL+"s=score/score";
    //单个地址详情
    public final static String MYPETACTOIN=URL+"s=Active/active";
    //修改密码
    public final static String PWDMODIFY=URL+"s=/index/pwdModify";
}
