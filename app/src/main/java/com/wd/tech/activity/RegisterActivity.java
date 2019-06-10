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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.wd.tech.R;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.MessageBean;
import com.wd.tech.contract.Contract;
import com.wd.tech.network.NetWorkUtils;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.Base64;
import com.wd.tech.util.RsaCoder;

import java.util.HashMap;

public class RegisterActivity extends BaseActivity implements Contract.ViewInterface {

    Contract.PresenterInterface presenterInterface;
    private EditText RegisterName;
    private EditText RegisterNum;
    private EditText RegisterPwd;
    private Button RegisterBtn;
    private String pwd;

    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case  NetWorkUtils.NETWORK_NONE:
                Toast.makeText(RegisterActivity.this,"没有网络",Toast.LENGTH_SHORT).show();
                //没有网络
                //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                StaticClass.NetState = 3;
                Intent intent = new Intent(RegisterActivity.this,NonetActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case  NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(RegisterActivity.this,"移动网络",Toast.LENGTH_SHORT).show();
                //移动网络
                //JumpMain();
                break;
            case  NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(RegisterActivity.this,"WiFi网络",Toast.LENGTH_SHORT).show();
                //WiFi网络
                //JumpMain();
                break;
        }
    }

    @Override
    public int intiLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置contentFeature,可使用切换动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
            //init_explode();// 分解
            //init_Slide();//滑动进入
            init_fade();//淡入淡出
        }
        return R.layout.activity_register;
    }

    @Override
    public void initView() {
        //实例化Presenter层接口
        presenterInterface = new Presenter<>(this);
        RegisterName =  findViewById(R.id.RegisterName);
        RegisterNum =  findViewById(R.id.RegisterNum);
        RegisterPwd =  findViewById(R.id.RegisterPwd);
        RegisterBtn =  findViewById(R.id.RegisterBtn);

    }

    @Override
    public void initData() {
        final HashMap<String,Object> map = new HashMap<>();
        RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = RegisterName.getText().toString().trim();
                String num = RegisterNum.getText().toString().trim();
                try {
                    pwd = RsaCoder.encryptByPublicKey(RegisterPwd.getText().toString().trim());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                map.put("phone",num);
                map.put("nickName",name);
                map.put("pwd",pwd);
                presenterInterface.RegisterPresenter(map);
            }
        });
    }

    public void JumpMain(){
        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
            finish();
        } else {
            startActivity(intent);
            finish();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_fade() {
        Transition transition = new Fade().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    @Override
    public void RegisterView(MessageBean messageBean) {
        if (messageBean.getMessage().equals("注册成功")){
            Toast.makeText(this,messageBean.getMessage(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(RegisterActivity.this).toBundle());
            } else {
                startActivity(intent);
            }
        }else{
            Toast.makeText(this,messageBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterInterface.Destory();
        presenterInterface = null;
    }
}
