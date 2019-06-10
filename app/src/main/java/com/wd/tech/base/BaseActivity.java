package com.wd.tech.base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.wd.tech.Immerse.StatusBarUtil;
import com.wd.tech.network.INetEvent;
import com.wd.tech.network.NetStateReceiver;
import com.wd.tech.network.NetWorkUtils;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */

public abstract class BaseActivity extends FragmentActivity implements INetEvent {

    public static INetEvent mINetEvent;
    private int netWorkState;
    private NetStateReceiver mNetStateReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(intiLayout());
        //初始化控件
        initView();
        //设置数据
        initData();

        StatusBarUtil.setRootViewFitsSystemWindows(this,true);
        //设置状态栏透明
        StatusBarUtil.setTranslucentStatus(this);
            //一般的手机的状态栏文字和图标都是白色的, 可如果你的应用也是纯白色的, 或导致状态栏文字看不清
            //所以如果你是这种情况,请使用以下代码, 设置状态使用深色文字图标风格, 否则你可以选择性注释掉这个if内容
            if (!StatusBarUtil.setStatusBarDarkTheme(this, true)) {
                //如果不支持设置深色风格 为了兼容总不能让状态栏白白的看不清, 于是设置一个状态栏颜色为半透明,
                //这样半透明+白=灰, 状态栏的文字能看得清
                StatusBarUtil.setStatusBarColor(this,0x55000000);
        }

        //网络监听
        mINetEvent=this;
        // 初始化时检查网络连接
        checkNet();
        // 动态注册网络状态监听广播
        mNetStateReceiver = new NetStateReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mNetStateReceiver , filter);

    }

    /**
     * 全局检测网络广播的回调 处理网络变化
     *  网络状态    -1:没网络  0:移动网络  1:WiFi网络
     */

    public void checkNet() {
        this.netWorkState= NetWorkUtils.getNetWorkState(BaseActivity.this);
    }

    public abstract void onNetChanged(int netWorkState);

    @Override
    public void onNetChange(int netWorkState) {
        this.netWorkState= netWorkState;
        onNetChanged(netWorkState);
    }

    /**
     * 初始化布局
     */
    public abstract int intiLayout();

    /**
     * 获取控件
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册
        unregisterReceiver(mNetStateReceiver);
        // 避免内存泄漏，置空
        if(mINetEvent != null){
            mINetEvent = null;
        }
    }

}
