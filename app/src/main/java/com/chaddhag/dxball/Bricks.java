package com.chaddhag.dxball;

import android.graphics.Paint;

class Bricks {
    float top, bottom, left, right;
    Paint paint;
    int color;


    Bricks(float left, float top, float right, float bottom, int color) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.color = color;
        paint = new Paint();
        paint.setColor(color);
    }

    public float getLeft() {
        return left;
    }

    public void setLeft(float left) {
        this.left = left;
    }

    public float getRight() {
        return right;
    }

    public void setRight(float right) {
        this.right = right;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }

    public Paint getPaint() {
        return paint;
    }

    public float getTop() {
        return top;
    }

    public void setTop(float top) {
        this.top = top;
    }


}
