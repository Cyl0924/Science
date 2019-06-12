package com.wd.tech.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.UserMessage;
import com.wd.tech.contract.Contract;
import com.wd.tech.network.NetWorkUtils;
import com.wd.tech.presenter.Presenter;


public class SettingActivity extends BaseActivity implements Contract.ObjectView {

    Contract.PresenterInterface presenterInterface;

    int FACEID = 1;

    private ImageView SettingBack;
    private SimpleDraweeView SettingSim;
    private TextView SettingName;
    private TextView SettingSex;
    private ImageView SettingQianming;
    private TextView SettingBirthday;
    private TextView SettingPhone;
    private TextView SettingEmail;
    private TextView SettingJifen;
    private TextView SettingVIP;
    private TextView SettingFaceID;
    private TextView SettingOut;

    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case NetWorkUtils.NETWORK_NONE:
                Toast.makeText(SettingActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                //没有网络
                //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                Intent intent = new Intent(SettingActivity.this, NonetActivity.class);
                StaticClass.NetState = 11;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SettingActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(SettingActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                //移动网络
                break;
            case NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(SettingActivity.this, "WiFi网络", Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_setting;
    }

    @Override
    public void initView() {

        //Log.e("tag",StaticClass.userId+"------"+StaticClass.sessionId);

        presenterInterface = new Presenter<>(this);

        SettingBack =  findViewById(R.id.SettingBack);
        SettingSim =  findViewById(R.id.SettingSim);
        SettingName =  findViewById(R.id.SettingName);
        SettingSex =  findViewById(R.id.SettingSex);
        SettingQianming =  findViewById(R.id.SettingQianming);
        SettingBirthday = findViewById(R.id.SettingBirthday);
        SettingPhone =  findViewById(R.id.SettingPhone);
        SettingEmail =  findViewById(R.id.SettingEmail);
        SettingJifen = findViewById(R.id.SettingJifen);
        SettingVIP =  findViewById(R.id.SettingVIP);
        SettingFaceID =  findViewById(R.id.SettingFaceID);
        SettingOut =  findViewById(R.id.SettingOut);

    }

    @Override
    public void initData() {

        presenterInterface.getStringPresenter();

        SettingOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StaticClass.userId = 0;
                StaticClass.sessionId = null;
                Intent intent = new Intent(SettingActivity.this,MainActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SettingActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        /*SettingQianming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this,SIgnatureActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SettingActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });*/

        SettingBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this,MainActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SettingActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        SettingFaceID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (FACEID == 1){
                    Toast.makeText(SettingActivity.this,"已绑定FaceID!",Toast.LENGTH_SHORT).show();
                }else{
                 /*   Intent intent = new Intent(SettingActivity.this, PreviewActivity.class);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SettingActivity.this).toBundle());
                    } else {
                        startActivity(intent);
                    }*/
                }
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_fade() {
        Transition transition = new Fade().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    @Override
    public void returnObject(Object obj) {
        UserMessage userMessage = (UserMessage) obj;
        SettingSim.setImageURI(userMessage.getResult().getHeadPic());
        SettingName.setText(userMessage.getResult().getNickName());
        if (userMessage.getResult().getSex() == 1){
            SettingSex.setText("男");
        }else{
            SettingSex.setText("女");
        }
        SettingBirthday.setText("1997-10-10");
        SettingPhone.setText(userMessage.getResult().getPhone());
        SettingEmail.setText(userMessage.getResult().getEmail());
        SettingJifen.setText(userMessage.getResult().getIntegral()+"");
        //Log.e("tag",userMessage.getResult().getWhetherVip()+"");
        if (userMessage.getResult().getWhetherVip() == 1){
            SettingVIP.setText("是");
        }else{
            SettingVIP.setText("否");
        }
        if (userMessage.getResult().getWhetherFaceId() == 1){
            SettingFaceID.setText("已绑定");
            FACEID = 1;
        }else{
            SettingFaceID.setText("点击绑定");
            FACEID = 2;
        }
    }

 /*   public static String longToDate(long lo){

        Date date = new Date(lo);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return sd.format(date);

    }*/

}
