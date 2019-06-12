package com.wd.tech.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;
import com.wd.tech.R;
import com.wd.tech.activity.SearchActivity;
import com.wd.tech.activity.WebActivity;
import com.wd.tech.adapter.RecommendAdapter;
import com.wd.tech.bean.BannerBean;
import com.wd.tech.bean.RecommendBean;
import com.wd.tech.contract.Contract;
import com.wd.tech.presenter.Presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class InformationFragment extends Fragment  implements  Contract.BannerView{

    /*
     *  第一个资讯页面的Fragment请根据对应的展示数据进行操作
     */
     XBanner ban;
     View view;
     Contract.PresenterInterface presenterInterface;
     TextView plate;
     RecyclerView recyclerView;
     ImageView search;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(getActivity(), R.layout.fragment_information,null);
        //实例化P层
        presenterInterface=new Presenter<>(this);
        initData();
        return view;
    }
    //XBanner 展示
    public  void initData() {
        ban=view.findViewById(R.id.ban);
        plate = view.findViewById(R.id.Plate);
        recyclerView = view.findViewById(R.id.recyclerView);
        search=view.findViewById(R.id.search);
        presenterInterface.bannerShow();
        //资讯推荐展示列表(包含单独板块列表展示)
        HashMap<String,Object> hashMap =new HashMap<>();
        hashMap.put("plateId",1);
        hashMap.put("page",1);
        hashMap.put("count",5);
        Log.e("tag",hashMap.toString());
        presenterInterface.RecommendList(hashMap);
        //资讯广告
        presenterInterface.Advertising();
        //搜索
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
            }
        });
    }
       //Banner的返回结果
    @Override
    public void Ban(Object obj) {
        final BannerBean bannerBean= (BannerBean) obj;
        final List<String>  list=new ArrayList<>();
        final List<String>  mlist=new ArrayList<>();
        final List<String>  xlist=new ArrayList<>();
        for (int i = 0; i < bannerBean.getResult().size() ; i++) {
            list.add(bannerBean.getResult().get(i).getImageUrl());
            mlist.add(bannerBean.getResult().get(i).getJumpUrl());
        }
        xlist.add("华为寻找AI");
        xlist.add("微信死于印度");
        xlist.add("中奖纪录");
        xlist.add("关于滴滴顺风车事件的几点思考");
        xlist.add("30个区县大PK:重庆也要东南飞");
        xlist.add("千股大跌，他财富一天缩水630亿元，你感受过这种绝望嘛");
        ban.setData(list,null);
       ban.setmAdapter(new XBanner.XBannerAdapter() {
           @Override
           public void loadBanner(XBanner banner, Object model, View view, int position) {
               Glide.with(getActivity()).load(list.get(position)).into((ImageView) view);
               plate.setText(xlist.get(position));
               ban.setAutoPalyTime(2000);
           }
       });
       ban.setOnItemClickListener(new XBanner.OnItemClickListener() {
           @Override
           public void onItemClick(XBanner banner, Object model, View view, int position) {
               Intent intent = new Intent(getActivity(), WebActivity.class);
               intent.putExtra("url", mlist.get(position));
               startActivity(intent);
           }
       });

    }
    //资讯推荐展示列表(包含单独板块列表展示)
    @Override
    public void RecommendList(Object obj) {
        RecommendBean bean= (RecommendBean) obj;
        List<RecommendBean.ResultBean> result = bean.getResult();
        Log.e("tag",result.size()+"");
        for (int i = 0; i <result.size() ; i++) {
            if (result.get(i).getWhetherAdvertising() == 1){
                Log.e("tag","sss");
            }else {
                Log.e("tag","ppp ");
            }
        }
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        RecommendAdapter adapter=new RecommendAdapter(getActivity(),bean.getResult());
        recyclerView.setAdapter(adapter);
    }
      //资讯广告的返回
    @Override
    public void Advertising(Object obj) {
//        AdvertisingBean bean= (AdvertisingBean) obj;
//        this.advertisingBean=bean;
    }

}
