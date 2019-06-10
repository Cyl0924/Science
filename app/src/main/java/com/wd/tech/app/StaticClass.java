package com.wd.tech.app;

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

    /*
     * 下面是全局静态常量 userID sessionId 可以直接类名点进行调用 切勿进行值的覆盖或者更改 指空等操作
     */
    public static int userId = 0;
    public static String sessionId = null;

}
