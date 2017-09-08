package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * 创建日期: 15/12/7 下午10:27
 * 作者:wanghao
 * 描述:
 */
public class DrawLineView extends View {


    public DrawLineView(Context context) {
        this(context, null);
    }

    public DrawLineView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawLineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        density = getContext().getResources().getDisplayMetrics().density;
    }

    float density;
    Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        int canvasWidth = canvas.getWidth();
        int halfWidth = canvasWidth / 2;
        int deltaY = canvas.getHeight() / 5;
        int halfDeltaY = deltaY / 2;
        float[] pts = {
                50, 0, halfWidth, halfDeltaY,
                halfWidth, halfDeltaY, canvasWidth - 50, 0,
                canvasWidth - 50, 0,canvasWidth,deltaY*4
        };

        //绘制一条线段
        canvas.drawLine(5, 0, canvasWidth - 50, deltaY / 2, paint);
        //绘制折线
        canvas.save();
        canvas.translate(0, deltaY);
        canvas.drawLines(pts, paint);
        canvas.restore();

        //设置线宽
        paint.setStrokeWidth(10 * density);

        //输出默认Cap
        Paint.Cap defaultCap = paint.getStrokeCap();
        Log.i("DemoLog", "默认Cap:" + defaultCap);

        //用BUTT作为Cap
        paint.setStrokeCap(Paint.Cap.BUTT);
        canvas.save();
        canvas.translate(0, deltaY * 2);
        canvas.drawLine(50, 0, halfWidth, 0, paint);
        canvas.restore();

        //用ROUND作为Cap
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.save();
        canvas.translate(0, deltaY * 2 + 20 * density);
        canvas.drawLine(50, 0, halfWidth, 0, paint);
        canvas.restore();

        //用SQUARE作为Cap
        paint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.save();
        canvas.translate(0, deltaY * 2 + 40 * density);
        canvas.drawLine(50, 0, halfWidth, 0, paint);
        canvas.restore();

        //恢复为默认的Cap
        paint.setStrokeCap(defaultCap);
    }
}
