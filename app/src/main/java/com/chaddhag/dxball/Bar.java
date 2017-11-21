package com.chaddhag.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class Bar {
    private float barLeft, barTop, barRight, barBottom;

    public void setBar(Canvas canvas) {
        barLeft = (canvas.getWidth() / 2) - (canvas.getWidth() / 6);
        barRight = barLeft + (canvas.getWidth() / 3);
        barBottom = canvas.getHeight() - 30;
        barTop = barBottom - 40;
    }

    public void drawBar(Canvas canvas, Paint paint) {
        paint.setColor(Color.rgb(103, 58, 183));
        barRight = barLeft + (canvas.getWidth() / 3);
        canvas.drawRect(barLeft, barTop, barRight, barBottom, paint);
        canvas.drawCircle(barLeft - 4, barBottom - 20, 20, paint);
        canvas.drawCircle(barRight + 4, barBottom - 20, 20, paint);
    }

    /* Getters and setters */

    public float getBarLeft() {
        return barLeft;
    }

    public float getBarBottom() {
        return barBottom;
    }

    public float getBarRight() {
        return barRight;
    }

    public float getBarTop() {
        return barTop;
    }

    public void setBarBottom(float barBottom) {
        this.barBottom = barBottom;
    }

    public void setBarTop(float barTop) {
        this.barTop = barTop;
    }

    public void setBarLeft(float barLeft) {
        this.barLeft = barLeft;
    }

    public void setBarRight(float barRight) {
        this.barRight = barRight;
    }
}
