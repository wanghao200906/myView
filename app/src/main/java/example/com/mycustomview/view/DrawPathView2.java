package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建日期: 15/12/13 下午9:02
 * 作者:wanghao
 * 描述:
 */
public class DrawPathView2 extends View implements Runnable {

    public DrawPathView2(Context context) {
        this(context, null);
    }

    public DrawPathView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawPathView2(Context context, AttributeSet attrs, int defStyleAttr) {
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
        // rXxx使用

        canvas.save();
        Path p = new Path();

        p.moveTo(100, 100);
        p.lineTo(100, 200);

        p.moveTo(300, 100);
        p.rLineTo(300, 200);
        canvas.drawPath(p, paint);
        canvas.restore();


        canvas.save();
        canvas.translate(w * 3 / 4, 500);
        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.GREEN);
        paint2.setStyle(Paint.Style.FILL);                   // 设置画笔模式为填充
        Path p2 = new Path();                                     // 创建Path
// 添加小正方形 (通过这两行代码来控制小正方形边的方向,从而演示不同的效果)
        RectF rf = new RectF(-200, -200, 200, 200);
        p2.addRect(rf, Path.Direction.CW);
//      p2.addRect(-200, -200, 200, 200, Path.Direction.CCW);


// 添加大正方形
        p2.addRect(-400, -400, 400, 400, Path.Direction.CCW);

        p2.setFillType(Path.FillType.WINDING);              // 设置Path填充模式为非零环绕规则
        canvas.drawPath(p2, paint2);                       // 绘制Path


        p2.computeBounds(rf, true);

        canvas.restore();

        canvas.save();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            paint.setStyle(Paint.Style.FILL);
            paint.setTextSize(40);
            int x = 80;
            int r = 100;

            canvas.translate(250, 600);


            Path path11 = new Path();
            Path path22 = new Path();
            Path path33 = new Path();
            Path path44 = new Path();

            path11.addCircle(0, 0, 200, Path.Direction.CW);
            path22.addRect(0, -200, 200, 200, Path.Direction.CW);
            path33.addCircle(0, -100, 100, Path.Direction.CW);
            path44.addCircle(0, 100, 100, Path.Direction.CCW);


            path11.op(path22, Path.Op.DIFFERENCE);
            path11.op(path33, Path.Op.UNION);
            path11.op(path44, Path.Op.DIFFERENCE);

            canvas.drawPath(path11, paint);


            Path path1 = new Path();
            Path path2 = new Path();
            Path pathOpResult = new Path();

            path1.addCircle(-x, 0, r, Path.Direction.CW);
            path2.addCircle(x, 0, r, Path.Direction.CW);


            pathOpResult.op(path1, path2, Path.Op.DIFFERENCE);

            canvas.translate(0, 300);
            canvas.drawText("DIFFERENCE", 240, 0, paint);
            canvas.drawPath(pathOpResult, paint);

            pathOpResult.op(path1, path2, Path.Op.REVERSE_DIFFERENCE);
            canvas.translate(0, 300);
            canvas.drawText("REVERSE_DIFFERENCE", 240, 0, paint);
            canvas.drawPath(pathOpResult, paint);

            pathOpResult.op(path1, path2, Path.Op.INTERSECT);
            canvas.translate(0, 300);
            canvas.drawText("INTERSECT", 240, 0, paint);
            canvas.drawPath(pathOpResult, paint);

            pathOpResult.op(path1, path2, Path.Op.UNION);
            canvas.translate(0, 300);
            canvas.drawText("UNION", 240, 0, paint);
            canvas.drawPath(pathOpResult, paint);

            pathOpResult.op(path1, path2, Path.Op.XOR);
            canvas.translate(0, 200);
            canvas.drawText("XOR", 240, 0, paint);
            canvas.drawPath(pathOpResult, paint);
            canvas.restore();
        }

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
