package com.example.drawonetouch;

import android.graphics.Path;

public class PathWithColor extends Path {
    int mColor;

    public PathWithColor(int color){
        mColor = color;
    }

    public int getColor() {
        return mColor;
    }
}
