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

public class DrawSkewView extends View {
    public DrawSkewView(Context context) {
        super(context);
    }

    public DrawSkewView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawSkewView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
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

        RectF rect = new RectF(0, 0, 200, 200);   // 矩形区域
        mPaint.setColor(Color.BLACK);           // 绘制黑色矩形
        canvas.drawRect(rect, mPaint);

        canvas.save();
        canvas.skew(1, 0);                       // 水平错切 <- 45度
        mPaint.setColor(Color.BLUE);            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint);

        canvas.restore();

        canvas.rotate(180); //旋转180度
        canvas.skew(1, 0);                       // 水平错切
        canvas.skew(0, 1);                       // 垂直错切
        mPaint.setColor(Color.RED);            // 绘制蓝色矩形
        canvas.drawRect(rect, mPaint);

    }
}
