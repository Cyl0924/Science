package com.wd.tech.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.network.NetWorkUtils;

public class NonetActivity extends BaseActivity {

    private Button yinbtn;
    private Intent intent;

    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case  NetWorkUtils.NETWORK_NONE:
                Toast.makeText(NonetActivity.this,"没有网络",Toast.LENGTH_SHORT).show();
                //没有网络
                break;
            case  NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(NonetActivity.this,"移动网络",Toast.LENGTH_SHORT).show();
                //移动网络
                JumpMain();
                break;
            case  NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(NonetActivity.this,"WiFi网络",Toast.LENGTH_SHORT).show();
                //WiFi网络
                JumpMain();
                break;
        }
    }

    public void JumpMain(){
        switch (StaticClass.NetState){
            case 1:
                intent = new Intent(NonetActivity.this,MainActivity.class);
                break;
            case 2:
                intent = new Intent(NonetActivity.this,LoginActivity.class);
                break;
            case 3:
                intent = new Intent(NonetActivity.this,RegisterActivity.class);
                break;
            case 4:
                intent = new Intent(NonetActivity.this,YindaoActivity.class);
                break;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(NonetActivity.this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public int intiLayout() {
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置contentFeature,可使用切换动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
              init_explode();// 分解
            //  init_Slide();//滑动进入
              init_fade();//淡入淡出
        }*/
        return R.layout.activity_nonet;
    }

    @Override
    public void initView() {
        yinbtn = findViewById(R.id.Yin_BTN);
    }

    @Override
    public void initData() {
        yinbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                    startActivity(intent);
                }
         });
    }

    //切换的动画具体使用看53  54  55 行调用
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_fade() {
        Transition transition = new Fade().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    //切换的动画具体使用看53  54  55 行调用
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_Slide() {
        Transition transition = new Slide().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    //切换的动画具体使用看53  54  55 行调用
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_explode() {
        Explode explode = new Explode();
        explode.setDuration(200);
        getWindow().setEnterTransition(explode);
    }

}