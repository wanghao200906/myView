package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import example.com.mycustomview.R;

/**
 * Created by wanghao on 2017/8/15.
 */

public class DrawSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = "DrawSurfaceView";
    LoopThread thread;
    Paint paint;

    public DrawSurfaceView(Context context) {
        super(context);
        init(); //初始化,设置生命周期回调方法
        Log.d(TAG, "DrawSurfaceView");
    }

    public DrawSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(); //初始化,设置生命周期回调方法
    }

    public DrawSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(); //初始化,设置生命周期回调方法
    }


    private void init() {

        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);

        SurfaceHolder holder = getHolder();
        holder.addCallback(this); //设置Surface生命周期回调
//        setZOrderMediaOverlay(true);
//        setZOrderOnTop(true);
//        holder.setFormat(PixelFormat.TRANSPARENT);
        thread = new LoopThread(holder, getContext());
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        Log.d(TAG, "draw: ");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d(TAG, "onSizeChanged: ");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: ");
        setMeasuredDimension(300, 400);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: ");
//    当SurfaceView被显示时会调用的方法，所以你需要再这边开启绘制的线 程

        thread.isRunning = true;
        thread.start();


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//在surface的大小发生改变时激发

        Log.d(TAG, "surfaceChanged: ");


    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "surfaceDestroyed: ");
//        当SurfaceView被隐藏会销毁时调用的方法，在这里你可以关闭绘制的线程
        thread.isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 执行绘制的绘制线程
     *
     * @author Administrator
     */
    class LoopThread extends Thread {

        SurfaceHolder surfaceHolder;
        Context context;
        boolean isRunning;
        float radius = 10f;

        public LoopThread(SurfaceHolder surfaceHolder, Context context) {

            this.surfaceHolder = surfaceHolder;
            this.context = context;
            isRunning = false;

        }

        @Override
        public void run() {

            Canvas c = null;

            while (isRunning) {

                try {
                    synchronized (surfaceHolder) {
                        c = surfaceHolder.lockCanvas(null);
                        doDraw(c);
                        //通过它来控制帧数执行一次绘制后休息50ms
                        Thread.sleep(10);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    surfaceHolder.unlockCanvasAndPost(c);
                }

            }

        }

        public void doDraw(Canvas c) {

            //这个很重要，清屏操作，清楚掉上次绘制的残留图像
            c.drawColor(Color.WHITE);
//            c.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);

            paint.setColor(getResources().getColor(R.color.colorPrimary));
            c.translate(200, 200);
            c.drawCircle(0, 0, radius++, paint);

            if (radius > 100) {
                radius = 10f;
            }

        }

    }
}
