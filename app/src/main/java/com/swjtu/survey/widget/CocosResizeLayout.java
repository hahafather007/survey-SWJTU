package com.swjtu.survey.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;

import org.cocos2dx.lib.ResizeLayout;

public class CocosResizeLayout extends ResizeLayout {
    public CocosResizeLayout(Context context) {
        this(context,null);
    }

    public CocosResizeLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child) {
        if(child instanceof SurfaceView){
            ((SurfaceView) child).setZOrderOnTop(false);
        }
        super.addView(child);
    }
}
