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
    //我的个人信息
    public final static String FRAGMENTWODEINFO =URL+"s=My/Message";
}
