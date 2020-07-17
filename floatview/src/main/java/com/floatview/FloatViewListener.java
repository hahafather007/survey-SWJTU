package com.floatview;

public abstract class FloatViewListener {

    public abstract void onClose();

    public abstract void onClick();

    //新增双击事件，非必须实现
    public void onDoubleClick() {
    }

    public void onMoved() {
    }

    public void onDragged() {
    }

}