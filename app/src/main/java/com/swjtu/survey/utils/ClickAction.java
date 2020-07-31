package com.swjtu.survey.utils;

import android.view.View;

import androidx.annotation.IdRes;


/**
 * 批量点击事件设置，不使用butterknife之类的库，防止过多编译时期注解
 */
public interface ClickAction extends View.OnClickListener {

    <V extends View> V findViewById(@IdRes int id);

    @Override
    default void onClick(View v) {

    }

    default void setOnClickListener(@IdRes int... ids) {
        for (int id : ids) {
            findViewById(id).setOnClickListener(this);
        }
    }
}