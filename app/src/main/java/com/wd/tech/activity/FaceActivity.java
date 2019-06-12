package com.wd.tech.activity;


import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.Transition;
import android.view.Window;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.network.NetWorkUtils;

public class FaceActivity extends BaseActivity {


    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case NetWorkUtils.NETWORK_NONE:
                Toast.makeText(FaceActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                //没有网络
                //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                Intent intent = new Intent(FaceActivity.this, NonetActivity.class);
                StaticClass.NetState = 13;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(FaceActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(FaceActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                //移动网络
                break;
            case NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(FaceActivity.this, "WiFi网络", Toast.LENGTH_SHORT).show();
                //WiFi网络
                break;
        }
    }

    @Override
    public int intiLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置contentFeature,可使用切换动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            // init_explode();// 分解
            //  init_Slide();//滑动进入
            init_fade();//淡入淡出
        }
        return R.layout.activity_face;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_fade() {
        Transition transition = new Fade().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }
}
