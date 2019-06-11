package com.wd.tech.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.wd.tech.fragment.CommunityFragment;
import com.wd.tech.fragment.InformationFragment;
import com.wd.tech.fragment.MessageFragment;

/**
 * @Author：Chen
 * @E-mail： 1850915912@qq.com
 * @Date：2019/3/15 11:29
 * @Description：描述信息
 */
public class FragmentAdapter extends FragmentStatePagerAdapter {

    /*
     * Fragment + ViewPager 适配器
     */

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        switch (i){
            case 0:
                fragment = new InformationFragment();
                break;
            case 1:
                fragment = new MessageFragment();
                break;
            case 2:
                fragment = new CommunityFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
