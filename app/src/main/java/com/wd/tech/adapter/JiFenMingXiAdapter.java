package com.wd.tech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.bean.JifenBeanMingXi;

import java.util.List;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class JiFenMingXiAdapter extends XRecyclerView.Adapter<JiFenMingXiAdapter.holder> {

    List<JifenBeanMingXi.ResultBean> list;
    Context context;

    public JiFenMingXiAdapter(List<JifenBeanMingXi.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.jifen_layout,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder extends XRecyclerView.ViewHolder{

        public holder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
