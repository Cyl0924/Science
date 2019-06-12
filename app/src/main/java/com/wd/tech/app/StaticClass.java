package com.wd.tech.app;

import android.widget.Button;

import java.util.List;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class StaticClass {

    /*
     * 全局参数存储类 添加参数请做好注释具体作用在哪
     */
    public static int NetState = 0;  //断网判断当前所属页面标识

    /*
     * 当前请求所要用到URL 作为静态调用 请做好注释属于哪个接口
     */
    public static String REGISTER = "techApi/user/v1/register";  //注册接口
    public static String LOGIN = "techApi/user/v1/login";  //登录接口
    public static String USERMESSAGE = "techApi/user/verify/v1/getUserInfoByUserId"; //根据ID查询用户所有信息
    public static String Banner="techApi/information/v1/bannerShow";//banner展示列表
    public static String AllInfoPlate="techApi/information/v1/findAllInfoPlate";//所有板块查询
    public static String RecommendList="techApi/information/v1/infoRecommendList" ;//资讯推荐展示列表(包含单独板块列表展示)
    public  static String FindByTitle = "techApi/information/v1/findInformationByTitle";//根据标题模糊查询
    public  static String FindBySource = "techApi/information/v1/findInformationBySource";//根据作者名字模糊查询
    public  static String  Advertising="techApi/information/v1/findInfoAdvertising";//资讯广告

    public static int userId = 0;
    public static String sessionId = null;
}
