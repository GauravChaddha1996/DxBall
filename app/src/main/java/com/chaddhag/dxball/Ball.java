package com.chaddhag.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

class Ball {
    private boolean ballAvailable = true;
    private float x, y, radius = 20;
    private float xStep = 15;
    private float yStep = -15;

    public void setBall(Canvas canvas, Bar bar) {
        float barMid = (bar.getBarRight() - bar.getBarLeft()) / 2;
        x = bar.getBarLeft() + barMid;
        y = bar.getBarTop() - radius;
        xStep = 15;
        yStep = -15;
    }

    public void drawBall(Canvas canvas, Paint paint) {
        paint.setColor(Color.rgb(213, 0, 0));
        canvas.drawCircle(x, y, radius, paint);
    }

    public void nextPos(Canvas canvas, Bar bar, Paint paint) {
        if (x + radius >= canvas.getWidth() || (x + radius == bar.getBarLeft() && y >= bar.getBarTop() && y <= bar.getBarBottom()) && xStep > 0) {
            xStep = -xStep;
            // When you touch the right wall or the bar edge
        } else if (y <= radius) {
            // When you touch the top wall
            yStep = -yStep;
        } else if (x <= radius) {
            // When you touch the left wall
            xStep = -xStep;
        } else if (y + radius > bar.getBarTop() && x > bar.getBarLeft() && x < bar.getBarRight()) {
            yStep = -yStep;
            // When you touch the bar
        } else if (y > bar.getBarBottom() && y <= canvas.getHeight()) {
            xStep = 0;
            yStep = 0;
            ballAvailable = false;
            // ball is lost on the down wall
        } else if (x + radius == bar.getBarLeft() - 20 && y >= bar.getBarTop()) {
            xStep = -xStep;
            // when you touch bar edges
        } else if (x + radius == bar.getBarRight() + 20 && y >= bar.getBarTop()) {
            xStep = -xStep;
            // when you touch bar edges
        }

        x += xStep;
        y += yStep;
        Log.d("tag", "x: " + x + " y: " + y);
    }

    public void ballBrickCollision(ArrayList<Bricks> br, Ball ball) {
        for (int i = 0; i < br.size(); i++) {
            if (((ball.getY() - ball.getRadius()) <= br.get(i).getBottom()) && ((ball.getY() + ball.getRadius()) >= br.get(i).getTop()) && ((ball.getX()) >= br.get(i).getLeft()) && ((ball.getX()) <= br.get(i).getRight())) {
                br.remove(i);
                // for collision of ball and brick from top and bottom
                GameCanvas.score += 10;
                ball.setyStep(-(ball.getyStep()));
            } else if (((ball.getY()) <= br.get(i).getBottom()) && ((ball.getY()) >= br.get(i).getTop()) && ((ball.getX() + ball.getRadius()) >= br.get(i).getLeft()) && ((ball.getX() - ball.getRadius()) <= br.get(i).getRight())) {
                br.remove(i);
                GameCanvas.score += 10;
                // for collision of ball and brick from left and right
                ball.setxStep(-(ball.getxStep()));
            }
        }
    }

    /* Getters and setters */

    public boolean isballAvailable() {
        return ballAvailable;
    }

    public void setBallAvailable(boolean _ballAvailable) {
        this.ballAvailable = _ballAvailable;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getRadius() {
        return radius;
    }

    public float getxStep() {
        return xStep;
    }

    public float getyStep() {
        return yStep;
    }

    public void setxStep(float xStep) {
        this.xStep = xStep;
    }

    public void setyStep(float yStep) {
        this.yStep = yStep;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }


}