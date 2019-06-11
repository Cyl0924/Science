package com.wd.tech.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wd.tech.R;

/**
 * @ProjectName: Science
 * @ClassName: CallerFrag
 * @Description: java类作用描述
 * @Author: 刘继超
 * @CreateDate: 2019/6/11 14:31:02
 */
public class CallerFrag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), R.layout.fragment_caller,null);

        return view;
    }
}
