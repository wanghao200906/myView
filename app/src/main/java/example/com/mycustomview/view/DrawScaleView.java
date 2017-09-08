package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wanghao on 2017/5/15.
 */

public class DrawScaleView extends View {
    public DrawScaleView(Context context) {
        super(context);
    }

    public DrawScaleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawScaleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        int width = getWidth();
        int height = getHeight();


        canvas.translate(width / 2, height / 2);
        RectF rectF = new RectF(0, -400, 400, 0);
        canvas.drawRect(rectF, mPaint);


        canvas.save();
        canvas.scale(-0.5f, 0.5f);                // 画布缩放
        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rectF, mPaint);
        canvas.restore();

        canvas.save();
        canvas.scale(0.5f, 0.5f, 200, 0);                // 画布缩放
        mPaint.setColor(Color.RED);            // 绘制蓝色矩形
        canvas.drawRect(rectF, mPaint);
        canvas.restore();

//        scale也可以重复叠加使用
        canvas.save();
        canvas.scale(0.5f, 0.5f);                // 画布缩放
        canvas.scale(0.4f, 0.4f);                // 画布缩放
        mPaint.setColor(Color.RED);            // 绘制蓝色矩形
        canvas.drawRect(rectF, mPaint);
        canvas.restore();


        canvas.save();
        canvas.translate(0, 500);
        RectF rect = new RectF(-400,-400,400,400);   // 矩形区域
        for (int i=0; i<=20; i++) {
            canvas.scale(0.9f,0.9f);
            canvas.drawRect(rect,mPaint);
        }
        canvas.save();

    }
}
