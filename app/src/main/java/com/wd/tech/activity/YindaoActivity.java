package com.wd.tech.activity;

import android.Manifest;
import android.app.ActivityOptions;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.wd.tech.R;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.network.NetWorkUtils;


public class YindaoActivity extends BaseActivity {

    private static int REQUEST_PERMISSION_CODE = 1;
    private LinearLayout yindao;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case  NetWorkUtils.NETWORK_NONE:
                Toast.makeText(YindaoActivity.this,"没有网络",Toast.LENGTH_SHORT).show();
                //没有网络
                //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                Intent intent = new Intent(YindaoActivity.this,NonetActivity.class);
                StaticClass.NetState = 4;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(YindaoActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case  NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(YindaoActivity.this,"移动网络",Toast.LENGTH_SHORT).show();
                //移动网络
                break;
            case  NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(YindaoActivity.this,"WiFi网络",Toast.LENGTH_SHORT).show();
                //WiFi网络
                break;
        }
    }

    @Override
    public int intiLayout() {
        return R.layout.activity_yindao;
    }

    @Override
    public void initView() {
        Toast.makeText(YindaoActivity.this,"点击进入主页面",Toast.LENGTH_SHORT).show();
        yindao = findViewById(R.id.Img_YINDAO);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, REQUEST_PERMISSION_CODE);
            }
        }
    }

    @Override
    public void initData() {
        yindao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(YindaoActivity.this,MainActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(YindaoActivity.this).toBundle());
                    finish();
                } else {
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

}
