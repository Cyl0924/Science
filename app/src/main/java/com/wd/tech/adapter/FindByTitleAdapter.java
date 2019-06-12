package com.wd.tech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.bean.FindByTitleBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FindByTitleAdapter extends RecyclerView.Adapter<FindByTitleAdapter.Holder> {
    Context context;
    List<FindByTitleBean.ResultBean> mlist;
    public FindByTitleAdapter(Context context, List<FindByTitleBean.ResultBean> list) {
        this.context=context;
        this.mlist=list;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.findbytitle_view, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {
        holder.title_name.setText(mlist.get(i).getTitle());
        holder.title_id.setText(mlist.get(i).getSource());
        long time = mlist.get(i).getReleaseTime();
        Date date = new Date(time);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        holder.title_time.setText(sd.format(date));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public  class Holder extends  RecyclerView.ViewHolder{
        @BindView(R.id.title_name)
        TextView title_name;
        @BindView(R.id.title_id)
        TextView title_id;
        @BindView(R.id.title_time)
        TextView title_time;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
