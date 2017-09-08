package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建日期: 15/12/9 下午1:02
 * 作者:wanghao
 * 描述:
 */
public class DrawRectView extends View {
    Paint paint;

    public DrawRectView(Context context) {
        this(context, null);
    }

    public DrawRectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawRectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    int width;
    int height;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        width = canvas.getWidth();
        height = canvas.getHeight();
        paint.setColor(Color.RED);

        //画一个矩形
        canvas.drawRect(0, 0, width / 2, height / 3, paint);
        paint.setColor(Color.GRAY);

        Rect r = new Rect(width / 2, 0, width, height / 3);
        canvas.drawRect(r, paint);

        paint.setColor(Color.GREEN);
        RectF f = new RectF(0, height / 3, width / 2, height / 2);
        canvas.drawRect(f, paint);

        //画一个圆角矩形
        RectF f1 = new RectF(width / 2, height / 3, width, height / 2);
        canvas.drawRoundRect(f1, 100, 100, paint);

    }


}
