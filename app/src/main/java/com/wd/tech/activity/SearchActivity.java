package com.wd.tech.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.adapter.FindByTitleAdapter;
import com.wd.tech.app.StaticClass;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.FindByTitleBean;
import com.wd.tech.contract.Contract;
import com.wd.tech.network.NetWorkUtils;
import com.wd.tech.presenter.Presenter;
import com.wd.tech.view.MyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchActivity extends BaseActivity implements  Contract.SearchView{

     ImageView serch_id;
     EditText search_etn;
     LinearLayout ll_id;
     RecyclerView serch_recy;
     String name;
     TextView search_cancle;
     LinearLayout search_error;
     MyView myView;
     int count=1;
     Contract.PresenterInterface presenterInterface;

    @Override
    public void onNetChanged(int netWorkState) {
            switch (netWorkState) {
                case NetWorkUtils.NETWORK_NONE:
                    Toast.makeText(SearchActivity.this, "没有网络", Toast.LENGTH_SHORT).show();
                    //没有网络
                    //使用Activity切换动画 如后面需求跳转Activity请根据这里的写法进行写
                    Intent intent = new Intent(SearchActivity.this, NonetActivity.class);
                    StaticClass.NetState = 14;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SearchActivity.this).toBundle());
                    } else {
                        startActivity(intent);
                    }
                    break;
                case NetWorkUtils.NETWORK_MOBILE:
                    Toast.makeText(SearchActivity.this, "移动网络", Toast.LENGTH_SHORT).show();
                    //移动网络
                    break;
                case NetWorkUtils.NETWORK_WIFI:
                    Toast.makeText(SearchActivity.this, "WiFi网络", Toast.LENGTH_SHORT).show();
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
        return R.layout.activity_search;
    }

    @Override
    public void initView() {
        serch_id=findViewById(R.id.serch_id);
        search_etn=findViewById(R.id.search_etn);
        ll_id=findViewById(R.id.ll_id);
        serch_recy=findViewById(R.id.serch_recy);
        search_cancle=findViewById(R.id.search_cancle);
        search_error=findViewById(R.id.search_error);
        myView=findViewById(R.id.myView);
        //实例化
        presenterInterface=new Presenter<>(this);
        initData();
    }

    @Override
    public void initData() {
        serch_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = search_etn.getText().toString();
                HashMap<String,Object>  hashMap=new HashMap<>();
                hashMap.put("title", name);
                hashMap.put("page",1);
                hashMap.put("count",5);
                presenterInterface.findInformationByTitle(hashMap);
            }
        });
        //取消
        search_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count==1){
                    search_etn.setText("");
                    ll_id.setVisibility(View.VISIBLE);
                    serch_recy.setVisibility(View.GONE);
                    search_error.setVisibility(View.GONE);
                    final Button button=new Button(SearchActivity.this);
                    button.setBackgroundResource(R.drawable.radius);
                    button.setText(name);
                    button.setTextSize(16);
                    myView.addView(button,0);
                    count++;
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(SearchActivity.this,button.getText(),Toast.LENGTH_LONG).show();
                        }
                    });
                }else
                {
                    SearchActivity.this.finish();
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
    //返回搜索的内容
    @Override
    public void findInformationByTitle(Object obj) {
        FindByTitleBean bean= (FindByTitleBean) obj;
        //让热搜隐藏
        ll_id.setVisibility(View.GONE);
        serch_recy.setVisibility(View.VISIBLE);
        search_error.setVisibility(View.GONE);
        if(bean.getResult().size()==0){
            HashMap<String,Object>  hashMap1=new HashMap<>();
            hashMap1.put("source",name);
            hashMap1.put("page",1);
            hashMap1.put("count",5);
            presenterInterface.findInformationBySource(hashMap1);
        }
         LinearLayoutManager layoutManager=new LinearLayoutManager(SearchActivity.this);
         layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         serch_recy.setLayoutManager(layoutManager);
          FindByTitleAdapter adapter=new FindByTitleAdapter(this,bean.getResult());
          serch_recy.setAdapter(adapter);
    }

    @Override
    public void findInformationBySource(Object obj) {
        FindByTitleBean bean= (FindByTitleBean) obj;
        if(bean.getResult().size()==0){
           search_error.setVisibility(View.VISIBLE);
           serch_recy.setVisibility(View.GONE);
           ll_id.setVisibility(View.GONE);
        }
         LinearLayoutManager layoutManager=new LinearLayoutManager(SearchActivity.this);
         layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
         serch_recy.setLayoutManager(layoutManager);
         FindByTitleAdapter adapter=new FindByTitleAdapter(this,bean.getResult());
         serch_recy.setAdapter(adapter);
    }
}
