package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewParent;
import android.view.ViewTreeObserver;

/**
 * Created by wanghao on 2017/6/1.
 */

public class ZoomImageView

        extends android.support.v7.widget.AppCompatImageView implements ViewTreeObserver.OnGlobalLayoutListener, ScaleGestureDetector.OnScaleGestureListener, View.OnTouchListener
//        , GestureDetector.OnGestureListener
{


    private boolean mOnes;

    private float mInitscale;//缩放的当前值
    private float mMinscale;//缩放的最小范围
    private float mMaxscale;//缩放的最大范围

    private Matrix mScaleMatrix;

    /**
     * 捕获用户多点触控
     */
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

//    ---------滑动

    private int touchSlop;

    /**
     * 上一次多点触控的数量
     */
    private int mLastPointerCount;

    private float mLastX;
    private float mLastY;
    private boolean isCheckTopAndBottom;
    private boolean isCheckLeftAndRight;

    private boolean isCanDrag;
    private boolean isAutoScale;

//    ---------双击变大变小

    public ZoomImageView(Context context) {
        super(context);
        init();
    }


    public ZoomImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public ZoomImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();


    }

    private void init() {
        mScaleMatrix = new Matrix();
        setScaleType(ScaleType.MATRIX);
        touchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        scaleGestureDetector = new ScaleGestureDetector(getContext(), this);
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                System.out.println("getScaleMatrix():" + getScaleMatrix());
                System.out.println("mInitscale:" + mInitscale);
                final float x = e.getX();
                final float y = e.getY();

                if (isAutoScale == true)
                    return true;


                Log.e("DoubleTap", getScaleMatrix() + " , " + mInitscale);
                if (getScaleMatrix() < mMinscale) {
                    ZoomImageView.this.postDelayed(
                            new AutoScaleRunnable(mMinscale, x, y), 160);
                    isAutoScale = true;
                } else if (getScaleMatrix() >= mMinscale && getScaleMatrix() < mMaxscale) {
                    ZoomImageView.this.postDelayed(
                            new AutoScaleRunnable(mMaxscale, x, y), 160);
                    isAutoScale = true;
                } else {
                    ZoomImageView.this.postDelayed(
                            new AutoScaleRunnable(mInitscale, x, y), 160);
                    isAutoScale = true;
                }
//

                return true;
            }


        });
        setOnTouchListener(this);


    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        if (!mOnes) {

            int w = getWidth();
            int h = getHeight();
            float f = 1.0f;

            Drawable drawable = getDrawable();
            if (drawable == null) {
                return;
            }
            int dw = drawable.getIntrinsicWidth();
            int dh = drawable.getIntrinsicHeight();
            System.out.println("dw:" + dw);
            System.out.println("dh:" + dh);
            System.out.println("w:" + w);
            System.out.println("h:" + h);
            float scale = 0;

            if ((dw > w && dh > h) || (dw < w && dh < h)) {

                scale = Math.min(w * f / dw, h * f / dh);
            }

            if (dw > w && dh < h) {
                scale = w * f / dw;

            }
            if (dw < w && dh > h) {
                scale = h * f / dh;
            }

            mInitscale = scale;
            mMaxscale = scale * 4;
            mMinscale = scale * 2;

            int dx = getWidth() / 2 - dw / 2;
            int dy = getHeight() / 2 - dh / 2;

            System.out.println("mInitscale:" + mInitscale);
            mScaleMatrix.postTranslate(dx, dy);
            mScaleMatrix.postScale(mInitscale, mInitscale, getWidth() / 2, getHeight() / 2);
            setImageMatrix(mScaleMatrix);

            mOnes = true;
        } else {

        }


    }

    /**
     * 获取图片的缩放值
     *
     * @return
     */
    public float getScaleMatrix() {

        float values[] = new float[9];
        mScaleMatrix.getValues(values);

        return values[Matrix.MSCALE_X];

    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        float scale = getScaleMatrix();
        float scale1 = getScaleX();
        float scaleFactor = scaleGestureDetector.getScaleFactor();//判断是放大还是放小。大于1是放大

        if (getDrawable() == null) {
            return true;
        }
        System.out.println("1scaleFactor:" + scaleFactor + "-1scale:" + scale);

//
        if ((scaleFactor < 1.0f && scale > mInitscale) || (scaleFactor > 1.0f && scale < mMaxscale)) {
//            mScaleMatrix.postScale(scaleFactor,scaleFactor,getWidth()/2,getHeight()/2);
            mScaleMatrix.postScale(scaleFactor, scaleFactor, scaleGestureDetector.getFocusX(), scaleGestureDetector.getFocusY());
            checkBorderAndCenterWhenScale();
            setImageMatrix(mScaleMatrix);

        }

        return true;
    }

    /**
     * 获取图片放大缩小 的 l,t,r,b
     *
     * @return
     */
    public RectF getMatrixRect() {
        RectF rectF = new RectF();
        Matrix matrix = mScaleMatrix;
        Drawable d = getDrawable();
        if (d != null) {
            rectF.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rectF);
        }

        return rectF;
    }

    /**
     * 缩放的时候 进行边界位置的控制 防止出现白边
     */
    private void checkBorderAndCenterWhenScale() {
        RectF matrixRect = getMatrixRect();
        float deltaX = 0;
        float deltaY = 0;
        int width = getWidth();
        int height = getHeight();

        if (matrixRect.width() > width) {

            if (matrixRect.left > 0) {
                deltaX = -matrixRect.left;
            }
            if (matrixRect.right < getWidth()) {
                deltaX = getWidth() - matrixRect.right;
            }

        } else {
            deltaX = width / 2 - matrixRect.right + matrixRect.width() / 2;
        }

        if (matrixRect.height() >= height) {

            if (matrixRect.top > 0) {
                deltaY = -matrixRect.top;
            }
            if (matrixRect.bottom < height) {
                deltaY = height - matrixRect.bottom;
            }
        } else {
            deltaY = height / 2 - matrixRect.bottom + matrixRect.height() / 2;

        }


        mScaleMatrix.postTranslate(deltaX, deltaY);
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {


        if (gestureDetector.onTouchEvent(event)) {
            return true;
        }
        scaleGestureDetector.onTouchEvent(event);


        int x = 0;
        int y = 0;
//        触动点的数量
        int pointerCount = event.getPointerCount();
        for (int i = 0; i < pointerCount; i++) {
            x += event.getX();
            y += event.getY();
        }

        x /= pointerCount;
        y /= pointerCount;


        if (mLastPointerCount != pointerCount) {
            mLastX = x;
            mLastY = y;

        }
        mLastPointerCount = pointerCount;

        RectF matrixRect = getMatrixRect();
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:

                if (matrixRect.width() > getWidth() + 0.01 || matrixRect.height() > getHeight() + 0.01) {

                    if(getParent() instanceof ViewParent){
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:

                if (matrixRect.width() > getWidth() + 0.01 || matrixRect.height() > getHeight() + 0.01) {

                    if(getParent() instanceof ViewParent){
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }
                }

                float dx = x - mLastX;
                float dy = y - mLastY;
                if (!isCanDrag) {
                    isCanDrag = isMoveAction(dx, dy);
                }
                if (isCanDrag) {

                    if (getDrawable() != null) {
                        isCheckTopAndBottom = isCheckLeftAndRight = true;
                        if (getMatrixRect().width() < getWidth()) {
                            isCheckLeftAndRight = false;
                            dx = 0;
                        }
                        if (getMatrixRect().height() < getHeight()) {
                            isCheckTopAndBottom = false;
                            dy = 0;
                        }
                        System.out.println("dx:" + dx + "-dy:" + dy);
                        mScaleMatrix.postTranslate(dx, dy);
                        checkBorderAndTranslate();
                        setImageMatrix(mScaleMatrix);
                    }

                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mLastPointerCount = 0;
                break;

        }


        return true;
    }


    private void checkBorderAndTranslate() {

        float detalX = 0;
        float detalY = 0;

        int width = getWidth();
        int height = getHeight();
        RectF rectF = getMatrixRect();

        if (rectF.top > 0 && isCheckTopAndBottom) {
            detalY = -rectF.top;
        }


        if (rectF.bottom < height && isCheckTopAndBottom) {
            detalY = height - rectF.bottom;
        }


        if (rectF.left > 0 && isCheckLeftAndRight) {
            detalX = -rectF.left;
        }


        if (rectF.right < width && isCheckLeftAndRight) {
            detalX = width - rectF.right;
        }

        mScaleMatrix.postTranslate(detalX, detalY);

    }

    /**
     * 判断是否移动了
     *
     * @param dx
     * @param dy
     * @return
     */
    private boolean isMoveAction(float dx, float dy) {

        return Math.abs(((dx * dx) + (dy * dy))) > touchSlop;
    }


    private class AutoScaleRunnable implements Runnable {
        static final float BIGGER = 1.07f;
        static final float SMALLER = 0.93f;
        private float mTargetScale;
        private float tmpScale;

        /**
         * 缩放的中心
         */
        private float x;
        private float y;

        /**
         * 传入目标缩放值，根据目标值与当前值，判断应该放大还是缩小
         *
         * @param targetScale
         */
        public AutoScaleRunnable(float targetScale, float x, float y) {
            this.mTargetScale = targetScale;
            this.x = x;
            this.y = y;
            if (getScaleMatrix() < mTargetScale) {
                tmpScale = BIGGER;
            } else {
                tmpScale = SMALLER;
            }

        }

        @Override
        public void run() {
            System.out.println("zoom run：" + getScaleMatrix());
            // 进行缩放
            mScaleMatrix.postScale(tmpScale, tmpScale, x, y);
            checkBorderAndCenterWhenScale();
            setImageMatrix(mScaleMatrix);

            final float currentScale = getScaleMatrix();
            //如果值在合法范围内，继续缩放
            if (((tmpScale > 1f) && (currentScale < mTargetScale))
                    || ((tmpScale < 1f) && (mTargetScale < currentScale))) {
                ZoomImageView.this.postDelayed(this, 16);
            } else {//设置为目标的缩放比例
                final float deltaScale = mTargetScale / currentScale;
                mScaleMatrix.postScale(deltaScale, deltaScale, x, y);
                checkBorderAndCenterWhenScale();
                setImageMatrix(mScaleMatrix);
                isAutoScale = false;
            }

        }
    }


}
