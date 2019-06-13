package com.wd.tech.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.view.KeyEvent;
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
    private long exitTime = 0;
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

    /*
     *监听返回键点击事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //触发一次返回键 关闭侧滑
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /*
     *实现连续2秒内连续点击两次按钮退出当前APP
     */
    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(NonetActivity.this, "再按一次退出程序",Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            System.exit(0);
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
            case 5:
                intent = new Intent(NonetActivity.this,CollectActivity.class);
                break;
            case 6:
                intent = new Intent(NonetActivity.this,AttentionActivity.class);
                break;
            case 7:
                intent = new Intent(NonetActivity.this,CardActivity.class);
                break;
            case 8:
                intent = new Intent(NonetActivity.this,NoticeActivity.class);
                break;
            case 9:
                intent = new Intent(NonetActivity.this,JifenActivity.class);
                break;
            case 10:
                intent = new Intent(NonetActivity.this,TaskActivity.class);
                break;
            case 11:
                intent = new Intent(NonetActivity.this,SettingActivity.class);
                break;
            case 12:
                //intent = new Intent(NonetActivity.this,SIgnatureActivity.class);
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
