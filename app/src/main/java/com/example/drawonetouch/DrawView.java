package com.example.drawonetouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_MOVE;

public class DrawView extends View {

    private static final float STROKE_WIDTH = 10f;

    private Paint mPaint = new Paint();
    private Path mPath = new Path();
    private Paint mBackgroundPaint = new Paint();
    private Paint mBoxPaint = new Paint();
    private Paint mLinePaint = new Paint();


    protected boolean buttonCurveWasTouched = true;
    protected boolean buttonRectangleWasTouched = false;
    protected boolean buttonStraightWasTouched = false;

    private Box mCurrentBox;
    private List<Box> mBoxes = new ArrayList<>();

    private Box mCurrentLine;
    private List<Box> mLines = new ArrayList<>();


    public DrawView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setUpPaint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);

        if (buttonCurveWasTouched) {
            canvas.drawPath(mPath, mPaint);

        }
        else if (buttonRectangleWasTouched){
            for (Box box : mBoxes) {
                float left = Math.min(box.getOrigin().x, box.getCurrent().x);
                float right = Math.max(box.getOrigin().x, box.getCurrent().x);
                float top = Math.min(box.getOrigin().y, box.getCurrent().y);
                float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);
                canvas.drawRect(left, top, right, bottom, mBoxPaint);
            }
        }else if (buttonStraightWasTouched){
            for (Box box : mLines){
                canvas.drawLine(box.getOrigin().x, box.getOrigin().y,
                        box.getCurrent().x, box.getCurrent().y, mLinePaint);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (buttonCurveWasTouched) {
            float eventX = event.getX();
            float eventY = event.getY();

            switch (event.getAction()) {
                case ACTION_DOWN:
                    mPath.moveTo(eventX, eventY);
                    return true;
                case ACTION_MOVE:
                    mPath.lineTo(eventX, eventY);
                    break;
                default:
                    return false;
            }

            invalidate();
            return true;
        }else if (buttonRectangleWasTouched) {
            PointF current = new PointF(event.getX(), event.getY());

            int action = event.getAction();
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                    mCurrentBox = new Box(current);
                    mBoxes.add(mCurrentBox);
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (mCurrentBox != null) {
                        mCurrentBox.setCurrent(current);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mCurrentBox = null;
                    break;
            }
            return true;
        }else if(buttonStraightWasTouched) {
            PointF current = new PointF(event.getX(), event.getY());

            int action = event.getAction();
            switch (action){
                case MotionEvent.ACTION_DOWN:
                mCurrentLine = new Box(current);
                mLines.add(mCurrentLine);
                break;
                case MotionEvent.ACTION_MOVE:
                    if (mCurrentLine != null){
                        mCurrentLine.setCurrent(current);
                        invalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    mCurrentLine = null;
                    break;
            }
            return true;
        }

        return true;
    }

    public void reset() {
        mPath.reset();
        mBoxes.clear();
        mLines.clear();
        invalidate();
    }

    private void setUpPaint() {
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(STROKE_WIDTH);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mBackgroundPaint.setColor(Color.WHITE);
        mPaint.setColor(Color.RED);
        mBoxPaint.setColor(Color.RED);
        mLinePaint.setColor(Color.RED);
        mLinePaint.setStrokeWidth(8f);
    }

    protected void setColorForPainting(String colorString){

        int color;
        switch (colorString){
            case ("red") :
                color = Color.RED;
                break;
            case ("green") :
                color = Color.GREEN;
                break;
            case ("blue") :
                color = Color.BLUE;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + colorString);
        }


        mPaint.setColor(color);
        mBoxPaint.setColor(color);
        mLinePaint.setColor(color);
    }
}
