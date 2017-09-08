package example.com.mycustomview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.View;

/**
 * 创建日期: 15/11/17 下午10:26
 * 作者:wanghao
 * 描述:
 */
public class DrawcoordinateView extends View implements Runnable {
    Paint mPaint;
    Context context;
    float degree = 0;

    public DrawcoordinateView(Context context) {
        this(context, null);
        this.context = context;
    }

    public DrawcoordinateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawcoordinateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);


    }

    int width;
    int height;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//       获取画布的宽高
        width = canvas.getWidth();
        height = canvas.getHeight();

//  画横竖线   横竖线的起点都是 画布的左上角 都是0
        mPaint.setColor(0xff00ff00);//绿色
        canvas.drawLine(0, 0, width, 0, mPaint);
        mPaint.setColor(0xff0000ff);//蓝色
        canvas.drawLine(0, 0, 0, height, mPaint);

//  平移之后画  translate   起点平移
        canvas.save();
        canvas.translate(width / 2, height / 2);
        mPaint.setColor(0xff00ff00);//绿色
        canvas.drawLine(0, 0, width, 0, mPaint);
        mPaint.setColor(0xff0000ff);//蓝色
        canvas.drawLine(0, 0, 0, height, mPaint);
        canvas.restore();

//  转动角度 rotate   以画布的左上角为原点旋转
        canvas.save();
        canvas.translate(width / 2 + 20, height / 2 + 20);
        canvas.rotate(degree);
        mPaint.setColor(0xff00ff00);//绿色
        canvas.drawLine(0, 0, width, 0, mPaint);
        mPaint.setColor(0xff0000ff);//蓝色
        canvas.drawLine(0, 0, 0, height, mPaint);
        canvas.restore();
//  平移旋转原点
        canvas.save();
        canvas.translate(width / 2 - 100, height / 2 - 100);
        canvas.rotate(degree, 200, 0);
        mPaint.setColor(0xff00ff00);//绿色
        canvas.drawLine(0, 0, width, 0, mPaint);
        mPaint.setColor(0xff0000ff);//蓝色
        canvas.drawLine(0, 0, 0, height, mPaint);
        canvas.restore();


        canvas.drawLines(new float[]{               // 绘制一组线 每四数字(两个点的坐标)确定一条线
                100,200,200,200,
                100,300,200,300
        },mPaint);



    }


    @Override
    public void run() {


        while (true) {
            if (degree < 360) {
                degree++;

                System.out.println(degree);
                postInvalidate();

            } else {
                degree = 0;
            }
            SystemClock.sleep(10);
        }
    }
}
