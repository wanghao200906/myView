package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建日期: 15/12/10 下午10:10
 * 作者:wanghao
 * 描述:
 */
public class DrawOvalView extends View {
    public DrawOvalView(Context context) {
        this(context, null);
    }

    public DrawOvalView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawOvalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = canvas.getWidth();
        int h = canvas.getHeight();
        paint.setColor(Color.GREEN);
//        椭圆
        RectF r = new RectF(10, 10, w, 100);
        canvas.drawOval(r, paint);

//     椭圆中控
        canvas.translate(0, 100);
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(r, paint);

//   描边椭圆
        canvas.translate(0, 150);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawOval(r, paint);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawOval(r,paint);

    }
}
