package com.Lechuang.app.entity;

/**
 * Created by dell on 2015-11-23.
 */
public class GlobalParam {
    public final static boolean dubug = true;
    //绑定头URL   http://69.165.64.194/chongai/index.php?
    public final static String IP = "http://123.206.68.159";
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
    //服务信息
    public final static String PETHOSPITAL = URL + "s=pethospital/getmeter";
    //积分商城
    public final static String SHOPPINGMALL = URL + "s=User/IntegralShop";
    //兑换积分
    public final static String CREDITSEXCHANGE = URL + "s=User/IntegralExchange";
    //验证码验证
    public final static String VERIFICATIONCODE = URL + "s=userreg/checkcode";
    //注册设置密码接口
    public final static String SETPASSWORD = URL + "s=userreg/setpassword";
    //用户注册
    public final static String USERREGISTRATION = URL + "s=userreg/savedata";
    //我的Fragment
    public final static String FRAGMENTWODEINFO = URL + "s=My/Message";
    //我的个人信息
    public final static String MEINFO = URL + "s=Register/userSave";
    //我的宠物信息
    public final static String MYPETINFO = URL + "s=index/myPet";
    //修改宠物信息
    public final static String PETMODIFYMYPETINFO = URL + "s=/index/petModify";
    //删除宠物信息
    public final static String DELPETMYPETINFO = URL + "s=/index/delPet";
    //添加宠物信息
    public final static String AAMYPETINFO = URL + "s=index/addPet";
    //添加宠物信息标签
    public final static String AAMYPETINFOTAGS = URL + "s=index/tags";
    //地址管理
    public final static String MYPETLOCATIONMANAGE = URL + "s=index/Locations";
    //添加地址
    public final static String MYPETADDLOCATION = URL + "s=index/addLocation";
    //编辑地址
    public final static String MYPETADDLOCATIONCOMPILE = URL + "s=/index/modLocation";
    //编辑地址
    public final static String MYPETDELLOCATION = URL + "s=/index/delLocation";
    //单个地址详情
    public final static String MYPETADDLOCATIONSINLE = URL + "s=/index/Locations";
    //单个地址详情
    public final static String MYPETINTEGRALTASK = URL + "s=score/score";
    //单个地址详情
    public final static String MYPETACTOIN = URL + "s=Active/active";
    //修改密码
    public final static String PWDMODIFY = URL + "s=/index/pwdModify";
    //附近人
    public final static String PEOPLENEARBY = URL + "s=User/MyNear";
    //宠物分类
    public final static String PETCLASSIFICATION = URL + "s=User/PetType";
    //宠物信息
    public final static String PETINFORMATION = URL + "s=User/PetTypeF";
    //宠物详情
    public final static String PETDETAILS = URL + "s=User/PetInfo";
    //展示首页信息
    public final static String HOMEVIEWPAGER = URL + "s=Homepage/Index";
    //首页地图定位
    public final static String MAPLOCATION = URL + "s=User/LatLon";
    //宠物搜索
    public final static String PETSEEK = URL + "s=index/search";
    //展示首页医院信息
    public final static String HOMEHPAGERHOSPITAL=URL+"s=Homepage/Hospital";
    //点击marker展示信息
    public final static String PETINFO=URL+"s=Homepetinfo/Findpet";
    //搜索框搜索附近宠物
    public final static String SEEKPET=URL+"s=Homepage/Findtexts";
    //首页宠物和医院的展示
    public final static String PETHOSPITALSHOW=URL+"s=Homepage/Message";
}
