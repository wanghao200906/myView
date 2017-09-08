package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

import example.com.mycustomview.R;


/**
 * 创建日期: 15/12/17 下午10:36
 * 作者:wanghao
 * 描述:
 */
public class DrawBitmapView extends View implements Runnable {
    public DrawBitmapView(Context context) {
        this(context, null);
    }

    public DrawBitmapView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawBitmapView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        bitmap1 = Bitmap.createBitmap(400, 400, Bitmap.Config.ARGB_8888);

        sourceDrawable = getContext().getResources().getDrawable(R.mipmap.ic_launcher);

        bitmap = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.ic_launcher);
        bitmap2 = BitmapFactory.decodeResource(getContext().getResources(), R.mipmap.chuan);


    }

    Paint paint;
    Drawable sourceDrawable;
    private Bitmap bitmap, bitmap2, bitmap1;
    int widthCache, widthCache2;
    int width;
    int height;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        width = getWidth();
        height = getHeight();
//画一个绿色区域
        canvas.save();
        Canvas c = new Canvas(bitmap1);
        c.drawColor(Color.GREEN);
        canvas.drawBitmap(bitmap1, 0, 0, paint);
        canvas.restore();


//        画一个drawable
        canvas.save();

        canvas.translate(0, 400);
        sourceDrawable.setBounds(0, 0, 100, 200);
        sourceDrawable.draw(canvas);

        canvas.restore();


//        画一个bitmap
        canvas.save();
        canvas.translate(0, 800);

        //如果bitmap不存在，那么就不执行下面的绘制代码
        if (bitmap == null) {
            return;
        }
        //直接完全绘制Bitmap
        canvas.drawBitmap(bitmap, 0, 0, paint);

        canvas.restore();


        //绘制Bitmap的一部分，并对其拉伸
        //srcRect定义了要绘制Bitmap的哪一部分
        canvas.save();
        canvas.translate(0, 1000);
        Rect srcRect = new Rect(0, 0, widthCache * 2, bitmap.getHeight());
        //dstRecF定义了要将绘制的Bitmap拉伸到哪里
        RectF dstRecF = new RectF(0, 0, bitmap.getWidth() * 2, bitmap.getHeight());
        canvas.drawBitmap(bitmap, srcRect, dstRecF, paint);
        canvas.restore();

        //绘制Bitmap的一部分，并对其拉伸
        //srcRect定义了要绘制Bitmap的哪一部分
        canvas.save();
        canvas.translate(0, 1200);
        Rect srcRect1 = new Rect(0, 0, widthCache2, 800);
        //dstRecF定义了要将绘制的Bitmap拉伸到哪里
        RectF dstRecF1 = new RectF(0, 0, widthCache2, 800);
        canvas.drawBitmap(bitmap2, srcRect1, dstRecF1, paint);
        canvas.restore();

    }


    public void setBitmap(Bitmap bm) {
        releaseBitmap();
        bitmap = bm;
    }

    private void releaseBitmap() {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        bitmap = null;
    }

    public void destroy() {
        releaseBitmap();
    }


    @Override
    public void run() {

        while (true) {
            if (widthCache < bitmap.getWidth()) {
                widthCache++;

                System.out.println(widthCache);
//                postInvalidate();

            } else {
                widthCache = 0;
            }

            if (widthCache2 < bitmap2.getWidth()) {
                widthCache2++;

                System.out.println(widthCache2);

            } else {
                widthCache2 = 0;
            }

            postInvalidate();


            SystemClock.sleep(10);
        }

    }
}
