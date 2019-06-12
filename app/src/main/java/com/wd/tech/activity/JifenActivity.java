package com.wd.tech.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.Fade;
import android.transition.Transition;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.JifenBean;
import com.wd.tech.bean.JifenBeanMingXi;
import com.wd.tech.contract.Contract;
import com.wd.tech.network.NetWorkUtils;
import com.wd.tech.presenter.Presenter;

import java.util.HashMap;

public class JifenActivity extends BaseActivity implements Contract.UserJifenView ,Contract.UsrtJifenMingXi{

    Contract.PresenterInterface presenterInterface;

    private TextView JiFenSum;
    private TextView JiFenDay;
    private XRecyclerView JifenRec;

    @Override
    public void onNetChanged(int netWorkState) {
        switch (netWorkState) {
            case NetWorkUtils.NETWORK_NONE:
                Toast.makeText(JifenActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                //没有网络
                //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                Intent intent = new Intent(JifenActivity.this, NonetActivity.class);
                StaticClass.NetState = 9;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(JifenActivity.this).toBundle());
                } else {
                    startActivity(intent);
                }
                break;
            case NetWorkUtils.NETWORK_MOBILE:
                Toast.makeText(JifenActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                //移动网络
                break;
            case NetWorkUtils.NETWORK_WIFI:
                Toast.makeText(JifenActivity.this, "WiFi网络", Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_jifen;
    }

    @Override
    public void initView() {
        presenterInterface = new Presenter<>(this);
        JiFenSum =  findViewById(R.id.JiFenSum);
        JiFenDay =  findViewById(R.id.JiFenDay);
        JifenRec =  findViewById(R.id.JifenRec);

    }

    @Override
    public void initData() {
        presenterInterface.getStringUrl(StaticClass.UserJIFEN);
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("page",1);
        hashMap.put("count",10);
        presenterInterface.getStringUrls(StaticClass.UserJIFENMINGXI,hashMap);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init_fade() {
        Transition transition = new Fade().setDuration(200);
        getWindow().setEnterTransition(transition);
        getWindow().setExitTransition(transition);
    }

    @Override
    public void returnUserJifen(JifenBean jifenBean) {
        if (jifenBean.getResult().getAmount() == 0){
            JiFenSum.setText("您还没有积分,快去做任务吧!");
            JiFenSum.setTextSize(20);
        }else{
            JiFenSum.setText(jifenBean.getResult().getAmount()+"");
        }
    }


    @Override
    public void returnMingXi(JifenBeanMingXi jifenBeanMingXi) {

    }

}
