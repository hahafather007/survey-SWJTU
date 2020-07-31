package com.swjtu.survey.widget;

import android.content.Context;
import android.util.AttributeSet;

import org.cocos2dx.lib.Cocos2dxGLSurfaceView;

public class CocosSurfaceView extends Cocos2dxGLSurfaceView {
    public CocosSurfaceView(Context context) {
        super(context,null);
    }

    public CocosSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setZOrderOnTop(boolean onTop) {
        super.setZOrderOnTop(false);
    }
}
