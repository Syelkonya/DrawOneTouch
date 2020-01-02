package com.example.drawonetouch;

import android.graphics.PointF;

public class Box {

    private PointF mOrigin;
    private PointF mCurrent;
    private int mColor;

    public Box(PointF origin, int color) {
        mOrigin = origin;
        mCurrent = origin;
        mColor = color;
    }

    public PointF getCurrent() {
        return mCurrent;
    }

    public void setCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getOrigin() {
        return mOrigin;
    }


    public int getColor() {
        return mColor;
    }


}
