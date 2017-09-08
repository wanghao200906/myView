package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建日期: 15/12/13 下午9:02
 * 作者:wanghao
 * 描述:
 */
public class DrawPathView extends View implements Runnable {

    public DrawPathView(Context context) {
        this(context, null);
    }

    public DrawPathView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPathView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int w = canvas.getWidth();
        int h = canvas.getHeight();


        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(20);
        paint.setStyle(Paint.Style.STROKE);

// 划线  moveTo、 setLastPoint、 lineTo 和 close


        canvas.save();
        Path path1 = new Path();
        canvas.translate(w / 4, h / 8);
        path1.lineTo(200, 200);
        path1.moveTo(200, 100);//不管之前的点在哪里 都直接从新的点开始画起
        path1.lineTo(200, 0);
        canvas.drawPath(path1, paint);
        canvas.restore();


        canvas.save();
        Path path2 = new Path();
        canvas.translate(w * 3 / 4, h / 8);
        path2.lineTo(200, 200);
        path2.setLastPoint(200, 100);//更改上一个点的坐标。然后从该坐标开始画起。
        path2.lineTo(200, 0);
        canvas.drawPath(path2, paint);
        canvas.restore();


        canvas.save();
        Path path3 = new Path();
        canvas.translate(w / 4, h * 3 / 8);
        path3.lineTo(200, 200);
        path3.lineTo(200, 0);
        path3.close();//画一个闭合的路径
        canvas.drawPath(path3, paint);
        canvas.restore();

        canvas.save();
        Path path4 = new Path();
        canvas.translate(0, h * 4 / 8);
// 画图形

//        矩形
        RectF f2 = new RectF(10, 10, 400, 200);
        path4.addRect(f2, Path.Direction.CW);//ccw 逆时针，cw 顺时针

//        圆角矩形
        RectF f3 = new RectF(10, 250, 400, 450);
        path4.addRoundRect(f3, 10, 10, Path.Direction.CW);

//       画弧度 arc
        RectF f = new RectF(410, 10, 800, 200);
        path4.addArc(f, 0, 200);

        //// 椭圆
        RectF f1 = new RectF(900, 10, 1000, 200);
        path4.addOval(f1, Path.Direction.CCW);
// 画圆
        path4.addCircle(1100, 10, 50, Path.Direction.CCW);

        canvas.drawPath(path4, paint);
        canvas.restore();


        canvas.save();

        canvas.translate(w / 2, h * 6 / 8);  // 移动坐标系到屏幕中心
//        canvas.scale(1,-1);                         // <-- 注意 翻转y坐标轴

        Path path = new Path();
        Path src = new Path();

        path.addRect(-200, -200, 200, 200, Path.Direction.CW);
        src.addCircle(0, 0, 100, Path.Direction.CW);

        path.addPath(src, 0, 200);
        paint.setColor(Color.RED);
        canvas.drawPath(path, paint);
        canvas.restore();


// 画arcto


        canvas.save();

        canvas.translate(10, h * 7 / 8);  // 移动坐标系到屏幕中心
        canvas.scale(1, -1);                         // <-- 注意 翻转y坐标轴

        Path path5 = new Path();
        path5.lineTo(100, 100);

        RectF oval = new RectF(0, 0, 300, 300);

//        path5.arcTo(oval, 0, 270);
        path5.arcTo(oval, 0, 270, true);  // true 圆弧的最后一点 和上一点 不进行连接，false 这两点进行连接

        RectF f11 = new RectF(0, 0, 200, 200);
        path5.addArc(f11, 0, 270);

//        path5.close();
        canvas.drawPath(path5, paint);

        canvas.restore();

    }

    int degree = 0;

    @Override
    public void run() {

        while (true) {
            if (degree > 360) {
                degree = 0;
            } else {
                degree++;
            }
            SystemClock.sleep(50);
            postInvalidate();
        }
    }
}
