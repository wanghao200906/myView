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
 * Created by wanghao on 2017/5/16.
 */

public class DrawRotateView extends View {
    public DrawRotateView(Context context) {
        super(context);
    }

    public DrawRotateView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawRotateView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(20);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        int mWidth = getWidth();
        int mHeight = getHeight();


        // 将坐标系原点移动到画布正中心
        canvas.translate(mWidth / 2, mHeight / 2);

        RectF rect = new RectF(0, -400, 400, 0);   // 矩形区域

        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint);

        canvas.save();
        canvas.rotate(180);                     // 旋转180度 <-- 默认旋转中心为原点
        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint);
        canvas.restore();


        canvas.save();
        canvas.rotate(180, 200, 0);               // 旋转180度 <-- 旋转中心向右偏移200个单位
        mPaint.setColor(Color.RED);            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint);
        canvas.restore();



        // 将坐标系原点移动到画布正中心
        canvas.translate(0, 600);

        canvas.drawCircle(0,0,200,mPaint);          // 绘制两个圆形
        canvas.drawCircle(0,0,50,mPaint);

        for (int i=0; i<=360; i+=10){               // 绘制圆形之间的连接线
            canvas.drawLine(0,50,0,200,mPaint);
            canvas.rotate(10);
        }

    }
}
