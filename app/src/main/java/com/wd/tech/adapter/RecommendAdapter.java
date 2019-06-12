package com.wd.tech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.bean.AdvertisingBean;
import com.wd.tech.bean.RecommendBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecommendAdapter extends RecyclerView.Adapter {
    Context context;
    List<RecommendBean.ResultBean> mlist;
    int type=1;
    public RecommendAdapter(Context context, List<RecommendBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if(type==1){
            View view=View.inflate(context,R.layout.recommend_view,null);
            return new Holder_1(view);
        }else
        {
            View view=View.inflate(context,R.layout.recommend_view_2,null);
            return new Holder_2(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if(type==1){
            ((Holder_1)viewHolder).recommend_img.setImageURI(mlist.get(i).getThumbnail());
            ((Holder_1)viewHolder).recommend_source.setText(mlist.get(i).getSource());
            ((Holder_1)viewHolder).recommend_summary.setText(mlist.get(i).getSummary());
            ((Holder_1)viewHolder).recommend_title.setText(mlist.get(i).getTitle());
            long time = mlist.get(i).getReleaseTime();
            Date date=new Date(time);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            ((Holder_1)viewHolder).recommend_time.setText(format.format(date));
        }else
        {
//             ((Holder_2)viewHolder).Advertising_tv.setText(result.getTitle());
//             ((Holder_2)viewHolder).Advertising_iv.setImageURI(result.getUrl());
        }
    }

    @Override
    public int getItemViewType(int position) {
        int i = mlist.get(position).getWhetherAdvertising();
        if(i==2){
            type=1;
            return type;
        }else
        {
            type=2;
            return type;
        }
    }

    public  class Holder_1 extends  RecyclerView.ViewHolder{
          @BindView(R.id.recommend_source)
          TextView recommend_source;
          @BindView(R.id.recommend_summary)
          TextView recommend_summary;
          @BindView(R.id.recommend_title)
          TextView recommend_title;
          @BindView(R.id.recommend_time)
          TextView recommend_time;
          @BindView(R.id.recommend_img)
          SimpleDraweeView recommend_img;
        public Holder_1(@NonNull View itemView) {
            super(itemView);
            if(type==1){
                ButterKnife.bind(this,itemView);
            }

        }
    }
    public  class Holder_2 extends  RecyclerView.ViewHolder{
        @BindView(R.id.Advertising_iv)
        SimpleDraweeView Advertising_iv;
        @BindView(R.id.Advertising_tv)
        TextView Advertising_tv;
        public Holder_2(@NonNull View itemView) {
            super(itemView);
            if(type==2){
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
