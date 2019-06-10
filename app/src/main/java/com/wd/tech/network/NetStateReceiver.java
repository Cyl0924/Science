package com.wd.tech.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.wd.tech.base.BaseActivity;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class NetStateReceiver extends BroadcastReceiver {

    private INetEvent mINetEvent= BaseActivity.mINetEvent;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            //容错机制
            if(mINetEvent!=null) {
                // 接口回调传过去状态的类型
                mINetEvent.onNetChange(NetWorkUtils.getNetWorkState(context));
            }
        }
    }
}
