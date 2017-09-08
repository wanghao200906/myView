package example.com.mycustomview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import example.com.mycustomview.R;

/**
 * Created by wanghao on 2017/5/25.
 * <p>
 * 思路 用 一阶贝塞尔曲线绘制 绘制 波浪。然后移动。
 */

public class DrawWaveView extends View implements View.OnClickListener {

    private Paint mPaint;
    private Paint mPaint2;
    private Path mPath;
    private Path mPath2;

    private int mWaveLength = 2000;
    private int mOffset;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mWaveCount;
    private int mCenterY;

    public DrawWaveView(Context context) {
        super(context);
    }

    public DrawWaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public DrawWaveView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mPath2 = new Path();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(getResources().getColor(R.color.skyblue));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2.setColor(getResources().getColor(R.color.skyblue2));
        mPaint2.setStyle(Paint.Style.FILL_AND_STROKE);


        setOnClickListener(this);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mScreenHeight = h;
        mScreenWidth = w;
        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);
        mCenterY = mScreenHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawWaveLeft(canvas);
        drawWaveRight(canvas);
    }

    private void drawWaveRight(Canvas canvas) {
        System.out.println(mOffset);

        mPath2.reset();
        mPath2.moveTo(mScreenWidth + mWaveLength - mOffset, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            // + (i * mWaveLength)
            // + mOffset
            mPath2.quadTo(mScreenWidth + (mWaveLength * 3 / 4) + (-i * mWaveLength) - mOffset, mCenterY + 60, mScreenWidth + (mWaveLength / 2) - (i * mWaveLength) - mOffset, mCenterY);
            mPath2.quadTo(mScreenWidth + (mWaveLength / 4) + (-i * mWaveLength) - mOffset, mCenterY - 60, mScreenWidth + (-i * mWaveLength) - mOffset, mCenterY);
        }
        mPath2.lineTo(0, mScreenHeight);
        mPath2.lineTo(mScreenWidth, mScreenHeight);
        mPath2.close();
        canvas.drawPath(mPath2, mPaint2);
    }

    private void drawWaveLeft(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(-mWaveLength + mOffset, mCenterY);
        for (int i = 0; i < mWaveCount; i++) {
            // + (i * mWaveLength)
            // + mOffset
            mPath.quadTo((-mWaveLength * 3 / 4) + (i * mWaveLength) + mOffset, mCenterY + 60, (-mWaveLength / 2) + (i * mWaveLength) + mOffset, mCenterY);
            mPath.quadTo((-mWaveLength / 4) + (i * mWaveLength) + mOffset, mCenterY - 60, i * mWaveLength + mOffset, mCenterY);
        }
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaint);

    }

    @Override
    public void onClick(View view) {
        ValueAnimator animator = ValueAnimator.ofInt(0, mWaveLength);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mOffset = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.start();
    }
}
