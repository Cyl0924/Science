package com.wd.tech.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.wd.tech.R;


import java.util.ArrayList;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class MessageFragment extends Fragment {
    private ArrayList fraglist;
    private ViewPager massagepager;
    private RadioGroup massagegroup;
    private RadioButton massagebutton;
    private RadioButton callerbutton;
    /*
     *  第二个消息页面的Fragment请根据对应的展示数据进行操作
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.fragment_message,null);
        massagepager = view.findViewById(R.id.massagepager);
        massagegroup = view.findViewById(R.id.massagegroup);
        massagebutton = view.findViewById(R.id.massagebutton);
        callerbutton = view.findViewById(R.id.callerbutton);
        fraglist = new ArrayList();
        fraglist.add(new CallerFrag());
        fraglist.add(new MassageFrag());

        massagegroup.check(massagegroup.getChildAt(0).getId());
        massagepager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return (Fragment) fraglist.get(i);
            }

            @Override
            public int getCount() {
                return fraglist.size();
            }
        });
        massagepager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                massagegroup.check(massagegroup.getChildAt(i).getId());

            }

            @Override
            public void onPageScrollStateChanged(int i) {


            }
        });

        massagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                massagepager.setCurrentItem(0);
            }
        });
        callerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                massagepager.setCurrentItem(1);
            }
        });
        return view;
    }
}
