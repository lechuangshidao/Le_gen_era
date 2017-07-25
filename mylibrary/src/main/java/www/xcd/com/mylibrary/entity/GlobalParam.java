package www.xcd.com.mylibrary.entity;

/**
 * Created by Android on 2017/5/17.
 */

public class GlobalParam {
    public final static String IPDDNS = "http://quankavip.com/";
    public final static String IP = IPDDNS+"index.php";
    //URL全部 http://101.200.145.202/index.php?g=Api&m=Business&
    public final static String URL = IP+"?g=Api&m=Business&";
    //登录
    public final static String ISLOGIN = URL + "a=Businesslogin";
    //会员管理
    public final static String VIPMANAGE = URL + "a=Memberslist";
    //新增会员
    public final static String NEWSADDVIP = URL + "a=Membersadd";
    //我的员工all
    public final static String MYSTAFF = URL + "a=mystafflist";
    //我的员工天数查询
    public final static String MYSTAFFTIME = URL + "a=mystaffrecord";
    // 快速消费二维码
    public final static String QUICKEXPENSETWOCODE = URL + "a=thefastconsumptionshow";
    // 快速消费
    public final static String QUICKEXPENSE = URL + "a=thefastconsumption";
    // 分享邀请验证二维码
    public final static String VERIFYTWOCODE = URL + "a=share";
    // 分享邀请验证
    public final static String VERIFY = URL + "a=sharemoney";
    // 领取特权二维码
    public final static String GETSPECIALTWOCODE = URL + "a=Privilegelist";
    // 领取特权
    public final static String GETSPECIAL = URL + "a=getprivilege";
    // 红包消费二维码
    public final static String DEXPENSETWOCODE = URL + "a=redbagshow";
    // 红包消费
    public final static String DEXPENSE = URL + "a=redbagconsumption";
    // 扫码储值二维码
    public final static String CODEVALUETWOCODE = URL + "a=scansavemoneyshow";
    // 扫码储值
    public final static String CODEVALUE = URL + "a=scansavemoneyadd";
    // 消费送积分
    public final static String EXPENSEINTEGRAL = URL + "a=giveintegraladd";
    // 特权消费
    public final static String SPECIALMONEY = URL + "a=privilegeconsumption";
    //手机号初始数据
    public final static String PHONEVALUEINITIALIZE = URL + "a=phonesavemoneyshow";
    //手机号储值
    public final static String PHONEVALUE = URL + "a=phonesavemoneyadd";
    //商家电话显示
    public final static String BUSINESSPHONESHOW = URL + "a=businessphoneshow";
    //更改商家电话
    public final static String ERCHANTPHONE = URL + "a=businessphonesave";
    //商家收货地址
    public final static String MERCHANTPLACELOCATION = URL + "a=businessaddress";
    //商家收货地址设置默认
    public final static String MERCHANTPLACELOCATIONSTATUS = URL + "a=businessaddressdefault";
    //商家收货地址删除
    public final static String MERCHANTPLACELOCATIONDELETE = URL + "a=businessaddressdel";
    //商家收货地址添加
    public final static String MERCHANTPLACELOCATIONADD = URL + "a=businessaddressadd";
    //主营设置
    public final static String MERCHANTMAINSETTING = URL + "a=mainsettings";
    //主营设置修改
    public final static String MERCHANTMAINSETTINGSAVE = URL + "a=mainsettingssave";
    //我的银行卡显示
    public final static String MYCARD = URL + "a=mycard";
    //我的银行卡
    public final static String MERCHANTMYBANKCARD = URL + "a=mybankcardadd";
    //商家描述详情
    public final static String MERCHANTDESCRIBE = URL + "a=businessinfoadd";
    //商家描述详情webview
    public final static String MERCHANTDESCRIBEWEBVIEW = IP + "?g=Api&m=Members&a=Introduction";
    //顶部商家详情
    public final static String TOPDETAILS = URL + "a=businessinformation";
    //首页顶部积分明细
    public final static String SHOPINTEGRALDETAIL = URL + "a=integralrecord";
    //首页顶部积分明细筛选
    public final static String SHOPINTEGRALDETAILSEARCH = URL + "a=integralrecordsearch";
    //账户数据详情
    public final static String AAOUNTMAINDATA = URL + "a=myintegral";
    //我的账户收益详情
    public final static String ACCPUNTMYEARNINGS = URL + "a=ProfitDetails";
    //我的账户新增会员记录
    public final static String ACCOUNTNEWSADDVIP = URL + "a=newuseraddlist";
    //我的全部订单记录
    public final static String ACCOUNTMYALLORDER = URL + "a=businessorder";
    //我的全部订单记录单个详情
    public final static String ACCOUNTMYALLORDERSINGLE = URL + "a=businessorderrecord";
    //我的账户会员兑换
    public final static String ACCOUNTVIPEXCHANGE = URL + "a=userorder";
    //我的账户会员兑换详情
    public final static String ACCOUNTTVIPEXCHANGEINTEGRAL = URL + "a=userorderrecord ";
    //兑换商城左侧数据
    public final static String STOREFRAGMENTLEFT = URL + "a=goodstype ";
    //兑换商城右侧所有
    public final static String STOREFRAGMENTRIGHTALL = URL + "a=goodslists ";
    //兑换商城右侧顶部图片
    public final static String STOREFRAGMENTRIGHTHEADERIMAGE = URL + "a=imglists ";
    //兑换商城右侧单个数据
    public final static String STOREFRAGMENTRIGHTSINGLE = URL + "a=goodssearch ";
    //结算商品详情
    public final static String STORESINLEDETAILS = URL + "a=buygoods ";
    //修改密码
    public final static String ACCOUNTUPDATAPASSWORD = URL + "a=updatepassword ";
    //意见反馈
    public final static String ACCOUNTOPINIONFEEDBACK = URL + "a=feedback ";
    //微信回调地址异步通知
    public final static String CERT_NOTIFYWEXIN = IPDDNS + "wxpay2/example/notify2.php";
    //支付宝回调地址异步通知
    public final static String CERT_NOTIFY = IPDDNS + "alipay/notify_url2.php";
    //发起支付
    public final static String ZFBPAY = URL + "a=ZFBpay ";
    //兑换商城模糊查询
    public final static String STORESEARCHGOOD = URL + "a=Searchgood ";
    //会员管理模糊查询
    public final static String HOMEVIPMEMBERSEARCH = URL + "a=Membersearch ";
    //会员管理模糊查询
    public final static String MERCHANTMYTWOCODE = URL + "a=mycode ";
    //修改头像
    public final static String PICUPLOAD = URL + "a=Picupload ";
}
