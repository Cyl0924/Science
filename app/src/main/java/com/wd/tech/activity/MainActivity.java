package com.wd.tech.activity;


import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.adapter.FragmentAdapter;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.UserMessage;
import com.wd.tech.contract.Contract;
import com.wd.tech.network.NetWorkUtils;
import com.wd.tech.presenter.Presenter;

public class MainActivity extends BaseActivity implements View.OnClickListener ,Contract.ObjectView {

    Contract.PresenterInterface presenterInterface;

    private long exitTime = 0;
    private DrawerLayout drawerLayout;
    private NavigationView left;
    private CoordinatorLayout right;
    private TextView lorR;
    private RadioGroup mainRg;
    private ViewPager mainVp;
    private RadioButton one;
    private RadioButton two;
    private RadioButton three;
    private LinearLayout show;
    private LinearLayout gone;
    //侧滑页面所有ID
    private SimpleDraweeView CeSim;
    private TextView CeName;
    private TextView CeQianming;
    private ImageView CeHuiYuan;
    private LinearLayout CeQian;
    private LinearLayout CeCollect;
    private LinearLayout CeAttention;
    private LinearLayout CeCard;
    private LinearLayout CeNotice;
    private LinearLayout CeJifen;
    private LinearLayout CeTask;
    private LinearLayout CeSetting;
    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case  NetWorkUtils.NETWORK_NONE:
                    Toast.makeText(MainActivity.this,"没有网络",Toast.LENGTH_SHORT).show();
                    //没有网络
                    //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                    Intent intent = new Intent(MainActivity.this,NonetActivity.class);
                    StaticClass.NetState = 1;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                    } else {
                        startActivity(intent);
                    }
                break;
            case  NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(MainActivity.this,"移动网络",Toast.LENGTH_SHORT).show();
                //移动网络
                break;
            case  NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(MainActivity.this,"WiFi网络",Toast.LENGTH_SHORT).show();
                //WiFi网络
                break;
        }
    }



    @Override
    public int intiLayout() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 设置contentFeature,可使用切换动画
            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
              //init_explode();// 分解
            //  init_Slide();//滑动进入
              init_fade();//淡入淡出
        }
        return R.layout.activity_main;
    }

    @Override
    public void initView() {

        presenterInterface = new Presenter<>(this);

        /*
         * drawerLayout：获取布局页面抽屉布局的id
         * left:获取布局页面侧滑页面的id
         * right：获取主页布局
         * lorR:登录注册按钮ID
         * mainRg：RadioGroup 控件ID
         * mainVp：ViewPager 控件ID
         * one two three  都是RadioButton 控件ID
         */
        drawerLayout = findViewById(R.id.DrawerID);
        left = findViewById(R.id.NavID);
        right = findViewById(R.id.CoorID);
        lorR = findViewById(R.id.LoRTvID);
        mainRg = findViewById(R.id.MainRgID);
        mainVp = findViewById(R.id.MainVpID);
        one = findViewById(R.id.MainRbIDone);
        two = findViewById(R.id.MainRbIDtwo);
        three = findViewById(R.id.MainRbIDthree);
        show = findViewById(R.id.CeShowID);
        gone = findViewById(R.id.CeGoneID);


        CeSim =  findViewById(R.id.CeSim);
        CeName =  findViewById(R.id.CeName);
        CeQianming =  findViewById(R.id.CeQianming);
        CeHuiYuan =  findViewById(R.id.CeHuiYuan);
        CeQian =  findViewById(R.id.CeQian);

        CeCollect =  findViewById(R.id.CeCollect);
        CeAttention =  findViewById(R.id.CeAttention);
        CeCard =  findViewById(R.id.CeCard);
        CeNotice = findViewById(R.id.CeNotice);
        CeJifen =  findViewById(R.id.CeJifen);
        CeTask =  findViewById(R.id.CeTask);
        CeSetting =  findViewById(R.id.CeSetting);

        CeCollect.setOnClickListener(this);
        CeAttention.setOnClickListener(this);
        CeCard.setOnClickListener(this);
        CeNotice.setOnClickListener(this);
        CeJifen.setOnClickListener(this);
        CeTask.setOnClickListener(this);
        CeSetting.setOnClickListener(this);

    }

    //重启Activiy判断是否登录成功
    @Override
    protected void onRestart() {
        super.onRestart();
        if(StaticClass.userId == 0 && StaticClass.sessionId == null){
            show.setVisibility(View.VISIBLE);
            gone.setVisibility(View.GONE);
        }else{
            presenterInterface.getStringPresenter();
            show.setVisibility(View.GONE);
            gone.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void initData() {

        //关联RadioGroup和ViewPager
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager());
        mainVp.setAdapter(adapter);

        /*
        * 实现侧滑效果
        * 侧栏滑动主页内容跟随滑动
        * */
        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                right.layout(left.getRight(), 0, left.getRight() + display.getWidth(), display.getHeight());
            }
            @Override
            public void onDrawerOpened(View drawerView) {

            }
            @Override
            public void onDrawerClosed(View drawerView) {

            }
            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        //登录注册点击事件
        lorR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
            }
        });

        // RadioGroup 和 RadioButton 点击事件
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainVp.setCurrentItem(0);
            }
        });
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainVp.setCurrentItem(1);
            }
        });
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainVp.setCurrentItem(2);
            }
        });
    }


    /*
     *监听返回键点击事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
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
            Toast.makeText(MainActivity.this, "再按一次退出程序",Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    /*
     * 实现切换淡入淡出的效果动画
     * 具体三种动画请到NonetActivity具体查看Q
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_fade() {
        Transition transition = new Fade().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    //侧滑页点击事件跳转
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.CeCollect:
                Jump(CollectActivity.class);
                break;
            case R.id.CeAttention:
                Jump(AttentionActivity.class);
                break;
            case R.id.CeCard:
                Jump(CardActivity.class);
                break;
            case R.id.CeNotice:
                Jump(NoticeActivity.class);
                break;
            case R.id.CeJifen:
                Jump(JifenActivity.class);
                break;
            case R.id.CeTask:
                Jump(TaskActivity.class);
                break;
            case R.id.CeSetting:
                Jump(SettingActivity.class);
                break;
        }
    }

    //返回个人信息详情
    @Override
    public void returnObject(Object obj) {
        UserMessage userMessage = (UserMessage) obj;
        CeSim.setImageURI(userMessage.getResult().getHeadPic());
        CeName.setText(userMessage.getResult().getNickName());
        CeQianming.setText(userMessage.getResult().getSignature());
    }

    //跳转方法封装抽取
    public void Jump(Class<?> pClass){
        Intent intent = new Intent(this,pClass);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(MainActivity.this).toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenterInterface.Destory();
        presenterInterface = null;
    }
}
