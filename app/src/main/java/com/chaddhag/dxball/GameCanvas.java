package com.chaddhag.dxball;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

class GameCanvas extends View {
    int state = 0;
    int level;
    public static int life = 2;
    public static boolean gameOver;
    float brickX = 0, brickY = 0;
    static int score = 0;
    Canvas canvas;
    boolean createGame;
    Paint paint;
    Bar bar;
    Ball ball;
    float touchPoint;
    boolean gameStart = true;
    ArrayList<Bricks> bricks = new ArrayList<Bricks>();
    Stage stage = new Stage();

    public GameCanvas(Context context) {
        super(context);
        paint = new Paint();
        level = 1;
        createGame = true;
        gameOver = false;
        bar = new Bar();
        ball = new Ball();

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int _action = MotionEventCompat.getActionMasked(event);
                if (life == 0) {
                    paint = new Paint();
                    state = 0;
                    level = 1;
                    score = 0;
                    brickX = brickY = 0;
                    createGame = true;
                    gameOver = false;
                    gameStart = true;
                    life = 2;
                    bar = new Bar();
                    ball = new Ball();
                    bricks = new ArrayList<Bricks>();
                    return true;
                }
                if (ball.isballAvailable()) {
                    touchPoint = event.getX();
                    if (touchPoint < v.getWidth() / 2 && bar.getBarLeft() - 20 > 0) {
                        bar.setBarLeft(bar.getBarLeft() - 10);
                        if (state < 1) {
                            ball.setX(ball.getX() - 10);
                        }
                    } else if (touchPoint >= v.getWidth() / 2 && bar.getBarRight() + 20 < v.getWidth()) {
                        bar.setBarLeft(bar.getBarLeft() + 10);
                        if (state < 1) {
                            ball.setX(ball.getX() + 10);
                        }
                    }
                }

                if (_action == MotionEvent.ACTION_UP) {
                    if (state < 2) {
                        state += 1;
                    }
                }
                return true;
            }
        });
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (gameStart) {
            gameStart = false;
            bar.setBar(canvas);
            ball.setBall(canvas, bar);
        }

        /* Background change here */
        canvas.drawRGB(255, 255, 255);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        ball.drawBall(canvas, paint);
        bar.drawBar(canvas, paint);

        if (state == 2) {
            ball.nextPos(canvas, bar, paint);
        }
        if (state <= 1) {
            paint.setColor(Color.BLACK);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("Move Bar And Tap to Start", canvas.getWidth() / 2 - canvas.getWidth() / 4,
                    canvas.getHeight() / 2 + canvas.getHeight() / 8, paint);
        }

        this.canvas = canvas;

        brickX = canvas.getWidth() / 7;
        brickY = (canvas.getHeight() / 15);

        if (createGame) {
            createGame = false;
            if (level == 1) {
                stage.stage_level_one(canvas, brickX, brickY, bricks);
            } else if (level == 2) {
                stage.stage_level_two(canvas, brickX, brickY, bricks);
            } else
                gameOver = true;
        }

        for (int i = 0; i < bricks.size(); i++) {
            canvas.drawRect(bricks.get(i).getLeft(), bricks.get(i).getTop(), bricks.get(i).getRight(), bricks.get(i).getBottom(), bricks.get(i).getPaint());
        }

        ball.ballBrickCollision(bricks, ball);

        if (bricks.size() == 0) {
            state = -1;
            level += 1;
            createGame = true;
            gameStart = true;
        }

        if (state == -1) {

            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(50);
            paint.setFakeBoldText(true);
            canvas.drawText("Level 2", canvas.getWidth() / 2 - canvas.getWidth() / 9, canvas.getHeight() / 2, paint);
            canvas.drawText("Your Score: " + score, canvas.getWidth() / 2 - canvas.getWidth() / 5, canvas.getHeight() / 2 + 134, paint);
            canvas.drawText("Tap To Next Level", canvas.getWidth() / 2 - canvas.getWidth() / 3 + 50, canvas.getHeight() / 2 + 300, paint);
        }

        if (ball.isballAvailable() == false) {
            ball.setBallAvailable(true);
            state = 0;
            gameStart = true;
            life -= 1;
        }

        paint.setTextSize(45);
        paint.setFakeBoldText(true);
        canvas.drawText("Life: " + life, 20, 75, paint);
        canvas.drawText("Score: " + score, canvas.getWidth() - 250, 75, paint);
        canvas.drawText("LEVEL " + level, canvas.getWidth() / 2 - 60, 75, paint);

        if (life == 0 || gameOver) {
            paint.setColor(Color.BLACK);
            canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), paint);
            paint.setColor(Color.WHITE);
            paint.setTextSize(80);
            paint.setFakeBoldText(true);
            canvas.drawText("Your Score: " + score, canvas.getWidth() / 2 - canvas.getWidth() / 3, canvas.getHeight() / 2 + 134, paint);
            gameOver = false;
            level = 0;
            life = 0;
        }

        invalidate();
    }


}