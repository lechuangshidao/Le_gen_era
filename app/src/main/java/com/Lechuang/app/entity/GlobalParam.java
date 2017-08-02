package com.Lechuang.app.entity;

/**
 * Created by dell on 2015-11-23.
 */
public class GlobalParam {
    //绑定头URL   http://69.165.64.194/chongai/index.php?
    public final static String IP = "http://69.165.64.194";
    public final static String URL = IP + "/chongai/index.php?";
    //登陆
    public final static String LOGIN = URL + "s=/index/index";
    //注册
    public final static String REGISTER = URL + "s=/Register/userAdd";
    //获取验证码
    public final static String GETVERIFICATIONCODE = URL + "s=Userreg/register";
    //新增地址
    public final static String NEWADDRESS = URL + "s=/index/addPet";
    //注册宠物
    public final static String REGISTERPet = URL + "s=/Register/userPetAdd";
    //附近
    public final static String PETHOSPITAL = URL + "s=pethospital/getmeter";
    //积分商城
    public final static String SHOPPINGMALL = URL + "s=User/IntegralShop";
    //兑换积分
    public final static String CREDITSEXCHANGE = IP + "/api/user/register";
    //验证码验证
    public final static String VERIFICATIONCODE = URL + "s=userreg/checkcode";
    //注册设置密码接口
    public final static String SETPASSWORD = URL + "s=userreg/setpassword";
    //用户注册
    public final static String USERREGISTRATION = URL + "s=userreg/savedata";
    //我的Fragment
    public final static String FRAGMENTWODEINFO =URL+"s=My/Message";
    //我的个人信息
    public final static String MEINFO =URL+"s=/index/modify";
    //我的宠物信息
    public final static String MYPETINFO =URL+"s=index/myPet";
    //添加宠物信息
    public final static String AAMYPETINFO =URL+"s=index/addPet";
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
}
