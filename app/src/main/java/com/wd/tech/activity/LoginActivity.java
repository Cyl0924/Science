package com.wd.tech.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.contract.Contract;
import com.wd.tech.network.NetWorkUtils;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.util.RsaCoder;

import java.util.HashMap;

public class LoginActivity extends BaseActivity implements Contract.LoginView{

    Contract.PresenterInterface presenterInterface;

    private EditText LoginTvPhone;
    private EditText LoginTvPwd;
    private ImageView LoginShowHide;
    private TextView ToRegister;
    private Button ToLoginBtn;
    private ImageView ToWechatID;
    private ImageView ToFaceID;

    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case  NetWorkUtils.NETWORK_NONE:
                Toast.makeText(LoginActivity.this,"没有网络",Toast.LENGTH_SHORT).show();
                //没有网络
                //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                Intent intent = new Intent(LoginActivity.this,NonetActivity.class);
                StaticClass.NetState = 2;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case  NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(LoginActivity.this,"移动网络",Toast.LENGTH_SHORT).show();
                //移动网络
                //JumpMain();
                break;
            case  NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(LoginActivity.this,"WiFi网络",Toast.LENGTH_SHORT).show();
                //WiFi网络
                //JumpMain();
                break;
                //uuuuuuuuuuuuuuuuu
        }
    }

    public void JumpMain(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
            finish();
        } else {
            startActivity(intent);
            finish();
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
        return R.layout.activity_login;
    }

    @Override
    public void initView() {

        presenterInterface = new Presenter<>(this);

        LoginTvPhone = findViewById(R.id.LoginTvPhone);
        LoginTvPwd = findViewById(R.id.LoginTvPwd);
        LoginShowHide = findViewById(R.id.LoginShowHide);
        ToRegister = findViewById(R.id.ToRegister);
        ToLoginBtn = findViewById(R.id.ToLoginBtn);

        ToWechatID = findViewById(R.id.ToWechatID);
        ToFaceID = findViewById(R.id.ToFaceID);
    }

    @Override
    public void initData() {
        //跳转注册页面
        ToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        //实现登录接口
        ToLoginBtn.setOnClickListener(new View.OnClickListener() {
            private String phone;
            private String pwd;
            HashMap<String,Object> map = new HashMap<>();
            @Override
            public void onClick(View view) {
                phone = LoginTvPhone.getText().toString().trim();
                   try {
                       pwd = RsaCoder.encryptByPublicKey(LoginTvPwd.getText().toString().trim());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                map.put("phone",phone);
                map.put("pwd",pwd);
                presenterInterface.LoginPresenter(map);
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
    public void LoginView(LoginBean loginBean) {
        if (loginBean.getMessage().equals("登录成功")){
            StaticClass.userId = loginBean.getResult().getUserId();
            StaticClass.sessionId = loginBean.getResult().getSessionId();
            Toast.makeText(this,loginBean.getMessage(),Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this).toBundle());
            } else {
                startActivity(intent);
            }
        }else{
            Toast.makeText(this,loginBean.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

}
