package com.wd.tech.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MyView extends ViewGroup {
    int mLeftMargin=20;
    int mTopMargin=20;
    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int leftMargin=mLeftMargin;
        int topMargin=mTopMargin;
        for (int i = 0; i < getChildCount(); i++) {
            int measuredHeight = getChildAt(i).getMeasuredHeight();
            int measuredWidth = getChildAt(i).getMeasuredWidth();
            if(leftMargin+mLeftMargin+measuredWidth>getWidth()){
                leftMargin=mLeftMargin;
                topMargin+=mTopMargin+measuredHeight;
                getChildAt(i).layout(leftMargin,topMargin,leftMargin+measuredWidth,topMargin+measuredHeight);
            }else
            {
                getChildAt(i).layout(leftMargin,topMargin,leftMargin+measuredWidth,topMargin+measuredHeight);
            }
            leftMargin+=mLeftMargin+measuredWidth;

        }


    }
}
