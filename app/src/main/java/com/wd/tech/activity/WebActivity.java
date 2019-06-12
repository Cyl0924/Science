package com.wd.tech.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.network.NetWorkUtils;

public class WebActivity extends BaseActivity {

    private WebView web;

    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case NetWorkUtils.NETWORK_NONE:
                Toast.makeText(WebActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                //没有网络
                //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                Intent intent = new Intent(WebActivity.this, NonetActivity.class);
                StaticClass.NetState = 13;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(WebActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(WebActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                //移动网络
                break;
            case NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(WebActivity.this, "WiFi网络", Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        web = findViewById(R.id.web);
    }

    @Override
    public void initData() {
        //得到 URL
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setJavaScriptEnabled(true);
        //展示WebView
        WebViewClient webViewClient=new WebViewClient();
        web.setWebViewClient(webViewClient);
        web.loadUrl(url);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_fade() {
        Transition transition = new Fade().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }
}
